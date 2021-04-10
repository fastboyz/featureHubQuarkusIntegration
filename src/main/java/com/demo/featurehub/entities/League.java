package com.demo.featurehub.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class League {

    @Id
    @GeneratedValue
    private Long id;

    private String countries;


}
