import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dynamic {
    // Written by Mike Simmons https://coderanch.com/t/740716/engineering/Data-Oriented-programming-applicable-Java#3443672

    private Dynamic() {}

    public static <T> T get(Object obj, String... path) {
        int index = 0;
        Object v = obj;
        try {
            while (index < path.length) {
                if (v == null) {
                    return null;
                }
                v = access(v, path[index]);
                index++;
            }
            @SuppressWarnings("unchecked") T cast = (T) v;
            return cast;
        } catch (RuntimeException | NoSuchFieldException | IllegalAccessException e) {
            throw new Dynamic.AccessException("Dynamic access error for path " + Arrays.toString(path)
                    + " at element " + index + " (" + path[index] + ") on object " + obj
                    + " with preceding element " + v + " (class " + v.getClass() + ")", e);
        }
    }

    private static Object access(Object obj, String key) throws NoSuchFieldException, IllegalAccessException {
        if (obj instanceof Map) {
            return ((Map<?,?>) obj).get(key);
        }
        Field field = obj.getClass().getDeclaredField(key);
        field.setAccessible(true);
        return field.get(obj);
    }

    public static <T> List<T> getList(Object obj, String... path) { return get(obj, path); }
    public static <T> Set<T> getSet(Object obj, String... path) { return get(obj, path); }
    public static <K,V> Map<K,V> getMap(Object obj, String... path) { return get(obj, path); }
    public static String getString(Object obj, String... path) { return get(obj, path); }

    public static byte getByte(Object obj, String... path) { return ifNull(get(obj, path), (byte) 0); }
    public static short getShort(Object obj, String... path) { return ifNull(get(obj, path), (short) 0); }
    public static int getInt(Object obj, String... path) { return ifNull(get(obj, path), 0); }
    public static long getLong(Object obj, String... path) { return ifNull(get(obj, path), 0L); }
    public static float getFloat(Object obj, String... path) { return ifNull(get(obj, path), 0.0F); }
    public static double getDouble(Object obj, String... path) { return ifNull(get(obj, path), 0.0); }
    public static boolean getBoolean(Object obj, String... path) { return ifNull(get(obj, path), false); }

    public static <T> T getOr(T elseValue, Object obj, String... path) { return ifNull(get(obj, path), elseValue); }
    public static byte getByteOr(byte elseValue, Object obj, String... path) { return ifNull(get(obj, path), elseValue); }
    public static short getShortOr(short elseValue, Object obj, String... path) { return ifNull(get(obj, path), elseValue); }
    public static int getIntOr(int elseValue, Object obj, String... path) { return ifNull(get(obj, path), elseValue); }
    public static long getLongOr(long elseValue, Object obj, String... path) { return ifNull(get(obj, path), elseValue); }
    public static float getFloatOr(float elseValue, Object obj, String... path) { return ifNull(get(obj, path), elseValue); }
    public static double getDoubleOr(double elseValue, Object obj, String... path) { return ifNull(get(obj, path), elseValue); }
    public static boolean getBooleanOr(boolean elseValue, Object obj, String... path) { return ifNull(get(obj, path), elseValue); }

    private static <T> T ifNull(T nullable, T elseValue) { return nullable != null ? nullable : elseValue; }

    public static class AccessException extends RuntimeException {
        public AccessException() {}
        public AccessException(String message) { super(message); }
        public AccessException(String message, Throwable cause) { super(message, cause); }
        public AccessException(Throwable cause) { super(cause); }
    }
}

class Book {
    public String title;
}

class Catalog {
    public Map<String, Book> books;
}

class Library {
    public Catalog catalog;
}


var watchmen = new Book(); watchmen.title = "Watchmen";
var books = Map.of("watchmen", watchmen);
var catalog = new Catalog(); catalog.books = books;
var library = new Library(); library.catalog = catalog;

String  foo() {
    // It compiles!
    return Dynamic.get(library, "catalog", "books", "watchmen", "title");
}
