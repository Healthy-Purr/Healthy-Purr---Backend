package com.dawmecnagtrt.healthypurr.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "diseases")
public class EvaluatedFood extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ev_food_id", nullable = false)
    private Integer evFoodId;

    private Float protein;
    private Float fat;
    private Float fiber;
    private Float moisture;
    private Float calcium;
    private Float phosphorus;

    @Column(name = "has_taurine", nullable = false)
    private Boolean hasTaurine;

}
