package chap7;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class MainLogic {

  public static void main(String args[]) {

  }

  // stream 방식 두 수의 합
  public static long sequentialSum(long n) {
    return Stream.iterate(1L, i -> i + 1)
        .limit(n)
        .reduce(0L, Long::sum);
  }

  // 기존 자바 방식 두 수의 합
  public static long iterativeSum(long n) {
    long result = 0;
    for (long i = 1L; i <= n; i++) {
      result += i;
    }

    return result;
  }

  // 병렬 스트림 방식
  public static long parallelSum(long n) {
    return Stream.iterate(1L, i -> i + 1)
        .limit(n)
        .parallel()
        .reduce(0L, Long::sum);
  }

  public long sideEffectSum(long n) {
    Accumulator accumulator = new Accumulator();
    LongStream.rangeClosed(1, n).forEach(accumulator::add);
    return accumulator.total;
  }

  public class Accumulator {
    public long total = 0;
    public void add(long value) {
      total += value;
    }
  }

  public long sideEffectParallelSum(long n) {
    Accumulator accumulator = new Accumulator();
    LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
    return accumulator.total;
  }
}
