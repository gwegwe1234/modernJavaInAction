package chap4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainLogic {

  public static void main(String args[]) {

    // threeHighCaloricDishNames
    List<String> threeHighCaloricDishNames =
        Dinner.menu.stream()
            .filter(dish -> dish.getCalories() > 300)
            .map(Dish::getName)
            .limit(3)
            .collect(Collectors.toList());
    System.out.println(threeHighCaloricDishNames);

    // one time consume
    List<String> title = Arrays.asList("Modern", "Java", "In", "Action");
    Stream<String> s = title.stream();
    s.forEach(System.out::println);
    // 한번 소비 됐기 때문에 에러 난다.
    //    Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed
    //    at java.util.stream.AbstractPipeline.sourceStageSpliterator(AbstractPipeline.java:279)
    //    at java.util.stream.ReferencePipeline$Head.forEach(ReferencePipeline.java:580)
    //    at chap4.MainLogic.main(MainLogic.java:26)
    //s.forEach(System.out::println);

    // 내부 반복과 외부 반복
    // 외부 반복
    List<String> names = new ArrayList<>();
    for (Dish dish : Dinner.menu) {
      names.add(dish.getName());
    }
    System.out.println(names);

    // iterator를 활용한 외부 반복
    List<String> itNames = new ArrayList<>();
    Iterator<Dish> iterator = Dinner.menu.iterator();
    while (iterator.hasNext()) {
      Dish dish = iterator.next();
      itNames.add(dish.getName());
    }

    System.out.println(itNames);

    // 스트림 내부 반복
    List<String> streamNames = Dinner.menu.stream()
        .map(Dish::getName)
        .collect(Collectors.toList());

    System.out.println(streamNames);

    // 퀴즈
    List<String> highColoricDishes = new ArrayList<>();
    Iterator<Dish> iterator1 = Dinner.menu.iterator();
    while(iterator1.hasNext()) {
      Dish dish = iterator1.next();
      if (dish.getCalories() > 300) {
        highColoricDishes.add(dish.getName());
      }
    }

    System.out.println(highColoricDishes);

    // 퀴즈 답
    List<String> streamHighCaloricDishes = Dinner.menu.stream()
        .filter(dish -> dish.getCalories() > 300)
        .map(Dish::getName)
        .collect(Collectors.toList());

    System.out.println(streamHighCaloricDishes);

    // 쇼트서킷 / 루프 퓨전
    List<String> loggedNames = Dinner.menu.stream()
        .filter(dish -> {
          System.out.println("filtering : " + dish.getName());
          return dish.getCalories() > 300;
        })
        .map(dish -> {
          System.out.println("mapping : " + dish.getName());
          return dish.getName();
        })
        .limit(3)
        .collect(Collectors.toList());

    // 결과가 리밋때문에 3개만 나온다 -> 쇼트서킷 // 필터와 맵핑이 번갈아 일어난다 -> 루프 퓨전
    //    filtering : pork
    //    mapping : pork
    //    filtering : beef
    //    mapping : beef
    //    filtering : chicken
    //    mapping : chicken

    
  }
}
