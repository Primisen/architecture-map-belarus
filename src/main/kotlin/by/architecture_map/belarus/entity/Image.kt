package by.architecture_map.belarus.entity

import jakarta.persistence.Entity
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
open class Image(

    open var url: String? = null,
    open var show: Boolean? = null,
    @ManyToOne
    @JoinColumn(name = "source_id")
    open var source: Source? = null,

    /**
     * If the image is taken from out resource, the name of the author of
     * the image is written in the 'author' field, if it is specified.
     * With the possibility of registration and adding pictures by users, the username
     * of the user who added the picture is written in the 'author' field.
     */
    open var author: String? = null

) : BaseEntity() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Image) return false

        if (id != other.id) return false
        if (url != other.url) return false
        if (show != other.show) return false
        if (source != other.source) return false
        if (author != other.author) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + source.hashCode()
        result = 31 * result + author.hashCode()
        return result
    }
}
