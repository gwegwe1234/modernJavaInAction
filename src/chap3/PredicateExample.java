package chap3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PredicateExample {

  // Predicate => boolean return

  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    list.add("A");
    list.add("B");

    List<String> filteredList = filter(list, (String s) -> "A".equals(s));

    System.out.println(filteredList);
  }

  public static <T> List<T> filter(List<T> list, Predicate<T> p) {
    List<T> result = new ArrayList<>();
    for (T t : list) {
      if(p.test(t)) {
        result.add(t);
      }
    }
    return result;
  }
}
