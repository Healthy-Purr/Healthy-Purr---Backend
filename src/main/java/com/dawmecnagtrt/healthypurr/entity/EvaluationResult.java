package com.dawmecnagtrt.healthypurr.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "evaluation_results")
public class EvaluationResult extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ev_result_id", nullable = false)
    private Integer evResultId;

    @Column(nullable = false)
    @Size(max = 30)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_id", nullable = false)
    private Cat cat;

    @Column(precision=2, scale=1, name = "accuracy_rate")
    private BigDecimal accuracyRate;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ev_food_id", nullable = false)
//    private EvaluatedFood evaluatedFood;
}
