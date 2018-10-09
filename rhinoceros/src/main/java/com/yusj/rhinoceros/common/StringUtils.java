package com.yusj.rhinoceros.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringUtils {

    private StringUtils() {
        /** non instantiable and non inheritable **/
    }

    /**
     * This method returns an immutable List<String>, but different from String's split()
     * it trims the results in the input String, and removes any empty string from
     * the resulting List.
     */
    public static List<String> split(String value, String separator) {
        String[] splits = value.split(separator);
        List<String> results = new ArrayList<String>();
        for (int i = 0; i < splits.length; i++) {
            splits[i] = splits[i].trim();
            if (splits[i].length() > 0) {
                results.add(splits[i]);
            }
        }
        return Collections.unmodifiableList(results);
    }

    /**
     * This method takes a List<String> and a delimiter and joins the strings
     * into a single string, where the original strings are separated using
     * the given delimiter.
     */
    public static String joinStrings(List<String> list, String delim) {
        if (list == null)
            return null;

        StringBuilder builder = new StringBuilder(list.get(0));
        for (String s : list.subList(1, list.size())) {
            builder.append(delim).append(s);
        }

        return builder.toString();
    }
}
