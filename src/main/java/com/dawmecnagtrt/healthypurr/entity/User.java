package com.dawmecnagtrt.healthypurr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(nullable = false)
    @Size(max = 30)
    private String name;

    @Column(nullable = false)
    @Size(max = 30)
    private String lastName;

    @Lob
    @JsonIgnore
    @Column(name = "user_pic")
    private byte[] userPic;

    private Integer status;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;


    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name= "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name= "role_id", referencedColumnName = "role_id")
    )
    private List<Role> userRoles;
}
