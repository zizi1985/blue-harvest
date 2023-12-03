package com.blueharvest.assignment.utils;

/**
 * @author Z.Eskandari
 * created on 11/26/23
 */
public class Utils {


    public static boolean isNotNull(String s) {
        return !isNull(s);
    }

    public static boolean isNull(String s) {
        if (s == null || s.equals("") || s.toLowerCase().equals("null")) {
            return true;
        }
        return false;
    }

    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    public static boolean isNull(Object obj) {
        if (obj == null)
            return true;
        String str = obj.toString();
        return isNull(str);
    }

    public static boolean isEqual(String s1, String s2) {
        return (!isNull(s1) && !isNull(s2) && s1.equals(s2));
    }


    public static boolean isValidTime(String time) {
        boolean valid = true;
        try {
            valid &= (time.length() == 5);
            valid &= (time.charAt(2) == ':');

            int hh = Integer.parseInt(time.substring(0, 2));
            int mm = Integer.parseInt(time.substring(3));

            valid &= (hh >= 0 && hh < 24);
            valid &= (mm >= 0 && mm < 60);
        } catch (Exception e) {
            valid = false;
        }
        return valid;
    }


    public static boolean isValidDate(String jalaiDate) {
        boolean valid = true;
        try {
            valid &= (jalaiDate.length() == 10);
            valid &= (jalaiDate.charAt(4) == '/');
            valid &= (jalaiDate.charAt(7) == '/');

            int j_y = Integer.parseInt(jalaiDate.substring(0, 4));
            int j_m = Integer.parseInt(jalaiDate.substring(5, 7));
            int j_d = Integer.parseInt(jalaiDate.substring(8));

            valid &= (j_y > 1200 && j_y < 1600);
            valid &= (j_m >= 1 && j_m <= 12);
            valid &= ((j_m >= 1 && j_m <= 6) && (j_d >= 1 && j_d <= 31)) || ((j_m >= 7) && (j_d >= 1 && j_d <= 30));
        } catch (Exception e) {
            valid = false;
        }
        return valid;
    }


}
