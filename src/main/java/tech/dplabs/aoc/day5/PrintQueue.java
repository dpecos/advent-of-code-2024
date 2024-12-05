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


  int getInvalidIndex(List<Integer> update) {
    var printedPages = new ArrayList<Integer>();
    var invalidIndex = -1;

    var i = 0;
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

      if (!pageValid) {
        invalidIndex = i;
        break;
      } else {
        i++;
        printedPages.add(page);
      }

    }

    return invalidIndex;
  }

  boolean isValidUpdate(List<Integer> update) {
    return this.getInvalidIndex(update) == -1;
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

  List<Integer> fixUpdate(List<Integer> update) {
    List<Integer> fixedUpdate = update;

    var isValid = this.isValidUpdate(update);
    if (!isValid) {
      fixedUpdate = new ArrayList<>(update);
    }

    while (!isValid) {
      var invalidIndex = this.getInvalidIndex(fixedUpdate);

      isValid = invalidIndex == -1;

      if (!isValid) {
        // swap the invalid index to the previous position
        var temp = fixedUpdate.get(invalidIndex - 1);
        fixedUpdate.set(invalidIndex - 1, fixedUpdate.get(invalidIndex));
        fixedUpdate.set(invalidIndex, temp);
      } else {
        break;
      }
    }
    return fixedUpdate;
  }

  public int sumMiddlePagesOfInvalidUpdates() {
    var total = 0;

    for (var update : this.getUpdates()) {
      if (!this.isValidUpdate(update)) {
        var fixedUpdate = this.fixUpdate(update);
        var middleIndex = fixedUpdate.size() / 2;
        total += fixedUpdate.get(middleIndex);
      }
    }

    return total;
  }
}
