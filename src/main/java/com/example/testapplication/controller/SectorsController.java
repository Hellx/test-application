package com.example.testapplication.controller;

import com.example.testapplication.domain.*;
import com.example.testapplication.service.SectorsService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/sectors")
public class SectorsController {

    @Autowired
    private SectorsService sectorsService;

    @Operation(summary = "Get all sectors", description = "{@APIResource=Sectors}")
    @RequestMapping(method = GET)
    @ResponseBody
    //@PreAuthorize("hasRole('') && hasPermission()")
    public List<Sector> getAllSectors() {
        List<Sector> sectors = sectorsService.getAllSectors();
        return sectors;
    }

    @Operation(summary = "Add forms data", description = "{@APIResource=Sectors}")
    @PostMapping(value = "/form-data/")
    //@PreAuthorize("hasRole('') && hasPermission()")
    public ResponseEntity<Void> addFormData(@Valid  @RequestBody SessionData sessionData) {
        System.out.println("Form data: " + sessionData);
        sectorsService.addSessionData(sessionData);
        return new ResponseEntity<>(NO_CONTENT);
    }
}
