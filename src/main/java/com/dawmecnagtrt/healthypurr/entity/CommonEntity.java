package com.dawmecnagtrt.healthypurr.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class CommonEntity implements Serializable {
    @Column(name = "created_at")
    @CreatedDate
    private Date createdAt;

    @Column(name = "last_update")
    @LastModifiedDate
    private Date lastUpdate;
}