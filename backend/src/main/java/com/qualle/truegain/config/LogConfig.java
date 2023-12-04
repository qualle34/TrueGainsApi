package com.qualle.truegain.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.web.exchanges.HttpExchange;
import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.BodyFilter;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.core.BodyFilters;
import org.zalando.logbook.servlet.LogbookFilter;

import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class LogConfig {

    @Bean
    public Logbook logbook() {
        BodyFilter bodyFilter = new CustomBodyFilter();
        return Logbook.builder()
                .bodyFilter(bodyFilter)
                .build();
    }

    @Bean
    public FilterRegistrationBean<LogbookFilter> logbookFilter(Logbook logbook) {
        FilterRegistrationBean<LogbookFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LogbookFilter(logbook));
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

//    @Bean
//    public HttpExchangeRepository httpTraceRepository() {
//        return new InMemoryHttpExchangeRepository() {
//
//            @Override
//            public void add(HttpExchange exchange) {
//
//                writeLog(exchange);
//
//                super.add(exchange);
//            }
//        };
//    }
//
//    private void writeLog(HttpExchange exchange) {
//
//
//
//        log.info(exchange.getRequest().getMethod() + " Request: " + exchange.getRequest().getUri() + "\n" +
//                "Headers: " + exchange.getRequest().getHeaders() + "\n" +
//                "Response status: " + exchange.getResponse().getStatus() + ", Time taken: " + TimeUnit.NANOSECONDS.toMillis(exchange.getTimeTaken().getNano()) + " milliseconds \n" +
//                "Headers: " + exchange.getResponse().getHeaders()
//        );
//
//
//    }
}
