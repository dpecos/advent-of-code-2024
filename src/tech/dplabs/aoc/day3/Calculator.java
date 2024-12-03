package tech.dplabs.aoc.day3;

import java.util.regex.Pattern;

public class Calculator {
  public static int calculate(String input, boolean enableConditionals) {
    var pattern = Pattern.compile( "((mul)\\((\\d{1,3}),(\\d{1,3})\\)|((do|don't))\\(\\))");

    int result = 0;

    boolean mulEnabled = true;

    var matcher = pattern.matcher(input);
    while (matcher.find())  {
      var op = matcher.group(2);
      if (op == null || op.isEmpty()) {
        op = matcher.group(0);
      }
      switch (op) {
        case "mul":
          result += mulEnabled ? Integer.parseInt(matcher.group(3)) * Integer.parseInt(matcher.group(4)) : 0;
          break;
        case "do()":
          mulEnabled = true;
          break;
        case "don't()":
          mulEnabled = false;
          break;
      }
    }

    return result;
  }
}
