package tech.dplabs.aoc.day4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordSearchTest {
  @Test
  void testLoadFromFile() {
    var wordSearch = WordSearch.loadFromFile("src/test/resources/day4_input.txt");

    assertEquals(10, wordSearch.length);
    assertEquals(10, wordSearch[9].length);
  }

  @Test
  void testCountXMAS() {
    var wordSearch = WordSearch.loadFromFile("src/test/resources/day4_input.txt");

    assertEquals(18, WordSearch.countXMAS(wordSearch));
  }

  @Test
  void testCheckCrossMAS1() {
    var wordSearch = new String[][] {
      {"M", "-", "S"},
      {"-", "A", "-"},
      {"M", "-", "S"}
    };

    assertTrue(WordSearch.checkCrossMAS(wordSearch, 0, 0, 1, 1));
  }

  @Test
  void testCheckCrossMAS2() {
    var wordSearch = new String[][] {
      {"M", "-", "M"},
      {"-", "A", "-"},
      {"S", "-", "S"}
    };

    assertTrue(WordSearch.checkCrossMAS(wordSearch, 0, 0, 1, 1));
  }

  @Test
  void testCheckCrossMAS3() {
    var wordSearch = new String[][] {
      {"S", "-", "M"},
      {"-", "A", "-"},
      {"S", "-", "M"}
    };

    assertTrue(WordSearch.checkCrossMAS(wordSearch, 2, 2, -1, -1));
  }

  @Test
  void testCheckCrossMAS4() {
    var wordSearch = new String[][] {
      {"S", "-", "S"},
      {"-", "A", "-"},
      {"M", "-", "M"}
    };

    assertTrue(WordSearch.checkCrossMAS(wordSearch, 2, 2, -1, -1));
  }

  @Test
  void testCountCrossMAS1() {
    var wordSearch = new String[][] {
      {"M", "-", "M"},
      {"-", "A", "-"},
      {"S", "-", "S"}
    };

    assertEquals(1, WordSearch.countCrossMAS(wordSearch));
  }

  @Test
  void testCountCrossMAS2() {
    var wordSearch = new String[][] {
      {"-", "S", "-"},
      {"M", "A", "S"},
      {"-", "M", "-"}
    };

    assertEquals(0, WordSearch.countCrossMAS(wordSearch));
  }

  @Test
  void testCountCrossMAS3() {
    var wordSearch = new String[][] {
      {"S", "-", "M", "-", "S"},
      {"-", "A", "-", "A", "-"},
      {"S", "-", "M", "-", "S"},
      {"-", "A", "-", "A", "-"},
      {"S", "-", "M", "-", "S"},
    };

    assertEquals(4, WordSearch.countCrossMAS(wordSearch));
  }

  @Test
  void testCountCrossMAS4() {
    var wordSearch = new String[][] {
      {"M", "-", "S", "-", "M", "-", "S"},
      {"-", "A", "-", "A", "-", "A", "-"},
      {"M", "-", "S", "_", "M", "-", "S"}
    };

    assertEquals(3, WordSearch.countCrossMAS(wordSearch));
  }

  @Test
  void testCountCrossMAS() {
    var wordSearch = WordSearch.loadFromFile("src/test/resources/day4_input.txt");

    assertEquals(9, WordSearch.countCrossMAS(wordSearch));
  }
}
