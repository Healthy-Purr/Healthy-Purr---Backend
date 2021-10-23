package com.dawmecnagtrt.healthypurr.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "diseases")
public class Disease extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disease_id", nullable = false)
    private Integer diseaseId;

    @Column(nullable = false)
    @Size(max = 30)
    private String description;
}
