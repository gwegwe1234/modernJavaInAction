package chap7;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SpliteratorMainLogic {

  public static void main(String[] args) {
    String SENTENCE = "hi i'm ted and you are sunny and you are cindy.";
    Stream<Character> stream = IntStream.range(0, SENTENCE.length())
        .mapToObj(SENTENCE::charAt);
    int iterativelyWordCount = countWordsIteratively(SENTENCE);
    int streamWordCount = countWords(stream);

    System.out.println(iterativelyWordCount);
    System.out.println(streamWordCount);

    //System.out.println(countWords(stream.parallel()));

    Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
    Stream<Character> parallelStream = StreamSupport.stream(spliterator, true);

    int parallelWordCount = countWords(parallelStream);

    System.out.println(parallelWordCount);
  }

  public static int countWordsIteratively(String s) {
    int counter = 0;
    boolean lastSpace = true;

    for(char c : s.toCharArray()) {
      if (Character.isWhitespace(c)) {
        lastSpace = true;
      } else {
        if (lastSpace) counter++;
        lastSpace = false;
      }
    }

    return counter;
  }

  private static int countWords(Stream<Character> stream) {
    WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
        WordCounter::accumulate, WordCounter::combine);

    return wordCounter.getCounter();
  }
}
