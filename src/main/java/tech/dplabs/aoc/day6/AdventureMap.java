package tech.dplabs.aoc.day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AdventureMap {
    private int[][] cells;
    private Guard guard;

    AdventureMap(int[][] cells, Guard guard) {
        this.cells = cells;
        this.guard = guard;
    }

    public static AdventureMap loadFromFile(String path) {
        var lines = 0;
        var columns = 0;
        try (var reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines++;
                columns = line.length();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var cells = new int[lines][columns];
        Guard guard = null;

        try (var reader = new BufferedReader(new FileReader(path))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                if (line != null) {
                    var parts = line.split("");

                    for (int i = 0; i < parts.length; i++) {
                        switch (parts[i]) {
                            case ".":
                                cells[lineNumber][i] = 0;
                                break;
                            case "#":
                                cells[lineNumber][i] = -1;
                                break;
                            case "^":
                                cells[lineNumber][i] = 1;
                                guard = new Guard(i, lineNumber, Direction.UP);
                            default:
                        }
                    }
                }
                lineNumber++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new AdventureMap(cells, guard);
    }

    private void printMap() {
        for (int i = 0; i < this.cells.length; i++) {
            for (int j = 0; j < this.cells[i].length; j++) {
                switch (this.cells[i][j]) {
                    case 0:
                        System.out.print(". ");
                        break;
                    case -1:
                        System.out.print("# ");
                        break;
                    default:
                        System.out.print("* ");
                        break;
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean moveGuard() {
        var withinMap = true;

        while (withinMap) {
            var x = this.guard.x();
            var y = this.guard.y();
            var direction = this.guard.direction();

            var vector = direction.vector();
            var nextX = x + vector[1];
            var nextY = y + vector[0];

            if (nextX < 0 || nextX >= this.cells[0].length || nextY < 0 || nextY >= this.cells.length) {
                withinMap = false;
            } else {
                if (direction.id() == this.cells[nextY][nextX]) {
                    return true;
                }
                switch (this.cells[nextY][nextX]) {
                    case 0:
                        this.cells[nextY][nextX] = direction.id();
                        break;
                    case -1:
                        direction = switch (direction) {
                            case Direction.UP -> Direction.RIGHT;
                            case Direction.RIGHT -> Direction.DOWN;
                            case Direction.DOWN -> Direction.LEFT;
                            case Direction.LEFT -> Direction.UP;
                        };
                        nextX = x;
                        nextY = y;
                        break;
                }
                this.guard = new Guard(nextX, nextY, direction);
            }
//            this.printMap();
        }

        return false;
    }

    public int countVisitedCells() {
        var count = 0;
        for (int i = 0; i < this.cells.length; i++) {
            for (int j = 0; j < this.cells[i].length; j++) {
                if (this.cells[i][j] > 0) {
                    count += 1;
                }
            }
        }
        return count;
    }

    private int[][] cloneCells(int[][] cells) {
        var newCells = cells.clone();
        for (int k = 0; k < newCells.length; k++) {
            newCells[k] = newCells[k].clone();
        }
        return newCells;
    }

    public int countLoops() {
        var count = 0;

        var originalCells = this.cloneCells(this.cells);
        var originalGuard = this.guard;

        for (int i = 0; i < this.cells.length; i++) {
            for (int j = 0; j < this.cells[i].length; j++) {
                // restore original cells
                this.cells = this.cloneCells(originalCells);
                this.guard = originalGuard;

                if (this.cells[i][j] != 0) {
                    continue;
                }
                this.cells[i][j] = -1;

//                this.printMap();

                if (this.moveGuard()) {
                    count += 1;
                }
            }
        }

        return count;
    }

    public Guard getGuard() {
        return this.guard;
    }
}
