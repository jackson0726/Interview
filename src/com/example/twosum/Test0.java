package com.example.twosum;

import java.util.Arrays;

public class Test0 {

    /**
     * Example 1:
     * Input: nums = [2,7,11,15], target = 9
     * Output: [0,1]
     * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
     *
     * Example 2:
     * Input: nums = [3,2,4], target = 6
     * Output: [1,2]
     *
     * Example 3:
     * Input: nums = [3,3], target = 6
     * Output: [0,1]
     */

    public static int[] twoSum(int[] a, int target) {
        int[] answer = new int[2];
        //solve problem here
        return answer;
    }

    public static void main(String[] args) {
        final int[] a = {2, 7, 11, 15};
        System.out.println("a[]: " + Arrays.toString(a));
        final int targetA = 19;
        System.out.println("Output: " + Arrays.toString(twoSum(a, targetA)));

        final int[] b = {3, 2, 4};
        System.out.println("b[]: " + Arrays.toString(b));
        final int targetB = 6;
        System.out.println("Output: " + Arrays.toString(twoSum(b, targetB)));
    }

}
