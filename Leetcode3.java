import java.util.*;
import java.math.*;

public class Leetcode3 extends Leetcode {

    /** Given a sorted array and a target value, return the index if the target is
     *  found. If not, return the index where it would be if it were inserted in
     *  order. You may assume no duplicates in the array. Here are few examples.
     *  [1,3,5,6], 5 → 2
     *  [1,3,5,6], 2 → 1
     *  [1,3,5,6], 7 → 4
     *  [1,3,5,6], 0 → 0
     *  https://oj.leetcode.com/problems/search-insert-position/ */
    public int searchInsert(int[] A, int target) {
        if (A == null || A.length == 0) return 0;
        int upper = A.length - 1, lower = 0;
        while (upper > lower + 1) {
            int middle = (upper + lower) / 2;
            if (A[middle] > target) upper = middle;
            else if (A[middle] < target) lower = middle;
            else return middle;
        }
        if (A[upper] < target) return upper + 1;
        else if (A[upper] == target || A[lower] < target) return upper;
        else return lower;
    }

    /** The count-and-say sequence is the sequence of integers beginning as follows:
     *  1, 11, 21, 1211, 111221, ...
     *  1 is read off as "one 1" or 11.
     *  11 is read off as "two 1s" or 21.
     *  21 is read off as "one 2, then one 1" or 1211.
     *  Given an integer n, generate the nth sequence.
     *  Note: The sequence of integers will be represented as a string.
     *  https://oj.leetcode.com/problems/count-and-say/ */
    public String countAndSay(int n) {
        if (n < 1) return "";
        String result = "1";
        for (int i = 1; i < n; i += 1) {
            StringBuilder str = new StringBuilder();
            int start = 0, prev = result.charAt(0) - '0', count = 0;
            for (int j = 0; j < result.length(); j += 1) {
                int curr = result.charAt(j) - '0';
                if (curr == prev) count += 1;
                else {
                    str.append(count);
                    str.append(prev);
                    prev = curr;
                    count = 1;
                }
            }
            str.append(count);
            str.append(prev);
            result = new String(str);
        }
        return result;
    }

    /** Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
     *  The Sudoku board could be partially filled, where empty cells are filled
     *  with the character '.'. Note: A valid Sudoku board (partially filled) is
     *  not necessarily solvable. Only the filled cells need to be validated.
     *  https://oj.leetcode.com/problems/valid-sudoku/ */
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != 9) return false;
        Set<Character> set1 = new HashSet<Character>();
        Set<Character> set2 = new HashSet<Character>();
        List<HashSet<Character>> setList = new ArrayList<HashSet<Character>>();
        for (int i = 0; i < 9; i += 1) setList.add(new HashSet<Character>());
        for (int i = 0; i < 9; i += 1) {
            for (int j = 0; j < 9; j += 1) {
                Set<Character> currSet = setList.get((i - i % 3) + j / 3);
                char c1 = board[i][j];
                char c2 = board[j][i];
                if (c1 != '.' && c1 < '1' && c1 > '9') return false;
                else if (set1.contains(c1) || currSet.contains(c1)) return false;
                else if (c1 != '.') {
                    set1.add(c1);
                    currSet.add(c1);
                }
                if (c2 != '.' && c2 < '1' && c2 > '9') return false;
                else if (set2.contains(c2)) return false;
                else if (c2 != '.') set2.add(c2);
            }
            set1.clear();
            set2.clear();
        }
        return true;
    }

    /** Write a program to solve a Sudoku puzzle by filling the empty cells. Empty
     *  cells are indicated by the character '.'. You may assume that there will
     *  be only one unique solution.
     *  https://oj.leetcode.com/problems/sudoku-solver/ */
    public void solveSudoku(char[][] board) {
        if (!isValidSudoku(board)) return;
        dfsSolver(board);
    }

    public boolean dfsSolver(char[][] board) {
        PriorityQueue<SuNode> queue = new PriorityQueue<SuNode>(10, new SuComparator());
        for (int i = 0; i < 9; i += 1) {
            for (int j = 0; j < 9; j += 1) {
                if (board[i][j] == '.') queue.add(new SuNode(i, j, board));
            }
        }
        while (queue.size() > 0) {
            SuNode curr = queue.poll();
            Set<Integer> num = curr.numSet();
            for (Integer n : num) {
                board[curr.row][curr.col] = (char) ('0' + n);
                if (dfsSolver(board)) return true;
            }
            board[curr.row][curr.col] = '.';
            return false;
        }
        return true;
    }

    public class SuNode {
        int row;
        int col;
        char[][] board;
        Set<Integer> num = new HashSet<Integer>();
        public SuNode(int i, int j, char[][] b) {
            row = i;
            col = j;
            board = b;
        }

        public Set<Integer> numSet() {
            for (int i = 1; i <= 9; i += 1) num.add(i);
            for (int i = 0; i < 9; i += 1) {
                int r = board[row][i] - '0';
                int c = board[i][col] - '0';
                int b = board[row - row % 3 + i / 3][col - col % 3 + i % 3] - '0';
                if (num.contains(r)) num.remove(r);
                if (num.contains(c)) num.remove(c);
                if (num.contains(b)) num.remove(b);
            }
            return num;
        }
    }

    public class SuComparator implements Comparator<SuNode> {
        @Override
        public int compare(SuNode n1, SuNode n2) {
            return n1.numSet().size() - n2.numSet().size();
        }
    }

    /** Given a collection of candidate numbers (C) and a target number (T), find
     *  all unique combinations in C where the candidate numbers sums to T. Each
     *  number in C may only be used once in the combination. */
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        Arrays.sort(num);
        List<Integer> curr = new ArrayList<Integer>();
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        if (num != null && num.length > 0) comSumHelper(num, target, 0, curr, 0, result);
        return result;
    }

    public void comSumHelper(int[] num, int target, int currPos
        , List<Integer> curr, int currSum, List<List<Integer>> result) {
        if (currSum > target) return;
        if (currSum == target) {
            result.add(curr);
            return;
        }
        if (currPos >= num.length) return;
        List<Integer> curr2 = new ArrayList<Integer>(curr);
        curr.add(num[currPos]);
        int nextPos = currPos;
        for (; nextPos < num.length && num[nextPos] == num[currPos]; nextPos += 1);
        comSumHelper(num, target, currPos + 1, curr, currSum + num[currPos], result);
        comSumHelper(num, target, nextPos, curr2, currSum, result);
    }

    /** Given n non-negative integers representing an elevation map where the
     *  width of each bar is 1, compute how much water it is able to trap after
     *  raining. For example, given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
     *  https://oj.leetcode.com/problems/trapping-rain-water/ */
    // checked answer

    public int trap(int[] A) {
        if (A == null || A.length == 0) return 0;
        int[] maxL = new int[A.length];
        int[] maxR = new int[A.length];
        int result = 0;
        int l = A[0], r = A[A.length - 1];
        for (int i = 1; i < A.length; i += 1) {
            maxL[i] = l;
            l = A[i] > l ? A[i] : l;
            maxR[A.length - 1 - i] = r;
            r = A[A.length - 1 - i] > r ? A[A.length - 1 - i] : r;
        }
        for (int i = 1; i < A.length - 1; i += 1) {
            int toAdd = (maxL[i] < maxR[i] ? maxL[i] : maxR[i]) - A[i];
            result += toAdd > 0 ? toAdd : 0;
        }
        return result;
    }

    /** Given a collection of numbers, return all possible permutations. For example,
     *  [1,2,3] have the following permutations:
     *  [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
     *  https://oj.leetcode.com/problems/permutations/ */
    public List<List<Integer>> permute(int[] num) {
        List<Integer> numList = new ArrayList<Integer>();
        for (int i = 0; i < num.length; i += 1) numList.add(num[i]);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0) {
            result.add(new ArrayList<Integer>());
            return result;
        }
        permuteHelper(numList, num.length - 1, result);
        return result;
    }

    public void permuteHelper(List<Integer> num, int end, List<List<Integer>> result) {
        if (end == 0) result.add(new ArrayList<Integer>(num));
        else {
            for (int i = end; i >= 0; i -= 1) {
                if (num.get(i) == num.get(end) && i != end) continue;
                Collections.swap(num, i, end);
                permuteHelper(num, end - 1, result);
                Collections.swap(num, i, end);
            }
        }
    }

    /** You are given an n x n 2D matrix representing an image.
     *  Rotate the image by 90 degrees (clockwise).
     *  https://oj.leetcode.com/problems/rotate-image/ */
    public void rotate(int[][] matrix) {
        if (matrix.length <= 1) return;
        int l = matrix.length;
        for (int i = 0; i < l / 2; i += 1) {
            int len = l - i * 2 - 1;
            int[] temp = new int[len];
            for (int j = 0; j < len; j += 1) {
                System.out.printf("i is %s, j is %s\n", i, j);
                temp[j] = matrix[i][j + i];
                matrix[i][j + i] = matrix[l - j - i - 1][i];
                matrix[l - j - i - 1][i] = matrix[l - i - 1][l - j - i - 1];
                matrix[l - i - 1][l - j - i - 1] = matrix[j + i][l - i - 1];
                matrix[j + i][l - i - 1] = temp[j];
            }
        }
    }

    /** Given a 2D binary matrix filled with 0's and 1's,
     *  find the largest rectangle containing all ones and return its area.
     *  https://oj.leetcode.com/problems/maximal-rectangle/ */
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix == null) return 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] numOfOne = new int[row][col];
        for (int j = 0; j < col; j += 1) {
            int numOne = 0;
            for (int i = 0; i < row; i += 1) {
                if (matrix[i][j] == '0') {
                    numOfOne[i][j] = 0;
                    numOne = 0;
                } else {
                    numOne += 1;
                    numOfOne[i][j] = numOne;
                }
            }
        }
        int max = 0;
        for (int i = 0; i < row; i += 1) {
            max = Math.max(maxArea(numOfOne[i]), max);
        }
        return max;
    }

    private int maxArea(int[] heights) {
        if (heights.length == 0 || heights == null) return 0;
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        int i = 0;
        while (i < heights.length) {
            if (stack.isEmpty() || heights[stack.peek()] <= heights[i]) {
                stack.push(i);
                i += 1;
            } else {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(max, height * width);
            }
        }
        while (!stack.isEmpty()) {
            int height = heights[stack.pop()];
            int width = stack.isEmpty() ? i : i - stack.peek() - 1;
            max = Math.max(max, height * width);
        }
        return max;
    }

    /** Implement pow(x, n).
     *  https://oj.leetcode.com/problems/powx-n/ */
    public double pow(double x, int n) {
        if (x == 0) return 0;
        if (n > 0) return power(x, n);
        else return 1 / power(x, -n);
    }

    public double power(double x, int n) {
        if (n == 0) return 1;
        double tmp = power(x, n / 2);
        double result = tmp * tmp;
        if (n % 2 == 1) return result * x;
        return result;
    }

    /** Given a string s and a dictionary of words dict, determine
     *  if s can be segmented into a space-separated sequence of one
     *  or more dictionary words. For example, given
     *  s = "leetcode",
     *  dict = ["leet", "code"].
     *  Return true because "leetcode" can be segmented as "leet code".
     *  https://oj.leetcode.com/problems/word-break/ */
    public boolean wordBreak(String s, Set<String> dict) {
        boolean[] mem = new boolean[s.length() + 1];
        mem[0] = true; // since we can always get to the start
        for (int i = 0; i < s.length(); i += 1) {
            if (mem[i] == false) continue;
            for (String temp : dict) {
                int end = i + temp.length();
                if (end > s.length()) continue;
                if (mem[end]) continue;
                if (s.substring(i, end).equals(temp)) mem[end] = true;
            }
        }
        return mem[s.length()];
    }

    /** Given a triangle, find the minimum path sum from top to bottom.
     *  Each step you may move to adjacent numbers on the row below.
     *  For example, given the following triangle
        [
            [2],
           [3,4],
          [6,5,7],
         [4,1,8,3]
        ]
     *  The minimum path sum from top to bottom is 11
     *  (i.e., 2 + 3 + 5 + 1 = 11). 
     *  https://oj.leetcode.com/problems/triangle/ */
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;
        int[] minSumSoFar = new int[triangle.size()];
        for (int i = 0; i < triangle.size(); i += 1) {
            int[] last = Arrays.copyOf(minSumSoFar, minSumSoFar.length);
            for (int j = 0; j <= i; j += 1) {
                int curr = triangle.get(i).get(j);
                if (j == 0) minSumSoFar[j] = last[j] + curr;
                else if (j == i) minSumSoFar[j] = last[j - 1] + curr;
                else {
                    int toAdd = Math.min(last[j], last[j - 1]);
                    minSumSoFar[j] = toAdd + curr;
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < triangle.size(); i += 1) {
            min = Math.min(minSumSoFar[i], min);
        }
        return min;
    }

    /** Given a string, determine if it is a palindrome, 
     *  considering only alphanumeric characters and ignoring cases.
     *  https://oj.leetcode.com/problems/valid-palindrome/ */
    public boolean isPalindrome(String s) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i += 1) {
            char tmp = s.charAt(i);
            if (tmp <= 'z' && tmp >= 'a') str.append(tmp);
            else if (tmp <= 'Z' && tmp >= 'A') str.append((char) (tmp - 'A' + 'a'));
            else if (tmp <= '9' && tmp >= '0') str.append(tmp);
        }
        String sim = new String(str);
        int p1 = 0;
        int p2 = sim.length() - 1;
        while (p1 < p2) {
            if (sim.charAt(p1) != sim.charAt(p2)) return false;
            p1 += 1;
            p2 -= 1;
        }
        return true;
    }

    /** Given a 2D board and a word, find if the word exists 
     *  in the grid. The word can be constructed from letters 
     *  of sequentially adjacent cell, where "adjacent" cells 
     *  are those horizontally or vertically neighboring. The 
     *  same letter cell may not be used more than once.
     *  For example, given board =
        [
          ["ABCE"],
          ["SFCS"],
          ["ADEE"]
        ]
        word = "ABCCED", -> returns true,
        word = "SEE", -> returns true,
        word = "ABCB", -> returns false.
     *  https://oj.leetcode.com/problems/word-search/ */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) return false;
        if (word.length() == 0) return true;
        boolean[][] used = new boolean[board.length][board[0].length];
        int index = 0;
        for (int i = 0; i < board.length; i += 1) {
            for (int j = 0; j < board[0].length; j += 1) {
                if (existHelper(board, word, used, i, j, index))
                    return true;
            }
        }
        return false;
    }

    public boolean existHelper(char[][] board, String word
        , boolean[][] used, int i, int j, int index) {
        if (index == word.length()) return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length)
            return false;
        if (used[i][j]) return false;
        if (board[i][j] != word.charAt(index)) return false;
        used[i][j] = true;
        boolean result = 
            existHelper(board, word, used, i - 1, j, index + 1)
            || existHelper(board, word, used, i + 1, j, index + 1)
            || existHelper(board, word, used, i, j - 1, index + 1)
            || existHelper(board, word, used, i, j + 1, index + 1);
        used[i][j] = false;
        return result;
    }

    /** Given a collection of integers that might contain 
     *  duplicates, S, return all possible subsets.
        Note:
        Elements in a subset must be in non-descending order.
        The solution set must not contain duplicate subsets.
        For example,
        If S = [1,2,2], a solution is:

        [
          [2],
          [1],
          [1,2,2],
          [2,2],
          [1,2],
          []
        ]
     *  https://oj.leetcode.com/problems/subsets-ii/ */
    public List<List<Integer>> subsetsWithDup(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0) return result;
        Arrays.sort(num);
        int start = 0;
        for (int i = 0; i < num.length; i += 1) {
            if (i != num.length - 1 && num[i] == num[i + 1]) continue;
            List<List<Integer>> last = new ArrayList<List<Integer>>();
            for (List<Integer> l : result) last.add(new ArrayList<Integer>(l));
            for (int j = 0; j < last.size(); j += 1) {
                for (int k = 1; k <= i - start + 1; k += 1) {
                    List<Integer> toAdd = new ArrayList<Integer>(last.get(j));
                    for (int x = 0; x < k; x += 1) toAdd.add(num[i]);
                    result.add(toAdd);
                }
            }
            for (int k = 1; k <= i - start + 1; k += 1) {
                List<Integer> toAdd = new ArrayList<Integer>();
                for (int x = 0; x < k; x += 1) toAdd.add(num[i]);
                result.add(toAdd);
            }
            start = i + 1;
        }
        result.add(new ArrayList<Integer>());
        return result;
    }

    /** Given a matrix of m x n elements (m rows, n columns),
     *  return all elements of the matrix in spiral order.
        For example,
        Given the following matrix:

        [
         [ 1, 2, 3 ],
         [ 4, 5, 6 ],
         [ 7, 8, 9 ]
        ]
     *  You should return [1,2,3,6,9,8,7,4,5].
     *  https://oj.leetcode.com/problems/spiral-matrix/ */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0) return result;
        int m = matrix.length, n = matrix[0].length;
        int level = 0;
        for (; level < (Math.min(m, n) + 1) / 2; level += 1) {
            if (level * 2 + 1 == Math.min(m, n)) {
                if (m < n) {
                    for (int j = level; j < n - level; j += 1) result.add(matrix[level][j]);
                } else {
                    for (int i = level; i < m - level; i += 1) result.add(matrix[i][level]);
                }
                break;
            }
            for (int j = level; j < n - level - 1; j += 1) {
                result.add(matrix[level][j]);
            }
            for (int i = level; i < m - level - 1; i += 1) {
                result.add(matrix[i][n - level - 1]);
            }
            for (int j = n - level - 1; j > level; j -= 1) {
                result.add(matrix[m - level - 1][j]);
            }
            for (int i = m - level - 1; i > level; i -= 1) {
                result.add(matrix[i][level]);
            }
        }
        return result;
    }

    /** Given an array with n objects colored red, white or blue
     *  , sort them so that objects of the same color are adjacent
     *  , with the colors in the order red, white and blue. Here, 
     *  we will use the integers 0, 1, and 2 to represent the color 
     *  red, white, and blue respectively.
     *  Note: You are not suppose to use the library's sort function
     *  for this problem.
     *  https://oj.leetcode.com/problems/sort-colors/ */
    public void sortColors(int[] A) {
        if (A == null || A.length == 0) return;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < A.length; i += 1) {
            if (map.containsKey(A[i])) {
                map.put(A[i], map.get(A[i]) + 1);
            } else {
                map.put(A[i], 1);
            }
        }
        int index = 0;
        for (int i = 0; i <= 2; i += 1) {
            if (!map.containsKey(i)) continue;
            int num = map.get(i);
            for (int j = index; j < num + index; j += 1) A[j] = i;
            index += num;
        }
    }

    /** Insertion Sort from 61B lecture 27 */
    public void insertionsort(int[] A) {
        for (int i = 1; i < A.length; i += 1) {
            int j;
            int x = A[i];
            for (j = i - 1; j >= 0; j -= 1) {
                if (A[j] <= x) break;
                A[j + 1] = A[j];
            }
            A[j + 1] = x;
        }
    }

    /** Given numRows, generate the first numRows of Pascal's triangle.
     *  For example, given numRows = 5,
     *  Return

        [
             [1],
            [1,1],
           [1,2,1],
          [1,3,3,1],
         [1,4,6,4,1]
        ]
     *  https://oj.leetcode.com/problems/pascals-triangle/ */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (numRows <= 0) return result;
        for (int i = 0; i < numRows; i += 1) {
            List<Integer> curr = new ArrayList<Integer>();
            for (int j = 0; j <= i; j += 1) {
                if (i == 0) {
                    curr.add(1);
                    continue;
                }
                int num = 0;
                if (j > 0) num += result.get(result.size() - 1).get(j - 1);
                if (j < i) num += result.get(result.size() - 1).get(j);
                curr.add(num);
            }
            result.add(curr);
        }
        return result;
    }

    /** Given an integer n, generate a square matrix filled with elements
     *  from 1 to n2 in spiral order.
        For example,
        Given n = 3,

        You should return the following matrix:
        [
         [ 1, 2, 3 ],
         [ 8, 9, 4 ],
         [ 7, 6, 5 ]
        ]
     *  https://oj.leetcode.com/problems/spiral-matrix-ii/ */
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int i = 0, j = 0, count = 1, limit = n * n;
        String state = "right";
        while (count <= limit) {
            switch (state) {
                case "right":
                    while (j < n && count <= limit && result[i][j] == 0) {
                        result[i][j] = count;
                        j += 1;
                        count += 1;
                        if (count > limit) break;
                    }
                    i += 1;
                    j -= 1;
                    state = "down";
                    break;
                case "down":
                    while (i < n && count <= limit && result[i][j] == 0) {
                        result[i][j] = count;
                        i += 1;
                        count += 1;
                        if (count > limit) break;
                    }
                    j -= 1;
                    i -= 1;
                    state = "left";
                    break;
                case "left":
                    while (j >= 0 && count <= limit && result[i][j] == 0) {
                        result[i][j] = count;
                        j -= 1;
                        count += 1;
                        if (count > limit) break;
                    }
                    i -= 1;
                    j += 1;
                    state = "up";
                    break;
                case "up":
                    while (i >= 0 && count <= limit && result[i][j] == 0) {
                        result[i][j] = count;
                        i -= 1;
                        count += 1;
                        if (count > limit) break;
                    }
                    j += 1;
                    i += 1;
                    state = "right";
                    break;
            }
        }
        return result;
    }

    /** Write an efficient algorithm that searches for a value in an m x n matrix.
     *  This matrix has the following properties:
        Integers in each row are sorted from left to right.
        The first integer of each row is greater than the last integer of the previous row.
        For example,
        Consider the following matrix:
        [
          [1,   3,  5,  7],
          [10, 11, 16, 20],
          [23, 30, 34, 50]
        ]
        Given target = 3, return true.
     *  https://oj.leetcode.com/problems/search-a-2d-matrix/ */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        if (matrix == null || matrix.length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        int a = 0, b = m * n - 1, curr = (a + b) / 2;
        while (a < b - 1) {
            int i = curr / n, j = curr % n;
            if (matrix[i][j] > target) b = curr;
            else if (matrix[i][j] < target) a = curr;
            else return true;
            curr = (a + b) / 2;
        }
        return (matrix[a / n][a % n] == target) || (matrix[b / n][b % n] == target);
    }
}
