package db.core;

public final class StringHelper {

    public static boolean notNullOrEmpty(final String name, final String value) {
        if (value == null || value.isEmpty())
            throw new IllegalArgumentException(name + "can not be null or empty");
        return true;
    }

}
