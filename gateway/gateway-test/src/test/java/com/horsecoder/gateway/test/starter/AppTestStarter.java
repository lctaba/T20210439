package com.horsecoder.gateway.test.starter;

import java.util.Arrays;
import java.util.HashSet;
import com.horsecoder.gateway.AppStarter;


public class AppTestStarter {

    public static void main(String[] args) {
        HashSet<String> argSet = new HashSet<>(Arrays.asList(args));
        argSet.add("--spring.profiles.active=dev");
        AppStarter.main(argSet.toArray(new String[0]));
    }

}
