#include <iostream>
#include <vector>
#include <ctime>
#include <thread>
#include <chrono>
#include <cmath>
#include <fstream>


void addVectors(std::vector<int> &a, std::vector<int> &b, std::vector<long> &c, int start,
                int end) {

    for (int i = start; i < end; i++) {
        c[i] = sqrt(pow(a[i], 4) + pow(b[i], 4));
    }
}

void printVector(std::vector<int> &a) {
    for (int i = 0; i < a.size(); i++) {
        std::cout << a[i] << " ";
    }
    std::cout << "\n";
}

void writeInFile(std::vector<int>& a, std::vector<int>& b) {
    std::ofstream outFile("file.txt");

    outFile << a.size() << "\n" << b.size() << "\n";

    for (int i = 0; i < a.size(); i++) {
        outFile << a[i] << " ";
    }

    outFile << "\n";

    for (int i = 0; i < b.size(); i++) {
        outFile << b[i] << " ";
    }
}

void readVectors(std::string filename, std::vector<int>& a, std::vector<int>& b) {
    std::ifstream inputFile(filename);

    if (inputFile) {
        int value;
        int dimA, dimB;

        inputFile >> dimA >> dimB;

        for (int i = 0; i < dimA; i++) {
            inputFile >> value;
            a[i] = value;
        }

        for (int i = 0; i < dimB; i++) {
            inputFile >> value;
            b[i] = value;
        }
    }
}

int main() {

    int n = 10000000;
    int boundary = 100000;
    int p = 4;

    std::vector<int> a(n);
    std::vector<int> b(n);
    std::vector<long> c(n);

    readVectors(R"(C:\Fac\Sem5\PPD\Lab03\file.txt)", a, b);

//    srand(time(nullptr));
//
//    for (int i = 0; i < n; i++) {
//        a[i] = rand() % boundary;
//        b[i] = rand() % boundary;
//    }

//    writeInFile(a, b);
//    printVector(a);
//    printVector(b);

    auto startTime = std::chrono::high_resolution_clock().now();

    addVectors(a, b, c, 0, n);

    auto endTime = std::chrono::high_resolution_clock().now();

//    printVector(c);

    double diff = std::chrono::duration<double, std::milli>(endTime - startTime).count();
    std::cout << diff << "\n";

    int cat = n / p, rest = n % p;
    int start, end = 0;
    std::vector<long> d(n);

    std::vector<std::thread> threads(p);

    startTime = std::chrono::high_resolution_clock().now();
    for (int i = 0; i < p; i++) {
        start = end;
        end = start + cat + (rest > 0 ? 1 : 0);
        rest--;

        threads[i] = std::thread(addVectors, std::ref(a), std::ref(b), std::ref(d),
                                 start, end);
    }

    for (int i = 0; i < p; i++) {
        threads[i].join();
    }

    endTime = std::chrono::high_resolution_clock().now();

//    printVector(d);

    diff = std::chrono::duration<double, std::milli>(endTime - startTime).count();
    std::cout << diff << "\n";

    return 0;
}
