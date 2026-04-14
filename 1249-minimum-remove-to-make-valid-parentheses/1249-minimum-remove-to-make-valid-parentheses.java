import java.util.*;

class Solution {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        char[] arr = s.toCharArray();

        // Step 1: mark invalid ')'
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') {
                stack.push(i);
            } else if (arr[i] == ')') {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    arr[i] = '#'; // mark invalid
                }
            }
        }

        // Step 2: mark remaining '('
        while (!stack.isEmpty()) {
            arr[stack.pop()] = '#';
        }

        // Step 3: build result
        StringBuilder result = new StringBuilder();
        for (char c : arr) {
            if (c != '#') {
                result.append(c);
            }
        }

        return result.toString();
    }
}