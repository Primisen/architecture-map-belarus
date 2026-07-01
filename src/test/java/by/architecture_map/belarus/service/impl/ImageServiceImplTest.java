package by.architecture_map.belarus.service.impl;

import by.architecture_map.belarus.entity.Image;
import by.architecture_map.belarus.exception.NotFoundException;
import by.architecture_map.belarus.repository.jpa.ImageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ImageServiceImplTest {

    @Mock
    private ImageRepository imageRepository;

    @InjectMocks
    private ImageServiceImpl imageService;

    @Test
    void whenFind_thenReturnImage() {
        int id = 1;
        Image image = new Image();
        image.setId(id);
        when(imageRepository.findById(id)).thenReturn(Optional.of(image));

        Image result = imageService.find(id);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        verify(imageRepository).findById(id);
    }

    @Test
    void whenNotFound_thenThrowNotFoundException() {
        int id = 2;
        when(imageRepository.findById(id)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> imageService.find(id));

        assertThat(exception.getMessage().contains(String.valueOf(id)));
        verify(imageRepository).findById(id);
    }
}
