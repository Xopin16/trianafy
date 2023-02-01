package com.salesianostriana.dam.trianafy.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Data
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Artist {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

}
