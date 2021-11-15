package com.dawmecnagtrt.healthypurr.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "evaluated_foods")
public class EvaluatedFood extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evaluated_food_id", nullable = false)
    private Integer evaluatedFoodId;

    private Float protein;
    private Float fat;
    private Float fiber;
    private Float moisture;
    private Float calcium;
    private Float phosphorus;

    @Column(name = "has_taurine", nullable = false)
    private Boolean hasTaurine;

    @OneToMany(mappedBy = "evaluatedFood", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    private List<EvaluationResult> evaluationResults;

}
