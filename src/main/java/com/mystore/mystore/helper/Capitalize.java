package com.mystore.mystore.helper;

/**
 *
 * @author Purva Saxena
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Capitalize {

    public String capitalize(String str) {

        try {
            str = str.toLowerCase();

            Pattern p = Pattern.compile("(?:^| )[^a-z]*[a-z]");
            Matcher m = p.matcher(str);

            StringBuffer result = new StringBuffer();

            while (m.find()) {
                m.appendReplacement(result, m.group().toUpperCase());
            }

            m.appendTail(result);

            return result.toString();
        } catch (Exception e) {

        }
        return "";
    }

    public Capitalize() {

    }
}
