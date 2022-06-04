package com.kildeen.allstreams;

import javax.annotation.*;
import javax.enterprise.context.*;

@ApplicationScoped
public class Bootstrap {
    @PostConstruct
    public void init () {
        System.out.println("I am alive!");
    }
}
