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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "photo")
@RequiredArgsConstructor
@Getter
@Setter(AccessLevel.PUBLIC)
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "url_address_to_photo", nullable = false, length = 140)
    private String urlAddressToPhoto;

    @ManyToOne
    @JoinColumn(name = "construction", nullable = false)
    private Construction construction;
}
