package com.feiyahan.hanfei.service.akka;

import javax.inject.Named;

/**
 * A simple service that can increment a number.
 */
@Named
public class CountingService {
    /**
     * Increment the given number by one.
     */
    public String increment(int count) {
        return "child 2 result "+count;
    }
}