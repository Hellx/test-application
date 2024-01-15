package com.example.testapplication.controller;

import com.example.testapplication.domain.Sector;
import com.example.testapplication.service.SectorsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SectorsController.class)
public class SectorsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SectorsService sectorsService;

    @Test
    public void shouldGetAllSectors() throws Exception {
        Sector sector = new Sector();
        sector.setOptionValue(1);
        sector.setName("Manufactoring");
        List<Sector> sectors = singletonList(sector);
        Mockito.when(sectorsService.getAllSectors()).thenReturn(sectors);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/sectors"));

        resultActions.andExpect(status().isOk());
    }
}
