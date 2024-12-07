package tech.dplabs.aoc.day7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


public record Equation(long result, long[] operands) {
  private static Stream<Long> generateCombinations(long acc, long[] operands, boolean joiningOperands) {
    if (operands.length == 0) {
      return Stream.of(acc);
    }

    var operators = new ArrayList<String>();
    operators.addAll(List.of("+", "*"));
    if (joiningOperands) {
      operators.add("||");
    }

    return operators.stream().flatMap(op -> {
      var newAcc = switch (op) {
        case "+" -> acc + operands[0];
        case "*" -> acc * operands[0];
        case "||" -> Long.parseLong("" + acc + operands[0]);
        default -> throw new IllegalStateException("Unexpected value: " + op);
      };
      return generateCombinations(newAcc, Arrays.copyOfRange(operands, 1, operands.length), joiningOperands);
    });
  }

  public boolean isSolvable(boolean joiningOperands) {
    return generateCombinations(0, this.operands, joiningOperands)
      .mapToLong(Long::longValue)
      .boxed()
      .anyMatch(i -> i == result);
  }
}
