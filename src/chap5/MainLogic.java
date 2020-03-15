package chap5;

import utils.Dish;
import utils.Dish.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import utils.SpecialDinner;

public class MainLogic {

  public static void main(String args[]) {
    // Distinct
    List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
    numbers.stream()
        .filter(i -> i % 2 == 0)
        .distinct()
        .forEach(System.out::println);

    // takeWhile
    // 기존 예제
    List<Dish> filteredMenu =
        SpecialDinner.specialMenu.stream()
            .filter(dish -> dish.getCalories() < 320)
            .collect(Collectors.toList());

    System.out.println(filteredMenu);

    // 스페셜 디너는 정렬이 되어있으므로, 특정 조건까지만 돌고 더 안돌도록 takeWhile을 써준다.
    // 근데 자바 9부터라.. 8로 받아서 예제가 안돌아감;; 나중에 9설정하고 돌려보기
    // dropWhile은 320보다 큰걸 찾음 즉 takeWhile의 정반대
//    List<Dish> takeWhileMenu =
//        SpecialDinner.specialMenu.stream()
//          .takeWhile(dish -> dish.getCalories() < 320)
//          .collect(Collectors.toList());

    // limit
    List<Dish> limitedMenu =
        SpecialDinner.specialMenu.stream()
            .filter(dish -> dish.getCalories() > 300)
            .limit(2)
            .collect(Collectors.toList());

    System.out.println(limitedMenu);

    // 정해진 숫자를 건너뛰는 skip
    List<Dish> skipedMenu =
        SpecialDinner.specialMenu.stream()
            .filter(dish -> dish.getCalories() > 300)
            .skip(2)
            .collect(Collectors.toList());

    System.out.println(skipedMenu);

    // 퀴즈
    List<Dish> quizMenu =
        SpecialDinner.specialMenu.stream()
            .filter(dish -> dish.getType() == Type.MEAT)
            .limit(2)
            .collect(Collectors.toList());

    System.out.println(quizMenu);

    // map
    List<Integer> mappedMenu =
        SpecialDinner.specialMenu.stream()
            .filter(dish -> dish.getType() == Type.FISH)
            .map(Dish::getCalories)
            .collect(Collectors.toList());

    System.out.println(mappedMenu);

    List<Integer> dishNameLength =
        SpecialDinner.specialMenu.stream()
            .map(Dish::getName)
            .map(String::length)
            .collect(Collectors.toList());

    System.out.println(dishNameLength);

    // 스트림 평면화
    String[] arrayOfWords = {"Hello", "Witcher"};
    Stream<String> streamOfWords = Arrays.stream(arrayOfWords);

    List<String> words = Arrays.asList("Hello", "World");
//    List<String> arraysStreamWords = words.stream()
//          .map(w -> w.split(""))
//          .map(Arrays::stream)
//          .distinct()
//          .collect(Collectors.toList());
    // -> 에러남

    List<String> uniqueCharacter =
        words.stream()
            .map(word -> word.split(""))
            .flatMap(Arrays::stream)
            .distinct()
            .collect(Collectors.toList());

    System.out.println(uniqueCharacter);

    // 퀴즈 2
    List<Integer> quizNumbers = Arrays.asList(1, 2, 3, 4, 5);

    List<Integer> squareNumbers =
        quizNumbers.stream()
            .map(n -> n * n)
            .collect(Collectors.toList());

    System.out.println(squareNumbers);

    // 검색과 매칭
    // 특정 매칭 확인
    if (SpecialDinner.specialMenu.stream().anyMatch(Dish::isVegetarian)) {
      System.out.println("Vegiterian");
    }

    // 모두 매칭 확인
    if (SpecialDinner.specialMenu.stream().allMatch(dish -> dish.getCalories() < 1000)) {
      System.out.println("Less than 1000");
    }

    // 하나도 아매칭 확인
    if (SpecialDinner.specialMenu.stream().noneMatch(dish -> dish.getCalories() > 1000)) {
      System.out.println("None Match Test");
    }

    // 요소 검색
    Optional<Dish> dish =
        SpecialDinner.specialMenu.stream()
          .filter(Dish::isVegetarian)
          .findAny();
    System.out.println(dish);

    SpecialDinner.specialMenu.stream()
        .filter(Dish::isVegetarian)
        .findAny()
        .ifPresent(dish1 -> System.out.println(dish1.getName()));

    // 첫 번째 요소 찾기
    List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
    Optional<Integer> firstSquareDivisibleByThree =
        someNumbers.stream()
          .map(n -> n * n)
          .filter(n -> n % 3 == 0)
          .findFirst();

    System.out.println(firstSquareDivisibleByThree.toString());

    // Reduce

    int reducedNumber = someNumbers.stream().reduce(0, (a,b) -> a + b);
    System.out.println(reducedNumber);

    Optional<Integer> max = someNumbers.stream().reduce(Integer::max);

    // 기본형 특화 스트림
    int calories =
        SpecialDinner.specialMenu.stream()
          .mapToInt(Dish::getCalories)
          .sum();

    System.out.println(calories);

    // 객체 스트림으로 복원
    IntStream intStream = SpecialDinner.specialMenu.stream().mapToInt(Dish::getCalories);
    Stream<Integer> integerToStream = intStream.boxed();

    // OptionalInt
    OptionalInt maxCalories = SpecialDinner.specialMenu.stream().mapToInt(Dish::getCalories).max();
    int integerMaxCalories = maxCalories.orElse(1);
    System.out.println(integerMaxCalories);

    // 숫자 범위
    IntStream evenNumbers = IntStream.rangeClosed(1, 100)
        .filter(i -> i % 2 == 0);
    System.out.println(evenNumbers.count());

    // 값으로 스트림 만들기
    Stream<String> stringStream = Stream.of("Modern", "Java", "In Action");
    stringStream.map(String::toUpperCase).forEach(System.out::println);
  }
}
