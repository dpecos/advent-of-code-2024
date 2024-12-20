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

      var pageValid = beforePages == null || beforePages.stream().noneMatch(printedPages::contains);

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

  private int sumMiddlePagesOfUpdates(List<List<Integer>> updates) {
    return updates.stream()
      .filter(this::isValidUpdate)
      .mapToInt(update -> update.get(update.size() / 2))
      .sum();
  }

  public int sumMiddlePagesOfValidUpdates() {
    var validUpdates = this.getUpdates().stream()
      .filter(this::isValidUpdate)
      .toList();
    return this.sumMiddlePagesOfUpdates(validUpdates);
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
    var fixedUpdates = this.getUpdates().stream()
      .filter(u -> !this.isValidUpdate(u))
      .map(this::fixUpdate)
      .toList();
    return this.sumMiddlePagesOfUpdates(fixedUpdates);
  }
}
