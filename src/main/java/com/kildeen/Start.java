package com.kildeen;

import org.apache.catalina.realm.*;
import org.apache.meecrowave.*;

public class Start {
    public static void main(String[] args) {
        new Meecrowave(new Meecrowave.Builder() {{
            setHttpPort(8181);
            setTomcatScanning(true);
            setTomcatAutoSetup(true);
            setRealm(new JAASRealm());
            user("admin", "secret");
        }})

                .bake()
                .await();
    }
}
