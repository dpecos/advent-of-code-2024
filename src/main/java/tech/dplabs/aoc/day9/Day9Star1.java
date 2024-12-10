import tech.dplabs.aoc.common.RunUtils;
import tech.dplabs.aoc.day9.DiskMap;

void main() {
  RunUtils.measureRuntime("Day 9 - Star 1", () -> {
    var diskMap = DiskMap.readFromFile("src/main/resources/day9_input.txt");
    diskMap.parseDiskDescriptor();
    diskMap.defragment(false);
    println(diskMap.checksum());
  });
}
