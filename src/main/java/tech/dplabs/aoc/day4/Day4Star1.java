import tech.dplabs.aoc.common.RunUtils;
import tech.dplabs.aoc.day4.WordSearch;

void main() {
  RunUtils.measureRuntime("Day 4 - Part 1", () -> {
    var wordSearch = WordSearch.loadFromFile("src/main/resources/day4_input.txt");
    println(WordSearch.countXMAS(wordSearch));
  });
}
