# Refleksi 1

selama mengimplementasikan fitur edit dan delete saya menerapkan beberapa prinsip clean code seperti Single Responsibility Principle (dimana setiap class mempunyai kegunaan sendiri seperti controller tidak mengandung business logic dan service tidak menanganin http request), dan penamaan variabel dan methode yang konsisten membuat kode yang Readable

dari sisi secure coding saya menerapkan redirect absolut untuk menghindari masalah routing relatif dan menggunakan parameter bertipe pada @PathVariable sehingga Spring melakukan validasi tipe input dasar, namun masih ada hal yang bisa ditingkatkan seperti mevalidasi method delete dan edit, dan mengatasi jika findById mengembalikan null 

# Refleksi 2

1. Setelah menulis unit test saya merasa lebih pede dengan kualitas kode yang saya buat dan memudahkan saya dalam menangani beberapa bug yang ditemukan saat test saya mengaslami failed

2. jika membuat class baru dengan setup dan instance variables yang sama tanpa refactor akan menyebabkan duplikasi kode, potensi konflik state, dan kesulitan maintenance. Lebih baik menggunakan inheritance atau setup method yang bisa dipakai ulang.

# Refleksi 3
1. code quality issue yang saya alami kebanyakan terdapat pada pembuatan constructor untuk semua class pada main dan mendeclare semua variabel yang tidak berubah sebagai final variabel dan juga mengganti method find by id agar sesuai aturan bahwa sebuah method hanya boleh memiliki 1 statement return

2. Implementasi sudah memenuhi definisi Continuous Integration karena workflow dijalankan secara otomatis setiap kali terjadi push dan pull request. Hal ini memastikan bahwa setiap perubahan kode langsung diuji melalui unit test (`./gradlew test`) dan dianalisis kualitasnya menggunakan PMD (`./gradlew pmdMain`). Selain itu, CI juga melakukan setup environment secara otomatis (Java 21 dan Gradle), sehingga proses build dan testing konsisten dan tidak bergantung pada environment lokal developer.
   Continuous Deployment juga sudah diterapkan melalui integrasi otomatis Koyeb dengan GitHub, sehingga aplikasi dapat langsung dideploy setelah perubahan.