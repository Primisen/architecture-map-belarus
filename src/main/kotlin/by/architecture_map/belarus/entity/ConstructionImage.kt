package by.architecture_map.belarus.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.PrimaryKeyJoinColumn

@Entity
@PrimaryKeyJoinColumn(name = "image_id")
data class ConstructionImage(

    @JsonIgnoreProperties("images")
    @ManyToOne
    @JoinColumn(name = "construction_id")
    var construction: Construction? = null,
    var takenTime: String? = null

) : Image()
