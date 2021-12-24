package com.dawmecnagtrt.healthypurr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cats")
public class Cat extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id", nullable = false)
    private Integer catId;

    @Column(nullable = false)
    @Size(max = 30)
    private String name;

    @Column(precision=5, scale=1, nullable = false)
    private BigDecimal weight;

    private Integer age;

    private Boolean gender;

    @Column(name = "has_disease", nullable = false)
    private Boolean hasDisease;

    @Column(name = "is_allergic", nullable = false)
    private Boolean isAllergic;

    @Lob
    @JsonIgnore
    @Column(name = "cat_pic")
    private byte[] catPic;

    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "cat", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    private List<EvaluationResult> evaluationResults;

    @OneToMany(mappedBy = "cat", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CatAllergic> allergics;

    @OneToMany(mappedBy = "cat", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CatDisease> diseases;

    @OneToMany(mappedBy = "cat", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Schedule> schedules;
}
