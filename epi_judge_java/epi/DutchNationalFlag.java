package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class DutchNationalFlag {
  public enum Color { RED, WHITE, BLUE }

  public static void dutchFlagPartition(int pivotIndex, List<Color> A) {


    System.out.println("PIVOT: " + A.get(pivotIndex).ordinal());

    if(A.get(pivotIndex).ordinal() == 2){
      movetoRight(A);
    }
    if(A.get(pivotIndex).ordinal() == 0){
      movetoLeft(A);
    }
    if(A.get(pivotIndex).ordinal() == 1){
      movetoRight(A);
      movetoLeft(A);
    }

  }

  static void movetoRight(List<Color> A){

    int start = 0;
    int end = A.size()-1;

    while(start < end) {

      if (A.get(start).ordinal() == 2) {
        while (A.get(end).ordinal() == 2 && start < end) {
          end--;
        }
        Collections.swap(A, start, end);
        start++;
        end--;
      }

      if (A.get(start).ordinal() != 2) {
        start++;
      }

    }

  }

  static void movetoLeft(List<Color> A){

    int start = 0;
    int end = A.size()-1;

    while(start < end) {

      if (A.get(start).ordinal() != 0) {
        while (A.get(end).ordinal() != 0 && start < end) {
          end--;
        }
        Collections.swap(A, start, end);
        start++;
        end--;
      } else {
        start++;
      }

    }

  }

  @EpiTest(testDataFile = "dutch_national_flag.tsv")
  public static void dutchFlagPartitionWrapper(TimedExecutor executor,
                                               List<Integer> A, int pivotIdx)
          throws Exception {
    List<Color> colors = new ArrayList<>();
    int[] count = new int[3];

    Color[] C = Color.values();
    for (int i = 0; i < A.size(); i++) {
      count[A.get(i)]++;
      colors.add(C[A.get(i)]);
    }

    Color pivot = colors.get(pivotIdx);
    executor.run(() -> dutchFlagPartition(pivotIdx, colors));

    int i = 0;
    while (i < colors.size() && colors.get(i).ordinal() < pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() == pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() > pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    if (i != colors.size()) {
      throw new TestFailure("Not partitioned after " + Integer.toString(i) +
              "th element");
    } else if (count[0] != 0 || count[1] != 0 || count[2] != 0) {
      throw new TestFailure("Some elements are missing from original array");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DutchNationalFlag.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
