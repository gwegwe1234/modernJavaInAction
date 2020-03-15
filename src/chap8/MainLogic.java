package chap8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import utils.Dinner;
import utils.Dish;

public class MainLogic {
  public static void main(String args[]) {
    Set<String> friendsSet = new HashSet<>(Arrays.asList("Ted", "Sunny", "Cindy"));
    Set<String> friends = Stream.of("Ted", "Sunny", "Cindy").collect(Collectors.toSet());

    // 리스트 팩토리
    // 자바 9

    // removeIf
    List<Dish> dishes = Dinner.menu;
//    for (Dish dish : dishes) {
//      if (dish.getCalories() < 500) {
//        dishes.remove(dish);
//      }
//    }
//
//    System.out.println(dishes);

//    dishes.removeIf(dish -> dish.getCalories() < 500);
//    System.out.println(dishes);

    dishes.stream().map(dish -> Character.toUpperCase(dish.getName().charAt(0)) + dish.getName().substring(1))
        .collect(Collectors.toList())
        .forEach(System.out::println);

    List<String> friendsList = Arrays.asList("ted", "sunny", "cindy");
    friendsList.replaceAll(friend -> Character.toUpperCase(friend.charAt(0)) + friend.substring(1));
    System.out.println(friendsList);

    Map<String, String> studyMembers = new HashMap<>();
    studyMembers.put("1", "Ted");
    studyMembers.put("2", "Sunny");
    studyMembers.put("3", "Cindy");

    studyMembers.entrySet().stream().sorted(Entry.comparingByKey()).forEachOrdered(System.out::println);

    Map<String, String> studyMembers2 = new HashMap<>();
    studyMembers.put("1", "Ted");
    studyMembers.put("2", "Sunny");
    studyMembers.put("3", "Cindy");

    System.out.println(studyMembers2.getOrDefault("4", "Jade"));

    // 계산 패턴
    Map<String, String> sunhoMovies = new HashMap<>();
    sunhoMovies.put("Raphael", "Star wars");
    sunhoMovies.put("Christine", "Star wars2");

    sunhoMovies.replaceAll((firend, movie) -> movie.toUpperCase());
    System.out.println(sunhoMovies);
  }

}
