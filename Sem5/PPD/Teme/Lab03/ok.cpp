#include <iostream>
#include <mpi.h>

using namespace std;

int main(int argc, char *argv[]) {
    int nprocs, myrank;
    int i;
    int *a, *b;
    MPI_Status status;
    MPI_Init(&argc, &argv);
    MPI_Comm_size(MPI_COMM_WORLD, &nprocs);
    MPI_Comm_rank(MPI_COMM_WORLD, &myrank);
    a = (int *) malloc(nprocs * sizeof(int));
    b = (int *) malloc(nprocs * nprocs * sizeof(int));
    for (int i = 0; i < nprocs; i++) a[i] = nprocs * myrank + i;
/*
COD DE COMPLETAT
*/

    MPI_Gather(a, nprocs, MPI_INT, b, nprocs,MPI_INT, 0 , MPI_COMM_WORLD);
    if (myrank == 0)
        for (i = 0; i < nprocs * nprocs; i++) printf("  %d", b[i]);
    MPI_Finalize();
    return 0;
}