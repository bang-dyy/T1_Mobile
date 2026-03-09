// ============================================================
// Nama     : Didy Ardiyanto
// NIM      : F1D02310046
// Kelas    : C
// ============================================================

// Fungsi utama program, titik awal eksekusi
fun main() {
    // Menampilkan header program
    println("===== SISTEM PENILAIAN =====")
    println()

    // Meminta user memasukkan nama mahasiswa
    print("Masukkan Nama Mahasiswa: ")

    // readLine() membaca input dari keyboard, ?: "" sebagai fallback jika null
    val nama = readLine() ?: ""

    // Memanggil fungsi inputNilai() untuk meminta dan memvalidasi nilai UTS
    // Fungsi ini akan terus meminta input sampai nilai valid (0-100)
    val uts = inputNilai("Masukkan Nilai UTS (0-100): ")

    // Meminta dan memvalidasi nilai UAS
    val uas = inputNilai("Masukkan Nilai UAS (0-100): ")

    // Meminta dan memvalidasi nilai Tugas
    val tugas = inputNilai("Masukkan Nilai Tugas (0-100): ")

    // Rumus: UTS bobot 30% + UAS bobot 40% + Tugas bobot 30%
    // Hasil bertipe Double karena melibatkan perkalian desimal
    val nilaiAkhir = (uts * 0.3) + (uas * 0.4) + (tugas * 0.3)

    // Menggunakan 'when' sebagai pengganti if-else berantai
    // nilaiAkhir.toInt() mengubah Double ke Int untuk pencocokan range
    val grade = when (nilaiAkhir.toInt()) {
        in 85..100 -> "A" // Nilai 85 sampai 100 mendapat grade A
        in 70..84  -> "B" // Nilai 70 sampai 84 mendapat grade B
        in 60..69  -> "C" // Nilai 60 sampai 69 mendapat grade C
        in 50..59  -> "D" // Nilai 50 sampai 59 mendapat grade D
        else       -> "E" // Nilai 0 sampai 49 mendapat grade E
    }

    // Memetakan setiap grade ke keterangan deskriptif
    val keterangan = when (grade) {
        "A" -> "Sangat Baik"
        "B" -> "Baik"
        "C" -> "Cukup"
        "D" -> "Kurang"
        else -> "Sangat Kurang" // Untuk grade E
    }


    // Mahasiswa LULUS jika nilai akhir >= 60, selain itu TIDAK LULUS
    // Menggunakan ekspresi if sebagai ternary (satu baris)
    val status = if (nilaiAkhir >= 60) "LULUS" else "TIDAK LULUS"

    println()
    println("===== HASIL PENILAIAN =====")

    // Menampilkan data mahasiswa dan nilai masing-masing komponen
    println("Nama       : $nama")           // String template dengan $nama
    println("Nilai UTS  : $uts (Bobot 30%)")
    println("Nilai UAS  : $uas (Bobot 40%)")
    println("Nilai Tugas: $tugas (Bobot 30%)")
    println("----------------------------")

    // Menampilkan hasil kalkulasi akhir
    println("Nilai Akhir: $nilaiAkhir")
    println("Grade      : $grade")
    println("Keterangan : $keterangan")
    println("Status     : $status")
    println()

    // Menampilkan pesan sesuai status kelulusan
    if (status == "LULUS") {
        println("Selamat! Anda dinyatakan LULUS.")
    } else {
        println("Maaf, Anda dinyatakan TIDAK LULUS.")
    }
}

// inputNilai
// Tujuan   : Meminta input nilai dari user dan memvalidasinya
// Parameter: prompt (String) - teks yang ditampilkan ke user
// Return   : Int - nilai valid dalam range 0-100
fun inputNilai(prompt: String): Int {

    // Loop tak terbatas, akan berhenti sendiri saat input valid
    while (true) {

        // Menampilkan teks permintaan input tanpa pindah baris
        print(prompt)

        // Membaca input user, trim() menghapus spasi di awal/akhir
        val input = readLine()?.trim()

        // toIntOrNull() mengubah String ke Int, mengembalikan null jika gagal
        // Ini mencegah crash jika user memasukkan huruf atau karakter non-angka
        val nilai = input?.toIntOrNull()

        // Memeriksa apakah nilai tidak null DAN berada dalam range 0-100
        if (nilai != null && nilai in 0..100) {
            return nilai // Input valid, kembalikan nilai ke pemanggil
        } else {
            // Input tidak valid, tampilkan pesan error dan ulangi loop
            println("Input tidak valid! Nilai harus berupa angka dalam range 0-100. Coba lagi.")
        }
    }
}