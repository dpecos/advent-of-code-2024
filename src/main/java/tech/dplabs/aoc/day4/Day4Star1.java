import tech.dplabs.aoc.day4.WordSearch;

void main() {
  var wordSearch = WordSearch.loadFromFile("src/main/resources/day4_input.txt");
  println(WordSearch.countXMAS(wordSearch));
}
