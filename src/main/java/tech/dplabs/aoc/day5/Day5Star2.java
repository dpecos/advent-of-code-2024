import tech.dplabs.aoc.day5.PrintQueue;

void main() {
  var printQueue = PrintQueue.load("src/main/resources/day5_input.txt");
  System.out.println(printQueue.sumMiddlePagesOfInvalidUpdates());
}
