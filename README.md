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

# Refleksi 4
## Prinsip yang Diterapkan
### 1. Single Responsibility Principle (SRP)

Setiap class memiliki satu tanggung jawab utama:

- **Controller** → Menangani HTTP request dan response.
- **Service** → Mengelola business logic.
- **Repository** → Mengelola akses dan penyimpanan data.
- **Model** → Merepresentasikan struktur data/domain.

Contoh:
- `CarController` hanya menangani permintaan terkait mobil.
- `CarRepository` hanya bertanggung jawab atas penyimpanan dan pengambilan data mobil.

Dengan demikian, setiap class hanya memiliki satu alasan untuk berubah.

---

### 2. Open/Closed Principle (OCP)

Proyek ini menggunakan abstraction melalui interface seperti:

- `InterfaceCarRepo`
- `InterfaceProductRepo`
- `CarService`
- `ProductService`

Class bergantung pada abstraction, bukan implementasi konkret.  
Hal ini memungkinkan penambahan fitur baru tanpa harus mengubah struktur utama sistem.

---

### 3. Liskov Substitution Principle (LSP)

Class implementasi repository mengikuti kontrak yang ditentukan oleh interface-nya.

Contoh:
- `CarRepository` mengimplementasikan `InterfaceCarRepo`
- `ProductRepository` mengimplementasikan `InterfaceProductRepo`

Objek implementasi dapat menggantikan tipe interface tanpa mengubah perilaku sistem.

---

### 4. Interface Segregation Principle (ISP)

Setiap entitas memiliki interface yang spesifik dan terpisah.

Contoh:
- `InterfaceCarRepo`
- `InterfaceProductRepo`

Tidak ada class yang dipaksa mengimplementasikan method yang tidak dibutuhkan.

---

### 5. Dependency Inversion Principle (DIP)

Modul tingkat tinggi tidak bergantung langsung pada modul tingkat rendah.

- Controller bergantung pada abstraction (`CarService`, `ProductService`).
- Service bergantung pada interface repository.
- Implementasi konkret diinjeksi menggunakan dependency injection.

Hal ini mengurangi ketergantungan langsung antar class.

---

## Keuntungan Menerapkan SOLID

### 1. Lebih Mudah Dipelihara (Maintainable)

Perubahan pada satu layer tidak memengaruhi layer lain.

Contoh:
Jika ingin mengubah cara penyimpanan data, cukup mengubah repository tanpa menyentuh controller.

---

### 2. Mudah Dikembangkan (Scalable)

Penambahan fitur baru tidak mengharuskan perubahan pada kode yang sudah ada.

Contoh:
Untuk menambahkan entitas baru seperti `Order`, cukup membuat:
- `OrderController`
- `OrderService`
- `OrderRepository`
- `Order` model

---

### 3. Mudah Diuji (Testable)

Karena menggunakan abstraction dan dependency injection:

- Service dapat di-mock saat menguji Controller.
- Repository dapat di-mock saat menguji Service.

Hal ini mempermudah pembuatan unit test.

---

### 4. Mengurangi Ketergantungan (Loose Coupling)

Controller tidak mengetahui detail penyimpanan data.  
Ia hanya berinteraksi melalui service.

Sistem menjadi lebih fleksibel terhadap perubahan.

---

## Dampak Jika Tidak Menerapkan SOLID

###  1. Tight Coupling

Jika controller langsung mengakses repository tanpa abstraction, perubahan kecil pada repository dapat memengaruhi controller.

---

### 2. Class Terlalu Kompleks

Jika satu class menangani:
- HTTP logic
- Business logic
- Data persistence

Maka class tersebut menjadi sulit dipahami dan dipelihara.

---

### 3. Sulit Dikembangkan

Tanpa OCP, penambahan fitur baru mengharuskan perubahan pada class lama, sehingga meningkatkan risiko bug.

---

### 4. Sulit Diuji

Tanpa DIP, object yang dibuat langsung dengan `new` sulit untuk di-mock saat unit testing.

---

### 5. Kode Menjadi Kaku

Tanpa pemisahan tanggung jawab yang jelas, perubahan kecil dapat berdampak besar ke banyak bagian sistem.
