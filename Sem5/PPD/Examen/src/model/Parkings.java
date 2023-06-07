package model;

public class Parkings {

    private final int parking1;
    private final int parking2;
    private final int parking3;

    private int parking1OcupiedSpaces;
    private int parking2OcupiedSpaces;
    private int parking3OcupiedSpaces;

    public Parkings(int parking1, int parking2, int parking3) {
        this.parking1 = parking1;
        this.parking2 = parking2;
        this.parking3 = parking3;

        this.parking1OcupiedSpaces = 0;
        this.parking2OcupiedSpaces = 0;
        this.parking3OcupiedSpaces = 0;
    }

    public synchronized int getTotalSpacesById(int id) {
        if (id == 1) {
            return parking1;
        }

        if (id == 2) {
            return parking2;
        }

        if (id == 3) {
            return parking3;
        }

        return 0;
    }


    public synchronized int reserveParkingIfAvailable(int id) {
        if (id == 1) {
            if (this.parking1OcupiedSpaces < this.parking1) {
                this.parking1OcupiedSpaces += 1;

                return this.parking1 - parking1OcupiedSpaces;
            } else {
                return -1;
            }
        }

        if (id == 2) {
            if (this.parking2OcupiedSpaces < this.parking2) {
                this.parking2OcupiedSpaces += 1;

                return this.parking2 - parking2OcupiedSpaces;
            } else {
                return -1;
            }
        }

        if (id == 3) {
            if (this.parking3OcupiedSpaces < this.parking3) {
                this.parking3OcupiedSpaces += 1;

                return this.parking3 - parking3OcupiedSpaces;
            } else {
                return -1;
            }
        }

        return -1;
    }
}
