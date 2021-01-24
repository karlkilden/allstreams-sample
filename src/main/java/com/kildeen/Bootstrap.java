package com.superbiz.jaxrs;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Bootstrap {





    @PostConstruct
    public void init () {
        System.out.println("I am alive!");
    }
}
