package tech.dplabs.aoc.day5;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PrintQueueTest {
  @Test
  void testLoadFromFile() {
    var printQueue = PrintQueue.load("src/test/resources/day5_input.txt");
    assertEquals(6, printQueue.getRules().size());
    assertEquals(6, printQueue.getRules().get(97).size());

    assertEquals(6, printQueue.getUpdates().size());
  }

  @Test
  void testUpdatesCheck() {
    var printQueue = PrintQueue.load("src/test/resources/day5_input.txt");

    assertTrue(printQueue.isValidUpdate(List.of(75, 47, 61, 53, 29)));
    assertTrue(printQueue.isValidUpdate(List.of(97, 61, 53, 29, 13)));
    assertTrue(printQueue.isValidUpdate(List.of(75, 29, 13)));
    assertFalse(printQueue.isValidUpdate(List.of(75, 97, 47, 61, 53)));
    assertFalse(printQueue.isValidUpdate(List.of(61, 13, 29)));
    assertFalse(printQueue.isValidUpdate(List.of(97, 13, 75, 29, 47)));
  }

  @Test
  void testSumMiddlePagesOfValidUpdates() {
    var printQueue = PrintQueue.load("src/test/resources/day5_input.txt");
    assertEquals(143, printQueue.sumMiddlePagesOfValidUpdates());
  }

  @Test
  void testFixRule() {
    var printQueue = PrintQueue.load("src/test/resources/day5_input.txt");

    assertEquals(List.of(97, 75, 47, 61, 53), printQueue.fixUpdate(List.of(75, 97, 47, 61, 53)));
    assertEquals(List.of(61, 29, 13), printQueue.fixUpdate(List.of(61, 13, 29)));
    assertEquals(List.of(97, 75, 47, 29, 13), printQueue.fixUpdate(List.of(97, 13, 75, 29, 47)));
  }

  @Test
  void testSumMiddlePagesOfInvalidUpdates() {
    var printQueue = PrintQueue.load("src/test/resources/day5_input.txt");
    assertEquals(123, printQueue.sumMiddlePagesOfInvalidUpdates());
  }
}
