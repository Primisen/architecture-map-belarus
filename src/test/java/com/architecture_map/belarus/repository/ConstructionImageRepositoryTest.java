package com.architecture_map.belarus.repository;

import com.architecture_map.belarus.entity.image.ConstructionImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;

@ActiveProfiles("test")
@DataJpaTest
class ConstructionImageRepositoryTest {

    @Autowired
    ConstructionImageRepository constructionImageRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    void givenArrayOfConstructionImageIds_whenGetRandomAndUniqueImages_thenNotContainsImagesWithGivenIds() {
        int[] ids = {3, 5, 6, 8, 9, 12, 4, 1, 19, 11};

        Set<ConstructionImage> images = constructionImageRepository.getRandomAndUniqueImages(ids);

        List<Integer> idsCollection = Arrays.stream(ids).boxed().toList();

        Set<ConstructionImage> containsImages = images
                .stream()
                .filter(
                        image ->
                                idsCollection.stream()
                                        .anyMatch(
                                                id -> image.getId().equals(id))
                )
                .collect(Collectors.toSet());

        assertThat(containsImages, is(empty()));
    }
}