package chap5;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Practice {

  public static void main(String args[]) {
    Customer customer = new Customer();
    // 1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오.
    List<Transaction> tr11 =
        customer.transactions.stream()
          .filter(transaction -> transaction.getYear() == 2011)
          .sorted(Comparator.comparing(Transaction::getValue))
          .collect(Collectors.toList());

    System.out.println(tr11);

    // 2. 거래자가 근무하는 모든 도시를 중복 없이 나열하시오.
    List<String> livedCities =
        customer.transactions.stream()
          .map(transaction -> transaction.getTrader().getCity())
          .distinct()
          .collect(Collectors.toList());

    System.out.println(livedCities);

    // 3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오.
    List<Trader> workingInCambridegePeople =
        customer.transactions.stream()
          .map(transaction -> transaction.getTrader())
          .filter(trader -> trader.getCity().equals("Cambridge"))
          .sorted(Comparator.comparing(Trader::getName))
          .collect(Collectors.toList());

    System.out.println(workingInCambridegePeople);

    // 4. 모든 거래자의 이름을 알파벳 순으로 정렬해서 반환하시오.
    String traderName =
        customer.transactions.stream()
          .map(transaction -> transaction.getTrader().getName())
          .distinct()
          .sorted()
          .reduce("", (n1, n2) -> n1 + n2);

    System.out.println(traderName);

    // 5. 밀라노에 거래자가 있는가?
    boolean isMilanTrader =
        customer.transactions.stream()
          .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));

    System.out.println(isMilanTrader);

    // 6. 케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오.
    List<Integer> livedInCambridgeTraderTransactionValue =
        customer.transactions.stream()
          .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
          .map(transaction -> transaction.getValue())
          .collect(Collectors.toList());

    System.out.println(livedInCambridgeTraderTransactionValue);

    // 7. 전체 트랜잭션 중 최댓값은 얼마인가?
    Optional<Integer> maxValue =
        customer.transactions.stream()
          .map(Transaction::getValue)
          .reduce(Integer::max);

    System.out.println(maxValue);
  }
}
