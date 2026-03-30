package by.architecture_map.belarus.service.impl;

import by.architecture_map.belarus.entity.Source;
import by.architecture_map.belarus.exception.NotFoundException;
import by.architecture_map.belarus.repository.jpa.SourceRepository;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
class SourceServiceImplTest {

    @Mock
    private SourceRepository sourceRepository;

    @InjectMocks
    private SourceServiceImpl sourceService;

    @Test
    void whenCreateSource_thenSaveSource() {
        // given
        Source source = new Source();
        source.setName("Source Name");
        source.setUrl("http://example.com");
        source.setDescription("Source Description");
        source.setId(1);
        when(sourceRepository.save(source)).thenReturn(source);

        // when
        Source result = sourceService.create(source);

        // then
        verify(sourceRepository, times(1)).save(source);
        assertEquals(source, result);
    }

    @Test
    void whenFindAllSources_thenReturnListOfSources() {
        // given
        Source source1 = new Source();
        source1.setName("Source 1");
        source1.setUrl("http://example.com/1");
        source1.setDescription("Description 1");
        source1.setId(1);

        Source source2 = new Source();
        source2.setName("Source 2");
        source2.setUrl("http://example.com/2");
        source2.setDescription("Description 2");
        source2.setId(2);

        List<Source> sources = Arrays.asList(source1, source2);
        when(sourceRepository.findAll()).thenReturn(sources);

        // when
        List<Source> result = sourceService.findAll();

        // then
        verify(sourceRepository, times(1)).findAll();
        assertEquals(sources, result);
    }

    @Test
    void whenUpdate_thenUpdateSource() {
        // given
        int id = 1;
        Source existingSource = new Source();
        existingSource.setName("Old Name");
        existingSource.setUrl("http://example.com");
        existingSource.setDescription("Old Description");
        existingSource.setId(id);

        Source updatedSource = new Source();
        updatedSource.setName("Updated Name");
        updatedSource.setUrl("http://new-url.com");
        updatedSource.setDescription("Updated Description");
        updatedSource.setId(id);

        when(sourceRepository.findById(id)).thenReturn(Optional.of(existingSource));
        when(sourceRepository.save(existingSource)).thenAnswer(invocation -> {
            existingSource.setName(updatedSource.getName());
            existingSource.setUrl(updatedSource.getUrl());
            existingSource.setDescription(updatedSource.getDescription());
            return existingSource;
        });

        // when
        Source result = sourceService.update(id, updatedSource);

        // then
        verify(sourceRepository, times(1)).findById(id);
        verify(sourceRepository, times(1)).save(existingSource);
        assertEquals(existingSource, result);
    }

    @Test
    void whenPatch_thenPatchSource() {
        // given
        int id = 1;
        Source existingSource = new Source();
        existingSource.setName("Old Name");
        existingSource.setUrl("http://example.com");
        existingSource.setDescription("Old Description");
        existingSource.setId(id);

        Source updatedSource = new Source();
        updatedSource.setName("Updated Name");
        updatedSource.setUrl("http://example2.com");
        updatedSource.setDescription("Updated Description");
        updatedSource.setId(id);

        when(sourceRepository.findById(id)).thenReturn(Optional.of(existingSource));
        when(sourceRepository.save(existingSource)).thenAnswer(invocation -> {
            existingSource.setName(updatedSource.getName());
            existingSource.setUrl(existingSource.getUrl());
            existingSource.setDescription(updatedSource.getDescription());
            return existingSource;
        });

        // when
        Source result = sourceService.patchUpdate(id, updatedSource);

        // then
        verify(sourceRepository, times(1)).findById(id);
        verify(sourceRepository, times(1)).save(existingSource);
        assertEquals(existingSource, result);
    }

    @Test
    void whenDelete_thenDeleteSource() {
        // given
        int id = 1;
        Source source = mock(Source.class);
        when(sourceRepository.findById(id)).thenReturn(Optional.of(source));
        doNothing().when(sourceRepository).deleteById(id);

        // when
        sourceService.delete(id);

        // then
        verify(sourceRepository, times(1)).findById(id);
        verify(sourceRepository, times(1)).deleteById(id);
    }

    @Test
    void whenDeleteSourceAndSourceDoesNotExists_thenThrowNotFoundException() {
        // given
        int id = 1;
        when(sourceRepository.findById(id)).thenReturn(Optional.empty());

        // when & then
        assertThrows(NotFoundException.class, () -> sourceService.delete(id));

        // verify
        verify(sourceRepository, times(1)).findById(id);
        verify(sourceRepository, never()).deleteById(id);
    }

}
