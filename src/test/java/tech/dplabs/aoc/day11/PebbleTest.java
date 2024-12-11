package tech.dplabs.aoc.day11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PebbleTest {
  @Test
  void testPebbleBlink() {
    var pebbles = new Pebbles("125 17");
    pebbles.blink();
    assertEquals("[253000, 1, 7]", pebbles.toString());
    pebbles.blink();
    pebbles.blink();
    pebbles.blink();
    pebbles.blink();
    pebbles.blink();
    assertEquals("[2097446912, 14168, 4048, 2, 0, 2, 4, 40, 48, 2024, 40, 48, 80, 96, 2, 8, 6, 7, 6, 0, 3, 2]", pebbles.toString());
    assertEquals(22, pebbles.size());
  }
}
