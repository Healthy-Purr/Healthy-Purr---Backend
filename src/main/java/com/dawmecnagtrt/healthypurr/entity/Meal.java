package com.dawmecnagtrt.healthypurr.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "meals")
public class Meal extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id", nullable = false)
    private Integer mealId;

    @Column(nullable = false)
    @Size(max = 100)
    private String description;

    private LocalTime hour;

    @Column(name="is_dry")
    private Boolean isDry;

    @Column(name="is_damp")
    private Boolean isDamp;

    @Column(name="has_medicine")
    private Boolean hasMedicine;

    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;
}
