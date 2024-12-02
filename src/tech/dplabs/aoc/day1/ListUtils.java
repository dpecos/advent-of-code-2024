package tech.dplabs.aoc.day1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListUtils {
  public static Pair<List<Integer>> readPairOfListsFromFile(String path) {
    var list1 = new ArrayList<Integer>();
    var list2 = new ArrayList<Integer>();

    try (var reader = new BufferedReader(new FileReader(path))) {
      String line;
      while ((line = reader.readLine()) != null) {
        var parts = line.split("\s+");
        list1.add(Integer.parseInt(parts[0]));
        list2.add(Integer.parseInt(parts[1]));
      }
    } catch (IOException e) {
      System.err.println("Error reading file");
      throw new RuntimeException(e);
    }

    return new Pair<>(list1, list2);

  }

  public static List<Integer> sortList(List<Integer> list) {
    return list.stream().sorted().collect(Collectors.toList());
  }

  public static int distanceBetweenLists(List<Integer> list1, List<Integer> list2) {
    var distance = 0;

    for (int i = 0; i < list1.size(); i++) {
      distance += Math.abs(list1.get(i) - list2.get(i));
    }

    return distance;
  }

  public static int similarityScoreBetweenLists(List<Integer> list1, List<Integer> list2) {
    var score = 0;

    for (int i = 0; i < list1.size(); i++) {
      var item = list1.get(i);
      var occurrencesInSecondList = list2.stream().filter(x -> x.equals(item)).count();
      score += item * occurrencesInSecondList;
    }

    return score;
  }
}
