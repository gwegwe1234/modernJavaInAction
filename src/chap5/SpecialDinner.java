package chap5;

import chap4.Dish;
import java.util.Arrays;
import java.util.List;

public class SpecialDinner {
  public static List<Dish> specialMenu = Arrays.asList(
      new Dish("seasonal fruit", true, 120, Dish.Type.OTHER),
      new Dish("prawns", false, 300, Dish.Type.FISH),
      new Dish("rice", true, 350, Dish.Type.OTHER),
      new Dish("chicken", false, 400, Dish.Type.MEAT),
      new Dish("french fries", true, 530, Dish.Type.OTHER));
}
