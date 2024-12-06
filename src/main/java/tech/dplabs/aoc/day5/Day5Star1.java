import tech.dplabs.aoc.common.RunUtils;
import tech.dplabs.aoc.day5.PrintQueue;

void main() {
  RunUtils.measureRuntime("Day 5 - Part 1", () -> {
    var printQueue = PrintQueue.load("src/main/resources/day5_input.txt");
    System.out.println(printQueue.sumMiddlePagesOfValidUpdates());
  });
}
