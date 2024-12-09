package day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class DiskMap {
  private final int[] map;
  private Integer[] disk;

  private DiskMap(int[] map) {
    this.map = map;
  }

  public static DiskMap readFromFile(String path) {
    int[] map = null;
    try (var reader = new BufferedReader(new FileReader(path))) {
      String line = reader.readLine();
      if (line != null) {
        map = line.chars().map(Character::getNumericValue).toArray();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return new DiskMap(map);
  }

  public int diskSize() {
    int totalSize = 0;
    for (int i = 0; i < this.map.length; i++) {
      totalSize += this.map[i];
    }
    return totalSize;
  }

  public void parseDiskDescriptor() {
    this.disk = new Integer[this.diskSize()];

    var diskPosition = 0;

    var isFile = true;
    var nextFileID = 0;

    for (int i = 0; i < this.map.length; i++) {
      var size = this.map[i];
      if (isFile) {
        for (var k = diskPosition; k < diskPosition + size; k++) {
          this.disk[k] = nextFileID;
        }
        nextFileID++;
      }
      diskPosition += size;
      isFile = !isFile;
    }
  }

  public String toString() {
    return Arrays.stream(this.disk).map(v -> v == null ? "." : v.toString()).reduce("", String::concat);
  }

  private int[] findGap(int pos) {
    var gapStart = -1;
    for (var i = pos; i < this.disk.length; i++) {
      if (this.disk[i] == null) {
        gapStart = i;
        break;
      }
    }
    if (gapStart == -1) {
      return null;
    } else {
      var size = 0;

      for (var i = gapStart; i < this.disk.length && this.disk[i] == null; i++) {
        size++;
      }

      return new int[]{gapStart, size};
    }
  }

  public void defragment(boolean contiguosFiles) {
    var i = 0;
    var tailValue = this.disk.length - 1;

    while (tailValue > i) {
      while (i < this.disk.length && this.disk[i] != null) {
        i++;
      }
      if (i < this.disk.length) {
        while (this.disk[tailValue] == null) {
          tailValue--;
        }
        if (i < tailValue) {
          if (contiguosFiles) {

            var sizeOfFile = 0;
            var fileID = this.disk[tailValue];
            for (var k = tailValue; k >= i && this.disk[k] != null && this.disk[k].equals(fileID); k--) {
              sizeOfFile++;
            }

            var gap = this.findGap(i);
            while (gap != null) {
              var gapStart = gap[0];
              var gapSize = gap[1];

              if (gapSize >= sizeOfFile) {
                // gap found and file fits!
                break;
              } else {
                gap = this.findGap(gapStart + gapSize);
              }
            }

            if (gap != null && gap[0] < tailValue - sizeOfFile) { // only move file if it's ahead of the beginning of the gap
              for (var k = 0; k < sizeOfFile; k++) {
                this.disk[gap[0] + k] = this.disk[tailValue - k];
                this.disk[tailValue - k] = null;
              }
            }
            i = 0; // we need to look for gaps from the beginning
            tailValue -= sizeOfFile; // next file
          } else {
            this.disk[i] = this.disk[tailValue];
            this.disk[tailValue] = null;
          }
        }
      } else {
        return;
      }
    }
  }

  public long checksum() {
    var checksum = 0L;
    for (var i = 0; i < this.disk.length; i++) {
      if (this.disk[i] != null) {
        checksum += (long) i * this.disk[i];
      }
    }
    return checksum;
  }
}
