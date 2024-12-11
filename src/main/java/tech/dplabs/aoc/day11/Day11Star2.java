Map<String, Long> cachePabbleCountPerLevel = new HashMap<>();

long count_pebbles(long value, int iteration) {
  String key = iteration + "-" + value;
  Long result = cachePabbleCountPerLevel.get(key);
  if (result != null) {
    return result;
  }

  if (iteration == 0) {
    result = 1L;
  } else {
    int numberDigits = (int) (Math.log10(value) + 1);
    if (value == 0) {
      result = count_pebbles(1, iteration - 1);
    } else if (numberDigits % 2 == 0) {
      var leftValue = value / (long) Math.pow(10, numberDigits / 2);
      var rightValue = value % (long) Math.pow(10, numberDigits / 2);

      result = count_pebbles(leftValue, iteration - 1) + count_pebbles(rightValue, iteration - 1);
    } else {
      result = count_pebbles(value * 2024L, iteration - 1);
    }
  }

  cachePabbleCountPerLevel.put(key, result);
  return result;
}

void main() {
  var result = Arrays.stream("28 4 3179 96938 0 6617406 490 816207".split(" "))
    .map(Long::parseLong)
    .map(v -> count_pebbles(v, 75))
    .collect(Collectors.summingLong(Long::longValue));

  println(result);
}
