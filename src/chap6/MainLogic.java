package chap6;

import static java.util.Arrays.asList;

import chap4.Dinner;
import chap4.Dish;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainLogic {

  public static void main(String args[]) {
    long howManyDishes = Dinner.menu.stream().collect(Collectors.counting());
    System.out.println(howManyDishes);

    long howManyDishes2 = Dinner.menu.stream().count();
    System.out.println(howManyDishes2);

    // 최댓값
    Comparator<Dish> dishCalories =
        Comparator.comparingInt(Dish::getCalories);

    Optional<Dish> mostCalorieDish =
        Dinner.menu.stream().collect(Collectors.maxBy(dishCalories));

    System.out.println(mostCalorieDish);

    // 총합 요약
    int totalCalories = Dinner.menu.stream().collect(Collectors.summingInt(Dish::getCalories));

    System.out.println(totalCalories);

    // 평균
    double avgCalories =
        Dinner.menu.stream().collect(Collectors.averagingInt(Dish::getCalories));

    System.out.println(avgCalories);

    // 문자열 연결
    String shortMenu = Dinner.menu.stream().map(Dish::getName).collect(Collectors.joining());

    System.out.println(shortMenu);

//    String shortMenu2 = Dinner.menu.stream().collect(Collectors.joining());

    String shortMenuByComma = Dinner.menu.stream().map(Dish::getName)
        .collect(Collectors.joining(", "));

    System.out.println(shortMenuByComma);

    // reducing 사용
    int totalCaloriesByReducing = Dinner.menu.stream()
        .collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));

    // 1 parameter reducing
    Optional<Dish> mostCalorieDishByReducing =
        Dinner.menu.stream().collect(
            Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));

    System.out.println(mostCalorieDishByReducing);

    int totalCaloriesByReducingCollectors =
        Dinner.menu.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));

    // 그룹핑
    Map<Dish.Type, List<Dish>> dishesByType =
        Dinner.menu.stream().collect(Collectors.groupingBy(Dish::getType));

    System.out.println(dishesByType);

    Map<CaloricLevel, List<Dish>> dishesByCaloricLevel =
        Dinner.menu.stream().collect(Collectors.groupingBy(dish -> {
          if (dish.getCalories() < 400) {
            return CaloricLevel.DIET;
          } else if (dish.getCalories() < 700) {
            return CaloricLevel.NORMAL;
          } else {
            return CaloricLevel.FAT;
          }
        }));

    System.out.println(dishesByCaloricLevel);

    // 요소 그루핑
    Map<Dish.Type, List<Dish>> caloricDishesByType =
        Dinner.menu.stream().filter(dish -> dish.getCalories() > 500)
            .collect(Collectors.groupingBy(Dish::getType));

    System.out.println(caloricDishesByType);

    // 요소 그루핑 2

//    Map<Dish.Type, List<Dish>> caloricDishesByFiltering =
//        Dinner.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.filtering(dish -> dish.getCalories > 500, Collectors.toList()));

    Map<String, List<String>> dishTags = new HashMap<>();
    dishTags.put("pork", asList("greasy", "salty"));
    dishTags.put("beef", asList("salty", "roasted"));
    dishTags.put("chicken", asList("fried", "crisp"));
    dishTags.put("french fries", asList("greasy", "fried"));
    dishTags.put("rice", asList("light", "natural"));
    dishTags.put("season fruit", asList("fresh", "natural"));
    dishTags.put("pizza", asList("tasty", "salty"));
    dishTags.put("prawns", asList("tasty", "roasted"));
    dishTags.put("salmon", asList("delicious", "fresh"));

    Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel =
        Dinner.menu.stream().collect(
            Collectors.groupingBy(Dish::getType,
                Collectors.groupingBy(dish -> {
                  if (dish.getCalories() < 400) {
                    return CaloricLevel.DIET;
                  } else if (dish.getCalories() < 700) {
                    return CaloricLevel.NORMAL;
                  } else {
                    return CaloricLevel.FAT;
                  }
                }))
        );

    System.out.println(dishesByTypeCaloricLevel);

    Map<Dish.Type, Long> typesCount =
        Dinner.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));

    System.out.println(typesCount);

    Map<Dish.Type, Optional<Dish>> mostCaloricByType =
        Dinner.menu.stream().collect(Collectors.groupingBy(Dish::getType,
            Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));

    System.out.println(mostCaloricByType);

    Map<Dish.Type, Dish> mostCaloricByTypeAndthen =
        Dinner.menu.stream().collect(
            Collectors.groupingBy(Dish::getType, Collectors.collectingAndThen(
                Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                Optional::get
            ))
        );

    System.out.println(mostCaloricByTypeAndthen);

    Map<Dish.Type, Integer> totalCaloriesByType =
        Dinner.menu.stream()
            .collect(
                Collectors.groupingBy(Dish::getType, Collectors.summingInt(Dish::getCalories)));

    Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =
        Dinner.menu.stream()
            .collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(dish -> {
              if (dish.getCalories() < 400) {
                return CaloricLevel.DIET;
              } else if (dish.getCalories() < 700) {
                return CaloricLevel.NORMAL;
              } else {
                return CaloricLevel.FAT;
              }
            }, Collectors.toSet())));

    System.out.println(caloricLevelsByType);

    Map<Boolean, List<Dish>> partitionedMenu =
        Dinner.menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));

    System.out.println(partitionedMenu);

    List<Dish> vegetarianDishes = partitionedMenu.get(true);

    System.out.println(vegetarianDishes);

    Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType =
        Dinner.menu.stream().collect(
            Collectors.partitioningBy(Dish::isVegetarian, Collectors.groupingBy(Dish::getType))
        );

    System.out.println(vegetarianDishesByType);

    Map<Boolean, List<Integer>> partitionPrimes =
        IntStream.rangeClosed(2, 100).boxed().collect(Collectors.partitioningBy(candidate -> isPrime(candidate)));

    System.out.println(partitionPrimes);
  }

  public enum CaloricLevel {DIET, NORMAL, FAT}

  public static boolean isPrime(int candidate) {
    return IntStream.range(2, candidate).noneMatch(i -> candidate % i == 0);
  }

}
