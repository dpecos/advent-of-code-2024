package tech.dplabs.aoc.day4;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordSearchTest {
  @Test
  void testLoadFromFile() {
    var wordSearch = WordSearch.loadFromFile("src/test/resources/day4_input.txt");

//    System.out.println(Arrays.deepToString(wordSearch));

    assertEquals(10, wordSearch.length);
    assertEquals(10, wordSearch[0].length);
    assertEquals(10, wordSearch[9].length);
  }

  @Test
  void testCountXMAS() {
    var wordSearch = WordSearch.loadFromFile("src/test/resources/day4_input.txt");

    assertEquals(18, WordSearch.countXMAS(wordSearch));
  }
}
