package by.architecture_map.belarus.service.impl;

import by.architecture_map.belarus.entity.ArchitecturalStyle;
import by.architecture_map.belarus.exception.NotFoundException;
import by.architecture_map.belarus.service.ImageService;
import by.architecture_map.belarus.repository.jpa.ArchitecturalStyleRepository;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArchitecturalStyleServiceImplTest {

    @Mock
    private ArchitecturalStyleRepository architecturalStyleRepository;

    @Mock
    private ImageService imageService;

    @InjectMocks
    private ArchitecturalStyleServiceImpl architecturalStyleService;

    @Test
    void whenSaveArchitecturalStyle_thenSaveStyle() {
        // given
        ArchitecturalStyle architecturalStyle = new ArchitecturalStyle();
        architecturalStyle.setName("Gothic");
        architecturalStyle.setId(1);
        when(architecturalStyleRepository.save(architecturalStyle)).thenReturn(architecturalStyle);

        // when
        ArchitecturalStyle result = architecturalStyleService.create(architecturalStyle);

        // then
        verify(architecturalStyleRepository, times(1)).save(architecturalStyle);
        assertEquals(architecturalStyle, result);
    }

    @Test
    void whenFindAllArchitecturalStyles_thenReturnListOfStyles() {
        // given
        ArchitecturalStyle gothic = new ArchitecturalStyle();
        gothic.setName("Gothic");
        gothic.setId(1);
        ArchitecturalStyle baroque = new ArchitecturalStyle();
        baroque.setName("Baroque");
        baroque.setId(2);
        List<ArchitecturalStyle> styles = Arrays.asList(gothic, baroque);
        when(architecturalStyleRepository.findAll()).thenReturn(styles);

        // when
        List<ArchitecturalStyle> result = architecturalStyleService.findAll();

        // then
        verify(architecturalStyleRepository, times(1)).findAll();
        assertEquals(styles, result);
    }

    @Test
    void whenFindArchitecturalStyle_thenReturnStyle() {
        // given
        int id = 1;
        ArchitecturalStyle architecturalStyle = new ArchitecturalStyle();
        architecturalStyle.setName("Gothic");
        architecturalStyle.setId(id);
        when(architecturalStyleRepository.findById(id)).thenReturn(Optional.of(architecturalStyle));

        // when
        ArchitecturalStyle result = architecturalStyleService.find(id);

        // then
        verify(architecturalStyleRepository, times(1)).findById(id);
        assertEquals(architecturalStyle, result);
    }

    @Test
    void whenFindArchitecturalStyleAndStyleDoesNotExist_thenThrowNotFoundException() {
        // given
        int id = 1;
        when(architecturalStyleRepository.findById(id)).thenReturn(Optional.empty());

        // when & then
        assertThrows(NotFoundException.class, () -> architecturalStyleService.find(id));

        // verify
        verify(architecturalStyleRepository, times(1)).findById(id);
    }

    @Test
    void whenUpdateArchitecturalStyle_thenUpdateStyle() {
        // given
        int id = 1;
        ArchitecturalStyle existingStyle = new ArchitecturalStyle();
        existingStyle.setName("Gothic");
        existingStyle.setId(id);
        ArchitecturalStyle updatedStyle = new ArchitecturalStyle();
        updatedStyle.setName("Updated Gothic");
        updatedStyle.setId(id);

        when(architecturalStyleRepository.findById(id)).thenReturn(Optional.of(existingStyle));
        when(architecturalStyleRepository.save(existingStyle)).thenAnswer(invocation -> {
            existingStyle.setName(updatedStyle.getName());
            return existingStyle;
        });

        // when
        ArchitecturalStyle result = architecturalStyleService.update(id, updatedStyle);

        // then
        verify(architecturalStyleRepository, times(1)).findById(id);
        verify(architecturalStyleRepository, times(1)).save(existingStyle);
        assertEquals(updatedStyle.getName(), result.getName());
    }

    @Test
    void whenUpdateArchitecturalStyleAndStyleDoesNotExist_thenThrowNotFoundException() {
        // given
        int id = 1;
        ArchitecturalStyle updatedStyle = new ArchitecturalStyle();
        updatedStyle.setName("Updated Gothic");
        updatedStyle.setId(id);
        when(architecturalStyleRepository.findById(id)).thenReturn(Optional.empty());

        // when & then
        assertThrows(NotFoundException.class, () -> architecturalStyleService.update(id, updatedStyle));

        // verify
        verify(architecturalStyleRepository, times(1)).findById(id);
    }

    @Test
    void whenPatchArchitecturalStyle_thenPatchStyle() {
        // given
        int id = 1;
        ArchitecturalStyle existingStyle = new ArchitecturalStyle();
        existingStyle.setName("Gothic");
        existingStyle.setDescription("Old Description");
        existingStyle.setId(id);
        ArchitecturalStyle patchStyle = new ArchitecturalStyle();
        patchStyle.setName("Gothic");
        patchStyle.setDescription("New Description");
        patchStyle.setId(id);

        when(architecturalStyleRepository.findById(id)).thenReturn(Optional.of(existingStyle));
        when(architecturalStyleRepository.save(existingStyle)).thenAnswer(invocation -> {
            existingStyle.setName(patchStyle.getName());
            return existingStyle;
        });

        // when
        ArchitecturalStyle result = architecturalStyleService.patchUpdate(id, patchStyle);

        // then
        verify(architecturalStyleRepository, times(1)).findById(id);
        verify(architecturalStyleRepository, times(1)).save(existingStyle);
        assertEquals(patchStyle.getName(), result.getName());
    }

    @Test
    void whenDeleteArchitecturalStyle_thenDeleteIfExists() {
        // given
        int id = 1;
        ArchitecturalStyle architecturalStyle = mock(ArchitecturalStyle.class);
        when(architecturalStyleRepository.findById(id)).thenReturn(Optional.of(architecturalStyle));
        doNothing().when(architecturalStyleRepository).deleteById(id);

        // when
        architecturalStyleService.delete(id);

        // then
        verify(architecturalStyleRepository, times(1)).findById(id);
        verify(architecturalStyleRepository, times(1)).deleteById(id);
    }

    @Test
    void whenDeleteArchitecturalStyleAndStyleDoesNotExist_thenThrowNotFoundException() {
        // given
        int id = 1;
        when(architecturalStyleRepository.findById(id)).thenReturn(Optional.empty());

        // when & then
        assertThrows(NotFoundException.class, () -> architecturalStyleService.delete(id));

        // verify
        verify(architecturalStyleRepository, times(1)).findById(id);
        verify(architecturalStyleRepository, never()).deleteById(id);
    }

}
