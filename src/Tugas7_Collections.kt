data class NilaiMahasiswa(
    val nim: String,         // Nomor Induk Mahasiswa
    val nama: String,        // Nama lengkap mahasiswa
    val mataKuliah: String,  // Nama mata kuliah
    val nilai: Int           // Nilai angka (0-100)
)

// getGrade
// Mengkonversi nilai Int menjadi huruf grade (A-E) Digunakan berulang kali di seluruh program
fun getGrade(nilai: Int): String {
    return when (nilai) {
        in 85..100 -> "A" // Sangat Baik
        in 70..84  -> "B" // Baik
        in 60..69  -> "C" // Cukup
        in 50..59  -> "D" // Kurang
        else       -> "E" // Sangat Kurang (0-49)
    }
}

//getKeterangan Mengkonversi grade huruf menjadi keterangan deskriptif
fun getKeterangan(grade: String): String {
    return when (grade) {
        "A" -> "Sangat Baik"
        "B" -> "Baik"
        "C" -> "Cukup"
        "D" -> "Kurang"
        else -> "Sangat Kurang"
    }
}

fun main() {
    // INISIALISASI DATA listOf() membuat immutable list (tidak bisa diubah) Berisi 10 objek NilaiMahasiswa dengan named parameters
    val dataMahasiswa = listOf(
        NilaiMahasiswa("2024001", "Budi Santoso",   "Pemrograman", 85),
        NilaiMahasiswa("2024002", "Ani Wijaya",     "Pemrograman", 92),
        NilaiMahasiswa("2024003", "Citra Dewi",     "Pemrograman", 68),
        NilaiMahasiswa("2024004", "Dani Pratama",   "Pemrograman", 45),
        NilaiMahasiswa("2024005", "Eka Putri",      "Pemrograman", 78),
        NilaiMahasiswa("2024006", "Fajar Nugroho",  "Pemrograman", 55),
        NilaiMahasiswa("2024007", "Gita Rahayu",    "Pemrograman", 90),
        NilaiMahasiswa("2024008", "Hendra Kusuma",  "Pemrograman", 72),
        NilaiMahasiswa("2024009", "Indah Permata",  "Pemrograman", 63),
        NilaiMahasiswa("2024010", "Joko Widodo",    "Pemrograman", 38)
    )

    //TAMPILKAN SEMUA DATA MAHASISWA
    // forEachIndexed memberikan index (mulai 0) + elemen sekaligus
    // padEnd() memformat string agar rata kiri dengan lebar tetap

    println("===== DATA NILAI MAHASISWA =====")
    println()

    // Header tabel dengan format kolom tetap
    println("No  NIM      Nama              MataKuliah    Nilai  Grade")
    println("--  -------  ----------------  ------------  -----  -----")

    // Iterasi semua data, index+1 agar nomor mulai dari 1
    dataMahasiswa.forEachIndexed { index, mhs ->
        val no        = (index + 1).toString().padEnd(3)   // Nomor urut
        val nim       = mhs.nim.padEnd(9)                  // NIM 9 karakter
        val nama      = mhs.nama.padEnd(18)                // Nama 18 karakter
        val mk        = mhs.mataKuliah.padEnd(14)          // Mata kuliah 14 karakter
        val nilai     = mhs.nilai.toString().padEnd(7)     // Nilai 7 karakter
        val grade     = getGrade(mhs.nilai)                // Konversi ke grade
        println("$no$nim$nama$mk$nilai$grade")
    }

    println()
    println("===== STATISTIK =====")

    // size mengembalikan jumlah elemen dalam list
    val totalMahasiswa = dataMahasiswa.size
    println("Total Mahasiswa : $totalMahasiswa")

    // map mengambil satu field saja, average() menghitung rata-rata
    // "%.1f" memformat Double dengan 1 angka desimal
    val rataRata = dataMahasiswa.map { it.nilai }.average()
    println("Rata-rata Nilai : ${"%.1f".format(rataRata)}")

    // maxByOrNull mencari elemen dengan nilai 'nilai' terbesar
    // !! (non-null assertion) karena list tidak kosong
    val tertinggi = dataMahasiswa.maxByOrNull { it.nilai }!!
    println("Nilai Tertinggi : ${tertinggi.nilai} (${tertinggi.nama})")

    // minByOrNull mencari elemen dengan nilai 'nilai' terkecil
    val terendah = dataMahasiswa.minByOrNull { it.nilai }!!
    println("Nilai Terendah  : ${terendah.nilai} (${terendah.nama})")


    println()
    println("===== MAHASISWA LULUS =====")

    // Menghasilkan list baru hanya berisi mahasiswa dengan nilai >= 70
    val mahasiswaLulus = dataMahasiswa.filter { it.nilai >= 70 }

    // forEachIndexed untuk menampilkan dengan nomor urut
    mahasiswaLulus.forEachIndexed { index, mhs ->
        val grade = getGrade(mhs.nilai)
        println("${index + 1}. ${mhs.nama} - ${mhs.nilai} ($grade)")
    }


    println()
    println("===== MAHASISWA TIDAK LULUS =====")

    val mahasiswaTidakLulus = dataMahasiswa.filter { it.nilai < 70 }

    mahasiswaTidakLulus.forEachIndexed { index, mhs ->
        val grade = getGrade(mhs.nilai)
        println("${index + 1}. ${mhs.nama} - ${mhs.nilai} ($grade)")
    }


    println()
    println("===== MAHASISWA PER GRADE =====")

    // groupBy menghasilkan Map dimana key = grade, value = list mahasiswa
    val groupedByGrade = dataMahasiswa.groupBy { getGrade(it.nilai) }

    // Iterasi Map yang sudah diurutkan berdasarkan key (A, B, C, D, E)
    groupedByGrade.toSortedMap().forEach { (grade, listMhs) ->
        val ket = getKeterangan(grade)
        println()
        println("  Grade $grade - $ket (${listMhs.size} mahasiswa):")

        // Tampilkan nama-nama mahasiswa dalam grade tersebut
        listMhs.forEach { mhs ->
            println("     • ${mhs.nama} (${mhs.nilai})")
        }
    }


    println()
    println("===== JUMLAH PER GRADE =====")

    // Daftar grade yang ingin ditampilkan (urut A-E)
    val semuaGrade = listOf("A", "B", "C", "D", "E")

    semuaGrade.forEach { grade ->
        // count() menghitung elemen yang memenuhi kondisi
        val jumlah = dataMahasiswa.count { getGrade(it.nilai) == grade }
        println("Grade $grade: $jumlah mahasiswa")
    }


    println()
    println("===== SELESAI =====")
}