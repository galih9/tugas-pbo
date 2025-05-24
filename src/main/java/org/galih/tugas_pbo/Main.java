package org.galih.tugas_pbo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Map<String, User> users = new HashMap<>();
    static Map<String, List<Jadwal>> jadwalMap = new HashMap<>();
    static String currentUser = null;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nSelamat datang di Aplikasi Jadwal Kursus Tambahan");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.print("Pilih opsi: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" ->
                    login();
                case "2" ->
                    register();
                default ->
                    System.out.println("Opsi tidak valid.");
            }
        }
    }

    static void register() {
        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();
        System.out.print("Masukkan Nama Lengkap: ");
        String name = scanner.nextLine();
        System.out.print("Masukkan Program Studi: ");
        String prodi = scanner.nextLine();

        users.put(nim, new User(nim, name, prodi));
        jadwalMap.put(nim, new ArrayList<>());
        System.out.println("Registrasi berhasil!");
    }

    static void login() {
        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();

        if (users.containsKey(nim)) {
            currentUser = nim;
            menuUtama();
        } else {
            System.out.println("Login gagal. NIM tidak ditemukan.");
        }
    }

    static void menuUtama() {
        while (true) {
            System.out.println("\n===== Menu Utama =====\n");
            tampilkanInformasiKursus();
            tampilkanJadwal();
            System.out.println("\n1. Tandai Selesai");
            System.out.println("2. Buat Jadwal Baru");
            System.out.println("3. Hapus Kursus");
            System.out.println("4. Update Kursus");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: ");
            String pilihan = scanner.nextLine();

            // di versi java terbaru, switch case direkomendasikan
            // diganti dengan rule switch case
            switch (pilihan) {
                case "1" ->
                    tandaiSelesai();
                case "2" ->
                    buatJadwalBaru();
                case "3" ->
                    hapusKursus();
                case "4" ->
                    updateKursus();
                case "5" -> {
                    currentUser = null;
                    return;
                }
                default ->
                    System.out.println("Opsi tidak valid.");
            }
        }
    }

    static void tampilkanInformasiKursus() {
        List<Jadwal> jadwal = jadwalMap.get(currentUser);
        LocalDateTime now = LocalDateTime.now();
        boolean adaMendatang = false, adaTerlewati = false;
        int totalHariIni = 0;
        int totalTerlewati = 0;

        for (Jadwal j : jadwal) {
            LocalDateTime jadwalWaktu = j.getDateTime();
            if (!j.selesai) {
                if (jadwalWaktu.toLocalDate().equals(now.toLocalDate())) {
                    totalHariIni++;
                }
                if (jadwalWaktu.isAfter(now)) {
                    adaMendatang = true;
                }
                if (jadwalWaktu.isBefore(now)) {
                    adaTerlewati = true;
                    totalTerlewati++;
                }
            }
        }

        if (adaMendatang) {
            System.out.println("\nAda kursus yang akan datang.");
        }
        if (adaTerlewati) {
            System.out.println("Ada kursus yang terlewati.");
        }
        System.out.println("Total kursus hari ini: " + totalHariIni);
        System.out.println("Total kursus yang terlewati: " + totalTerlewati);
    }

    static void tampilkanJadwal() {
        List<Jadwal> jadwal = jadwalMap.get(currentUser);
        if (jadwal.isEmpty()) {
            System.out.println("Tidak ada data jadwal. \n");
            return;
        }
        System.out.println("\nJadwal Kursus Anda:");
        for (Jadwal j : jadwal) {
            System.out.printf("ID: %d | Kursus: %s | Tanggal: %s | Jam: %s | Dosen: %s | Selesai: %s\n",
                    j.id, j.namaKursus, j.tanggal, j.jam, j.dosen, j.selesai ? "Ya" : "Belum");
        }
    }

    static void tandaiSelesai() {
        System.out.print("Masukkan ID jadwal yang ingin ditandai selesai (pisahkan dengan koma): ");
        String[] ids = scanner.nextLine().split(",");
        for (String idStr : ids) {
            try {
                int id = Integer.parseInt(idStr.trim());
                for (Jadwal j : jadwalMap.get(currentUser)) {
                    if (j.id == id) {
                        j.selesai = true;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("ID tidak valid: " + idStr);
            }
        }
    }

    static void buatJadwalBaru() {
        System.out.print("Masukkan Nama Kursus: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Tanggal (YYYY-MM-DD): ");
        String tanggal = scanner.nextLine();
        System.out.print("Masukkan Jam (HH:MM): ");
        String jam = scanner.nextLine();
        System.out.print("Masukkan Nama Dosen: ");
        String dosen = scanner.nextLine();

        jadwalMap.get(currentUser).add(new Jadwal(nama, tanggal, jam, dosen));
        System.out.println("Jadwal berhasil ditambahkan.");
    }

    static void hapusKursus() {
        System.out.print("Masukkan ID jadwal yang ingin dihapus (pisahkan dengan koma): ");
        String[] ids = scanner.nextLine().split(",");
        List<Jadwal> userJadwal = jadwalMap.get(currentUser);
        userJadwal.removeIf(j -> {
            for (String idStr : ids) {
                try {
                    if (j.id == Integer.parseInt(idStr.trim())) {
                        return true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ID tidak valid: " + idStr);
                }
            }
            return false;
        });
    }

    static void updateKursus() {
        System.out.print("Masukkan ID jadwal yang ingin diupdate: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (Jadwal j : jadwalMap.get(currentUser)) {
            if (j.id == id) {
                System.out.print("Nama Kursus Baru: ");
                j.namaKursus = scanner.nextLine();
                System.out.print("Tanggal Baru (YYYY-MM-DD): ");
                j.tanggal = scanner.nextLine();
                System.out.print("Jam Baru (HH:MM): ");
                j.jam = scanner.nextLine();
                System.out.print("Nama Dosen Baru: ");
                j.dosen = scanner.nextLine();
                System.out.println("Jadwal berhasil diperbarui.");
                return;
            }
        }
        System.out.println("ID tidak ditemukan.");
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
