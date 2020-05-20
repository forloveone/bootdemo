package com.leecode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 这个是发现这个网站上的题很好,所以做一下
 */
public class LeecodeTest {
    @Test
    public void twoSum() {
        int[] a = {2, 7, 11, 15};
        int[] ints = twoSum2(a, 9);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

    public int[] twoSum(int[] nums, int target) {
        int[] tar = new int[2];
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] + nums[i + 1] == 9) {
                tar[0] = i;
                tar[1] = i + 1;
                return tar;
            }
        }
        return tar;
    }

    //最优解

    /**
     * 给定 nums = [2, 7, 11, 15], target = 9
     * <p>
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     */
    private int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}

    /*
    给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例：

输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807
     */
//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//
//    }
