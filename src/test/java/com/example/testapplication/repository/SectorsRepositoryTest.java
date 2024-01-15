package com.example.testapplication.repository;

import com.example.testapplication.domain.Sector;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class SectorsRepositoryTest {

    @Autowired
    private SectorsRepository sectorsRepository;

    @Test
    public void shouldGetAllSectors() {
        List<Sector> sectors = sectorsRepository.getAllSectors();

        assertEquals(79, sectors.size());
    }
}
