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

    for (int i = size - 1; i >= 0; i--) {
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
    int *a;
    int *b;
    int *c;

    string pathToNumber1 = R"(C:\Fac\Sem5\PPD\Teme\Lab03\data\number1.txt)";
    string pathToNumber2 = R"(C:\Fac\Sem5\PPD\Teme\Lab03\data\number2.txt)";
    string pathToResult = R"(C:\Fac\Sem5\PPD\Teme\Lab03\data\resultMpi1.txt)";

    MPI_Init(nullptr, nullptr);

    int p;
    MPI_Comm_size(MPI_COMM_WORLD, &p);

    int rank;
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);

    int start, end;
    MPI_Status status;

    int sizeA, sizeB, size;

    ifstream finA(pathToNumber1);
    ifstream finB(pathToNumber2);

    finA >> sizeA;
    finB >> sizeB;

    if (sizeA >= sizeB) {
        size = sizeA;
    } else {
        size = sizeB;
    }

    a = new int[size];
    b = new int[size];
    c = new int[size + 1];

    if (rank == 0) {
        auto startTime = chrono::high_resolution_clock::now();

        int cat = size / (p - 1), rest = size % (p - 1);
        start = 0;

        for (int i = 1; i < p; i++) {
            end = start + cat + (rest > 0 ? 1 : 0);
            rest--;
            for (int j = start; j < end; j++) {
                if (j < sizeA) {
                    finA >> a[j];
                } else {
                    a[j] = 0;
                }

                if (j < sizeB) {
                    finB >> b[j];
                } else {
                    b[j] = 0;
                }
            }

            MPI_Send(&start, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
            MPI_Send(&end, 1, MPI_INT, i, 0, MPI_COMM_WORLD);

            MPI_Send(a + start, end - start, MPI_INT, i, 0, MPI_COMM_WORLD);
            MPI_Send(b + start, end - start, MPI_INT, i, 0, MPI_COMM_WORLD);

            start = end;
        }

        finA.close();
        finB.close();

        for (int i = 1; i < p; i++) {
            MPI_Recv(&start, 1, MPI_INT, i, 0, MPI_COMM_WORLD, &status);
            MPI_Recv(&end, 1, MPI_INT, i, 0, MPI_COMM_WORLD, &status);

            MPI_Recv(c + start, end - start, MPI_INT, i, 0, MPI_COMM_WORLD, &status);
        }

        writeNumberInFile(c, end, pathToResult);

        auto endTime = chrono::high_resolution_clock::now();

        double diff = chrono::duration<double, milli>(endTime - startTime).count();

        cout << diff;

        string pathToSequentialResult = R"(C:\Fac\Sem5\PPD\Teme\Lab03\data\result.txt)";
        if (!compareFiles(pathToResult, pathToSequentialResult)) {
            cout << "The answer is wrong!";
        }

    } else {

        MPI_Recv(&start, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
        MPI_Recv(&end, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);

        MPI_Recv(a + start, end - start, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
        MPI_Recv(b + start, end - start, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);

        bool hasCarry = false;
        for (int i = start; i < end; i++) {
            int sum = a[i] + b[i];

            if (hasCarry) {
                sum++;
            }

            c[i] = sum % 10;

            if (sum / 10) {
                hasCarry = true;
            } else {
                hasCarry = false;
            }
        }

        if (rank == p - 1 && hasCarry) {
            // if the last process has carry
            c[end] = 1;
            end++;
        }

        if (rank != p - 1) {
            // sends carry if it's not the last process
            MPI_Send(&hasCarry, 1, MPI_C_BOOL, rank + 1, 0, MPI_COMM_WORLD);
        }

        if (rank != 1) {
            // recive carry if it's not the first process
            MPI_Recv(&hasCarry, 1, MPI_C_BOOL, rank - 1, 0, MPI_COMM_WORLD, &status);

            if (hasCarry) {
                c[start]++;
            }
        }

        MPI_Send(&start, 1, MPI_INT, 0, 0, MPI_COMM_WORLD);
        MPI_Send(&end, 1, MPI_INT, 0, 0, MPI_COMM_WORLD);

        MPI_Send(c + start, end - start, MPI_INT, 0, 0, MPI_COMM_WORLD);
    }

    MPI_Finalize();

    delete[] a;
    delete[] b;
    delete[] c;

    return 0;
}
