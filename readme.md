## 📚 Kursus Tracker App

Aplikasi terminal berbasis Java yang memungkinkan mahasiswa untuk mencatat, mengelola, dan melacak jadwal kursus tambahan mereka. Aplikasi ini dirancang dengan prinsip **pemrograman berorientasi objek (OOP)** dan mencakup berbagai fitur dasar Java.

---

### 🧽 Alur Aplikasi

1. **Halaman Awal**

   * Menampilkan sambutan awal.
   * Pilihan:

     * `1. Login`
     * `2. Register`

2. **Register**

   * Input NIM, Nama Lengkap, dan Program Studi.
   * Setelah sukses, kembali ke halaman awal.

3. **Login**

   * Input NIM.
   * Jika valid, masuk ke **Menu Utama**.

4. **Menu Utama**

   * Menampilkan:

     * Informasi kursus yang akan datang.
     * Kursus yang terlewat.
     * Total kursus hari ini.
     * Total kursus yang terlewati.
     * Tabel daftar jadwal pengguna saat ini.
   * Pilihan Menu:

     * `1. Tandai Selesai`
     * `2. Buat Jadwal Baru`
     * `3. Hapus Kursus`
     * `4. Update Kursus`
     * `5. Keluar` (kembali ke halaman awal)

---

### 📋 Fitur Aplikasi

* Menambahkan, menghapus, dan memperbarui jadwal kursus.
* Menandai kursus selesai.
* Melihat kursus yang akan datang dan yang telah terlewati.
* Menampilkan jumlah kursus hari ini dan jumlah kursus terlewati.
* Multi-pengguna berbasis NIM.

---

### 🛠️ Instalasi & Menjalankan Aplikasi

#### Prasyarat

* Java Development Kit (JDK) versi 8 atau lebih baru.

#### Cara Kompilasi & Menjalankan

1. **Kloning atau salin file**:
   Simpan file Java ini sebagai `Main.java`.

2. **Kompilasi**:
   Buka terminal/command prompt di direktori file, lalu jalankan:

   ```bash
    javac org/galih/tugas_pbo/Main.java
   ```

3. **Jalankan Program**:
   Setelah berhasil dikompilasi, jalankan aplikasi dengan:

   ```bash
    java -cp . org/galih/tugas_pbo/Main.java 
   ```

---

### 🧑‍💻 Struktur Kode

* `User`: Menyimpan data pengguna (NIM, nama, prodi).
* `Jadwal`: Menyimpan informasi jadwal kursus.
* `KursusTracker`: Kelas utama yang menjalankan aplikasi.

---

### ✨ Catatan Teknis

* Data pengguna dan jadwal disimpan dalam memori saat runtime (belum persist ke file atau database).
* Menggunakan fitur OOP seperti kelas, objek, konstruktor, dan pengelompokan data.
* Mendemonstrasikan berbagai fitur dasar Java seperti:

  * Tipe data primitif dan variabel
  * Array (List)
  * Operator (aritmatika, logika, bitwise)
  * Struktur kendali (`if`, `switch`)
  * I/O stream dasar (`Scanner`)
  * Pemrosesan tanggal dan waktu (`LocalDateTime`)
* Terkait switch case, saya menggunakan rule switch case. Salah satu fitur terbaru java dimana menyederhanakan switch case agar mudah dibaca dan di maintain.