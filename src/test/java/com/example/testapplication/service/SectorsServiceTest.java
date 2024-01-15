package com.example.testapplication.service;

import com.example.testapplication.domain.Sector;
import com.example.testapplication.repository.SectorsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
public class SectorsServiceTest {

    @Mock
    private SectorsRepository sectorsRepository;
    @InjectMocks
    private SectorsService sectorsService;

    @Test
    public void shouldGetAllSectors() {
        Sector sector = new Sector();
        when(sectorsRepository.getAllSectors()).thenReturn(singletonList(sector));

        List<Sector> result = sectorsService.getAllSectors();

        assertEquals(1, result.size());
        assertEquals(sector, result.get(0));
    }
}
