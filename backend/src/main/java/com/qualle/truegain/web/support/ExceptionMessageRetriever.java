package com.qualle.truegain.web.support;

import java.util.Collection;

public interface ExceptionMessageRetriever {

    Collection<String> retrieve(Throwable throwable);

}