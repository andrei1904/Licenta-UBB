#include <iostream>
#include <fstream>
#include <vector>
#include <thread>
#include "MyBarrier.h"

using namespace std;

MyBarrier* barrier;

double **initialiseMatrix(int sizeN, int sizeM) {
    auto **matrix = new double *[sizeN];
    for (int i = 0; i < sizeN; i++) {
        matrix[i] = new double[sizeM];
    }

    return matrix;
}

void readData(const string &fileName, int &N, int &M, int &n, int &m) {
    ifstream fin(fileName);

    fin >> N;
    fin >> M;
    fin >> n;
    fin >> m;
}

void readMatrix(double **data, int n, int m, const string &fileName) {
    ifstream fin(fileName);

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            double x = 0;
            fin >> x;
            data[i][j] = x;
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

double applyFilter(double **matrix, double **filter, int n, int m, int N, int M, int x, int y) {
    double sum = 0;

    int pos_x = x - n / 2;
    int pos_y = y - m / 2;

    for (int i = pos_x, posIF = 0; i < n + pos_x; i++, posIF++) {
        for (int j = pos_y, posJF = 0; j < m + pos_y; j++, posJF++) {

            if (i < 0 && j >= 0 && j <= M - 1) {
                sum += matrix[0][j] * filter[posIF][posJF];
            } else if (i >= 0 && i <= N - 1 && j < 0) {
                sum += matrix[i][0] * filter[posIF][posJF];
            } else if (i >= N && j >= 0 && j <= M - 1) {
                sum += matrix[N - 1][j] * filter[posIF][posJF];
            } else if (i >= 0 && i <= N - 1 && j >= M) {
                sum += matrix[i][M - 1] * filter[posIF][posJF];
            } else if (i < 0 && j < 0) {
                sum += matrix[0][0] * filter[posIF][posJF];
            } else if (i >= N && j >= M) {
                sum += matrix[N - 1][M - 1] * filter[posIF][posJF];
            } else if (i < 0 && j >= M) {
                sum += matrix[0][M - 1] * filter[posIF][posJF];
            } else if (i >= N && j < 0) {
                sum += matrix[N - 1][0] * filter[posIF][posJF];
            } else {
                sum += matrix[i][j] * filter[posIF][posJF];
            }
        }
    }

    return sum;
}

void applyFilterParallel(double **matrix, double **filter, int N, int M, int n, int m, int start, int end) {
    double **utilMatrix = initialiseMatrix(end - start + n, M);

    int line = 0;
    if (start != 0 && start - n / 2 >= 0) {
        for (int i = start - n / 2; i < start; i++) {
            for (int j = 0; j < M; j++) {
                utilMatrix[line][j] = matrix[i][j];
            }
            line++;
        }
    }

    int index = line;
    for (int i = start; i < end; i++) {
        for (int j = 0; j < M; j++) {
            utilMatrix[index][j] = matrix[i][j];
        }
        index++;
    }

    if (end != N && end + n / 2 <= N) {
        for (int i = end; i < end + n / 2; i++) {
            for (int j = 0; j < M; j++) {
                utilMatrix[index][j] = matrix[i][j];
            }
            index++;
        }
    }

    barrier->wait();

    for (int i = start; i < end; i++) {
        for (int j = 0; j < M; j++) {
            double result = applyFilter(utilMatrix, filter, n, m, N, M, i - start + line, j);

            matrix[i][j] = result;
        }
    }
}

void resolveParallel(double **matrix, double **filter, int N, int M, int n, int m, int p) {

    int start = 0, end;
    int noLines = N / p, remainingLines = N % p;

    vector<thread> threads(p);

    for (int i = 0; i < p; i++) {
        end = start + noLines + (remainingLines > 0 ? 1 : 0);
        remainingLines -= 1;

        threads[i] = thread(applyFilterParallel, ref(matrix), ref(filter), N, M, n, m, start, end);

        start = end;
    }

    for (int i = 0; i < p; i++) {
        threads[i].join();
    }
}

int main(int argc, char** argv) {

    int N, M, n, m, p;
    double **matrix;
    double **filter;

    p = stoi(argv[1]);
    readData(R"(C:\Fac\Sem5\PPD\Teme\Lab02\C++\in_data.txt)", N, M, n, m);

    matrix = initialiseMatrix(N, M);
    filter = initialiseMatrix(n, m);

    barrier = new MyBarrier(p);

    readMatrix(matrix, N, M, R"(C:\Fac\Sem5\PPD\Teme\Lab01\date.txt)");
    readMatrix(filter, n, m, R"(C:\Fac\Sem5\PPD\Teme\Lab02\Java\filter.txt)");

    auto startTime = chrono::high_resolution_clock::now();
    resolveParallel(matrix, filter, N, M, n, m, p);
    auto endTime = chrono::high_resolution_clock::now();

    writeResult(matrix, N, M, R"(C:\Fac\Sem5\PPD\Teme\Lab02\C++\result.txt)");

    double diff = chrono::duration<double, milli>(endTime - startTime).count();
    cout << 0 << "\n";
    cout << diff;

    return 0;
}
