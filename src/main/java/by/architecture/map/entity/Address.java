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
import javax.persistence.Table;

@Entity
@Table(name = "address")
@RequiredArgsConstructor
@Getter
@Setter(AccessLevel.PUBLIC)
@ToString
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "locality", nullable = false, length = 20)
    private String locality;

    @Column(name = "district", nullable = false, length = 15)
    private String district;

    @Column(name = "region", nullable = false, length = 15)
    private String region;

    @Column(name = "street", length = 2)
    private String street;

    @Column(name = "house_number", length = 5)
    private String house_number;
}
