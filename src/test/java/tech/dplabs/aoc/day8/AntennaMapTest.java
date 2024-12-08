package tech.dplabs.aoc.day8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tech.dplabs.aoc.common.MatrixUtils.countValues;

public class AntennaMapTest {
  @Test
  void testLoadFromFile() {
    var map = AntennaMap.loadFromFile("src/test/resources/day8_input.txt");
    assertEquals(12, map.getHeight());
    assertEquals(12, map.getWidth());
  }

  @Test
  void testCountAntiNodes() {
    var map = AntennaMap.loadFromFile("src/test/resources/day8_input.txt");
    var antinodes = map.markAntiNodes(false);
    assertEquals(14, countValues(antinodes, AntennaMap.ANTI_NODE));
  }

  @Test
  void testCountMultipleAntiNodes() {
    var map = AntennaMap.loadFromFile("src/test/resources/day8_input.txt");
    var antinodes = map.markAntiNodes(true);
    assertEquals(34, countValues(antinodes, AntennaMap.ANTI_NODE));
  }
}
