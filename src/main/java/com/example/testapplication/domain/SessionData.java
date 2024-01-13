package com.example.testapplication.domain;

import com.example.testapplication.domain.PersonalSector;
import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class SessionData {
    private String uid;
    private List<PersonalSector> sectors;
    private String name;
    private String agreement;
}
