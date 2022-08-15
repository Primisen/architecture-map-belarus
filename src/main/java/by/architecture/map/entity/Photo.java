package by.architecture.map.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "photo")
@RequiredArgsConstructor
@Getter
@Setter(AccessLevel.PUBLIC)
@ToString
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "url_address_to_photo", nullable = false, length = 140)
    private String urlAddressToPhoto;

    @ManyToOne
    @JoinColumn(name = "construction_id", nullable = false)
    private Construction construction;
}
