package com.dawmecnagtrt.healthypurr.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cat_diseases")
public class CatDisease extends CommonEntity{
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disease_id", nullable = false)
    private Disease disease;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_id", nullable = false)
    private Cat cat;

    private Integer status;
}
