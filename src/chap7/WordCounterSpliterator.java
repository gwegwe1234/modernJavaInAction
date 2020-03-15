package chap7;

import java.util.Spliterator;
import java.util.function.Consumer;

public class WordCounterSpliterator implements Spliterator<Character> {

  private final String string;
  private int currentChar = 0;

  public WordCounterSpliterator(String string) {
    this.string = string;
  }

  @Override
  public boolean tryAdvance(Consumer<? super Character> action) {
    action.accept(string.charAt(currentChar++)); // 현재 문자를 소비한다.
    return currentChar < string.length(); // 소비할 문자가 남아있으면 true를 반환
  }

  @Override
  public Spliterator<Character> trySplit() {
    int currentSize = string.length() - currentChar;
    if (currentSize < 10) {
      return null;
    }

    for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {
      if(Character.isWhitespace(string.charAt(splitPos))) {
        Spliterator<Character> spliterator =
            new WordCounterSpliterator(string.substring(currentChar, splitPos));
        currentChar = splitPos;
        return spliterator;
      }
    }

    return null;
  }

  @Override
  public long estimateSize() {
    return string.length() - currentChar;
  }

  @Override
  public int characteristics() {
    return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
  }
}
