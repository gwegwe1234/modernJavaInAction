package chap6;

import static chap6.ToListCollectorMain.partitionPrimes;

public class CollectorHarness {
  public static void main(String[] args) {
    long fastest = Long.MAX_VALUE;
    for (int i = 0; i < 1; i++) {
      long start = System.nanoTime();
      partitionPrimes(1_000_000);
      long duration = (System.nanoTime() - start) / 1_000_000;
      if (duration < fastest) fastest = duration;
    }
    System.out.println(
        "Fastest execution done in " + fastest + " msecs");
  }
}

