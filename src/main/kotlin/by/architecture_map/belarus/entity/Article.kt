package by.architecture_map.belarus.entity

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToOne

@Entity
data class Article(

    var title: String? = null,
    var content: String? = null,
    var shortDescription: String? = null,

    @OneToOne
    @JoinColumn(name = "demonstrative_image_id", referencedColumnName = "id")
    var demonstrativeImage: Image? = null,

    @ManyToMany
    @JoinTable(
        name = "tag_construction",
        joinColumns = [JoinColumn(name = "article_id")],
        inverseJoinColumns = [JoinColumn(name = "tag_id")]
    )
    var tags: List<Tag>? = listOf()

) : BaseEntity()
