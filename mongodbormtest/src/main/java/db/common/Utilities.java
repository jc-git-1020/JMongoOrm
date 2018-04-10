package db.common;

public final class Utilities {

    public static boolean stringNotNullOrEmpty(final String name, final String value){
        if(value == null || value.isEmpty() )
            throw new IllegalArgumentException(name + "can not be null or empty");
        return true;
    }

}
