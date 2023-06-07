#include <iostream>
#include <fstream>
#include <vector>
#include <thread>

#define MAX 10000

using namespace std;

double** initialiseMatrix() {
    auto** matrix = new double*[MAX];
    for (int i = 0; i < MAX; i++) {
        matrix[i] = new double[MAX];
    }

    return matrix;
}

void readMatrix(double **matrix, int n, int m, const string &fileName) {
    ifstream fin(fileName);

    for (int i = 0; i < n; i++) {

        for (int j = 0; j < m; j++) {
            fin >> matrix[i][j];
        }
    }

    fin.close();
}

void writeResult(double **data, int n, int m, const string &fileName) {

    ofstream fout(fileName);
    fout << "";

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            fout << data[i][j] << " ";
        }

        fout << "\n";
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

double applyFilter(double **matrix, int n, int m, int N, int M, int x, int y) {
    double sum = 0;

    int pos_x = x - n / 2;
    int pos_y = y - m / 2;

    for (int i = pos_x; i < n + pos_x; i++) {
        for (int j = pos_y; j < m + pos_y; j++) {

            if (i < 0 && j >= 0 && j <= M - 1) {
                sum += matrix[0][j];
            } else if (i >= 0 && i <= N - 1 && j < 0) {
                sum += matrix[i][0];
            } else if (i >= N && j >= 0 && j <= M - 1) {
                sum += matrix[N - 1][j];
            } else if (i >= 0 && i <= N - 1 && j >= M) {
                sum += matrix[i][M - 1];
            } else if (i < 0 && j < 0) {
                sum += matrix[0][0];
            } else if (i >= N && j >= M) {
                sum += matrix[N - 1][M - 1];
            } else if (i < 0 && j >= M) {
                sum += matrix[0][M - 1];
            } else if (i >= N && j < 0) {
                sum += matrix[N - 1][0];
            } else {
                sum += matrix[i][j];
            }
        }
    }

    return sum / (n * m);
}

void resolveSequential(double **input, double **output,
                       int N, int M, int n, int m) {

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            output[i][j] = applyFilter(input, n, m, N, M, i, j);
        }
    }
}

void applyFilterParallelDistributed(int startI, int endI, int startJ, int endJ, int n, int m, int N,
                                    int M, double **input, double **output) {

    for (int i = startI; i <= endI && i < N; i++) {
        int j;
        if (i == startI) {
            j = startJ;
        } else {
            j = 0;
        }
        while (j < M) {
            if (j == endJ && i == endI) {
                break;
            }

            output[i][j] = applyFilter(input, n, m, N, M, i, j);
            j++;
        }
    }
}

void resolveParallelDistributed(double **input, double **output,
                                int N, int M, int n, int m, int p) {

    int start = 0, end = 0;
    int startI = 0, endI = 0;
    int startJ = 0, endJ = 0;
    int total = N * M;
    int elementsForThreads = total / p;
    int remainingElements = total % p;

    vector<thread> threads(p);

    for (int i = 0; i < p; i++) {
        start = end;
        startI = endI;
        startJ = endJ;
        if (startJ == M - 1) {
            startJ = 0;
        }
        end = start + elementsForThreads + (remainingElements > 0 ? 1 : 0);
        remainingElements -= 1;

        endI = end / M;
        endJ = end % M;
        if (endJ == 0) {
            endJ = M - 1;
        }

        threads[i] = thread(applyFilterParallelDistributed, startI, endI, startJ, endJ, n, m, N, M,
                            ref(input), ref(output));
    }

    for (int i = 0; i < p; i++) {
        threads[i].join();
    }
}

int main(int argc, char** argv) {
    auto startTime = chrono::high_resolution_clock::now();

    int N = 10;
    int M = 10;
    int n = 3;
    int m = 3;

    auto matrix = initialiseMatrix();

    readMatrix(matrix, N, M, R"(C:\Fac\Sem5\PPD\Teme\Lab01\date.txt)");

    // resolve sequential

    auto matrix_V = initialiseMatrix();

    resolveSequential(matrix, matrix_V, N, M, n, m);
    writeResult(matrix_V, N, M, R"(C:\Fac\Sem5\PPD\Teme\Lab01_C++\out_sequential.txt)");
    if (compareFiles(R"(C:\Fac\Sem5\PPD\Teme\Lab01_C++\out_sequential.txt)",
                     R"(C:\Fac\Sem5\PPD\Teme\Lab01\date.txt)")) {
        cout << "Wrong results";
        return 0;
    }


    // resolve parallel distributed

//    auto matrix_V_PD = initialiseMatrix();
//
//    int p = stoi(argv[1]);
//
//    resolveParallelDistributed(matrix, matrix_V_PD, N, M, n, m, p);
//    writeResult(matrix_V_PD, N, M, R"(C:\Fac\Sem5\PPD\Teme\Lab01_C++\out_parallel.txt)");
//
//    if (!compareFiles(R"(C:\Fac\Sem5\PPD\Teme\Lab01_C++\out_sequential.txt)",
//                     R"(C:\Fac\Sem5\PPD\Teme\Lab01_C++\out_parallel.txt)")) {
//        cout << "Wrong results";
//        return 0;
//    }

    auto endTime = chrono::high_resolution_clock::now();

    double diff = chrono::duration<double, milli>(endTime - startTime).count();

    cout << 0 << "\n";
    cout << diff;

    return 0;
}

