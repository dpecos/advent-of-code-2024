package tech.dplabs.aoc.day3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tech.dplabs.aoc.day3.Calculator.calculate;

public class CalculatorTest {

  @Test
  void testCalculate() {
    assertEquals(161, calculate("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))", false));
  }

  @Test
  void testCalculateConditionalsAllOff() {
    assertEquals(0, calculate("don't()xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))", true));
  }

  @Test
  void testCalculateConditionalsPartialOff() {
    assertEquals(121, calculate("do()xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)don't()mul(8,5))", true));
  }
}
