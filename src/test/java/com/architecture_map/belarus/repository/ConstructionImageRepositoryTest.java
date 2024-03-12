package com.architecture_map.belarus.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ConstructionImageRepositoryTest {

    @Autowired
    ConstructionImageRepository constructionImageRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    void givenArrayOfConstructionImageIds_whenGetRandomAndUniqueImages_thenSuccess() {

    }

    @Test
    void getByConstructionArchitecturalStyleId() {
    }
}