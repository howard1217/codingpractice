import java.util.*;
import java.math.*;

public class Leetcode2 extends Leetcode {

	/** Given a string s, partition s such that every substring of the 
	 *  partition is a palindrome.
	 *  https://oj.leetcode.com/problems/palindrome-partitioning/ */
	public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        if (s == null || s.length() == 0) return result;
        int len = s.length();
        boolean[][] isPalindrome = new boolean[len][len];
        //build the palindrome table
        for (int i = 0; i < len; i += 1) isPalindrome[i][i] = true;
        for (int i = 0; i < len - 1; i += 1) 
        	isPalindrome[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
        for (int l = 3; l <= len; l += 1) {
        	for (int i = 0; i <= len - l; i += 1)
        		isPalindrome[i][i + l - 1]
        			= (s.charAt(i) == s.charAt(i + l - 1))
        			&& isPalindrome[i + 1][i + l - 2];
        }
        partitionHelper(s, isPalindrome, result, new ArrayList<String>(), 0);
        return result;
    }

    public static void partitionHelper(String s, boolean[][] isPalindrome,
    	List<List<String>> result, List<String> curr, int start) {
    	int len = s.length();
    	if (start == len) {
    		result.add(curr);
    		return;
    	}
    	for (int i = start; i < len; i += 1) {
    		if (isPalindrome[start][i]) {
    			List<String> toAdd = new ArrayList<String>(curr);
    			toAdd.add(s.substring(start, i + 1));
    			partitionHelper(s, isPalindrome, result, toAdd, i + 1);
    		}
    	}
    }

    /** Given a string s, partition s such that every substring of the 
     *  partition is a palindrome.
     *  https://oj.leetcode.com/problems/palindrome-partitioning-ii/ */
    public static int minCut(String s) {
        int len = s.length();
        boolean[][] isPalindrome = new boolean[len][len];
        //build the palindrome table
        for (int i = 0; i < len; i += 1) isPalindrome[i][i] = true;
        for (int i = 0; i < len - 1; i += 1) 
        	isPalindrome[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
        for (int l = 3; l <= len; l += 1) {
        	for (int i = 0; i <= len - l; i += 1)
        		isPalindrome[i][i + l - 1]
        			= (s.charAt(i) == s.charAt(i + l - 1))
        			&& isPalindrome[i + 1][i + l - 2];
        }
        int[] minSteps = new int[len + 1];
        for (int i = 0; i <= len; i += 1) minSteps[i] = i + 1;
        minCutHelper(isPalindrome, minSteps, 0, 0);
        return minSteps[len];
    }

    public static void minCutHelper(boolean[][] isPalindrome, int[] minSteps,
 		int currCut, int start) {
    	int len = isPalindrome.length;
    	if (currCut > minSteps[start]) return;
    	else minSteps[start] = currCut;
    	for (int i = len - 1; i >= start; i -= 1) {
    		if (isPalindrome[start][i]) {
    			if (i == len - 1) {
    				if (currCut < minSteps[len]) minSteps[len] = currCut;
    				return;
    			}
    			minCutHelper(isPalindrome, minSteps, currCut + 1, i + 1);
    		}
    	}
    }

    /** Given an array of integers, find two numbers such that they add up
     *  to a specific target number.
     *  https://oj.leetcode.com/problems/two-sum/ */
    public static int[] twoSum(int[] numbers, int target) {
        //from number to index
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        int len = numbers.length;
        for (int i = 0; i < len; i += 1) {
            int temp = numbers[i];
            if (map.containsKey(temp)) map.get(temp).add(i + 1);
            else {
                List<Integer> toAdd = new ArrayList<Integer>();
                toAdd.add(i + 1);
                map.put(temp, toAdd);
            }
        }
        Arrays.sort(numbers);
        int[] result = new int[2];
        int p1 = 0;
        int p2 = len - 1;
        while (p2 > p1) {
            int t1 = numbers[p1];
            int t2 = numbers[p2];
            if (t1 * 2 == target) {
                p1 = map.get(t1).get(0);
                p2 = map.get(t1).get(1);
                break;
            } else if (t2 * 2 == target) {
                p1 = map.get(t2).get(0);
                p2 = map.get(t2).get(1);
                break;
            }
            if (t1 + t2 == target) {
                p1 = map.get(t1).get(0);
                p2 = map.get(t2).get(0);
                break;
            } else if (t1 + t2 > target) p2 -= 1;
            else p1 += 1;
        }
        result[0] = p1 < p2 ? p1 : p2;
        result[1] = p1 > p2 ? p1 : p2;
        return result;
    }

    /** There are two sorted arrays A and B of size m and n 
     *  respectively. Find the median of the two sorted arrays.
     *  The overall run time complexity should be O(log (m+n)).
     *  https://oj.leetcode.com/problems/median-of-two-sorted-arrays/ */
    public static double findMedianSortedArrays(int A[], int B[]) {
        int sum = A.length + B.length;
        double r1 = findKthElement(A, B, sum / 2 + 1, A.length - 1, 0,
            B.length - 1, 0);
        double r2 = findKthElement(A, B, (sum - 1) / 2 + 1, A.length - 1, 0,
            B.length - 1, 0);
        return (r1 + r2) / 2.0;
    }
    
    public static double findKthElement(int A[], int B[], int k, int topA, 
        int bottomA, int topB, int bottomB) {
        if (A == null || A.length == 0) return B[k - 1];
        if (B == null || B.length == 0) return A[k - 1];
        if (k <= 4) return findKth(A, B, k, topA, bottomA, topB, bottomB);
        if (topA == bottomA) return dealWithOne(A, B, k, topA, topB, bottomB);
        if (topB == bottomB) return dealWithOne(B, A, k, topB, topA, bottomA);
        if (topA == bottomA + 1) return dealWithTwo(A, B, k, bottomA, topB, bottomB);
        if (topB == bottomB + 1) return dealWithTwo(B, A, k, bottomB, topA, bottomA);
        int sumA = topA + bottomA;
        int sumB = topB + bottomB;
        int numA = topA - bottomA + 1; //number of elements in A
        int numB = topB - bottomB + 1; //number of elements in B
        double medianA = (A[sumA / 2] + A[(sumA + 1) / 2]) / 2.0;
        double medianB = (B[sumB / 2] + B[(sumB + 1) / 2]) / 2.0;
        if (medianA > medianB) {
            if ((numA + numB + 1) / 2 > k) {
                int newTopA = (sumA + 1) / 2;
                return findKthElement(A, B, k, 
                    newTopA, bottomA, topB, bottomB);
            } else {
                int newBottomB = sumB / 2;
                return findKthElement(A, B, k - (newBottomB - bottomB),
                    topA, bottomA, topB, newBottomB);
            }
        } else {
            if ((numA + numB + 1) / 2 > k) {
                int newTopB = (sumB + 1) / 2;
                return findKthElement(A, B, k, 
                    topA, bottomA, newTopB, bottomB);
            } else {
                int newBottomA = sumA / 2;
                return findKthElement(A, B, k - (newBottomA - bottomA), 
                    topA, newBottomA, topB, bottomB);
            }
        }
    }

    public static double findKth(int[] A, int[] B, int k, int topA,
        int bottomA, int topB, int bottomB) {
        ArrayList<Integer> toSort =  new ArrayList<Integer>();
        for (int i = bottomA; i <= topA && i <= bottomA + 3; i += 1)
            toSort.add(A[i]);
        for (int i = bottomB; i <= topB && i <= bottomB + 3; i += 1)
            toSort.add(B[i]);
        Collections.sort(toSort);
        return toSort.get(k - 1);
    }

    public static double dealWithOne(int[] onlyOne, int[] other, int k,
        int one, int top, int bottom) {
        int num = onlyOne[one];
        if (num >= other[bottom + k - 1]) return (double) other[bottom + k - 1];
        if (num < other[bottom + k - 1] && num > other[bottom + k - 2]) return (double) num;
        return (double) other[bottom + k - 2];
    }

    public static double dealWithTwo(int[] onlyTwo, int[] other, int k,
        int low, int top, int bottom) {
        int small = onlyTwo[low];
        int large = onlyTwo[low + 1];
        if (small >= other[bottom + k - 1]) return (double) other[bottom + k - 1];
        if (small < other[bottom + k - 1] && large >= other[bottom + k - 1])
            return dealWithOne(onlyTwo, other, k, low, top, bottom);
        if (small >= other[bottom + k - 2]) return small;
        if (small < other[bottom + k - 2]) {
            if (large >= other[bottom + k - 2]) return other[bottom + k - 2];
            if (large >= other[bottom + k - 3]) return large;
        }
        return other[bottom + k - 3];
    }

    /** Sort a linked list in O(n log n) time using constant space complexity.
     *  https://oj.leetcode.com/problems/sort-list/ */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode left = head;
        ListNode right = head;
        while (right.next != null && right.next.next != null) {
            left = left.next;
            right = right.next.next;
        }
        right = left.next;
        left.next = null;
        left = head;
        left = sortList(left);
        right = sortList(right);
        ListNode headPointer, otherPointer;
        if (right.val < left.val) {
            head = right;
            headPointer = right;
            otherPointer = left;
        } else {
            head = left;
            headPointer = left;
            otherPointer = right;
        } while (headPointer != null && otherPointer != null) {
            int currVal = headPointer.val;
            if (headPointer.next == null) {
                headPointer.next = otherPointer;
                break;
            }
            int nextVal = headPointer.next.val;
            int otherVal = otherPointer.val;
            if (otherVal >= currVal && otherVal <= nextVal) {
                ListNode temp = headPointer.next;
                headPointer.next = otherPointer;
                otherPointer = otherPointer.next;
                headPointer.next.next = temp;
                headPointer = headPointer.next;
                continue;
            } else if (otherVal > nextVal) headPointer = headPointer.next;
        }
        return head;
    }

    /** Given a singly linked list L: L0→L1→…→Ln-1→Ln, reorder it to:
     *  L0→Ln→L1→Ln-1→L2→Ln-2→… Do this in-place without altering the
     *  nodes' values.
     *  https://oj.leetcode.com/problems/reorder-list/ */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode left = head;
        ListNode right = head;
        while (right.next != null && right.next.next != null) {
            left = left.next;
            right = right.next.next;
        }
        right = reverseList(left.next);
        left.next = null;
        left = head;
        mergeList(left, right);
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode curr = head;
        ListNode next = head;
        while (true) {
            next = curr.next;
            if (next == null) return head;
            curr.next = next.next;
            next.next = head;
            head = next;
        }
    }

    public void mergeList(ListNode left, ListNode right) {
        while (right.next != null) {
            ListNode tempLeft = left.next;
            ListNode tempRight = right.next;
            left.next = right;
            left.next.next = tempLeft;
            left = tempLeft;
            right = tempRight;
        }
        right.next = left.next;
        left.next = right;
    }

    /** Given a string, find the length of the longest substring without 
     *  repeating characters. For example, the longest substring without
     *  repeating letters for "abcabcbb" is "abc", which the length is 3.
     *  For "bbbbb" the longest substring is "b", with the length of 1.
     *  https://oj.leetcode.com/problems/longest-substring-without-repeating-characters/ */
    // checked answer
    public int lengthOfLongestSubstring(String s) {
        if (s == null) return 0;
        Set<Character> set = new HashSet<Character>();
        int result = 0;
        int back = 0;
        char[] arr = s.toCharArray();
        for (int front = 0; front < s.length(); front += 1) {
            char c = arr[front];
            if (!set.contains(c)) {
                set.add(c);
            } else {
                result = Math.max(result, front - back);
                for (int i = back; i <= front; i += 1) {
                    if (arr[i] == c) {
                        back = i + 1;
                        break;
                    }
                    set.remove(arr[i]);
                }
            }
        }
        return Math.max(result, s.length() - back);
    }

    /** You are given two linked lists representing two non-negative numbers.
     *  The digits are stored in reverse order and each of their nodes contain
     *  a single digit. Add the two numbers and return it as a linked list.
     *  https://oj.leetcode.com/problems/add-two-numbers/ */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        int leftOver = (l1.val + l2.val) / 10;
        ListNode head = new ListNode((l1.val + l2.val) % 10);
        ListNode headPointer = head;
        l1 = l1.next;
        l2 = l2.next;
        while (l1 != null && l2 != null) {
            int sum = (l1.val + l2.val + leftOver) % 10;
            leftOver = (l1.val + l2.val + leftOver) / 10;
            headPointer.next = new ListNode(sum);
            headPointer = headPointer.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        if (l1 != null || l2 != null) {
            if (l1 == null) headPointer.next = l2;
            else headPointer.next = l1;
            while (headPointer.next != null) {
                headPointer = headPointer.next;
                int sum = (leftOver + headPointer.val) % 10;
                leftOver = (leftOver + headPointer.val) / 10;
                headPointer.val = sum;
                if (leftOver == 0) return head;
            }
        }
        if (leftOver > 0) headPointer.next = new ListNode(leftOver);
        return head;
    }

    /** Determine whether an integer is a palindrome. Do this without extra space.
     *  https://oj.leetcode.com/problems/palindrome-number/ */
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int i = 1, j = 1000000000;
        for (; j > x; j /= 10);
        for (; j > i; j /= 10, i *= 10)
            if ((x / j) % 10 != (x / i) % 10) return false;
        return true;
    }

    /** Implement wildcard pattern matching with support for '?' and '*'.
     *  https://oj.leetcode.com/problems/wildcard-matching/ */
    // checked answer (hard problem)
    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        int i = 0, j = 0, star = -1, lastS = 0;
        while (i < sLen) {
            while (j < pLen && p.charAt(j) == '*') {
                star = j;
                j += 1;
                lastS = i;
            }
            if ((j == pLen) || (s.charAt(i) != p.charAt(j) && p.charAt(j) != '?')) {
                if (star < 0) return false;
                else {
                    j = star + 1;
                    i = ++lastS;
                }
            } else {
                j += 1;
                i += 1;
            }
        }
        for (; j < pLen && p.charAt(j) == '*'; j += 1);
        return j == pLen;  
    }

    /** The string "PAYPALISHIRING" is written in a zigzag pattern on a given number 
     *  of rows like this: (you may want to display this pattern in a fixed font for better legibility)
     *  P   A   H   N
     *  A P L S I I G
     *  Y   I   R
     *  And then read line by line: "PAHNAPLSIIGYIR"
     *  Write the code that will take a string and make this conversion given a number 
     *  of rows:
     *  string convert(string text, int nRows);
     *  convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
     *  https://oj.leetcode.com/problems/zigzag-conversion/ */
    public String convert(String s, int nRows) {
        if (nRows == 1 || s == null || s.length() <= 2) return s;
        int group = nRows * 2 - 2;
        StringBuilder[] rows = new StringBuilder[nRows];
        for (int i = 0; i < nRows; i += 1) rows[i] = new StringBuilder();
        for (int i = 0; i < s.length(); i += 1) {
            int remainder = i % group;
            if (remainder < nRows) rows[remainder].append(s.charAt(i));
            else rows[group - remainder].append(s.charAt(i));
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < nRows; i += 1) result.append(new String(rows[i]));
        return new String(result);
    }

    /** Implement atoi to convert a string to an integer.
     *  https://oj.leetcode.com/problems/string-to-integer-atoi/ */
    public int atoi(String str) {
        if (str == null) return 0;
        str = str.trim();
        if (str.length() == 0) return 0;
        char flag = '+';
        int i = 0;
        if (str.charAt(i) == '+') {
            i += 1;
        } else if (str.charAt(i) == '-') {
            flag = '-';
            i += 1;
        }
        double result = 0;
        while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            result = result * 10 + str.charAt(i) - '0';
            i += 1;
        }
        if (flag == '-') result = -result;
        if (result > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (result < Integer.MIN_VALUE) return Integer.MIN_VALUE; 
        return (int) result;     
    }
        
    /** Given n non-negative integers a1, a2, ..., an, where each represents a point 
     *  at coordinate (i, ai). n vertical lines are drawn such that the two endpoints 
     *  of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis
     *  forms a container, such that the container contains the most water.
     *  https://oj.leetcode.com/problems/container-with-most-water/ */
    public int maxArea(int[] height) {
        if (height == null || height.length < 2) return 0;
        int left = 0, right = height.length - 1;
        int result = 0;
        while (left < right) {
            int hLeft = height[left], hRight = height[right];
            if (Math.min(hLeft, hRight) * (right - left) > result)
                result = Math.min(hLeft, hRight) * (right - left);
            if (hLeft < hRight) left += 1;
            else right -= 1;
        }
        return result;
    }

    /** Write a function to find the longest common prefix string amongst an array 
     *  of strings.
     *  https://oj.leetcode.com/problems/longest-common-prefix/ */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        if (strs.length == 1) return strs[0];
        String s0 = strs[0], s1 = strs[1];
        int i = 0; 
        for (; i < s0.length() && i < s1.length() && s0.charAt(i) == s1.charAt(i); i += 1);
        String result = s0.substring(0, i);
        if (strs.length == 2) return result;
        for (i = 2; i < strs.length; i += 1) {
            int j = 0;
            if (strs[i].length() == 0) return "";
            for (; j < result.length() && j < strs[i].length() 
                && strs[i].charAt(j) == result.charAt(j); j += 1);
            result = strs[i].substring(0, j);
            if (result.equals("")) return result;
        }
        return result;
    }

    /** Given an array S of n integers, are there elements a, b, c in S such that 
     *  a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
     *  https://oj.leetcode.com/problems/3sum/ */
    public List<List<Integer>> threeSum(int[] num) {
        if (num == null) return null;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (num.length < 3) return result;
        List<Integer> nums = new ArrayList<Integer>();
        for (int i = 0; i < num.length; i += 1) nums.add(num[i]);
        Collections.sort(nums);
        int prev = Integer.MIN_VALUE;
        for (int j = 0; j < num.length - 2; j += 1) {
            int temp = nums.remove(0);
            if (temp == prev) continue;
            List<List<Integer>> toAdd = twoSum(-temp, nums);
            if (toAdd == null) continue;
            else {
                for (List<Integer> list : toAdd) {
                    list.add(0, temp);
                    result.add(list);
                }
            }
            prev = temp;
        }
        return result;
    }

    public List<List<Integer>> twoSum(int target, List<Integer> nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int left = 0, right = nums.size() - 1;
        while (left < right) {
            int leftNum = nums.get(left), rightNum = nums.get(right);
            int sum = leftNum + rightNum;
            if (sum > target) {
                while (nums.get(right) == rightNum && left < right) right -= 1;
            } else if (sum < target) {
                while (nums.get(left) == leftNum && left < right) left += 1;
            } else if (sum == target) {
                List<Integer> toAdd = new ArrayList<Integer>();
                toAdd.add(leftNum);
                toAdd.add(rightNum);
                result.add(toAdd);
                while (nums.get(left) == leftNum && left < right) left += 1;
            }
        }
        return result;
    }

    /** Given a digit string, return all possible letter combinations that the number
     *  could represent.
     *  https://oj.leetcode.com/problems/letter-combinations-of-a-phone-number/ */
    public List<String> letterCombinations(String digits) {
        String[] arr = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        LinkedList<String> result = new LinkedList<String>();
        result.add("");
        if (digits == null || digits.length() == 0) return result;
        for (int i = 0; i < digits.length(); i += 1) {
            int num = digits.charAt(i) - '0';
            if (num > 1) {
                int len = result.size();
                for (int k = 0; k < len; k += 1) {
                    String s = result.remove();
                    for (int j = 0; j < arr[num].length(); j += 1) {
                        StringBuilder temp = new StringBuilder(s);
                        temp.append(arr[num].charAt(j));
                        result.add(new String(temp));
                    }
                }
            }
        }
        return result;
    }

    /** Given a linked list, remove the nth node from the end of list and return its head.
     *  https://oj.leetcode.com/problems/remove-nth-node-from-end-of-list/ */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        ListNode end = head;
        while (n > 0) {
            if (end.next == null) return head.next;
            end = end.next;
            n -= 1;
        }
        ListNode curr = head;
        while (end.next != null) {
            end = end.next;
            curr = curr.next;
        }
        curr.next = curr.next.next;
        return head;
    }

    /** Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
     *  determine if the input string is valid. The brackets must close in the correct
     *  order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
     *  https://oj.leetcode.com/problems/valid-parentheses/ */
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) return true;
        Set<Character> left = new HashSet<Character>(Arrays.asList('(', '[', '{'));
        Set<Character> right = new HashSet<Character>(Arrays.asList(')', ']', '}'));
        Map<Character, Character> map = new HashMap<Character, Character>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        Stack<Character> used = new Stack<Character>();
        for (int j = 0; j < s.length(); j += 1) {
            char temp = s.charAt(j);
            if (left.contains(temp)) used.push(temp);
            else if (right.contains(temp)) {
                if (used.isEmpty()) return false;
                if (map.get(used.pop()) != temp) return false;
            } else return false;
        }
        return used.isEmpty();
    }

    /** Given n pairs of parentheses, write a function to generate all combinations 
     *  of well-formed parentheses.
     *  https://oj.leetcode.com/problems/generate-parentheses/ */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        if (n <= 0) return result;
        StringBuilder curr = new StringBuilder();
        parHelper(curr, result, n, n);
        return result;
    }

    public void parHelper(StringBuilder curr, List<String> result
        , int left, int right) {
        assert left <= right;
        if (left == 0 && right == 0) {
            result.add(new String(curr));
            return;
        }
        if (right > left) {
            StringBuilder temp = new StringBuilder(new String(curr));
            temp.append(')');
            parHelper(temp, result, left, right - 1);
        }
        if (left > 0) {
            curr.append('(');
            parHelper(curr, result, left - 1, right);
        }
    }

    /** Merge k sorted linked lists and return it as one sorted list.
     *  https://oj.leetcode.com/problems/merge-k-sorted-lists/ */
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.size() == 0) return null;
        if (lists.size() == 1) return lists.get(0);
        int size = lists.size();
        ListNode result = null;
        ListNode head = null;
        PriorityQueue<ListNode> queue
            = new PriorityQueue<ListNode>(size, new ListNodeComparator());
        for (int i = 0; i < size; i += 1) {
            if (lists.get(i) != null) queue.add(lists.get(i));
        }
        while (!queue.isEmpty()) {
            ListNode temp = queue.remove();
            if (temp.next != null) queue.add(temp.next);
            if (result == null) {
                result = temp;
                head = temp;
            } else {
                result.next = temp;
                result = result.next;
            }
        }
        return head;
    }

    public class ListNodeComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode n1, ListNode n2) {
            if (n1.val < n2.val) return -1;
            if (n1.val > n2.val) return 1;
            return 0;
        }
    }

    /** Given a linked list, swap every two adjacent nodes and return its head.
     *  https://oj.leetcode.com/problems/swap-nodes-in-pairs/ */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode curr = head.next;
        ListNode prev = head;
        prev.next = curr.next;
        curr.next = prev;
        head = curr;
        ListNode temp = prev;
        prev = curr;
        curr = temp;
        while (curr.next != null && curr.next.next != null) {
            temp = curr;
            prev = curr.next;
            curr = curr.next.next;
            prev.next = curr.next;
            curr.next = prev;
            temp.next = curr;
            temp = prev;
            prev = curr;
            curr = temp;
        }
        return head;
    }

    /** Given a linked list, reverse the nodes of a linked list k at a time 
     *  and return its modified list.
     *  https://oj.leetcode.com/problems/reverse-nodes-in-k-group/ */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) return head;
        ListNode front = head, end = head, beforeFront = null, afterEnd = null;
        for (int i = 0; i < k - 1; i += 1) {
            front = front.next;
            if (front == null) return head;
            if (i == k - 2) head = front;
        }
        beforeFront = front.next;
        reverseKHelper(beforeFront, front, end, afterEnd);
        while (front != null) {
            ListNode temp = front;
            front = end;
            end = temp;
            afterEnd = front;
            end = front.next;
            for (int j = 0; j < k; j += 1) {
                front = front.next;
                if (front == null) return head;
            }
            beforeFront = front.next;
            reverseKHelper(beforeFront, front, end, afterEnd);
        }
        return head;
    }

    public void reverseKHelper(ListNode beforeFront, ListNode front, ListNode end,
        ListNode afterEnd) {
        ListNode prev = end, curr = end, last = end;
        while (curr != front) {
            curr = prev.next;
            prev.next = prev.next.next;
            curr.next = last;
            last = curr;
        }
        if (afterEnd != null) afterEnd.next = last;
    }

    /** Given an array and a value, remove all instances of that value in
     *  place and return the new length.
     *  https://oj.leetcode.com/problems/remove-element/ */
    public int removeElement(int[] A, int elem) {
        if (A == null || A.length == 0) return 0;
        int front = 0, end = A.length - 1;
        for (; end >= 0 && A[end] == elem; end -= 1);
        while (front < end) {
            if (A[front] == elem) {
                int temp = A[front];
                A[front] = A[end];
                A[end] = temp;
                for (; end >= 0 && A[end] == elem; end -= 1);
            }
            front += 1;
        }
        int result = A[front] == elem ? front : front + 1;
        return result;
    }

    /** Returns a pointer to the first occurrence of needle in haystack,
     *  or null if needle is not part of haystack. 
     *  https://oj.leetcode.com/problems/implement-strstr/ */
    public String strStr(String haystack, String needle) {
        if (haystack == null || needle == null) return null;
        int lenH = haystack.length(), lenN = needle.length();
        if (lenH == 0 && lenN == 0) return "";
        if (lenN == 0) return haystack;
        char first = needle.charAt(0), last = needle.charAt(lenN - 1);
        for (int i = 0; i <= lenH - lenN; i += 1) {
            if (haystack.charAt(i) == first && haystack.charAt(i + lenN - 1) == last)
                if (haystack.substring(i, i + lenN).equals(needle)) 
                    return haystack.substring(i);
        }
        return null;
    }

    /** Divide two integers without using multiplication, division and mod operator.
     *  https://oj.leetcode.com/problems/divide-two-integers/ */
    // REDO with binary shifting (hard problem)
    public int divide(int dividend, int divisor) {
        boolean negative = ((dividend ^ divisor) >> 31) == -1 ? true : false;
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        int old = dividend;
        if (divisor == 0) return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        if (divisor < 0 && dividend < 0) return 1;
        if (divisor < 0 || (divisor > dividend && dividend > 0)) return 0;
        if (dividend < 0) dividend = Integer.MAX_VALUE;
        String s1 = Integer.toString(dividend), s2 = Integer.toString(divisor);
        int len1 = s1.length(); int len2 = s2.length();
        int[] ans = new int[len1 - len2 + 1];
        int pos = 0, tempDiv = Integer.parseInt(s1.substring(0, len2));
        while (pos < ans.length) {
            if (old < 0 && pos == ans.length - 1 && tempDiv != Integer.MAX_VALUE)
                tempDiv += 1;
            while (tempDiv >= divisor) {
                ans[pos] += 1;
                tempDiv -= divisor;
            }
            if (old < 0 && pos == ans.length - 1 && tempDiv + 1 == divisor &&
                divisor > 214748364) ans[pos] += 1;
            if (len1 == pos + len2) break;
            StringBuilder newTempDiv = new StringBuilder(Integer.toString(tempDiv));
            newTempDiv.append(s1.charAt(pos + len2));
            tempDiv = Integer.parseInt(new String(newTempDiv));
            pos += 1;
        }
        StringBuilder str = new StringBuilder();
        int i = 0;
        while (ans[i] == 0 && i < ans.length - 1) i += 1;
        for (; i < ans.length; i += 1) str.append(ans[i]);
        if (negative) str.insert(0, '-');
        return Integer.parseInt(new String(str));
    }

    /**  You are given a string, S, and a list of words, L, that are all of the 
     *   same length. Find all starting indices of substring(s) in S that is a 
     *   concatenation of each word in L exactly once and without any intervening 
     *   characters. For example, given:
     *   S: "barfoothefoobarman", L: ["foo", "bar"]
     *   You should return the indices: [0,9] (order does not matter).
     *   https://oj.leetcode.com/problems/substring-with-concatenation-of-all-words/ 
     *   http://blog.csdn.net/u013027996/article/details/24795107 */
    // hard problem
    public List<Integer> findSubstring(String S, String[] L) {
        List<Integer> result = new ArrayList<Integer>();
        if (S == null || L == null || L.length == 0 || S.length() == 0) return result;
        int lenWord = L[0].length();
        if (lenWord * L.length > S.length()) return result;
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < L.length; i += 1) {
            if (!map.containsKey(L[i])) map.put(L[i], 1);
            else map.put(L[i], map.get(L[i]) + 1);
        }
        for (int i = 0; i < lenWord; i += 1) {
            Map<String, Integer> currMap = new HashMap<String, Integer>();
            int currNumWord = 0, start = i;
            for (int j = i; j <= S.length() - lenWord; j += lenWord) {
                String currWord = S.substring(j, j + lenWord);
                if (!map.containsKey(currWord)) {
                    currMap.clear();
                    start = j + lenWord;
                    currNumWord = 0;
                    continue;
                }
                int toAdd = 1;
                if (currMap.containsKey(currWord)) toAdd += currMap.get(currWord);
                currMap.put(currWord, toAdd);
                currNumWord += 1;
                if (toAdd > map.get(currWord)) {
                    String temp = currWord;
                    while (map.get(temp) < currMap.get(temp)) {
                        currWord = S.substring(start, start + lenWord);
                        start += lenWord;
                        currNumWord -= 1;
                        if (currMap.get(currWord) == 1) currMap.remove(currWord);
                        else currMap.put(currWord, currMap.get(currWord) - 1);
                        if (temp.equals(currWord)) break;
                    }
                }
                if (currNumWord == L.length) {
                    currWord = S.substring(start, start + lenWord);
                    result.add(start);
                    if (currMap.get(currWord) == 1) currMap.remove(currWord);
                    else currMap.put(currWord, currMap.get(currWord) - 1);
                    start += lenWord;
                    currNumWord -= 1;
                }
            }
            currMap.clear();
        }
        Collections.sort(result);
        return result;
    }

    /** Implement next permutation, which rearranges numbers into the lexicographically 
     *  next greater permutation of numbers. If such arrangement is not possible, it 
     *  must rearrange it as the lowest possible order (ie, sorted in ascending order).
     *  The replacement must be in-place, do not allocate extra memory. Here are some 
     *  examples. Inputs are in the left-hand column and its corresponding outputs are
     *  in the right-hand column.
     *  1,2,3 → 1,3,2
     *  3,2,1 → 1,2,3
     *  1,1,5 → 1,5,1
     *  https://oj.leetcode.com/problems/next-permutation/ */
    public void nextPermutation(int[] num) {
        if (num == null || num.length <= 1) return;
        for (int i = num.length - 2; i >= 0; i -= 1) {
            int j = -1;
            for (int k = num.length - 1; k > i; k -= 1) {
                if (num[k] > num[i]) {
                    j = k;
                    break;
                }
            }
            if (j != -1) {
                swap(num, i, j);
                i += 1;
                j = num.length - 1;
                for (; i < j; i += 1, j -= 1) swap(num, i, j);
                return;
            }
        }
        for (int i = 0; i < num.length / 2; i += 1)
            swap(num, i, num.length - i - 1);
    }

    /** Given a sorted array of integers, find the starting and ending position of a 
     *  given target value. Your algorithm's runtime complexity must be in the order
     *  of O(log n). If the target is not found in the array, return [-1, -1]. 
     *  For example, given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4].
     *  https://oj.leetcode.com/problems/search-for-a-range/ */
    public int[] searchRange(int[] A, int target) {
        int[] result = {-1, -1};
        if (A == null || A.length == 0) return result;
        int start = 0, end = A.length - 1;
        int upper = A.length - 1, lower = 0;
        while (upper > lower + 1) {
            if (A[start] < target) lower = start;
            else upper = start;
            start = (upper + lower) / 2;
        }
        if (A[lower] == target) start = lower;
        else if (A[upper] == target) start = upper;
        else return result;
        result[0] = start;
        upper = A.length - 1;
        lower = 0;
        while (upper > lower + 1) {
            if (A[end] <= target) lower = end;
            else upper = end;
            end = (upper + lower) / 2;
        }
        if (A[upper] == target) end = upper;
        else if (A[lower] == target) end = lower;
        result[1] = end; 
        return result;
    }
}
