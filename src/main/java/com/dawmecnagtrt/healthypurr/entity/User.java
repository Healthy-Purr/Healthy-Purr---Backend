package com.dawmecnagtrt.healthypurr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Data
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

    private String name;

    private String lastName;

    @Lob
    @JsonIgnore
    @Column(name = "user_pic")
    private byte[] userPic;

    private Integer status;

    @NotEmpty(message = "The account name can't be empty")
    @Column(name = "username", nullable = false)
    private String username;

    @NotEmpty(message = "The account password can't be empty")
    @Column(name = "password", nullable = false)
    private String password;

}
