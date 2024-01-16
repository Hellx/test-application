package com.example.testapplication.service;

import com.example.testapplication.domain.*;
import com.example.testapplication.repository.SectorsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectorsService {
    private final SectorsRepository sectorsRepository;

    public List<Sector> getAllSectors() {
        return sectorsRepository.getAllSectors();
    }

    public Long addSessionData(SessionData sessionData) {
        if (sectorsRepository.getPersonalSectorsUid(sessionData.getUid())) {
            sectorsRepository.deletePersonalSector(sessionData.getUid());
        }
        if (sectorsRepository.getSessionDetailsUid(sessionData.getUid())) {
            sectorsRepository.deleteSessionDetails(sessionData.getUid());
        }
        for (PersonalSector personalSector : sessionData.getSectors()) {
            sectorsRepository.addPersonalSector(personalSector, sessionData);
        }
        return sectorsRepository.addSessionDetails(sessionData);
    }
}
