package com.pk.data_refinery_simulator.util;

import java.util.Random;

public final class RandomUtils {
    final static Random random = new Random();

    public static String randomNumericString(int length){


        StringBuilder builder = new StringBuilder();

        for(int i=0; i<length; i++)
        {
            builder.append(random.nextInt(10));
        }

        return builder.toString();
    }

    public static int randomInteger(int min, int max) {
    //Specifically being used for port numbers where min=1024, max=65535
        if (min > max) {
            throw new IllegalArgumentException(
                    "Minimum value cannot be greater than maximum value."
            );
        }
        return random.nextInt(max - min + 1) + min;
        // nextInt(n) generates values from 0 to n-1, so +1 includes the maximum value.
    }
}