# Projeyi Çalıştırma

Bu projeyi çalıştırmak için aşağıdaki adımları izleyin:

## Adım 1: Docker imajını indirme

Projeyi çalıştırmadan önce Docker üzerinden PostgreSQL veritabanının containerini oluşturmamız gerekiyor.
bunun için aşağdaki komutu terminale yazmanız gerekiyor.

```bash
docker-compose up
````
## Adım 2: Projeyi Çalıştırma

## API Endpointleri

### Kitap (Book) Endpointleri

- **GET** `/api/v1/book/allBook`
    - Açıklama: Tüm kitapları al.
- **POST** `/api/v1/book/saveBook`
    - Açıklama: Yeni bir kitap kaydet.
- **GET** `/api/v1/book/{id}`
    - Açıklama: ID'ye göre bir kitap al.

### Sepet Endpointleri

- **GET** `/api/v1/sepet/getByUserId/{userId}`
    - Açıklama: Kullanıcı ID'sine göre sepet öğelerini al.
- **PUT** `/api/v1/sepetogesi/addBook`
    - Açıklama: Sepete bir kitap ekle.
- **DELETE** `/api/v1/sepetogesi/deleteBook`
    - Açıklama: Sepetten bir kitap sil.

### Kullanıcı (User) Endpointleri

- **POST** `/api/v1/user/addNewUser`
    - Açıklama: Yeni bir kullanıcı ekle.
- **POST** `/api/v1/user/generateToken`
    - Açıklama: Bir kullanıcı için kimlik doğrulama tokeni oluştur.
- **GET** `/api/v1/user/welcome`
    - Açıklama: Kullanıcılara hoş geldiniz mesajı.

### Ödeme (Payment) Endpointleri

- **POST** `/api/v1/payment/save/{userId}`
    - Açıklama: Bir kullanıcının ödeme detaylarını kaydet.

## Alternatif Kurulum DOCKER
### Adım 1: Docker imajını indirme

Projeyi çalıştırmak için öncelikle DockerHub'dan Docker imajını indirin:

```bash
docker pull demirrs/bookstore
````
## Adım 2: Docker konteynerini başlatma
```bash
docker run -d --name bookstore -p 8080:8080 demirrs/bookstore
````