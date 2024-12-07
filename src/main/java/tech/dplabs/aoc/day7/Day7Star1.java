import tech.dplabs.aoc.day7.Calibration;

void main() {
  var calibration = Calibration.loadFromFile("src/main/resources/day7_input.txt");
  System.out.println(calibration.calibrationResult());
}
