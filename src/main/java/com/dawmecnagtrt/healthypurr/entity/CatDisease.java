package com.dawmecnagtrt.healthypurr.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(catDiseaseId.class)
@Table(name = "cat_diseases")
public class CatDisease extends CommonEntity{

    @Id
    @Column(name = "cat_id", nullable = false, insertable = false)
    private Integer catId;

    @Id
    @Column(name = "disease_id", nullable = false, insertable = false)
    private Integer diseaseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disease_id", nullable = false, insertable = false, updatable = false)
    private Disease disease;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_id", nullable = false, insertable = false, updatable = false)
    private Cat cat;

    private Boolean status;
}

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
class catDiseaseId implements Serializable {
    private Integer catId;
    private Integer diseaseId;



}
