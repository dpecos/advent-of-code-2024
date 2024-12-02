package tech.dplabs.aoc.day1;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tech.dplabs.aoc.day1.ListUtils.distanceBetweenLists;
import static tech.dplabs.aoc.day1.ListUtils.sortList;

class ListUtilsTest {

  @Test
  void testSortList() {
    List<Integer> list = List.of(3,4,2,1,3,3);
    List<Integer> expected = List.of(1,2,3,3,3,4);
    assertEquals(expected, sortList(list));
  }

  @Test
  void testListDistance() {
    var list1 = List.of(3,4,2,1,3,3);
    var list2 = List.of(4,3,5,3,9,3);

    var list1sorted = sortList(list1);
    var list2sorted = sortList(list2);

    assertEquals(11, distanceBetweenLists(list1sorted, list2sorted));
  }

  @Test
  void testReadingPairOfLists() {
    var pair = ListUtils.readPairOfListsFromFile("test/tech/dplabs/aoc/day1/input.txt");
    var list1 = List.of(3,4,2,1,3,3);
    var list2 = List.of(4,3,5,3,9,3);

    assertEquals(list1, pair.first());
    assertEquals(list2, pair.second());
  }
}
