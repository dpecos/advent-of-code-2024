import tech.dplabs.aoc.common.RunUtils;

import static tech.dplabs.aoc.day1.ListUtils.readPairOfListsFromFile;
import static tech.dplabs.aoc.day1.ListUtils.similarityScoreBetweenLists;

void main() {
  RunUtils.measureRuntime("Day 1 - Part 2", () -> {
    var lists = readPairOfListsFromFile("src/main/resources/day1_input.txt");

    var locationIDs = lists.first();
    var locationOccurrences = lists.second();

    System.out.println(similarityScoreBetweenLists(locationIDs, locationOccurrences));
  });
}
