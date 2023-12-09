package com.qualle.truegain.model.entity.custom;

import jakarta.persistence.Id;


public interface TotalLoad {

    @Id
    long getId();

    Float getLoad();
}
