package tech.dplabs.aoc.day1;

import static tech.dplabs.aoc.day1.ListUtils.*;

public class Day1Star2 {

  public static void main(String[] args) {

    var lists = readPairOfListsFromFile("src/main/resources/day1_input.txt");

    var locationIDs = lists.first();
    var locationOccurrences = lists.second();

    System.out.println(similarityScoreBetweenLists(locationIDs, locationOccurrences));
  }
}
