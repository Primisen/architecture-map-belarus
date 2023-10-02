package com.architecture_map.belarus.entity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "architect")
@Getter
@Setter(AccessLevel.PUBLIC)
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Architect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String middleName;
    private String surname;
    private String yearsOfLife;
    private String workDescription;
}
