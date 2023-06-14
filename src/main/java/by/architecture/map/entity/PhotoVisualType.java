package by.architecture.map.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


//remove
@Entity
@Table(name = "photo_visual_type")
@RequiredArgsConstructor
@Getter
@Setter(AccessLevel.PUBLIC)
public class PhotoVisualType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;
}
