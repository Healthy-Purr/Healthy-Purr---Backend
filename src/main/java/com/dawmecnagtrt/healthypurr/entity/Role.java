package com.dawmecnagtrt.healthypurr.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Integer roleId;

    @NotEmpty(message = "El nombre no puede ser vacio ")
    @Column(name = "role_name", nullable = false)
    private String roleName;

    @Column(name = "role_description")
    private String roleDescription;

    @Column(name = "role_state")
    public String state;
}