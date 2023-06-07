#include <iostream>
#include <fstream>
#include <mpi.h>
#include <chrono>

using namespace std;

void printArray(int arr[], int size) {
    for (int i = 0; i < size; i++) {
        cout << arr[i] << " ";
    }
    cout << "\n";
}

void writeNumberInFile(int *number, int size, const string &fileName) {
    ofstream fout(fileName);

    int start = size - 1;
    int x = size - 1;

    while (number[x] == 0) {
        start--;
        x--;
    }

    for (int i = start; i >= 0; i--) {
        fout << number[i];
    }

    fout.close();
}

bool compareFiles(const string &fileName1, const string &fileName2) {
    ifstream f1(fileName1, ifstream::binary | ifstream::ate);
    ifstream f2(fileName2, ifstream::binary | ifstream::ate);

    if (f1.fail() || f2.fail()) {
        return false;
    }

    if (f1.tellg() != f2.tellg()) {
        return false;
    }

    f1.seekg(0, ifstream::beg);
    f2.seekg(0, ifstream::beg);

    return equal(istreambuf_iterator<char>(f1.rdbuf()),
                 istreambuf_iterator<char>(),
                 istreambuf_iterator<char>(f2.rdbuf()));
}

int main() {
    auto startTime = chrono::high_resolution_clock::now();
    auto endTime = chrono::high_resolution_clock::now();

    int *a;
    int *b;
    int *c;

    string pathToNumber1 = R"(C:\Fac\Sem5\PPD\Teme\Lab03\data\number1.txt)";
    string pathToNumber2 = R"(C:\Fac\Sem5\PPD\Teme\Lab03\data\number2.txt)";
    string pathToResult = R"(C:\Fac\Sem5\PPD\Teme\Lab03\data\resultMpi2.txt)";

    MPI_Init(nullptr, nullptr);

    int p;
    MPI_Comm_size(MPI_COMM_WORLD, &p);

    int rank;
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);

    MPI_Status status;

    int sizeA, sizeB, size;

    int *aux_a, *aux_b, *aux_c;

    ifstream finA(pathToNumber1);
    ifstream finB(pathToNumber2);

    finA >> sizeA;
    finB >> sizeB;

    if (sizeA >= sizeB) {
        size = sizeA;
    } else {
        size = sizeB;
    }

    int n = size / p;

    int rest = size % p;
    int difference = p - rest;

    a = new int[size + difference + 1];
    b = new int[size + difference + 1];
    c = new int[size + difference + 2];

    if (rest) {
        for (int i = size; i < size + difference; i++) {
            a[i] = 0;
            b[i] = 0;
        }
        size = size + difference;
        n = size / p;
    }

    aux_a = new int[n + 1];
    aux_b = new int[n + 1];
    aux_c = new int[n + 1];

    if (rank == 0) {
        startTime = chrono::high_resolution_clock::now();

        if (sizeA >= sizeB) {

            for (int i = sizeB; i < sizeA; i++) {
                b[i] = 0;
            }

        } else {

            for (int i = sizeA; i < sizeB; i++) {
                a[i] = 0;
            }
        }

        for (int i = 0; i < size; i++) {
            finA >> a[i];
            finB >> b[i];
        }
    }

    MPI_Scatter(a, n, MPI_INT, aux_a, n, MPI_INT, 0, MPI_COMM_WORLD);
    MPI_Scatter(b, n, MPI_INT, aux_b, n, MPI_INT, 0, MPI_COMM_WORLD);

    bool hasCarry = false;
    for (int i = 0; i < n; i++) {
        int sum = aux_a[i] + aux_b[i];

        if (hasCarry) {
            sum++;
        }

        aux_c[i] = sum % 10;

        if (sum / 10) {
            hasCarry = true;
        } else {
            hasCarry = false;
        }
    }

    if (rank != p - 1) {
        // sends carry if it's not the last process
        MPI_Send(&hasCarry, 1, MPI_C_BOOL, rank + 1, 0, MPI_COMM_WORLD);
    }

    if (rank != 0) {
        // recive carry if it's not the first process
        MPI_Recv(&hasCarry, 1, MPI_C_BOOL, rank - 1, 0, MPI_COMM_WORLD, &status);

        if (hasCarry) {
            aux_c[0]++;
        }
    }

    MPI_Gather(aux_c, n, MPI_INT, c, n, MPI_INT, 0, MPI_COMM_WORLD);

    if (rank == 0) {
        if (a[size - 1] + b[size - 1] > 9) {
            c[size++] = 1;
        }

        writeNumberInFile(c, size, pathToResult);

        endTime = chrono::high_resolution_clock::now();
        double diff = chrono::duration<double, milli>(endTime - startTime).count();

        cout << diff;

        string pathToSequentialResult = R"(C:\Fac\Sem5\PPD\Teme\Lab03\data\result.txt)";
        if (!compareFiles(pathToResult, pathToSequentialResult)) {
            cout << "The answer is wrong!";
        }
    }

    MPI_Finalize();

    delete[] a;
    delete[] b;
    delete[] c;
    delete[] aux_a;
    delete[] aux_b;
    delete[] aux_c;

    return 0;
}
