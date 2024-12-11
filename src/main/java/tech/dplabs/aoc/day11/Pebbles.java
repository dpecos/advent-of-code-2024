package tech.dplabs.aoc.day11;

import java.util.ArrayList;
import java.util.Arrays;

public class Pebbles {
  private Stone[] stones;

  public Pebbles(String pebbles) {
    this.stones = Arrays.stream(pebbles.split(" "))
      .map(Long::parseLong)
      .map(Stone::new)
      .toArray(Stone[]::new);
  }

  public void blink() {
    var newStones = new ArrayList<Stone>();
    for (int i = 0; i < this.stones.length; i++) {
      var result = this.stones[i].blink();
      newStones.addAll(Arrays.asList(result));
    }
    this.stones = newStones.toArray(Stone[]::new);
  }

  public int size() {
    return this.stones.length;
  }

  public String toString() {
    return Arrays.toString(stones);
  }
}
