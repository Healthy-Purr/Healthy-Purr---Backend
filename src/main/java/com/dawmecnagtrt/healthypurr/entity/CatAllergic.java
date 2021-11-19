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
@IdClass(catAllergicId.class)
@Table(name = "cat_allergics")
public class CatAllergic extends CommonEntity{

    @Id
    @Column(name = "cat_id", nullable = false, insertable = false)
    private Integer catId;

    @Id
    @Column(name = "allergic_id", nullable = false, insertable = false)
    private Integer allergicId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "allergic_id", nullable = false, insertable = false, updatable = false)
    private Allergic allergic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_id", nullable = false, insertable = false, updatable = false)
    private Cat cat;

    private Boolean status;
}

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
class catAllergicId implements Serializable {
    private Integer catId;
    private Integer allergicId;



}