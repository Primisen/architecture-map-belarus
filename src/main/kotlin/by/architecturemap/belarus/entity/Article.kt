package by.architecturemap.belarus.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToOne
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.springframework.data.elasticsearch.annotations.Document

@Entity
@Document(indexName = "article")
data class Article(

    @NotBlank(message = "Title may not be blank")
    var title: String,

    @NotBlank(message = "Content may not be blank")
    var content: String,

    @NotBlank(message = "Short description may not be blank")
    var shortDescription: String,

    @NotNull(message = "Demonstrative image may not be null")
    @OneToOne(cascade = [CascadeType.PERSIST, CascadeType.REMOVE])
    @JoinColumn(name = "demonstrative_image_id", referencedColumnName = "id")
    var demonstrativeImage: Image,

    @NotEmpty(message = "Tag may not be empty")
    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "tag_article",
        joinColumns = [JoinColumn(name = "article_id")],
        inverseJoinColumns = [JoinColumn(name = "tag_id")]
    )
    var tag: List<Tag> = listOf()

) : BaseEntity()
