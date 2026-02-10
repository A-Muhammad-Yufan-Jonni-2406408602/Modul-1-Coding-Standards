# Refleksi 1

selama mengimplementasikan fitur edit dan delete saya menerapkan beberapa prinsip clean code seperti Single Responsibility Principle (dimana setiap class mempunyai kegunaan sendiri seperti controller tidak mengandung business logic dan service tidak menanganin http request), dan penamaan variabel dan methode yang konsisten membuat kode yang Readable

dari sisi secure coding saya menerapkan redirect absolut untuk menghindari masalah routing relatif dan menggunakan parameter bertipe pada @PathVariable sehingga Spring melakukan validasi tipe input dasar, namun masih ada hal yang bisa ditingkatkan seperti mevalidasi method delete dan edit, dan mengatasi jika findById mengembalikan null 

# Refleksi 2

1. Setelah menulis unit test saya merasa lebih pede dengan kualitas kode yang saya buat dan memudahkan saya dalam menangani beberapa bug yang ditemukan saat test saya mengaslami failed

2. jika membuat class baru dengan setup dan instance variables yang sama tanpa refactor akan menyebabkan duplikasi kode, potensi konflik state, dan kesulitan maintenance. Lebih baik menggunakan inheritance atau setup method yang bisa dipakai ulang.