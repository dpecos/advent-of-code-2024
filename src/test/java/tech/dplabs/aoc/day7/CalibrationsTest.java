package tech.dplabs.aoc.day7;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalibrationsTest {
  @Test
  void testLoadFromFile() {
    var calibration = Calibration.loadFromFile("src/test/resources/day7_input.txt");
    var equations = calibration.getEquations();
    assertEquals(9, equations.length);

    assertEquals(190, equations[0].result());
    assertEquals(List.of(10L, 19L), Arrays.stream(equations[0].operands()).boxed().toList());
  }

  @Test
  void testEquationSolver() {
    var equation = new Equation(190, new long[]{10, 19});
    assertTrue(equation.isSolvable());
  }

  @Test
  void testCalibration() {
    var calibration = Calibration.loadFromFile("src/test/resources/day7_input.txt");
    assertEquals(3749, calibration.calibrationResult());
  }
}
