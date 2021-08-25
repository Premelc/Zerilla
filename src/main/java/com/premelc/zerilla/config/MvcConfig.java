package com.premelc.zerilla.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/register").setViewName("/action/register");
        registry.addViewController("/login").setViewName("/action/login");
        registry.addViewController("/events").setViewName("events/discover");
        registry.addViewController("/host").setViewName("events/host");
        registry.addViewController("/specific_event_added").setViewName("events/discover");
    }

}