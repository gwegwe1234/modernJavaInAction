package chap6;

import static java.util.stream.Collectors.partitioningBy;

import chap4.Dinner;
import chap4.Dish;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ToListCollectorMain {

  public static void main(String args[]) {
    List<Dish> dishes = Dinner.menu.stream().collect(new ToListCollector<Dish>());

    System.out.println(dishes);

    List<Dish> dishes2 = Dinner.menu.stream().collect(
        ArrayList::new,
        List::add,
        List::addAll);

    // partioningBy 커스텀 컬렉터 예제
    System.out.println(partitionPrimesWithCustomCollector(100));
  }

  public static boolean isPrime(List<Integer> primes, int candidate) {
    int candidateRoot = (int) Math.sqrt((double) candidate);
    return takeWhile(primes, i -> i <= candidateRoot)
        .stream()
        .noneMatch(p -> candidate % p == 0);
  }

  public static <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
    int i = 0;
    for (A item : list) {
      if (!p.test(item)) {
        return list.subList(0, i);
      }
      i++;
    }
    return list;
  }



  public static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
    return IntStream.rangeClosed(2, n).boxed()
          .collect(new PrimeNumbersCollector());
  }

  public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
    return IntStream.rangeClosed(2, n).boxed()
        .collect(
            partitioningBy(candidate -> MainLogic.isPrime(candidate)));
  }
}
