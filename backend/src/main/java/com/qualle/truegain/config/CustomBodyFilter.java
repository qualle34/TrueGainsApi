package com.qualle.truegain.config;

import org.zalando.logbook.BodyFilter;

import javax.annotation.Nullable;

public class CustomBodyFilter implements BodyFilter {

    @Override
    public String filter(@Nullable String contentType, String body) {
        return body;
    }

    @Nullable
    @Override
    public BodyFilter tryMerge(BodyFilter next) {
        return BodyFilter.super.tryMerge(next);
    }
}
