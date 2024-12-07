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
    assertTrue(equation.isSolvable(false));
  }

  @Test
  void testCalibration() {
    var calibration = Calibration.loadFromFile("src/test/resources/day7_input.txt");
    assertEquals(3749, calibration.calibrationResult(false));
  }

  @Test
  void testEquationSolverJoining() {
    var equation = new Equation(156, new long[]{15, 6});
    assertTrue(equation.isSolvable(true));

    var equation2 = new Equation(7290, new long[]{6, 8, 6, 15});
    assertTrue(equation.isSolvable(true));
  }

  @Test
  void testCalibrationJoining() {
    var calibration = Calibration.loadFromFile("src/test/resources/day7_input.txt");
    assertEquals(11387, calibration.calibrationResult(true));
  }
}
