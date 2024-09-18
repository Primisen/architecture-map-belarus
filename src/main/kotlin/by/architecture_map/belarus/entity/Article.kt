package by.architecture_map.belarus.entity

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne

@Entity
data class Article(

    var title: String? = null,
    var content: String? = null,
    var shortDescription: String? = null,

    @OneToOne
    @JoinColumn(name = "demonstrative_image_id", referencedColumnName = "id")
    var demonstrativeImage: Image? = null,

    @OneToMany
    var tags: List<Tag>? =  listOf()

) : BaseEntity()
