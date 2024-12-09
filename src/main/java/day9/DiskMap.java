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

  public void defragment() {
    var i = 0;
    var tailValue = this.disk.length - 1;

    while (i < this.disk.length && i < tailValue) {
      while (i < this.disk.length && this.disk[i] != null) {
        i++;
      }
      if (i < tailValue) {
        while (this.disk[tailValue] == null) {
          tailValue--;
        }
        if (i < tailValue) {
          this.disk[i] = this.disk[tailValue];
          this.disk[tailValue] = null;
        } else {
          return;
        }
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
