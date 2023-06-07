#include <stdio.h>

int main() {
	int n, suma = 0, ok = 1;

	printf("Introduceti numarul de numere: ");
	ok = scanf_s("%d", &n);
	fflush(stdin);
	while (!ok && n < 0) {
		printf("Introduceti numarul de numere");
		ok = scanf_s("%d", &n);
	}

	for (int i = 0; i < n; i++) {
		int x;
		printf("Introduceti un numar: ");
		ok = scanf_s("%d", &x);
		if (!ok) {
			printf("Introduceti un numar: ");
			ok = scanf_s("%d", &x);
		}
		suma += x;
	}

	printf("Suma este: %d", suma);
	return 0;
}