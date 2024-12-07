import tech.dplabs.aoc.common.RunUtils;
import tech.dplabs.aoc.day7.Calibration;

void main() {
  RunUtils.measureRuntime("Day 7 - Part 2", () -> {
    var calibration = Calibration.loadFromFile("src/main/resources/day7_input.txt");
    System.out.println(calibration.calibrationResult(true));
  });
}
