package tech.dplabs.aoc.day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Calibration {
  private final Equation[] equations;

  Calibration(Equation[] equations) {
    this.equations = equations;
  }

  public static Calibration loadFromFile(String path) {
    try (var reader = new BufferedReader(new FileReader(path))) {
      var equations = reader.lines()
        .map(line -> {
          var parts = line.split(":");
          var result = Long.parseLong(parts[0]);
          var operands = Arrays.stream(parts[1].split(" "))
            .filter(s -> !s.isBlank())
            .mapToLong(Long::parseLong)
            .toArray();
          return new Equation(result, operands);
        })
        .toArray(Equation[]::new);

      return new Calibration(equations);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Equation[] getEquations() {
    return equations;
  }

  public long calibrationResult(boolean joiningOperands) {
    return Arrays.stream(this.equations)
      .filter(eq -> eq.isSolvable(joiningOperands))
      .mapToLong(Equation::result)
      .sum();
  }
}
