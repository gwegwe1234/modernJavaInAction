package chap3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderExample {

  public static void main(String[] args) {
    try {
      String oneLine = processFile((BufferedReader br) -> br.readLine());
      String twoLine = processFile((BufferedReader br) -> br.readLine() + br.read());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static String processFile(BufferedReaderProcessor p) throws IOException {
    try(BufferedReader br = new BufferedReader(new FileReader("data/txt"))) {
      return p.process(br);
    }
  }
}
