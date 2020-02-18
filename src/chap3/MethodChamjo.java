package chap3;

import static chap3.FunctionExample.map;
import static chap3.PredicateExample.filter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class MethodChamjo {
  public static void main(String[] args) {
    Predicate<Integer> num = (Integer i) -> Integer.parseInt("3") == i;
    filter(Arrays.asList(1,2,3), num);

    List<String> str = Arrays.asList("a", "f", "c", "d");
    str.sort(String::compareToIgnoreCase);
    str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));

    System.out.println(str);

    ToIntFunction<String> stringToInt = (String s) -> Integer.parseInt(s);
    ToIntFunction<String> stringToIntCham = Integer::parseInt;

    BiPredicate<List<String>, String> contains = (list, element) -> list.contains(element);
    BiPredicate<List<String>, String> contains2 = List::contains;


    // 아래 두개는 같은 로직
    Supplier<Apple> c1 = Apple::new;
    Apple a1 = c1.get();

    Supplier<Apple> c2 = () -> new Apple();
    Apple a2 = c2.get();

    // 인자가 있는 생성자
    Function<Integer, Apple> c3 = Apple::new;
    Apple a3 = c3.apply(100);

    Function<String, Apple> c4 = Apple::new;
    Apple a4 = c4.apply("Red");

    Function<Integer, Apple> c5 = (Integer i) -> new Apple(i);
    Apple a5 = c5.apply(100);

    Function<String, Apple> c6 = (String s) -> new Apple(s);
    Apple a6 = c6.apply("Red");

    // map 을 활용해서 Apple 생성자 정의
    List<Integer> weights = Arrays.asList(7,3,1,5,6);
    List<Apple> apples = map(weights, c3);
    for (Apple apple : apples) {
      System.out.println(apple.getWeight());
    }

    List<Apple> apples2 = map(weights, Apple::new);
    for (Apple apple : apples2) {
      System.out.println(apple.getWeight());
    }

    List<Apple> apples3 = map(weights, (weight) -> new Apple(weight));
    for (Apple apple : apples3) {
      System.out.println(apple.getWeight());
    }

    // 인자가 2개인 생성자
    BiFunction<Integer, String, Apple> c7 = Apple::new;
    Apple a7 = c7.apply(100, "red");

    BiFunction<Integer, String, Apple> c8 = (weight, color) -> new Apple(weight, color);
    Apple a8 = c8.apply(200, "blue");

    // 맵 활용
    Map<String, Function<Integer, Apple>> map = new HashMap<>();
    map.put("redApple", Apple::new);

    map.get("redApple").apply(100);
  }

}