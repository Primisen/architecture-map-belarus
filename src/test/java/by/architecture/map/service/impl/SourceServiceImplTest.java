package by.architecture.map.service.impl;

import by.architecture.map.dto.SourceDto;
import by.architecture.map.entity.Source;
import by.architecture.map.exception.SourceException;
import by.architecture.map.mapper.SourceMapper;
import by.architecture.map.repository.SourceRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.answer;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SourceServiceImplTest {

    @Mock
    private SourceRepository sourceRepository;

    @Mock
    private SourceMapper sourceMapper;

    @InjectMocks
    private SourceServiceImpl sourceService;

    private SourceDto sourceDto;
    private Source source;
    private Integer id;

    @BeforeEach
    public void init() {

        sourceDto = SourceDto.builder()
                .name("My home is Belarus")
                .url("my-home-is-belarus.com")
                .build();

        id = 1;

        source = Source.builder()
                .id(id)
                .name(sourceDto.getName())
                .url(sourceDto.getUrl())
                .build();
    }

    @Test
    @SneakyThrows
    void givenSourceDto_whenSavingSourceDto_thenReturnSavedSource() {

        //Arrange
        when(sourceRepository.save(any(Source.class))).thenReturn(source);
        when(sourceMapper.toSource(any(SourceDto.class))).thenReturn(source);

        //Act
        Source actual = sourceService.add(sourceDto);

        //Assert
        assertThat(actual).isEqualTo(source);
    }

    @Test
    void givenSourceListWithElement_whenFindAllSources_thenGetSourceListSizeGreaterThanNull() {

        //Arrange
        List<Source> sources = new ArrayList<>();
        sources.add(source);
        when(sourceRepository.findAll()).thenReturn(sources);

        //Act
        List<Source> actual = sourceService.findAll();

        //Assert
        assertThat(actual.size()).isGreaterThan(0);
    }

//    @Test
//    void  update() throws SourceException {
//
//        //Arrange
//        SourceDto sourceUpdates = SourceDto.builder()
//                .name("Радзіма")
//                .url("radzima.by")
//                .build();
//
//        when(sourceMapper.toSource(sourceUpdates)).thenReturn(source);
////        doNothing().when(sourceRepository.findById(anyInt())
////                .orElseThrow(() -> new SourceException("Source with id = " + id + "not exists.")));
//
//        when(sourceRepository.findById(anyInt())
//                .orElseThrow(() -> new SourceException("Source with id = " + id + "not exists.")))
//                .thenReturn(source);
//
//        //Act
//        Source actual = sourceService.update(id, sourceUpdates);
//
//        //Assert
//        assertThat(source).isEqualTo(actual);
//    }


    @Test
    @SneakyThrows
    void givenSourceId_whenDeleteSource_thenDeleteSource() {

        //Arrange
        doNothing().when(sourceRepository).deleteById(id);
        when(sourceRepository.existsById(id)).thenReturn(true);

        //Act
        sourceService.delete(id);

        //Assert
        verify(sourceRepository, times(1)).deleteById(id);
    }

}