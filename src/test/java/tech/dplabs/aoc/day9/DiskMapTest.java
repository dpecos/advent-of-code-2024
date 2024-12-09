package tech.dplabs.aoc.day9;

import day9.DiskMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiskMapTest {
  @Test
  void testLoadFromFile() {
    var diskMap = DiskMap.readFromFile("src/test/resources/day9_input.txt");
    assertEquals(42, diskMap.diskSize());
  }

  @Test
  void testParseDiskDescriptor() {
    var diskMap = DiskMap.readFromFile("src/test/resources/day9_input.txt");
    diskMap.parseDiskDescriptor();
    assertEquals("00...111...2...333.44.5555.6666.777.888899", diskMap.toString());
  }

  @Test
  void testDefragment() {
    var diskMap = DiskMap.readFromFile("src/test/resources/day9_input.txt");
    diskMap.parseDiskDescriptor();
    diskMap.defragment();
    assertEquals("0099811188827773336446555566..............", diskMap.toString());
  }

  @Test
  void testChecksum() {
    var diskMap = DiskMap.readFromFile("src/test/resources/day9_input.txt");
    diskMap.parseDiskDescriptor();
    diskMap.defragment();
    assertEquals(1928, diskMap.checksum());
  }
}
