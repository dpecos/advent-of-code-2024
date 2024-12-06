import tech.dplabs.aoc.common.RunUtils;
import tech.dplabs.aoc.day6.AdventureMap;
import tech.dplabs.aoc.day6.Guard;

void main() {
  RunUtils.measureRuntime("Day 6 - Part 2", () -> {
    var map = AdventureMap.loadFromFile("src/main/resources/day6_input.txt");
    var guard = Guard.loadFromFile("src/main/resources/day6_input.txt");
    println(map.countLoops(guard));
  });
}
