package com.example.moviedemo.common;

public class Utils {

    public static boolean checkRatingRange(final double value) {
        double MAX = 5.0;
        double MIN = 0.5;

        if(value <= MAX && value >=MIN) {
            return true;
        }

        return false;
    }
}
