package org.galih.tugas_pbo;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World !");

    }
}

class User {
    User(String nim, String name, String programStudi) {
    }
}

class Jadwal {
    static int counter = 0;
    int id;
    String namaKursus;
    String tanggal;
    String jam;
    String dosen;
    boolean selesai;

    Jadwal(String namaKursus, String tanggal, String jam, String dosen) {
        this.id = ++counter;
        this.namaKursus = namaKursus;
        this.tanggal = tanggal;
        this.jam = jam;
        this.dosen = dosen;
        this.selesai = false;
    }

    LocalDateTime getDateTime() {
        try {
            return LocalDateTime.parse(this.tanggal + "T" + this.jam);
        } catch (Exception e) {
            return LocalDateTime.MIN;
        }
    }
}

