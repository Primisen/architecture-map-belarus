package by.architecture_map.belarus.service.impl;

import by.architecture_map.belarus.entity.Address;
import by.architecture_map.belarus.entity.ArchitecturalStyle;
import by.architecture_map.belarus.entity.Construction;
import by.architecture_map.belarus.exception.NotFoundException;
import by.architecture_map.belarus.repository.jpa.ConstructionRepository;
import io.mockk.Runs;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConstructionServiceImplTest {

    @Mock
    private ConstructionRepository constructionRepository;

    @Mock
    private ElasticsearchOperations elasticsearchOperations;

    @InjectMocks
    private ConstructionServiceImpl constructionService;

    private Construction buildConstruction(String name, String region, String styleName) {
        Address address = new Address();
        address.setRegion(region);

        ArchitecturalStyle style = new ArchitecturalStyle();
        style.setName(styleName);

        Construction construction = new Construction();
        construction.setName(name);
        construction.setAddress(address);
        construction.setArchitecturalStyle(style);

        return construction;
    }

    @Test
    void whenCreateConstruction_thenSaveConstruction() {
        // given
        Construction construction = buildConstruction("Name2", "Test", "Style");
        construction.setId(1);
        when(constructionRepository.save(construction)).thenReturn(construction);

        // when
        Construction result = constructionService.create(construction);

        // then
        verify(constructionRepository, times(1)).save(construction);
        assertEquals(construction, result);
    }

    @Test
    void whenFindAllConstruction_thenReturnListOfConstruction() {
        // given
        Construction c1 = buildConstruction("Name1", "Test", "Style");
        c1.setId(1);
        Construction c2 = buildConstruction("Name2", "Test", "Style");
        c2.setId(2);
        List<Construction> constructions = Arrays.asList(c1, c2);
        when(constructionRepository.findAll()).thenReturn(constructions);

        // when
        List<Construction> result = constructionService.findAll();

        // then
        verify(constructionRepository, times(1)).findAll();
        assertEquals(constructions, result);
    }

    @Test
    void whenFind_thenReturnConstruction() {
        // given
        int id = 1;
        Construction construction = buildConstruction("Name1", "Test", "Style");
        construction.setId(id);
        when(constructionRepository.findById(id)).thenReturn(Optional.of(construction));

        // when
        Construction result = constructionService.find(id);

        // then
        verify(constructionRepository, times(1)).findById(id);
        assertEquals(construction, result);
    }

    @Test
    void whenUpdate_thenUpdateConstruction() {
        // given
        int id = 1;
        Construction existingConstruction = buildConstruction("Name1", "Test", "Style");
        existingConstruction.setId(id);
        Construction updatedConstruction = buildConstruction("Name2", "Test2", "Style2");
        updatedConstruction.setId(id);

        when(constructionRepository.findById(id)).thenReturn(Optional.of(existingConstruction));
        when(constructionRepository.save(existingConstruction)).thenAnswer(invocation -> {
            existingConstruction.setName(updatedConstruction.getName());
            existingConstruction.setDescription(updatedConstruction.getDescription());
            existingConstruction.setArchitecturalStyle(updatedConstruction.getArchitecturalStyle());
            existingConstruction.setAddress(updatedConstruction.getAddress());
            existingConstruction.setArchitects(updatedConstruction.getArchitects());
            existingConstruction.setBuildingDate(updatedConstruction.getBuildingDate());
            existingConstruction.setImages(updatedConstruction.getImages());
            return existingConstruction;
        });

        // when
        Construction result = constructionService.update(id, updatedConstruction);

        // then
        verify(constructionRepository, times(1)).findById(id);
        verify(constructionRepository, times(1)).save(existingConstruction);
        assertEquals(existingConstruction, result);
    }

    @Test
    void whenPatch_thenPatchConstruction() {
        // given
        int id = 1;
        Construction existingConstruction = buildConstruction("Name", "Test", "Style");
        existingConstruction.setId(id);
        Construction updatedConstruction = buildConstruction("Name 2", "Test 2", "Another style");
        updatedConstruction.setId(id);

        when(constructionRepository.findById(id)).thenReturn(Optional.of(existingConstruction));
        when(constructionRepository.save(existingConstruction)).thenAnswer(invocation -> {
            existingConstruction.setName(updatedConstruction.getName());
            existingConstruction.setAddress(updatedConstruction.getAddress());
            existingConstruction.setArchitects(updatedConstruction.getArchitects());
            existingConstruction.setArchitecturalStyle(updatedConstruction.getArchitecturalStyle());
            existingConstruction.setImages(updatedConstruction.getImages());
            existingConstruction.setDescription(updatedConstruction.getDescription());
            existingConstruction.setBuildingDate(updatedConstruction.getBuildingDate());
            return existingConstruction;
        });

        // when
        Construction result = constructionService.patchUpdate(id, updatedConstruction);

        // then
        verify(constructionRepository, times(1)).findById(id);
        verify(constructionRepository, times(1)).save(existingConstruction);
        assertEquals(existingConstruction, result);
    }

    @Test
    void whenDelete_thenDeleteConstruction() {
        // given
        int id = 1;
        Construction construction = mock(Construction.class);
        when(constructionRepository.findById(id)).thenReturn(Optional.of(construction));
        doNothing().when(constructionRepository).deleteById(id);

        // when
        constructionService.delete(id);

        // then
        verify(constructionRepository, times(1)).findById(id);
        verify(constructionRepository, times(1)).deleteById(id);
    }

    @Test
    void whenDeleteConstructionAndConstructionDoesNotExists_thenThrowNotFoundException() {
        // given
        int id = 1;
        when(constructionRepository.findById(id)).thenReturn(Optional.empty());

        // when & then
        assertThrows(NotFoundException.class, () -> constructionService.delete(id));

        // verify
        verify(constructionRepository, times(1)).findById(id);
        verify(constructionRepository, never()).deleteById(id);
    }

}
