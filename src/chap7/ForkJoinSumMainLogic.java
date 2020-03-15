package chap7;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

public class ForkJoinSumMainLogic {

  public static void main(String[] args) {
    long[] numbers = LongStream.rangeClosed(1, 100L).toArray();
    ForkJoinSumCalculator task = new ForkJoinSumCalculator(numbers);
    System.out.println( new ForkJoinPool().invoke(task));
  }
}
