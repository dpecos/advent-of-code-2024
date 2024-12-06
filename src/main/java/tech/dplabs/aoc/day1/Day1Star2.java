package tech.dplabs.aoc.day1;

import tech.dplabs.aoc.common.RunUtils;

import static tech.dplabs.aoc.day1.ListUtils.readPairOfListsFromFile;
import static tech.dplabs.aoc.day1.ListUtils.similarityScoreBetweenLists;

public class Day1Star2 {

  public static void main(String[] args) {
    RunUtils.measureRuntime(() -> {
      var lists = readPairOfListsFromFile("src/main/resources/day1_input.txt");

      var locationIDs = lists.first();
      var locationOccurrences = lists.second();

      System.out.println(similarityScoreBetweenLists(locationIDs, locationOccurrences));
    });
  }
}
