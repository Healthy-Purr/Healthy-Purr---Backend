package com.dawmecnagtrt.healthypurr.entity;

import lombok.*;

import javax.persistence.*;
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

    private LocalTime hour;

    private String quantity;

    private Boolean dry;

    private Boolean damp;

    private Boolean medicine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;
}
