import tech.dplabs.aoc.common.RunUtils;
import tech.dplabs.aoc.day8.AntennaMap;

import static tech.dplabs.aoc.common.MatrixUtils.countValues;

void main() {
  RunUtils.measureRuntime("Day 8 - Star 2", () -> {
    var map = AntennaMap.loadFromFile("src/main/resources/day8_input.txt");
    var antinodes = map.markAntiNodes(true);
    println(countValues(antinodes, AntennaMap.ANTI_NODE));
  });
}
