#include <mpi.h>
#include <iostream>
#include <vector>

using namespace std;

bool pred(vector<int> const& v) {

    for (int i = 0; i < v.size() - 1; i++) {
        if (v[i] > v[i + 1]) {
            return false;
        }
    }
    return true;
}

vector<int> permute(vector<int> a, int l, int r, vector<vector<int>> permutari)
{
    // Base case
    if (l == r)
        permutari.push_back(a);
    else
    {
        // Permutations made
        for (int i = l; i <= r; i++)
        {

            // Swapping done
            swap(a[l], a[i]);

            // Recursion called
            permute(a, l+1, r, permutari);

            //backtrack
            swap(a[l], a[i]);
        }
    }
}

int main() {

    MPI_Init(nullptr, nullptr);

    int p;
    MPI_Comm_size(MPI_COMM_WORLD, &p);

    int rank;
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);

    MPI_Status status;

    vector<int> vec;
    vec.push_back(1);
    vec.push_back(2);
    vec.push_back(3);
    vec.push_back(4);

    vector<vector<int>> permutari;
    int start = 0, end = 0, size = 0;

    if (rank == 0) {

        permute(vec, 0, vec.size(), permutari);

        size = permutari.size();

        int cat = size / (p - 1);
        int rest = size % (p - 1);
        start = 0;

        for (int i = 1; i < p; i++) {
            end = start + cat + (rest > 0 ? 1 : 0);
            rest--;

            MPI_Send(&start, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
            MPI_Send(&end, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
            MPI_Send(&size, 1, MPI_INT, i, 0,MPI_COMM_WORLD);
            MPI_Send(permutari.data(), size, MPI_INT, i, 0, MPI_COMM_WORLD);

            start = end;
        }

        for (int i = 1; i < p; i++) {
            int ok = 0;
            int r = 0;

            MPI_Recv(&ok, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
            MPI_Recv(&permutari, ok, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
            MPI_Recv(&r, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);

            for (auto perm : permutari) {
                cout << "{";
                for (auto el : perm) {
                    cout << el;
                }
                cout << "} ";
            }
            cout << "\n" << r << "\n\n";
        }
    } else {

        MPI_Recv(&start, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
        MPI_Recv(&end, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
        MPI_Recv(&size, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);

        MPI_Recv(permutari.data(), size, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);

        vector<vector<int>> res;
        for (int i = start; i < end; i++) {
            if (!pred(permutari[i])) {
                res.push_back(permutari[i]);
            }
        }

        int ok = res.size();
        MPI_Send(&ok, 1, MPI_INT, 0, 0, MPI_COMM_WORLD);
        MPI_Send(res.data(), res.size(), MPI_INT, 0, 0, MPI_COMM_WORLD);
        MPI_Send(&rank, 1, MPI_INT, 0, 0, MPI_COMM_WORLD);
    }

    MPI_Finalize();
}
