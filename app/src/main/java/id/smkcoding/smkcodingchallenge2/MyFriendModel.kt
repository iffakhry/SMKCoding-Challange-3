package id.smkcoding.smkcodingchallenge2

data class MyFriendModel (
    private var nama : String,
    private var email: String,
    private var telp : String,
    private var alamat: String,
    private var key: String? = null) {
        constructor() : this("", "", "", "") {
        }

    fun getKey(): String? {
        return key
    }

    fun setKey(key: String?) {
        this.key = key
    }

    fun getNama(): String? {
        return nama
    }

    fun setNama(nama: String?) {
        if (nama != null) {
            this.nama = nama
        }
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String?) {
        if (email != null) {
            this.email = email
        }
    }

    fun getTelp(): String? {
        return telp
    }

    fun setTelp(telp: String?) {
        if (telp != null) {
            this.telp = telp
        }
    }

    fun getAlamat(): String? {
        return alamat
    }

    fun setAlamat(alamat: String?) {
        if (alamat != null) {
            this.alamat = alamat
        }
    }
}
