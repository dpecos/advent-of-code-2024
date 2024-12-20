import tech.dplabs.aoc.common.RunUtils;

import static tech.dplabs.aoc.day1.ListUtils.*;


void main() {
  RunUtils.measureRuntime("Day 1 - Part 1", () -> {
    var lists = readPairOfListsFromFile("src/main/resources/day1_input.txt");

    var list1sorted = sortList(lists.first());
    var list2sorted = sortList(lists.second());

    System.out.println(distanceBetweenLists(list1sorted, list2sorted));
  });
}
