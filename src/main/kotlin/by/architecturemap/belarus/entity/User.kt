package by.architecturemap.belarus.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

@Entity
/**
 * In application using PostgreSQL DB in which 'user' is reserved keyword,
 * so table has short name 'usr'
 */
@Table(name = "usr")
data class User(

    @Email
    var username: String,

    /**
     * When the User is registered, this enabled field will be set to false.
     * During the account verification process – if successful – it will become true
     */
    @NotNull(message = "Enable field may not be null")
    var enable: Boolean = false,

    @NotBlank(message = "Name may not be blank")
    var password: CharArray,

    var name: String? = null,
    var surname: String? = null,
    var aboutThemself: String? = null,

    @NotEmpty(message = "Name may not be empty")
    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "role_user",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    var roles: Set<Role> = setOf(),

    @OneToMany(mappedBy = "user", cascade = [CascadeType.REMOVE])
    var images: List<UserImage> = listOf()

) : BaseEntity() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (username != other.username) return false
        if (!password.contentEquals(other.password)) return false
        if (roles != other.roles) return false

        return true
    }

    override fun hashCode(): Int {
        var result = username.hashCode() ?: 0
        result = 31 * result + (password.contentHashCode() ?: 0)
        result = 31 * result + roles.hashCode()
        return result
    }
}
