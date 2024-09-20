package by.architecture_map.belarus.entity

import jakarta.persistence.Entity

@Entity
data class User(
    var username: String? = null,
    var password: CharArray? = null
) : BaseEntity() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (username != other.username) return false
        if (password != null) {
            if (other.password == null) return false
            if (!password.contentEquals(other.password)) return false
        } else if (other.password != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = username?.hashCode() ?: 0
        result = 31 * result + (password?.contentHashCode() ?: 0)
        return result
    }
}
