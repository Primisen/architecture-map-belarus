package by.architecturemap.belarus.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToOne
import org.springframework.data.elasticsearch.annotations.Document

@Entity
@Document(indexName = "article")
data class Article(

    var title: String,
    var content: String,
    var shortDescription: String,

    @OneToOne
    @JoinColumn(name = "demonstrative_image_id", referencedColumnName = "id")
    var demonstrativeImage: Image,

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "tag_article",
        joinColumns = [JoinColumn(name = "article_id")],
        inverseJoinColumns = [JoinColumn(name = "tag_id")]
    )
    var tag: List<Tag> = listOf()

) : BaseEntity()
