#include <iostream>
#include <fstream>
#include <deque>
#include <chrono>

using namespace std;

void generateNumbers(int n1, int n2, const string& fileName1, const string& fileName2) {
    srand(time(nullptr));

    ofstream fout1(fileName1);
    ofstream fout2(fileName2);

    fout1 << n1 << " ";
    for (int i = 0; i < n1 - 1; i++) {
        fout1 << rand() % 10 << " ";
    }

    int x = rand() % 10;
    while (x == 0) {
        x = rand() % 10;
    }
    fout1 << x;

    fout2 << n2 << " ";
    for (int i = 0; i < n2; i++) {
        fout2 << rand() % 10 << " ";
    }

    x = rand() % 10;
    while (x == 0) {
        x = rand() % 10;
    }
    fout2 << x;
}

int *readArrayFromFile(ifstream &fin, int size) {
    int *arr;
    arr = new int[size + 1];

    int i = 0;
    int digit = 0;
    while (fin >> digit) {
        arr[i++] = digit;
    }

    while (i < size) {
        arr[i] = 0;
        i++;
    }

    return arr;
}

void writeNumberInFile(int *number, int size, const string &fileName) {
    ofstream fout(fileName);

    for (int i = size - 1; i >= 0; i--) {
        fout << number[i];
    }

    fout.close();
}

int main() {
    auto startTime = chrono::high_resolution_clock::now();

    int *a;
    int *b;
    int *c;

    string pathToNumber1 = R"(C:\Fac\Sem5\PPD\Teme\Lab03\data\number1.txt)";
    string pathToNumber2 = R"(C:\Fac\Sem5\PPD\Teme\Lab03\data\number2.txt)";
    string pathToResult = R"(C:\Fac\Sem5\PPD\Teme\Lab03\data\result.txt)";

//    generateNumbers(100, 100000, pathToNumber1, pathToNumber2);

    ifstream finA(pathToNumber1);
    ifstream finB(pathToNumber2);
    int sizeA, sizeB, size;
    finA >> sizeA;
    finB >> sizeB;


    if (sizeA >= sizeB) {
        size = sizeA;
    } else {
        size = sizeB;
    }

    a = readArrayFromFile(finA, size);
    finA.close();

    b = readArrayFromFile(finB, size);
    finB.close();

//    writeNumberInFile(a, sizeA, R"(C:\Fac\Sem5\PPD\Teme\Lab03\data\number1Normal.txt)");
//    writeNumberInFile(b, sizeB, R"(C:\Fac\Sem5\PPD\Teme\Lab03\data\number2Normal.txt)");

    c = new int[size + 1];

    bool hasCarry = false;

    for (int i = 0; i < size; i++) {
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

    if (hasCarry) {
        c[size++] = 1;
    }

    writeNumberInFile(c, size, pathToResult);

    delete[] a;
    delete[] b;
    delete[] c;

    auto endTime = chrono::high_resolution_clock::now();

    double diff = chrono::duration<double, milli>(endTime - startTime).count();

    cout << 0 << "\n";
    cout << diff;

    return 0;
}
