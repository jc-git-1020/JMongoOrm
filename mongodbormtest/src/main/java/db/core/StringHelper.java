package db.core;

public final class StringHelper {

    public static boolean notNullOrEmpty(final String name, final String value) {
        if (value == null || value.isEmpty())
            throw new IllegalArgumentException(name + "can not be null or empty");
        return true;
    }

    //首字母大写
    public static String capitalize(String str){
        if(str.length() == 0)
            return "";
        char[] cs= str.toCharArray();
        if(Character.isLowerCase(cs[0])){
            cs[0]-=32;
            return String.valueOf(cs);
        }else
            return str;
    }

}
