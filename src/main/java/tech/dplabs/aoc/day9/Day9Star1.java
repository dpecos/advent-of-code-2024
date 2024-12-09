import day9.DiskMap;
import tech.dplabs.aoc.common.RunUtils;

void main() {
  RunUtils.measureRuntime("Day 9 - Star 1", () -> {
    var diskMap = DiskMap.readFromFile("src/main/resources/day9_input.txt");
    diskMap.parseDiskDescriptor();
    diskMap.defragment();
    println(diskMap.checksum());
  });
}
