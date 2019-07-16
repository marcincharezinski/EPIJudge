package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class IntAsArrayIncrement {
    @EpiTest(testDataFile = "int_as_array_increment.tsv")
    public static List<Integer> plusOne(List<Integer> A) {
        int lastIndex = A.size() - 1;
        int lastValue = A.get(lastIndex);

        if (lastValue != 9) {
            A.set(lastIndex, lastValue + 1);
        } else {
            while (A.get(lastIndex) == 9 && lastIndex != 0) {
                A.set(lastIndex, 0);
                lastIndex--;
            }
            if (lastIndex == 0 && A.get(lastIndex) == 9) {
                A.set(lastIndex, 1);
                A.add(0);
            } else {
                A.set(lastIndex, A.get(lastIndex) + 1);
            }
        }
        return A;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
