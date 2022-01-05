package com.kildeen;

import org.apache.catalina.realm.*;
import org.apache.meecrowave.*;

public class Start {
    public static void main(String[] args) {
        new Meecrowave(new Meecrowave.Builder().
                httpPort(8181).
                tomcatScanning(true).
                tomcatAutoSetup(true).
                realm(new JAASRealm()).
                user("admin", "secret"))
                .bake()
                .await();
    }
}
