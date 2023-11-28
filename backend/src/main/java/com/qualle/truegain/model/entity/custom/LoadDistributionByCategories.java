package com.qualle.truegain.model.entity.custom;

import jakarta.persistence.Id;


public interface LoadDistributionByCategories {

    @Id
    Long getId();

    String getName();

    Float getLoad();
}
