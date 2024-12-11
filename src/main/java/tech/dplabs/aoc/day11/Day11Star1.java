import tech.dplabs.aoc.day11.Pebbles;

void main() {
  var pebbles = new Pebbles("28 4 3179 96938 0 6617406 490 816207");

  for (var i = 0; i < 25; i++) {
    pebbles.blink();
  }
  println(pebbles.size());
}
