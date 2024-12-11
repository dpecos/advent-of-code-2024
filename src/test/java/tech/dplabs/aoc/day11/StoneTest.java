package tech.dplabs.aoc.day11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoneTest {
  @Test
  void testStoneBlinkRule1() {
    var stone = new Stone(0);
    assertEquals(1, stone.blink()[0].value());
  }

  @Test
  void testStoneBlinkRule2() {
    var stone = new Stone(103021);
    assertEquals(103, stone.blink()[0].value());
    assertEquals(21, stone.blink()[1].value());
  }

  @Test
  void testStoneBlinkRule3() {
    var stone = new Stone(1);
    assertEquals(2024, stone.blink()[0].value());
  }
}
