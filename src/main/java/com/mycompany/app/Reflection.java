class GenericAccess {
    static Object get(Object o, String k) throws IllegalAccessException, NoSuchFieldException {
        if(o instanceof Map) {
            return ((Map)o).get(k);
        }
        return (o.getClass().getDeclaredField(k).get(o));
    }

    static Object getIn(Object o, List<String> path) throws IllegalAccessException, NoSuchFieldException {
        Object v = o;
        for (String k : path) {
            v = get(v, k);
        }
        return v;
    }

    static Object getIn(Object o, String... path) throws IllegalAccessException, NoSuchFieldException {
        return getIn(o, Arrays.asList(path));
    }

    static String getAsString(Object o, String k) throws IllegalAccessException, NoSuchFieldException {
        return (String)get(o, k);
    }

    static String getInAsString(Object o, List<String> path) throws IllegalAccessException, NoSuchFieldException {
        return (String)getIn(o, path);
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

GenericAccess.get(books, "watchmen");

GenericAccess.getIn(books, List.of("watchmen", "title"));

GenericAccess.getInAsString(library, List.of("catalog", "books", "watchmen", "title")).toUpperCase();


class Person {
    String firstName;
    String lastName;
}

public interface IGetter<T> {
    public T get (Object o) throws IllegalAccessException, NoSuchFieldException ;
}

class Getter <T> implements IGetter <T> {
    private String key;

    public <T> Getter (String k) {
        this.key = k;
    }

    public T get (Object o) throws IllegalAccessException, NoSuchFieldException {
        return (T)(GenericAccess.get(o, key));
    }
}

Getter<String> TITLE = new Getter("title");
TITLE.get(watchmen);

class GetterIn <T> {
    private List<String> path;

    public <T> GetterIn (String... path) {
        this.path = Arrays.asList(path);
    }

    T getIn (Object o) throws IllegalAccessException, NoSuchFieldException {
        return (T)(GenericAccess.getIn(o, path));
    }
}

GetterIn<String> TITLE2 = new GetterIn("catalog", "books", "watchmen", "title");
TITLE2.getIn(library)

