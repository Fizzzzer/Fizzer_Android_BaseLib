package com.fizzer.doraemon.base.Utils;

import android.text.Html;
import android.text.Spanned;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Fizzer on 2019/5/23.
 * Email: fizzer503@gmail.com
 * Function:
 */

public class StringUtils {
    /**
     * get Unicode Length
     *
     * @param value
     * @return
     */
    public static int getStringUnicodeLength(String value) {
        int valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        // Judge the unicodelength is 1 or 2
        for (int i = 0; i < value.length(); i++) {

            String temp = value.substring(i, i + 1);

            if (temp.matches(chinese)) {

                valueLength += 2;
            } else {

                valueLength += 1;
            }
        }

        return valueLength;
    }

    public static String alignString(String initString, int requireLength) {
        int stringLength = getStringUnicodeLength(initString);
        String transString = initString;
        try {
            if (requireLength > stringLength)
                for (int i = 0; i < (requireLength - stringLength); i++) {
                    transString = transString + "  ";
                }
            // if ((requireLength < stringLength)&&requireLength>=2) {
            // transString = initString.substring(0, requireLength/2 - 1) +
            // "..";
            // }
            // transString=String.format("%-12s",initString);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return transString;
    }

    public static String alignStrings(String initString, int requireLength) {
        int stringLength = getStringUnicodeLength(initString);
        String transString = initString;
        try {
            if (requireLength > stringLength)
                for (int i = 0; i < (requireLength - stringLength); i++) {
                    transString = transString + "  ";
                }
            if ((requireLength < stringLength) && requireLength >= 2) {
                transString = initString.substring(0, requireLength - 1) + "..";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transString;
    }

    public static String inputStreamToString(InputStream inputStream) {
        StringBuilder total = new StringBuilder();
        try {
            BufferedReader r = new BufferedReader(new InputStreamReader(
                    inputStream));

            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
        } catch (IOException e) {
        }
        return total.toString();
    }

    public static String decodeHtml(String str) {

        String rst = str;

        rst = rst.replaceAll("&lt;", "<");
        rst = rst.replaceAll("&gt;", ">");
        rst = rst.replaceAll("&quot;", "\"");
        rst = rst.replaceAll("&amp;", "&");

        return rst;

    }

    public static String SHA1(String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            return toHexString(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String toHexString(byte[] keyData) {
        if (keyData == null) {
            return null;
        }
        int expectedStringLen = keyData.length * 2;
        StringBuilder sb = new StringBuilder(expectedStringLen);
        for (int i = 0; i < keyData.length; i++) {
            String hexStr = Integer.toString(keyData[i] & 0x00FF, 16);
            if (hexStr.length() == 1) {
                hexStr = "0" + hexStr;
            }
            sb.append(hexStr);
        }
        return sb.toString();
    }

    /**
     * @param str
     * @return if string is null or its size is 0 or it is made by space, return
     * true, else return false.
     */
    public static boolean isBlank(String str) {
        return (str == null || str.trim().length() == 0);
    }

    public static boolean ifStringInList(String s, ArrayList<String> arrayList) {
        if (judgeNotNull(s) && judgeNotNull(arrayList)) {
            for (String str : arrayList) {
                if (str.trim().contains(s))
                    return true;
            }
        }

        return false;
    }

    public static boolean ifStringExactlyInList(String s,
                                                ArrayList<String> arrayList) {
        if (judgeNotNull(s) && judgeNotNull(arrayList)) {
            for (String str : arrayList) {
                if (str.equals(s))
                    return true;
            }
        }

        return false;
    }

    /**
     * md5
     *
     * @param str
     * @return
     */
    public static String getMD5Str(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(
                        Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }

        return md5StrBuff.toString();
    }

    /**
     */
    public static Spanned getSpanned(String s) {
        Spanned spanned = Html.fromHtml(s);
        return spanned;
    }

    public static boolean isNull(String s) {
        return s == null;
    }

    public static String addZero(int n) {
        if (n < 10)
            return "0" + n;
        return "" + n;
    }

    /**
     * @param s
     * @return
     */
    public static boolean isNotEmpty(String s) {
        return s != null && !"".equals(s.trim()) && !"null".equals(s.trim())
                && !"NULL".equals(s.trim());
    }

    /**
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        return s == null || "".equals(s.trim());
    }


    /**
     * @param string
     * @return
     * @see #judgeNotNull(String, String...)
     */
    public static boolean judgeNotNull(String string) {
        // return string != null && !string.equals("") && !string.equals("null") ? true : false;

        return judgeNotNull(string, new String[0]);
    }

    /**
     * Judge if a variable of String or String[] is null or ""
     *
     * @param string
     * @param strings
     * @return
     */
    public static boolean judgeNotNull(String string, String... strings) {
        boolean flag = true;
        if (!(string != null && string.trim().length() > 0 && !string.equals("null") && !string.trim().equals("")))
            return false;
        for (String s : strings) {
            if (s == null || string.trim().length() == 0 || s.equals("null")) {
                flag = false;
                break;
            }
        }

        return flag;
    }

    public static boolean judgeNotNull(byte[] bytes) {
        return bytes != null && bytes.length >= 1;
    }

    public static boolean judgeNotNull(Map<String, String> map) {
        return map != null && map.size() > 0 ? true : false;
    }

    public static boolean judgeNotNull(List<?> list) {
        return list != null && list.size() > 0 ? true : false;
    }

    public static boolean judgeNotNull(List<?> list, List<?>... lists) {
        boolean flag = true;
        if (list == null || list.size() == 0) return false;
        if (judgeNotNull(lists))
            for (List<?> l : lists) {
                if (l == null || l.size() == 0) {
                    flag = false;
                    return false;
                }
            }
        return flag;
    }

    public static boolean judgeNotNull(Object object) {
        return judgeNotNull(object, new Object[0]);
    }

    public static boolean judgeNotNull(Object object, Object... objects) {
        boolean flag = true;
        if (object == null) return false;
        for (Object o : objects) {
            if (o == null) {
                flag = false;
                return flag;
            }
        }

        return flag;
    }

    public static String substring(String str, String subStr) {
        String subResultStr = null;
        String[] splits = str.split("&");
        for (String s : splits) {
            if (s.contains(subStr)) {
                String[] splits_n = s.split("=");
                if (splits_n.length > 1) {
                    subResultStr = splits_n[1];
                }

            }
        }

        return subResultStr;
    }

    public static String replace(String str, String subStr, String replaceStr) {
        StringBuilder sb = new StringBuilder();
        String[] splits = str.split("&");
        if (replaceStr == null) {
            return null;
        }
        int length = splits.length;
        for (int index = 0; index < length; index++) {
            if (splits[index].contains(subStr)) {
                splits[index] = replaceStr;
            }
            sb.append(splits[index]).append("&");
        }
        if (sb.length() < 1) {
            return null;
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }


}
