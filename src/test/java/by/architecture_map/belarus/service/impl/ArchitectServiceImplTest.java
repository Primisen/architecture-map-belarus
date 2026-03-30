package by.architecture_map.belarus.service.impl;

import by.architecture_map.belarus.entity.Architect;
import by.architecture_map.belarus.repository.jpa.ArchitectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ArchitectServiceImplTest {

    @Mock
    private ArchitectRepository architectRepository;

    @InjectMocks
    private ArchitectServiceImpl architectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenSaveArchitect_thenSaveArchitect() {
        // given
        Architect architect = new Architect();
        architect.setName("John Doe");
        when(architectRepository.save(architect)).thenReturn(architect);

        // when
        Architect result = architectService.create(architect);

        // then
        verify(architectRepository, times(1)).save(architect);
        assertEquals(architect, result);
    }

    @Test
    void whenFindAll_thenReturnListOfArchitects() {
        // given
        Architect a1 = new Architect();
        a1.setName("John Doe");
        Architect a2 = new Architect();
        a2.setName("Jane Doe");
        List<Architect> architects = Arrays.asList(a1, a2);
        when(architectRepository.findAll()).thenReturn(architects);

        // when
        List<Architect> result = architectService.findAll();

        // then
        verify(architectRepository, times(1)).findAll();
        assertEquals(architects, result);
    }

}
