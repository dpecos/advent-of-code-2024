import tech.dplabs.aoc.day6.AdventureMap;

void main() {
  var map = AdventureMap.loadFromFile("src/main/resources/day6_input.txt");
  map.moveGuard();
  println(map.countVisitedCells());
}
