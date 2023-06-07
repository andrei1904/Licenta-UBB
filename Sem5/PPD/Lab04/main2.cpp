#include <ctime>
#include "mpi.h"
#include "iostream"

using namespace std;

void printArray(int arr[], int size) {
    for (int i = 0; i < size; i++) {
        cout << arr[i] << " ";
    }
    cout << "\n";
}

int main(int argc, char **argv) {

    MPI_Init(nullptr, nullptr);

    int p;
    MPI_Comm_size(MPI_COMM_WORLD, &p);

    int rank;
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);

    const int n = 12;
    int a[n], b[n], c[n];
    int *aux_a, *aux_b, *aux_c;

    aux_a = new int[n / p];
    aux_b = new int[n / p];
    aux_c = new int[n / p];

    if (rank == 0) {
        srand(time(nullptr));

        for (int i = 0; i < n; i++) {
            a[i] = rand() % 10;
            b[i] = rand() % 10;
        }

        printArray(a, n);
        printArray(b, n);

    }

    MPI_Scatter(a, n / p, MPI_INT, aux_a, n / p, MPI_INT, 0, MPI_COMM_WORLD);
    MPI_Scatter(b, n / p, MPI_INT, aux_b, n / p, MPI_INT, 0, MPI_COMM_WORLD);

    for (int i = 0; i < n / p; i++) {
        aux_c[i] = aux_a[i] + aux_b[i];
    }

    MPI_Gather(aux_c, n / p, MPI_INT, c, n / p, MPI_INT, 0, MPI_COMM_WORLD);

    if (rank == 0) {
        printArray(c, n);
    }

    MPI_Finalize();

    delete aux_a;
    delete aux_b;
    delete aux_c;

    return 0;
}