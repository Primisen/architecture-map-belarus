package by.architecturemap.belarus.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.Entity
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
open class ConstructionImage(

    @JsonIgnoreProperties("images")
    @ManyToOne
    @JoinColumn(name = "construction_id")
    open var construction: Construction? = null,
    open var takenTime: String? = null

) : Image(

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as ConstructionImage

        if (construction != other.construction) return false
        if (takenTime != other.takenTime) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (construction?.hashCode() ?: 0)
        result = 31 * result + (takenTime?.hashCode() ?: 0)
        return result
    }
}
