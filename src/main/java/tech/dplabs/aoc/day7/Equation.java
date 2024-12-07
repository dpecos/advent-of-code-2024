package tech.dplabs.aoc.day7;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


public record Equation(long result, long[] operands) {
  public boolean isSolvable() {
    return generateCombinations(0, operands).mapToLong(Long::longValue).boxed().anyMatch(i -> i == result);
  }

  private Stream<Long> generateCombinations(long acc, long[] operands) {
    if (operands.length == 0) {
      return Stream.of(acc);
    }

    var operators = List.of("+", "*");

    return operators.stream().flatMap(op -> {
      var newAcc = switch (op) {
        case "+" -> acc + operands[0];
        case "*" -> acc * operands[0];
        default -> throw new IllegalStateException("Unexpected value: " + op);
      };
      return generateCombinations(newAcc, Arrays.copyOfRange(operands, 1, operands.length));
    });
  }
}
