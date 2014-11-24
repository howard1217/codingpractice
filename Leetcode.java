import java.util.*;
import java.math.*;

/*
To do problems:
http://www.careercup.com/question?id=6702347652694016
http://www.careercup.com/question?id=19286747
http://www.careercup.com/question?id=6287528252407808
http://www.careercup.com/question?id=4681660918398976
http://www.cnblogs.com/feiling/p/3235977.html
http://stackoverflow.com/questions/8189334/google-combinatorial-optimization-interview-problem
*/
public class Leetcode {
    
    /* Definition of ListNode. */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }

        @Override
        public String toString() {
            StringBuilder str = new StringBuilder();
            ListNode ptr = this;
            while (ptr != null) {
                str.append(ptr.val);
                if (ptr.next != null) {
                    str.append("->");
                    ptr = ptr.next;
                    continue;
                } else break;
            }
            return new String(str);
        }
    }

    /* Definition of TreeNode. */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }

        @Override
        public String toString() {
            StringBuilder str = new StringBuilder();
            str.append("{");
            LinkedList<TreeNode> fringe = new LinkedList<TreeNode>();
            fringe.add(this);
            int numOfNull = 0;
            while (!fringe.isEmpty()) {
                TreeNode temp = fringe.remove();
                if (temp != null) {
                    for (int i = 0; i < numOfNull; i += 1) {
                        str.append("#");
                        str.append(",");
                    }
                    numOfNull = 0;
                    fringe.add(temp.left);
                    fringe.add(temp.right);
                    str.append(temp.val);
                    str.append(",");
                } else numOfNull += 1;
            }
            str.replace(str.length() - 1, str.length(), "}");
            return new String(str);
        }
    }

    public static String multiply(String num1, String num2) {
        String reverse1;
        String reverse2;
        if (num1.length() >= num2.length()) {
            reverse1 = new StringBuffer(num1).reverse().toString();
            reverse2 = new StringBuffer(num2).reverse().toString();
        } else {
            reverse1 = new StringBuffer(num2).reverse().toString();
            reverse2 = new StringBuffer(num1).reverse().toString();
        }
        char[] char1 = reverse1.toCharArray();
        char[] char2 = reverse2.toCharArray();
        Stack<Integer> fringe = new Stack<Integer>();
        int tens = 0;
        int decimal = 0;
        int sum = 0;
        for (int i = 0; i < char1.length + char2.length - 1; i += 1) {
            for (int j = 0; j <= i; j += 1) {
                if (char1.length > (i - j) && char2.length > j) {
                    int product = (char1[i - j] - 48) * (char2[j] - 48);
                    sum += product;
                }
            }
            sum += tens;
            tens = sum / 10;
            decimal = sum % 10;
            fringe.push(decimal);
            if (tens != 0 && i == char1.length + char2.length - 2) {
                fringe.push(tens);
            }
            sum = 0;
        }
        String result = "";
        boolean start = false;
        while (!fringe.empty()) {
            int toAdd = fringe.pop();
            if (!start) {
                if (toAdd != 0) {
                    result += Integer.toString(toAdd);
                    start = true;
                }
            } else {
                result += Integer.toString(toAdd);
            }
        }
        if (result.length() == 0) {
            result += Integer.toString(0);
        }
        return result;
    }

    public static int searchInRotatedArray(int[] A, int target) {
        return searchHelper(A, target, 0, A.length - 1);
    }

    public static int searchHelper(int[] A, int target, int min, int max) {
        int mid = (min + max) / 2;
        if (min > max) {
            return -1;
        } else if (A[mid] == target) {
            return mid;
        } else if (A[mid] > A[max]) {
            if (A[mid] > target) {
                int result1 = searchHelper(A, target, min, mid - 1);
                int result2 = searchHelper(A, target, mid + 1, max);
                return Math.max(result1, result2);
            } else {
                return searchHelper(A, target, mid + 1, max);
            }
        } else if (A[mid] < A[min]) {
            if (A[mid] < target) {
                int result1 = searchHelper(A, target, min, mid - 1);
                int result2 = searchHelper(A, target, mid + 1, max);
                return Math.max(result1, result2);
            } else {
                return searchHelper(A, target, min, mid - 1);
            }
        } else {
            if (A[mid] > target) {
                return searchHelper(A, target, min, mid - 1);
            } else {
                return searchHelper(A, target, mid + 1, max);
            }
        }
    }

    public static ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        HashSet<Integer> keys = new HashSet<Integer>();
        for (int i = 0; i < candidates.length; i += 1) {
            int temp = candidates[i];
            if (!keys.contains(temp)) {
                ArrayList<Integer> toAdd = new ArrayList<Integer>();
                toAdd.add(temp);
                keys.add(temp);
                sumHelper(result, toAdd, candidates, temp, target);
            }
        }
        return result;
    }

    public static void sumHelper(ArrayList<ArrayList<Integer>> result
        , ArrayList<Integer> toAdd, int[] candidates, int currSum, int target) {
        HashSet<Integer> keys = new HashSet<Integer>();
        if (currSum > target) {
            return;
        } else if (currSum == target) {
            result.add(toAdd);
            return;
        } else {
            int max = toAdd.get(toAdd.size() - 1);
            for (int i = 0; i < candidates.length; i += 1) {
                int curr = candidates[i];
                if (curr >= max && !keys.contains(curr)) {
                    ArrayList<Integer> newToAdd = new ArrayList<Integer>(toAdd);
                    newToAdd.add(curr);
                    keys.add(curr);
                    sumHelper(result, newToAdd, candidates, currSum + curr, target);
                }
            }
        }
    }

    /** Given an input string, reverse the string word by word. */
    public static String reverseWords(String s) {
        String newS = s.trim();
        String[] temp = newS.split("\\s+");
        reverseArray(temp);
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < temp.length - 1; i += 1) {
            result.append(temp[i]);
            result.append(" ");
        }
        if (temp.length > 0) {
            result.append(temp[temp.length - 1]);
        }
        return result.toString();
    }

    public static void reverseArray(String[] a) {
        int bound = a.length / 2;
        for (int i = 0; i < bound; i += 1) {
            String temp = a[i];
            a[i] = a[a.length - i - 1];
            a[a.length - i - 1] = temp;
        }
    }

    /** Given a linked list, return the node where the cycle begins.
      * If there is no cycle, return null. */
    // Checked answer
    public boolean hasCycle(ListNode head) {
        ListNode n1 = head;
        ListNode n2 = head;
        while (n1 != null && n1.next != null) {
            n1 = n1.next.next;
            n2 = n2.next;
            if (n1 == n2) {
                return true;
            }
        }
        return false;
    }

    public ListNode detectCycle(ListNode head) {
        ListNode n1 = head;
        ListNode n2 = head;
        while (n1 != null && n1.next != null) {
            n1 = n1.next.next;
            n2 = n2.next;
            if (n1 == n2) {
                return n1;
            }
        }
        return null;
    }


    /** Given two words (start and end), and a dictionary, find all shortest 
     *  transformation sequence(s) from start to end. */
    // Checked answer
    public static List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> result = new ArrayList<List<String>>();
        ArrayList<String> elem = new ArrayList<String>();
        if (start == null || end == null) return result;
        if (start.equals(end)) {
            elem.add(start);
            elem.add(end);
            result.add(elem);
            return result;
        }
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        map.put(end, new ArrayList<String>());
        map.put(start, new ArrayList<String>());
        for (String s : dict) {
            map.put(s, new ArrayList<String>());
        }
        ArrayDeque<String> fringe = new ArrayDeque<String>();
        ArrayList<String> currentlevel = new ArrayList<String>();
        fringe.add(start);
        while (!fringe.isEmpty()) {
            int level = fringe.size();
            currentlevel.clear();
            for (int i = 0; i < level; i += 1) {
                String top = fringe.remove();
                if (dict.contains(top)) dict.remove(top);
                currentlevel.add(top);
            }
            for (String curs : currentlevel) {
                for (int i = 0; i < curs.length(); i += 1) {
                    for (char j = 'a'; j < 'z'; j += 1) {
                        char[] tmpchar = curs.toCharArray();
                        tmpchar[i] = j;
                        String tmps = new String(tmpchar);
                        if ((!tmps.equals(curs) && dict.contains(tmps)) || tmps.equals(end)) {
                            if (!fringe.contains(tmps)) fringe.add(tmps);
                            map.get(tmps).add(curs);
                        }
                    }
                }
            }
            if (fringe.contains(end)) break;
        }
        elem.add(end);
        buildpath(start, end, map, elem, result);
        return result;
    }

    public static void buildpath(String start, String end, HashMap<String, ArrayList<String>> map,
        ArrayList<String> elem, List<List<String>> result) {
        ArrayList<String> pre = map.get(end);
        if (end.equals(start)) {
            ArrayList<String> temp = new ArrayList<String>(elem);
            Collections.reverse(temp);
            result.add(temp);
            return;
        }
        for (String s : pre) {
            elem.add(s);
            buildpath(start, s, map, elem, result);
            elem.remove(elem.size() - 1);
        }
    }

    /* Sort a linked list using insertion sort. */
    public static ListNode insertionSortList(ListNode head) {
        if (head == null) return null;
        ListNode curr = head.next;
        ListNode prev = head;
        boolean changed = false;
        while (curr != null) {
            if (curr.val <= head.val) {
                prev.next = curr.next;
                curr.next = head;
                head = curr;
                curr = prev.next;
                changed = false;
                continue;
            }
            ListNode tempprev = head;
            ListNode temp = head.next;
            while (temp != curr) {
                if (curr.val > temp.val) { 
                    tempprev = tempprev.next;
                    temp = temp.next;
                } else {
                    tempprev.next = curr;
                    prev.next = curr.next;
                    curr.next = temp;
                    curr = prev.next;
                    changed = true;
                    break;
                }
                changed = false;
            }
            if (!changed) {
                prev = prev.next;
                curr = curr.next;
            }
        }
        return head;
    }

    /* Given an unsorted integer array, find the first missing positive integer. */
    // Checked answer
    public static int firstMissingPositive(int[] A) {
        for (int i = 0; i < A.length; i += 1) {
            while (A[i] >= 1 && A[i] <= A.length && A[i] != A[A[i] - 1]) {
                swap(A, i, A[i] - 1);
            }
        }
        for (int j = 0; j < A.length; j += 1) {
            if (A[j] - 1 != j) return j + 1;
        }
        return A.length + 1;
    }

    public static void swap(int[] A, int i, int j) {
        int temp;
        temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    /** Given a binary tree and a sum, find all root-to-leaf 
     *  paths where each path's sum equals the given sum. */
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;
        List<Integer> path = new ArrayList<Integer>();
        path.add(root.val);
        pathSumHelper(root, sum, root.val, path, result);
        return result;
    }

    public static void pathSumHelper(TreeNode node, int sum
        , int currSum, List<Integer> path, List<List<Integer>> result) {
        if (currSum == sum && node.left == null && node.right == null) {
            result.add(path);
        } else {
            if (node.left != null) {
                List<Integer> left = new ArrayList<Integer>(path);
                left.add(node.left.val);
                pathSumHelper(node.left, sum, currSum + node.left.val, left, result);
            }
            if (node.right != null) {
                List<Integer> right = new ArrayList<Integer>(path);
                right.add(node.right.val);
                pathSumHelper(node.right, sum, currSum + node.right.val, right, result);
            }
        }
    }

    /** A robot is located at the top-left corner of a m x n grid
     *  The robot can only move either down or right at any point in time.
     *  The robot is trying to reach the bottom-right corner of the grid.
     *  How many possible unique paths are there?. */
    public static int uniquePaths(int m, int n) {
        int[][] grid = new int[m][n];
        grid[0][0] = 1;
        if (grid[m - 1][n - 1] == 0) uniquePathsHelper(grid, m - 1, n - 1);
        return grid[m - 1][n - 1];
    }

    public static void uniquePathsHelper(int[][] grid, int x, int y) {
        int up = 0;
        int left = 0;
        if (x > 0) {
            if (grid[x - 1][y] == 0) uniquePathsHelper(grid, x - 1, y);
            left = grid[x - 1][y];
        }
        if (y > 0) {
            if (grid[x][y - 1] == 0) uniquePathsHelper(grid, x, y - 1);
            up = grid[x][y - 1];
        }
        grid[x][y] = left + up;
    }

    /** Given an unsorted array of integers, 
     *  find the length of the longest consecutive elements sequence. */
    public static int longestConsecutive(int[] num) {
        if (num == null) return 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        boolean[] sthBefore = new boolean[num.length];
        for (int i = 0; i < num.length; i += 1) map.put(num[i], null);
        for (int j = 0; j < num.length; j += 1) {
            if (map.containsKey(num[j] - 1)) {
                map.put(num[j] - 1, num[j]);
                sthBefore[j] = true;
            } else {
                sthBefore[j] = false;
            }
        }
        int result = 1;
        int counter = 0;
        for (int k = 0; k < num.length; k += 1) {
            if (sthBefore[k] == false) {
                Integer temp = num[k];
                while (temp != null) {
                    temp = map.get(temp);
                    counter += 1;
                }
                if (counter > result) result = counter;
                counter = 0;
            }
        }
        return result;
    }

    /** Given a collection of numbers that might contain duplicates,
     *  return all possible unique permutations. */
    //Checked answer
    public static List<List<Integer>> permuteUnique(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        permuteUnique(num, 0, result);
        return result;
    }

    public static void permuteUnique(int[] num, int start, List<List<Integer>> result) {
        if (start >= num.length) {
            ArrayList<Integer> toAdd = new ArrayList<Integer>();
            for (int j = 0; j < num.length; j += 1) {
                toAdd.add(num[j]);
            }
            result.add(toAdd);
            return;
        }
        for (int i = start; i < num.length; i += 1) {
            if (!containsDuplicate(num, start, i)) {
                swap(num, start, i);
                permuteUnique(num, start + 1, result);
                swap(num, start, i);
            }
        }
    }

    public static boolean containsDuplicate(int[] num, int start, int end) {
        for (int i = start; i < end; i += 1) {
            if (num[i] == num[end]) return true;
        }
        return false;
    }

    /** The n-queens puzzle is the problem of placing n queens on an n×n chessboard 
     *  such that no two queens attack each other. Given an integer n, return all 
     *  distinct solutions to the n-queens puzzle. For example, there exists two distinct
     *  solutions to the 4-queens puzzle.
     *  [
     *  [".Q..",  // Solution 1
     *  "...Q",
     *  "Q...",
     *  "..Q."],
     *
     *  ["..Q.",  // Solution 2
     *  "Q...",
     *  "...Q",
     *  ".Q.."]
     *  ] */
    public static List<String[]> solveNQueens(int n) {
        int[][] dangerous = new int[n][n];
        boolean[][] board = new boolean[n][n];
        ArrayList<String[]> result = new ArrayList<String[]>();
        if (n <= 0) return result;
        checkAvail(dangerous, board, 0, result);
        return result;
    }

    public static void checkAvail(int[][] dangerous, boolean[][] board, int level,
        ArrayList<String[]> result) {
        for (int i = 0; i < board.length; i += 1) {
            if (dangerous[level][i] == 0) {
                board[level][i] = true;
                if (level == board.length - 1) {
                    addToResult(board, result);
                    board[level][i] = false;
                    return;
                }
                markDangerous(dangerous, level, i, 1);
                checkAvail(dangerous, board, level + 1, result);
                markDangerous(dangerous, level, i, -1);
                board[level][i] = false;
            }
        }
    }

    public static void addToResult(boolean[][] board, ArrayList<String[]> result) {
        String[] toAdd = new String[board.length];
        for (int i = 0; i < board.length; i += 1) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < board.length; j += 1) {
                if (board[i][j]) {
                    row.append("Q");
                } else {
                    row.append(".");
                }
            }
            toAdd[i] = new String(row);
        }
        result.add(toAdd);
    }

    public static void markDangerous(int[][] dangerous, int row, int col, int target) {
        int diff = row - col;
        int sum = row + col;
        for (int j = 0; j < dangerous.length; j += 1) {
            dangerous[j][col] += target;
            dangerous[row][j] += target;
            if ((diff + j >= 0) && (diff + j < dangerous.length)) dangerous[diff + j][j] += target;
            if ((sum - j >= 0) && (sum - j < dangerous.length)) dangerous[j][sum - j] += target;
        }
        dangerous[row][col] -= target * 3;
    }

    public static void printBoard(List<String[]> board) {
        for (String[] temp : board) {
            for (int i = 0; i < board.get(0).length; i += 1) {
                System.out.println(temp[i]);
            }
            System.out.println("+++++++++");
        }
    }

    /** Given a string S, find the longest palindromic substring in S. You may assume that the 
     *  maximum length of S is 1000, and there exists one unique longest palindromic substring. */
    public static String longestPalindrome(String s) {
        if (s == null) return null;
        int len = s.length();
        if (len <= 1) return s;
        String longestString = s.substring(0, 1);
        int longest = 1;
        boolean[][] isPalindrome = new boolean[len][len];
        for (int i = 0; i < len; i += 1) {
            isPalindrome[i][i] = true;
            if (i < len - 1 && s.charAt(i) == s.charAt(i + 1)) {
                isPalindrome[i][i + 1] = true;
                longestString = s.substring(i, i + 2);
                longest = 2;
            }
        }
        for (int l = 3; l <= len; l += 1) {
            for (int i = 0; i <= len - l; i += 1) {
                int j = i + l - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    isPalindrome[i][j] = isPalindrome[i + 1][j - 1];
                    if (isPalindrome[i][j] && l > longest) {
                        longestString = s.substring(i, j + 1);
                        longest = l;
                    }
                }
            }
        }
        return longestString;
    }

    /** Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
     *  https://oj.leetcode.com/problems/scramble-string/ */
    // Checked answer
    public static boolean isScramble(String s1, String s2) {
        if (!isAnagram(s1, s2)) return false;
        if (s1.equals(s2)) return true;
        for (int i = 1; i < s1.length(); i += 1) {
            String s1left = s1.substring(0, i);
            String s1right = s1.substring(i);
            String s2left = s2.substring(0, i);
            String s2right = s2.substring(i);
            if (isScramble(s1left, s2left) && isScramble(s1right, s2right)) return true;
            s2left = s2.substring(s2.length() - i);
            s2right = s2.substring(0, s2.length() - i);
            if (isScramble(s1left, s2left) && isScramble(s1right, s2right)) return true;
        }
        return false;
    }

    public static boolean isAnagram(String s1, String s2) {
        if (s1 == null && s2 == null) return true;
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < s1.length(); i += 1) {
            String temp = s1.substring(i, i + 1);
            if (!map.containsKey(temp)) {
                map.put(temp, 1);
            } else {
                map.put(temp, map.get(temp) + 1);
            }
        }
        for (int i = 0; i < s2.length(); i += 1) {
            String temp = s2.substring(i, i + 1);
            if (!map.containsKey(temp)) return false;
            int num = map.get(temp);
            if (num == 1) {
                map.remove(temp);
            } else {
                map.put(temp, num - 1);
            }
        }
        return true;
    }

    /** Given a binary tree, return the zigzag level order traversal of its nodes' values.
     *  (ie, from left to right, then right to left for the next level and alternate between).
     *  https://oj.leetcode.com/problems/binary-tree-zigzag-level-order-traversal/ */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;
        ArrayDeque<TreeNode> fringe1 = new ArrayDeque<TreeNode>();
        ArrayDeque<TreeNode> fringe2 = new ArrayDeque<TreeNode>();
        fringe1.add(root);
        while (!fringe1.isEmpty() || !fringe2.isEmpty()) {
            List<Integer> toAdd1 = new ArrayList<Integer>();
            while (!fringe1.isEmpty()) {
                TreeNode temp1 = fringe1.remove();
                toAdd1.add(temp1.val);
                if (temp1.left != null) fringe2.add(temp1.left);
                if (temp1.right != null) fringe2.add(temp1.right);
            }
            result.add(toAdd1);
            List<Integer> toAdd2 = new ArrayList<Integer>();
            while (!fringe2.isEmpty()) {
                TreeNode temp2 = fringe2.remove();
                toAdd2.add(temp2.val);
                if (temp2.left != null) fringe1.add(temp2.left);
                if (temp2.right != null) fringe1.add(temp2.right);
            }
            Collections.reverse(toAdd2);
            if (toAdd2.size() > 0) result.add(toAdd2);
        }
        return result;
    }

    /** Return the starting gas station's index if you can travel 
     *  around the circuit once, otherwise return -1.
     *  https://oj.leetcode.com/problems/gas-station/ */
    //  Checked answer
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        if (gas == null || cost == null || gas.length == 0 || cost.length == 0 || gas.length != cost.length) return -1;
        int sum = 0; 
        int start = 0;
        for (int i = 0; i < len * 2; i += 1) {
            sum += gas[i % len] - cost[i % len];
            if (sum < 0) {
                start = (i + 1) % len;
                sum = 0;
            } else if (sum >= 0 && start == (i + 1) % len) {
                return start;
            }
        }
        return -1;
    }

    /** Given a string containing just the characters '(' and ')', 
     *  find the length of the longest valid (well-formed) parentheses substring.
     *  https://oj.leetcode.com/problems/longest-valid-parentheses/. */
    public static int longestValidParentheses(String s) {
        if (s == null || s.length() <= 1) return 0;
        int len = s.length();
        int i = 0;
        int sum = 0;
        int longest = 0;
        int currentlength = 0;
        while (i < len) {
            char temp = s.charAt(i);
            currentlength += 1;
            if (temp == '(') sum += 1;
            else sum -= 1;
            if (sum < 0) {
                currentlength = 0;
                sum = 0;
            } else if (sum == 0 && currentlength > longest) longest = currentlength;
            i += 1;
        }
        i = len - 1;
        sum = 0; 
        currentlength = 0;
        while (i >= 0) {
            char temp = s.charAt(i);
            currentlength += 1;
            if (temp == ')') sum += 1;
            else sum -= 1;
            if (sum < 0) {
                currentlength = 0;
                sum = 0;
            } else if (sum == 0 && currentlength > longest) longest = currentlength;
            i -= 1;
        }
        return longest;
    }

    /** Given an array S of n integers, find three integers in S such that the sum is 
     *  closest to a given number, target. Return the sum of the three integers.
     *  You may assume that each input would have exactly one solution.
     *  https://oj.leetcode.com/problems/3sum-closest/ */
    public static int threeSumClosest(int[] num, int target) {
        Arrays.sort(num);
        int sum = 0;
        int result = 0;
        int diff = Integer.MAX_VALUE;
        for (int i = 0; i < num.length; i += 1) {
            int temp = twoSumClosest(num, i, target - num[i]);
            sum = num[i] + temp;
            int sub = Math.abs(target - sum);
            if (sub < diff) {
                diff = sub;
                result = sum;
            }
            if (sub == 0) return sum;
        }
        return result;
    }

    public static int twoSumClosest(int[] num, int curr, int target) {
        int i = 0;
        int j = num.length - 1;
        int diff = Integer.MAX_VALUE;
        int result = 0;
        while (i < j) {
            if (i == curr) i += 1;
            if (j == curr) j -= 1;
            if (i == j) break;
            int sum = num[i] + num[j];
            int sub = Math.abs(target - sum);
            if (sub < diff) {
                diff = sub;
                result = sum;
            }
            if (sum > target) j -= 1;
            else if (sum < target) i += 1;
            else return sum;
        }
        return result;
    }

    /** Given a string S and a string T, find the minimum window 
     *  in S which will contain all the characters in T in complexity O(n).
     *  https://oj.leetcode.com/problems/minimum-window-substring/ */
    // Checked answer (Hard Problem)
    public static String minWindow(String S, String T) {
        if (S == null || T == null || S.length() < T.length()) return "";
        int lenS = S.length();
        int lenT = T.length();
        int[] needToFind = new int[256];
        for (int i = 0; i < lenT; i += 1) needToFind[T.charAt(i)] += 1;
        int[] hasFound = new int[256];
        int min = Integer.MAX_VALUE;
        int countOfCharFound = 0;
        int startPos = 0;
        int endPos = 0;
        for (int i = 0, j = 0; j < lenS; j += 1) {
            int curr = (int) S.charAt(j);
            int begin = (int) S.charAt(i);
            if (needToFind[curr] == 0) continue;
            hasFound[curr] += 1;
            if (hasFound[curr] <= needToFind[curr]) countOfCharFound += 1;
            if (countOfCharFound == lenT) {
                while (needToFind[begin] == 0 ||
                    hasFound[begin] > needToFind[begin]) {
                    if (hasFound[begin] > needToFind[begin]) 
                        hasFound[begin] -= 1;
                    i += 1;
                    begin = (int) S.charAt(i);
                }
                int windowlen = j - i + 1;
                if (windowlen < min) {
                    startPos = i;
                    endPos = j + 1;
                    min = windowlen;
                }
            }
        }
        return S.substring(startPos, endPos);
    }

    /** Given two words word1 and word2, find the minimum number of steps 
     *  required to convert word1 to word2. (each operation is counted as 1 step.) */
    public static int minDistance(String word1, String word2) {
        if (word1 == null && word2 == null) return 0;
        if (word1 == null) return word2.length();
        if (word2 == null) return word1.length();
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dis = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i += 1) dis[i][0] = i;
        for (int j = 0; j <= len2; j += 1) dis[0][j] = j;
        return buildtable(dis, word1, word2, len1, len2);
    }

    public static int buildtable(int[][] dis, String w1, String w2, int i, int j) {
        if (i == 0 || j == 0) return dis[i][j];
        int r1, r2, r3, add;
        add = w1.charAt(i - 1) == w2.charAt(j - 1) ? 0 : 1;
        r1 = dis[i - 1][j] > 0 ? dis[i - 1][j] + 1 
            : buildtable(dis, w1, w2, i - 1, j) + 1;
        r2 = dis[i][j - 1] > 0 ? dis[i][j - 1] + 1 
            : buildtable(dis, w1, w2, i, j - 1) + 1;
        r3 = dis[i - 1][j - 1] > 0 ? dis[i - 1][j - 1] + add
            : buildtable(dis, w1, w2, i - 1, j - 1) + add;
        dis[i][j] = Math.min(r1, Math.min(r2, r3));
        return dis[i][j];
    }

    /** Given n, how many structurally unique BST's 
     *  (binary search trees) that store values 1...n? */
    public static int numTrees(int n) {
        int[] info = new int[n];
        return numTreeHelper(info, n);
    }

    public static int numTreeHelper(int[] info, int n) {
        assert n >= 0;
        if (n <= 1) return 1;
        if (info[n - 1] != 0) return info[n - 1];
        int sum = 0;
        for (int i = 1; i <= n; i += 1) {
            sum += numTreeHelper(info, i - 1) * numTreeHelper(info, n - i);
        }
        info[n - 1] = sum;
        return sum;
    }

    /** Given n, generate all structurally unique BST's 
     *  (binary search trees) that store values 1...n. 
     *  http://oj.leetcode.com/problems/unique-binary-search-trees-ii/ */
    public static List<TreeNode> generateTrees(int n) {
        return treeGenerator(1, n);
    }

    public static List<TreeNode> treeGenerator(int start, int end) {
        List<TreeNode> result = new ArrayList<TreeNode>();
        if (start > end) {
            result.add(null);
            return result;
        }
        for (int i = start; i <= end; i += 1) {
            List<TreeNode> left = treeGenerator(start, i - 1);
            List<TreeNode> right = treeGenerator(i + 1, end);
            for (int j = 0; j < left.size(); j += 1) {
                for (int k = 0; k < right.size(); k += 1) {
                    TreeNode root = new TreeNode(i);
                    root.left = left.get(j);
                    root.right = right.get(k);
                    result.add(root);
                }
            }
        }
        return result;
    }

    /** Two elements of a binary search tree (BST) are swapped by mistake.
     *  https://oj.leetcode.com/problems/recover-binary-search-tree/ */
    public static void recoverTree(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        inorderTraversal(nodes, root);
        int first = 0;
        int second = 0;
        boolean firstIsFound = false;
        for (int i = 0; i < nodes.size() - 1; i += 1) {
            TreeNode curr = nodes.get(i);
            TreeNode next = nodes.get(i + 1);
            if (next.val < curr.val && !firstIsFound) {
                first = i;
                second = i + 1;
                firstIsFound = true;
            } else if (next.val < curr.val) {
                second = i + 1;
            }
        }
        int temp = nodes.get(first).val;
        nodes.get(first).val = nodes.get(second).val;
        nodes.get(second).val = temp; 
    }

    public static void inorderTraversal(List<TreeNode> nodes, TreeNode root) {
        if (root == null) return;
        inorderTraversal(nodes, root.left);
        nodes.add(root);
        inorderTraversal(nodes, root.right);
    }

    /** Given a sorted array, remove the duplicates in place such that each 
     *  element appear only once and return the new length.
     *  https://oj.leetcode.com/problems/remove-duplicates-from-sorted-array/ */
    public static int removeDuplicates(int[] A) {
        if (A == null || A.length == 0) return 0;
        int len = A.length;
        int dup = A[0];
        int toMove = 0;
        for (int i = 1; i < len; i += 1) {
            if (A[i] == dup) toMove += 1;
            else dup = A[i];
            A[i - toMove] = A[i];
        }
        return len - toMove;
    }

    /** Given a roman numeral, convert it to an integer. */
    public static int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int num = 0;
        int val;
        for (int i = 0; i < s.length(); i += 1) {
            val = map.get(s.charAt(i));
            if (i == s.length() - 1 || map.get(s.charAt(i + 1)) <= val) {
                num += val;
            } else {
                num -= val;
            }
        }
        return num;
    }

    /** Given an integer, convert it to a roman numeral.
     *  https://oj.leetcode.com/problems/integer-to-roman/ */
    public static String intToRoman(int num) {
        Map<Integer, Character> map = new HashMap<Integer, Character>();
        map.put(1, 'I');
        map.put(5, 'V');
        map.put(10, 'X');
        map.put(50, 'L');
        map.put(100, 'C');
        map.put(500, 'D');
        map.put(1000, 'M');
        StringBuilder str = new StringBuilder();
        String s = ((Integer) num).toString();
        int len = s.length();
        for (int i = 0; i < len; i += 1) {
            int pow = 1;
            for (int j = 0; j < len - i - 1; j += 1) pow *= 10;
            int curr = (int) s.charAt(i) - 48;
            if (curr == 4 || curr == 9) {
                str.append(map.get(pow));
                str.append(map.get((curr + 1) * pow));
                curr = 0;
            } else if (curr >= 5) {
                str.append(map.get(5 * pow));
                curr -= 5;
            }
            if (curr < 4) {
                for (int j = 0; j < curr; j += 1) 
                    str.append(map.get(pow));
            }
        }
        return new String(str);
    }
}

