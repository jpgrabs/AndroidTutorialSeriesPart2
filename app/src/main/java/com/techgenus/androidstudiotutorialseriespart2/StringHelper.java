package com.techgenus.androidstudiotutorialseriespart2;

import android.annotation.SuppressLint;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringHelper {
    private static final String TAG = "StringHelper";

    public static String capWords(String value) {
        StringBuilder word = new StringBuilder();
        String[] splitString = value.split(" ");
        int index = 0;
        for (String val : splitString) {
            StringBuilder strBuilder =  word.append(capitalize(val));
            if (index < splitString.length - 1) strBuilder.append(" ");
            index++;
        }
        return word.toString();
    }

    public static String capitalize(String value) {
        return value.substring(0, 1).toUpperCase() +
                value.substring(1).toLowerCase();
    }

    private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (byte b : data) {
            int halfbyte = (b >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                halfbyte = b & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static String converToSHA512(String text)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        byte[] hash = md.digest();
        return convertToHex(hash);
    }
    ///just click alt enter for quick import library
    @SuppressLint("SimpleDateFormat")
    public static String parseDate(String inputFormat, String outputFormat, String date) {
        String output = "";
        SimpleDateFormat inFormat = new SimpleDateFormat(inputFormat);
        SimpleDateFormat outFormat = new SimpleDateFormat(outputFormat);
        try {
            Date d = inFormat.parse(date);
            output = outFormat.format(d);
        } catch (ParseException e) {
            Log.e(TAG, e.getMessage());
        }
        return output;
    }
}
