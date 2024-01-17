package com.example.testapplication.repository;

import com.example.testapplication.domain.Sector;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
@Sql(scripts = "classpath:data.sql")
public class SectorsRepositoryTest {

    @Autowired
    private SectorsRepository sectorsRepository;

    @Test
    public void shouldGetAllSectors() {
        List<Sector> sectors = sectorsRepository.getAllSectors();

        assertEquals(79, sectors.size());
    }

    @Test
    public void shouldFindThatUidExistsInPersonalSectors() {
        Boolean isUid = sectorsRepository.getPersonalSectorsUid("1234");

        assertEquals(true, isUid);
    }

    @Test
    public void shouldFindThatUidExistsInSessionDetails() {
        Boolean isUid = sectorsRepository.getSessionDetailsUid("1234");

        assertEquals(true, isUid);
    }

    @Test
    public void shouldDeleteRecordFromPersonalSectors() {
        int uid = sectorsRepository.deletePersonalSector("1234");
        Boolean isUid = sectorsRepository.getPersonalSectorsUid("1234");

        assertEquals(false, isUid);
    }

    @Test
    public void shouldDeleteRecordFromSessionDetails() {
        int uid = sectorsRepository.deleteSessionDetails("1234");
        Boolean isUid = sectorsRepository.getSessionDetailsUid("1234");

        assertEquals(false, isUid);
    }

}
