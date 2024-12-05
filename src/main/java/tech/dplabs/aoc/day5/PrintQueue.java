package tech.dplabs.aoc.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PrintQueue {
  private final Map<Integer, List<Integer>> rules;
  private final List<List<Integer>> updates;

  private PrintQueue(Map<Integer, List<Integer>> rules, List<List<Integer>> updates) {
    this.rules = rules;
    this.updates = updates;
  }

  public static PrintQueue load(String path) {
    var rules = new HashMap<Integer, List<Integer>>();
    var updates = new ArrayList<List<Integer>>();

    var readingRules = true;

    try (var reader = new BufferedReader(new FileReader(path))) {
      String line;
      while ((line = reader.readLine()) != null) {
        if (line.isEmpty()) {
          readingRules = false;
          continue;
        }
        if (readingRules) {
          var parts = line.split("\\|");
          var key = Integer.parseInt(parts[0]);
          var value = Integer.parseInt(parts[1]);

          var beforeNumbers = rules.getOrDefault(key, new ArrayList<Integer>());
          beforeNumbers.add(value);
          rules.put(key, beforeNumbers);
        } else {
          updates.add(Arrays.stream(line.split(",")).map(Integer::parseInt).toList());
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return new PrintQueue(rules, updates);
  }

  Map<Integer, List<Integer>> getRules() {
    return this.rules;
  }

  List<List<Integer>> getUpdates() {
    return this.updates;
  }


  boolean isValidUpdate(List<Integer> update) {
    var printedPages = new ArrayList<Integer>();
    var updateValid = true;

    for (var page : update) {
      var beforePages = this.getRules().get(page);

      var pageValid = true;
      if (beforePages != null) {
        for (var beforePage : beforePages) {
          if (printedPages.contains(beforePage)) {
            pageValid = false;
            break;
          }
        }
      }

      updateValid = updateValid && pageValid;
      if (!updateValid) {
        break;
      }
      printedPages.add(page);
    }

    return updateValid;
  }

  public int sumMiddlePagesOfValidUpdates() {
    var total = 0;

    for (var update : this.getUpdates()) {
      if (this.isValidUpdate(update)) {
        var middleIndex = update.size() / 2;
        total += update.get(middleIndex);
      }
    }

    return total;
  }
}
