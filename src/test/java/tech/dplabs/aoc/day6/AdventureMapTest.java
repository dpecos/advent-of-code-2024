package tech.dplabs.aoc.day6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AdventureMapTest {

  @Test
  void testLoadMapFromFile() {
    var map = AdventureMap.loadFromFile("src/test/resources/day6_input.txt");

    assertEquals(1, map.countVisitedCells());
  }

  @Test
  void testLoadGuardFromFile() {
    var guard = Guard.loadFromFile("src/test/resources/day6_input.txt");

    assertEquals(6, guard.y());
    assertEquals(4, guard.x());
  }

  @Test
  void testMoveGuard() {
    var map = AdventureMap.loadFromFile("src/test/resources/day6_input.txt");
    var guard = Guard.loadFromFile("src/test/resources/day6_input.txt");
    guard.patrol(map);
    assertEquals(41, map.countVisitedCells());
  }

  @Test
  void testInitiallyNotInLoop() {
    var map = AdventureMap.loadFromFile("src/test/resources/day6_input.txt");
    var guard = Guard.loadFromFile("src/test/resources/day6_input.txt");
    assertFalse(guard.patrol(map));
  }

  @Test
  void testCountLoopOptions() {
    var map = AdventureMap.loadFromFile("src/test/resources/day6_input.txt");
    var guard = Guard.loadFromFile("src/test/resources/day6_input.txt");
    assertEquals(6, map.countLoops(guard));
  }
}
