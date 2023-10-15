package com.truegain.web.support;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class DefaultExceptionMessageRetriever implements ExceptionMessageRetriever {

    @Override
    public Collection<String> retrieve(Throwable th) {
        List<String> result = new ArrayList<>();
        while (th != null) {
            result.add(th.getClass().getSimpleName() + ":" + th.getMessage());
            th = th.getCause();
        }
        return result;
    }
}