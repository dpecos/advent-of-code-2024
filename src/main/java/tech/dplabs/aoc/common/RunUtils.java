package tech.dplabs.aoc.common;

import static java.io.IO.println;

public class RunUtils {
  public static void measureRuntime(Runnable runnable) {
    measureRuntime(null, runnable);
  }

  public static void measureRuntime(String description, Runnable runnable) {
    var separator = "-".repeat(description != null ? description.length() : 20);
    if (description != null) {
      println(separator);
      println(description);
      println(separator + "\n");
    }
    long start = System.currentTimeMillis();

    runnable.run();

    long end = System.currentTimeMillis();
    println("\n" + separator);
    println("[ Execution time: " + (end - start) / 1000f + " sec ]");
  }
}
