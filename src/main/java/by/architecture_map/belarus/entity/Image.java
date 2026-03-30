package by.architecture_map.belarus.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Image extends BaseEntity {

    private String url;
    private Boolean show;

    @ManyToOne
    @JoinColumn(name = "source_id")
    private Source source;

    private String author;

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Image)) return false;

        Image image = (Image) other;
        return Objects.equals(getId(), image.getId()) &&
                Objects.equals(url, image.url) &&
                Objects.equals(show, image.show) &&
                Objects.equals(source, image.source) &&
                Objects.equals(author, image.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), url, source, author);
    }
}
