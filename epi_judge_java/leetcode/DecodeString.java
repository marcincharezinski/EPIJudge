package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class DecodeString {

    public static void main(String[] args) {
        String s = "3[a]2[bc]";
        String solution = "aaabcbc";

        if (decodeString(s).equals(solution)) {
            System.out.println("YES");
        } else {
            System.out.println("No: " + decodeString(s));

        }
    }


    public static String decodeString(String s) {

        Deque<Character> deque = new ArrayDeque();
        StringBuilder stringBuilder = new StringBuilder();
        List<Character> result = new ArrayList<>();


        return "";
    }

}

