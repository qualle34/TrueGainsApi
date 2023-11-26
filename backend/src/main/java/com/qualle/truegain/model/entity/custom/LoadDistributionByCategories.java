package com.qualle.truegain.model.entity.custom;

import jakarta.persistence.Id;


public interface LoadDistributionByCategories {

    @Id
    long getId();

    String getName();

    float getLoad();
}
