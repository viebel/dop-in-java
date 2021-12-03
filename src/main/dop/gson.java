import com.google.gson.*;
var gson = new Gson();

public class Book {
  public final String isbn;
  public final String title;
  public final Integer publicationYear;
  public Book (
    String isbn, 
    String title, 
    Integer publicationYear) {
      this.isbn = isbn;
      this.title = title;
      this.publicationYear = publicationYear;
    }
}

var watchmenRecord = new Book(
  "978-1779501127",
  "Watchmen",
  1987
);

var sevenHabitsRecord = new Book(
    "978-1982137274",
    "7 Habits of Highly Effective People",
    2020
);


System.out.println(gson.toJson(sevenHabitsRecord));
// {"isbn":"978-1982137274","title":"7 Habits of Highly Effective People","publicationYear":2020}



public class BookAttributes {
  public Integer numberOfPages;
  public String language; 
  public BookAttributes(Integer numberOfPages, String language) {
    this.numberOfPages = numberOfPages;
    this.language = language;
  }
}

public class BookWithAttributes {
  public String isbn;
  public String title;
  public Integer publicationYear;
  public BookAttributes attributes;
  public BookWithAttributes (
    String isbn, 
    String title, 
    Integer publicationYear,
    Integer numberOfPages,
    String language) {
      this.isbn = isbn;
      this.title = title;
      this.publicationYear = publicationYear;
      this.attributes = new BookAttributes(numberOfPages, language);
    }
}

var sevenHabitsNestedRecord = new BookWithAttributes(
    "978-1982137274",
    "7 Habits of Highly Effective People",
    2020,
    432,
    "en"
);

System.out.println(gson.toJson(sevenHabitsNestedRecord));
// {"isbn":"978-1982137274","title":"7 Habits of Highly Effective People","publicationYear":2020,"attributes":{"numberOfPages":432,"language":"en"}}



Map searchResultsRecords = Map.of(
  "978-1779501127", new Book(
    "978-1779501127",
    "Watchmen",
    1987
  ),
  "978-1982137274", new Book(
    "978-1982137274",
    "7 Habits of Highly Effective People",
    2020
  )
);

System.out.println(gson.toJson(searchResultsRecords));
// {"978-1779501127":{"isbn":"978-1779501127","title":"Watchmen","publicationYear":1987},"978-1982137274":{"isbn":"978-1982137274","title":"7 Habits of Highly Effective People","publicationYear":2020}}







