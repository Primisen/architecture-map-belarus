package by.architecture_map.belarus.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
//import org.springframework.data.elasticsearch.annotations.Document

@Entity
//@Document(indexName = "article")
@Data
@EqualsAndHashCode(callSuper = true)
public class Article extends BaseEntity {

    private String title;
    private String content;
    private String shortDescription;

    @OneToOne
    @JoinColumn(name = "demonstrative_image_id", referencedColumnName = "id")
    private Image demonstrativeImage;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tag_article",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tag = new ArrayList<>();
}
