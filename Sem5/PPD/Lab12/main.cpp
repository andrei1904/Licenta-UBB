#include <iostream>
#include "omp.h"
#include <vector>

using namespace std;

void printArray(vector<int> v) {
    for (int i = 0; i < v.size(); i++) {
        cout << v[i] << " ";
    }
    cout << "\n";
}

int main() {

    int i, j, k, t;
    int N = 3;

    int A[3][3] = {{1, 2,  3},
                   {5, 6,  7},
                   {9, 10, 11}};
    int B[3][3] = {{1, 2,  3},
                   {5, 6,  7},
                   {9, 10, 11}};
    int C[3][3];

    omp_set_num_threads(2);

#pragma omp parallel shared(A, B, C) private(i, j, k, t) firstprivate(N){
#pragma omp for schedule(dynamic) {

}

}
return 0;
}
