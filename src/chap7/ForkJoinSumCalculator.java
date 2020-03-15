package chap7;

import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {

  private final long[] numbers;
  private final int start;
  private final int end;
  private static final long THRESHOLD = 10_000; // 이 값 이하의 서브태스크는 분리 불가능

  public ForkJoinSumCalculator(long[] numbers) {
    this(numbers, 0, numbers.length);
  }

  private ForkJoinSumCalculator(long[] numbers, int start, int end) {
    this.numbers = numbers;
    this.start = start;
    this.end = end;
  }

  @Override
  protected Long compute() {
    int length = end - start;
    if (length <= THRESHOLD) {
      return computeSequentially();
    }

    ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
    leftTask.fork();

    ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
    rightTask.fork();

    Long rightResult = rightTask.compute();
    Long leftResult = leftTask.join(); // 첫 번째 서브태스크의결과를 읽거나 아직 결과가 없으면 기다린다.

    return leftResult + rightResult;
  }

  private long computeSequentially() {
    long sum = 0;
    for (int i = 0; i < end; i++) {
      sum += numbers[i];
    }

    return sum;
  }
}
