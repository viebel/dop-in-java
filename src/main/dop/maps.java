import java.util.AbstractMap.*;
import org.pcollections.*
import java.util.stream.*;
import com.github.underscore.*;

import org.organicdesign.fp.collections.*;
import org.organicdesign.fp.function.*;
import org.organicdesign.fp.tuple.*;
import static org.organicdesign.fp.StaticImports.*;



// create an immutable map from data
PMap<String, Integer> coordinates = HashTreePMap.from(Map.of("x", 1, "y", 2));

// transform the values of a map
coordinates.entrySet().stream().collect(Collectors.toUnmodifiableMap(e-> e.getKey(), e -> 100 * e.getValue()));


// Using underscore
U.toMap(U.map(coordinates.entrySet(), x -> new SimpleImmutableEntry<>(x.getKey(), 100 * x.getValue())));


// Paguro
ImList<Tuple3<String,String,ImList<String>>> emails =
    vec(tup("Fred", vec("fred@gmail.com", "fred@hello.com")), tup("Jane", vec("jane@gmail.com", "jane@hello.com")));

vec(tup("Fred", vec("fred@gmail.com", "fred@hello.com")),
    tup("Jane", vec("jane@gmail.com", "jane@hello.com")))
    .flatMap(person -> person._2()
             .map(email -> tup(email, person._1())))
    .toImMap(x -> x)
