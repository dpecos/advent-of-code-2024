package tech.dplabs.aoc.day1;

import static tech.dplabs.aoc.day1.ListUtils.*;

public class Day1Star1 {

  public static void main(String[] args) {

    var lists = readPairOfListsFromFile("src/tech/dplabs/aoc/day1/input.txt");

    var list1sorted = sortList(lists.first());
    var list2sorted = sortList(lists.second());

    System.out.println(distanceBetweenLists(list1sorted, list2sorted));
  }
}
