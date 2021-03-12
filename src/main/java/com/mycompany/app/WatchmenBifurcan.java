import io.lacuna.bifurcan.List;
import io.lacuna.bifurcan.Map;
import io.lacuna.bifurcan.Value;

var authorIds = new List().addLast("alan-moore").addLast("dave-gibbons");
var watchmen = new Map().put("title", "Watchmen").put("pulicationYear", 1987).put("authorIds", authorIds);

var books = new Map().put("978-1779501127", watchmen);
var alan = new Map().put("id", "alan-moore").put("name", "Alan Moore");
var dave = new Map().put("id", "dave-gibbons").put("name", "Dave Gibbons");

var authors = new Map().put("alan-moore", alan).put("dave-gibbons", dave);

var catalog = new Map().put("booksByIsbn", books).put("authorsById", authors);

Value.wrap(catalog)
    .get("booksByIsbn")
    .get("978-1779501127")
    .get("authorIds")
    .nth(0)
    .<String>as()
    .toUpperCase();







var authorIds = new List().addLast("alan-moore")
    .addLast("dave-gibbons");
var watchmen = new Map().put("title", "Watchmen")
    .put("authorIds", authorIds);
var books = new Map().put("978-1779501127", watchmen);

Value.wrap(books)
    .get("978-1779501127")
    .get("authorIds")
    .nth(0)
    .<String>as()
    .toUpperCase(); // "ALAN-MOORE"


