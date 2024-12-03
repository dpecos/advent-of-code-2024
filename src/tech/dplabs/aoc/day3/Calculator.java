package tech.dplabs.aoc.day3;

import java.util.regex.Pattern;

public class Calculator {
  public static int calculate(String input) {
    var pattern = Pattern.compile( "mul\\((\\d{1,3}),(\\d{1,3})\\)");

    int result = 0;

    var matcher = pattern.matcher(input);
    while (matcher.find())  {
      result += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
    }

    return result;
  }
}
