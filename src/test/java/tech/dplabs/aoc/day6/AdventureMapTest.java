package tech.dplabs.aoc.day6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdventureMapTest {

  @Test
  void testLoadFromFile() {
    var map = AdventureMap.loadFromFile("src/test/resources/day6_input.txt");

    assertEquals(6, map.getGuard().y());
    assertEquals(4, map.getGuard().x());

    assertEquals(1, map.countVisitedCells());
  }

  @Test
  void testMoveGuard() {
    var map = AdventureMap.loadFromFile("src/test/resources/day6_input.txt");
    map.moveGuard();
    assertEquals(41, map.countVisitedCells());
  }
}
