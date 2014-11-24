import java.util.*;

public class Test extends Leetcode {

    /** Run all tests. */
    public static void main(String[] args) {
        System.out.println("========================================");
        // report("Multiply", testMultiply());
        // report("Serach in Rotated Sorted Array", testSIRSA());
        // report("Combination Sum", testCS());
        // report("Reverse Words in a String", testRWIAS());
        // report("Word Ladder II", testWLII());
        // report("Insertion Sort List", testISL());
        // report("First Missing Positive", testFMP());
        // report("Path Sum II", testPSII());
        // report("Unique Paths", testUP());
        // report("Longest Consecutive Sequence", testLCS());
        // report("Permutations II", testPII());
        // report("N-Queens", testNQ());
        // report("Longest Palindromic Substring", testLPS());
        // report("Scramble String", testSS());
        // report("Binary Tree Zigzag Level Order Traversal", testBTZLOT());
        // report("Gas Station", testGS());
        // report("Longest Valid Parentheses", testLVP());
        // report("3Sum Closest", test3SC());
        // report("Minimum Window Substring", testMWS());
        // report("Edit Distance", testED());
        // report("Unique Binary Search Trees", testUBST());
        // report("Unique Binary Search Trees II", testUBSTII());
        // report("Recover Binary Search Tree", testRBST());
        // report("Remove Duplicates from Sorted Array", testRDFSA());
        // report("Roman to Integer", testRTI());
        // report("Integer to Roman", testITR());
        // report("Palindrome Partitioning", testPP());
        // report("Palindrome Partitioning II", testPPII());
        // report("Two Sum", testTS());
        // report("Median of Two Sorted Arrays", testMOTSA());
        // report("Sort List", testSL());
        // report("Reorder List", testRL());
        // report("Longest Substring Without Repeating Characters", testLSWRC());
        // report("Add Two Numbers", testATN());
        // report("Palindrome Number", testPN());
        // report("Wildcard Matching", testWM());
        // report("ZigZag Conversion", testZC());
        // report("String to Integer (atoi)", testSTI());
        // report("Container With Most Water", testCWMW());
        // report("Longest Common Prefix", testLCP());
        // report("3Sum", test3S());
        // report("Letter Combinations of a Phone Number", testLCOAPN());
        // report("Generate Parentheses", testGP());
        // report("Swap Nodes in Pairs", testSNIP());
        // report("Reverse Nodes in k-Group", testRNIKG());
        // report("Implement strStr()", testIS());
        // report("Divide Two Integers", testDTI());
        // report("Substring with Concatenation of All Words", testSWCOAW());
        // report("Next Permutation", testNP());
        // report("Search for a Range", testSFAR());
        // report("Search Insert Position", testSIP());
        // report("Count and Say", testCAS());
        // report("Sudoku Solver", testSuS());
        // report("Combination Sum II", testCSII());
        // report("Trapping Rain Water", testTRW());
        // report("Permutations", testP());
        // report("Rotate Image", testRI());
        // report("Pow(x, n)", testPow());
        report("Word Search", testWS());
        System.out.println("========================================");
        System.out.printf("Run %s tests. %s passes. %s fails.\n", numPasses
            + numFails, numPasses, numFails);
    }

    /** Print message that test NAME has (if ISOK) or else has not
     *  passed. */
    private static void report(String name, boolean isOK) {
        if (isOK) {
            System.out.printf("%s OK.%n", name);
            numPasses += 1;
        } else {
            System.out.printf("%s FAILS.%n", name);
            numFails += 1;
        }
    }

    /** Return true iff Multiply passes its tests. */
    private static boolean testMultiply() {
        if (multiply("99", "9").equals("891")) return true;
        return false;
    }

    /** Return true iff Serach in Rotated Sorted Array passes its tests. */
    private static boolean testSIRSA() {
        int[] a = {4, 5, 6, 7, 0, 1, 2};
        if (searchInRotatedArray(a, 0) != 4) return false;
        return true;
    }

    /** Return true iff Combination Sum passes its tests. */
    private static boolean testCS() {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        ArrayList<ArrayList<Integer>> result = combinationSum(candidates, target);
        if (result.get(0).get(0) != 2
            || result.get(0).get(1) != 2
            || result.get(0).get(2) != 3
            || result.get(1).get(0) != 7) return false;
        return true;
    }

    /** Return true iff Reverse Words in a String passes its tests. */
    private static boolean testRWIAS() {
        if (reverseWords("the sky is blue").equals("blue is sky the")
            && reverseWords(" 1").equals("1")
            && reverseWords(" ").equals("")
            && reverseWords(" a b ").equals("b a")) return true;
        return false;
    }

    /** Return true iff Word Ladder II passes its tests. */
    private static boolean testWLII() {
        String[] set = {"hot", "dot", "dog", "lot", "log"};
        HashSet<String> dict = new HashSet<String>(Arrays.asList(set));
        List<List<String>> l = findLadders("hit", "cog", dict);
        if (l.get(0).get(0).equals("hit")
            && l.get(0).get(1).equals("hot")
            && l.get(0).get(2).equals("dot")
            && l.get(0).get(3).equals("dog")
            && l.get(0).get(4).equals("cog")
            && l.get(1).get(0).equals("hit")
            && l.get(1).get(1).equals("hot")
            && l.get(1).get(2).equals("lot")
            && l.get(1).get(3).equals("log")
            && l.get(1).get(4).equals("cog")) return true;
        return false;
    }

    /** Return true iff Insertion Sort List passes its tests. */
    private static boolean testISL() {
        int[] a = {4, 5, 6, 7, 0, 1, 2};
        ListNode n1 = new ListNode(3);
        n1.next = new ListNode(4);
        n1.next.next = new ListNode(1);
        n1.next.next.next = new ListNode(5);
        if (insertionSortList(n1).toString().equals("1->3->4->5")) return true;
        return false;
    }

    /** Return true iff First Missing Positive passes its tests. */
    private static boolean testFMP() {
        int[] miss = {1, 1};
        if (firstMissingPositive(miss) == 2) return true;
        return false;
    }

    /** Return true iff Path Sum II passes its tests. */
    private static boolean testPSII() {
        int[] miss = {1, 1};
        TreeNode tree1 = new TreeNode(5);
        tree1.left = new TreeNode(4);
        tree1.right = new TreeNode(8);
        List<List<Integer>> l = pathSum(tree1, 13);
        if (l.get(0).get(0) == 5
            && l.get(0).get(1) == 8) return true;
        return false;
    }

    /** Return true iff Unique Path passes its tests. */
    private static boolean testUP() {
        if (uniquePaths(3, 3) == 6) return true;
        return false;
    }

    /** Return true iff Longest Consecutive Sequence passes its tests. */
    private static boolean testLCS() {
        int[] num = {100, 4, 200, 1, 3, 2};
        if (longestConsecutive(num) == 4) return true;
        return false;
    }

    /** Return true iff Permutations II passes its tests. */
    private static boolean testPII() {
        int[] num3 = {1, 2, 3};
        if (permuteUnique(num3).size() == 6) return true;
        return false;
    }

    /** Return true iff N-Queens passes its tests. */
    private static boolean testNQ() {
        List<String[]> l = solveNQueens(4);
        if (l.get(0)[0].equals(".Q..")
            && l.get(0)[1].equals("...Q")
            && l.get(0)[2].equals("Q...")
            && l.get(0)[3].equals("..Q.")
            && l.get(1)[0].equals("..Q.")
            && l.get(1)[1].equals("Q...")
            && l.get(1)[2].equals("...Q")
            && l.get(1)[3].equals(".Q..")) return true;
        return false;
    }

    /** Return true iff Longest Palindromic Substring passes its tests. */
    private static boolean testLPS() {
        if (longestPalindrome("wejknfasdfdsalkjhew").equals("asdfdsa")
            && longestPalindrome("ccc").equals("ccc")) return true;
        return false;
    }

    /** Return true iff Scramble String passes its tests. */
    private static boolean testSS() {
        if (isScramble("rgtae", "great")
            && isScramble("dbac", "abcd")
            && !isScramble("bdac", "abcd")) return true;
        return false;
    }

    /** Return true iff Binary Tree Zigzag Level Order Traversal passes its tests. */
    private static boolean testBTZLOT() {
        TreeNode tree = new TreeNode(3);
        tree.left = new TreeNode(9);
        tree.right = new TreeNode(20);
        tree.right.left = new TreeNode(15);
        tree.right.right = new TreeNode(7);
        List<List<Integer>> l = zigzagLevelOrder(tree);
        if (l.get(0).get(0) == 3
            && l.get(1).get(0) == 20
            && l.get(1).get(1) == 9
            && l.get(2).get(0) == 15
            && l.get(2).get(1) == 7) return true;
        return false;
    }

    /** Return true iff Gas Station passes its tests. */
    private static boolean testGS() {
        int[] gas = {1, 200, 1};
        int[] cost = {100, 1, 100};
        if (canCompleteCircuit(gas, cost) == 1) return true;
        return false;
    }

    /** Return true iff Longest Valid Parentheses passes its tests. */
    private static boolean testLVP() {
        if (longestValidParentheses("(()") == 2
            && longestValidParentheses("())") == 2
            && longestValidParentheses(")()())") == 4
            && longestValidParentheses("(((((((((((((((((()") == 2) return true;
        return false;
    }

    /** Return true iff 3Sum Closest passes its tests. */
    private static boolean test3SC() {
        int[] num4 = {-1, 2, 1, -4};
        if (threeSumClosest(num4, 1) == 2) return true;
        return false;
    }

    /** Return true iff Minimum Window Substring passes its tests. */
    private static boolean testMWS() {
        if (minWindow("ADOBECODEBANC", "ABC").equals("BANC")
            && minWindow("a", "b").equals("")) return true;
        return false;
    }

    /** Return true iff Unique Binary Search Trees passes its tests. */
    private static boolean testED() {
        if (minDistance("abc", "def") == 3
            && minDistance("banana", "branan") == 2) return true;
        return false;
    }

    /** Return true iff Unique Binary Search Trees passes its tests. */
    private static boolean testUBST() {
        if (numTrees(3) == 5) return true;
        return false;
    }

    /** Return true iff Unique Binary Search Trees II passes its tests. */
    private static boolean testUBSTII() {
        List<TreeNode> treeList = generateTrees(3);
        //trivial and useless
        if (numTrees(3) == 5) return true;
        return false;
    }

    /** Return true iff Unique Binary Search Trees passes its tests. */
    private static boolean testRBST() {
        TreeNode tree = new TreeNode(7);
        tree.left = new TreeNode(4);
        tree.left.left = new TreeNode(2);
        tree.left.right = new TreeNode(8);
        tree.right = new TreeNode(9);
        tree.right.left = new TreeNode(5);
        tree.right.right = new TreeNode(12);
        String sol = "{7,4,9,2,5,8,12}";
        recoverTree(tree);
        if (tree.toString().equals(sol)) return true;
        return false;
    }

    /** Return true iff Unique Binary Search Trees passes its tests. */
    private static boolean testRDFSA() {
        int[] num = {1, 1, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4};
        if (removeDuplicates(num) == 4) return true;
        return false;
    }

    /** Return true iff Roman to Integer passes its tests. */
    private static boolean testRTI() {
        int i = romanToInt("MCMLIV");
        int i1 = romanToInt("MCMXC");
        int i2 = romanToInt("MMXIV");
        if (i == 1954 && i1 == 1990 && i2 == 2014) return true;
        return false;
    }

    /** Return true iff Roman to Integer passes its tests. */
    private static boolean testITR() {
        String i = "MCMLIV";
        String i1 = "MCMXC";
        String i2 = "MMXIV";
        if (intToRoman(1954).equals(i) 
            && intToRoman(1990).equals(i1)
            && intToRoman(2014).equals(i2)) return true;
        return false;
    }

    /** Return true iff Unique Binary Search Trees II passes its tests. */
    private static boolean testPP() {
        String s = "aab";
        String s1 = "efe";
        List<List<String>> l = l2.partition(s);
        List<List<String>> l1 = l2.partition(s1);
        if (l.get(0).get(0).equals("a") && l.get(0).get(1).equals("a")
            && l.get(0).get(2).equals("b") && l.get(1).get(0).equals("aa")
            && l.get(1).get(1).equals("b")) return true;
        return false;
    }

    /** Return true iff Unique Binary Search Trees II passes its tests. */
    private static boolean testPPII() {
        String s = "aab";
        String s1 = "efe";
        int i = l2.minCut(s);
        int i1 = l2.minCut(s1);
        if (i == 1 && i1 == 0) return true;
        return false;
    }

    /** Return true iff Two Sum passes its tests. */
    private static boolean testTS() {
        int[] num = {2, 7, 11, 15};
        int[] num1 = {3, 2, 4};
        int[] result = l2.twoSum(num, 9);
        int[] result1 = l2.twoSum(num1, 6);
        if (result[0] == 1 && result[1] == 2
            && result1[0] == 2 && result1[1] == 3) return true;
        return false;
    }

    /** Return true iff Median of Two Sorted Arrays passes its tests. */
    private static boolean testMOTSA() {
        int[] a1 = {1, 3, 6, 8, 10, 11};
        int[] b1 = {1, 2, 7, 9, 15, 17, 18};
        double r1 = 8.0;
        int[] a2 = {1, 1, 1, 1, 1, 1};
        int[] b2 = {};
        double r2 = 1.0;
        int[] a3 = {1};
        int[] b3 = {2, 3, 4, 5, 6, 7, 8};
        double r3 = 4.5;
        int[] a4 = {1};
        int[] b4 = {1};
        double r4 = 1.0;
        int[] a5 = {1, 2};
        int[] b5 = {3, 4, 5, 6, 7, 8};
        double r5 = 4.5;
        int[] a6 = {76,89,104,287,566,596,660,719,879,1012,1080,1225,1304,1568,1898,1959,1965,2140,2276,2395,2634,2764,2801,2877,3009,3010,3188,3318,3356,3459,3549,3586,3793,3844,3890,4297,4328,4423,4494,4546,4570,4613,4616,4630,4680,4807,5002,5237,5245,5312,5345,5489,5606,5731,5732,5796,5816,6116,6197,6201,6204,6303,6339,6357,6395,6412,6445,6552,6584,6612,6678,6823,6825,6892,7272,7311,7534,7535,7686,7891,8032,8112,8120,8226,8239,8578,8583,8807,9214,9317,9384,9388,9447,9484,9611,9739,9753,9812,9838,9854,9905,9936,9944,9978,10033,10346,10356,10581,10583,10755,10764,10819,10845,11040,11049,11111,11169,11183,11249,11597,11640,11739,11743,11856,11925,11950,11991,12086,12175,12531,12551,12581,12721,12987,13101,13176,13259,13342,13401,13550,13635,13657,13713,13981,14014,14032,14039,14093,14152,14205,14322,14339,14361,14444,14449,14471,14536,14600,14661,14892,15034,15313,15399,15530,15553,15653,15723,15734,15767,15811,16002,16057,16066,16182,16453,16540,16629,16924,16946,17323,17339,17362,17416,17466,17629,17769,17978,17985,18262,18417,18485,18555,18565,18565,18652,18687,18701,18709,18791,19076,19094,19163,19171,19195,19263,19381,19381,19432,19566,19625,19722,19738,19743,19892,19960,20124,20272,20290,20324,20405,20516,20587,20734,20808,20915,20958,20965,21069,21234,21384,21440,21441,21595,21690,21704,21710,21734,21802,21858,21956,21989,22004,22055,22102,22235,22254,22272,22283,22399,22487,22623,22646,22724,22774,22821,22825,23076,23251,23306,23477,23751,23852,24057,24123,24179,24288,24436,24529,24685,24897,25077,25116,25190,25325,25547,25552,25614,25707,25754,25824,25920,25941,25955,25962,26071,26091,26182,26193,26199,26494,26525,26535,26624,26815,26944,27031,27055,27068,27085,27207,27298,27347,27349,27388,27522,27737,27900,28046,28150,28180,28184,28253,28300,28398,28438,28615,28698,28867,28933,28959,29213,29219,29224,29279,29396,29511,29528,29632,29693,29850,29897,29972,29979,30057,30085,30115,30123,30225,30544,30550,30770,30787,30823,31070,31259,31324,31714,31971,32033,32076,32251,32319,32350,32408,32475,32681,32701,32764};
        int[] b6 = {122,255,318,346,361,452,520,584,603,657,669,695,708,730,745,757,766,770,773,787,799,818,845,873,875,899,966,985,1103,1114,1164,1238,1243,1261,1284,1339,1351,1424,1431,1457,1468,1482,1493,1514,1584,1601,1630,1644,1683,1739,1744,1751,1793,1867,1870,1909,1912,1941,1970,2017,2137,2155,2194,2214,2236,2257,2472,2514,2548,2654,2734,2791,2798,2823,2886,2887,2919,2941,2958,2998,2999,3026,3054,3061,3174,3192,3225,3282,3358,3389,3392,3406,3427,3429,3470,3501,3555,3590,3604,3676,3718,3724,3744,3765,3796,3803,3808,3846,3883,3914,3916,4049,4092,4102,4118,4128,4159,4170,4170,4287,4296,4309,4349,4363,4374,4571,4594,4606,4621,4637,4731,4746,4775,4800,4816,4832,4837,4867,4880,4935,4942,4976,5007,5077,5106,5122,5179,5199,5237,5255,5265,5341,5370,5378,5394,5398,5467,5493,5518,5548,5630,5651,5762,5842,5867,5914,5915,5935,6013,6081,6092,6132,6178,6217,6245,6289,6409,6410,6445,6464,6478,6481,6660,6711,6711,6767,6778,6782,6788,6844,6855,6945,7036,7107,7119,7210,7229,7256,7292,7292,7355,7395,7446,7455,7472,7477,7481,7529,7558,7560,7590,7661,7669,7749,7802,7862,7886,7922,7993,8007,8009,8051,8055,8064,8071,8211,8305,8410,8443,8457,8463,8496,8629,8633,8649,8744,8745,8834,9021,9059,9081,9098,9125,9126,9136,9210,9222,9235,9318,9353,9367,9384,9475,9495,9519,9543,9596,9597,9679,9691,9705,9708,9842,9890,9905,9907,9914,9923,9932,9937,9939,10006,10015,10098,10154,10156,10183,10202,10209,10226,10229,10245,10290,10307,10361,10412,10438,10446,10450,10461,10534,10545,10651,10727,10860,10940,10970,10996,11056,11088,11091,11111,11160,11162,11216,11241,11292,11292,11465,11475,11538,11576,11696,11704,11825,11858,12014,12070,12083,12153,12163,12171,12202,12211,12225,12239,12251,12316,12549,12580,12583,12593,12702,12718,12731,12769,12813,12961,12973,13016,13027,13031,13033,13035,13082,13097,13125,13140,13143,13190,13219,13281,13283,13326,13349,13364,13394,13418,13439,13448,13451,13462,13528,13540,13616,13694,13729,13790,13800,13808,13891,13920,13943,13979,14216,14354,14371,14378,14388,14487,14530,14543,14543,14642,14712,14782,14786,14828,14829,14832,14897,14911,14914,14973,15012,15054,15080,15107,15123,15130,15149,15151,15159,15236,15262,15319,15328,15340,15372,15445,15458,15461,15568,15576,15651,15658,15670,15736,15819,15868,15871,15991,16044,16198,16229,16251,16322,16335,16364,16397,16403,16408,16417,16456,16492,16495,16562,16565,16605,16620,16644,16652,16809,16861,16908,16942,16955,17044,17061,17102,17225,17240,17335,17337,17361,17462,17469,17540,17649,17686,17728,17754,17781,17830,17874,18019,18023,18130,18133,18181,18254,18255,18281,18370,18380,18391,18398,18419,18488,18491,18520,18638,18663,18763,18857,18865,18894,18944,18956,18987,18997,19044,19067,19071,19135,19165,19277,19287,19302,19333,19347,19389,19497,19570,19587,19592,19640,19803,19872,19880,19923,19927,19946,19993,20004,20007,20088,20223,20247,20298,20376,20378,20417,20433,20439,20471,20472,20505,20527,20640,20652,20705,20726,20762,20788,20811,20814,20851,20862,20879,20909,21014,21146,21236,21273,21290,21361,21436,21466,21492,21673,21796,21831,21834,21898,21973,21991,22019,22020,22046,22046,22151,22193,22213,22244,22296,22329,22440,22444,22454,22462,22471,22518,22537,22554,22578,22647,22762,22895,22897,22946,22988,22989,22997,23035,23076,23109,23136,23325,23343,23383,23387,23437,23450,23533,23541,23562,23596,23779,23796,23820,23932,24010,24125,24142,24242,24373,24547,24560,24587,24598,24650,24737,24767,24774,24832,24868,24876,24892,24911,24947,24982,25004,25041,25115,25223,25226,25245,25384,25398,25483,25567,25607,25636,25690,25713,25728,25732,25738,25784,25811,25884,25930,25955,25959,25982,26035,26076,26093,26107,26124,26157,26192,26212,26231,26264,26302,26361,26367,26380,26422,26453,26486,26544,26580,26770,26785,26804,26851,26880,26972,26977,27025,27041,27096,27098,27182,27202,27235,27284,27349,27372,27396,27454,27511,27529,27537,27598,27719,27736,27832,27860,27864,27884,27917,27934,27948,28039,28041,28042,28043,28050,28074,28100,28106,28123,28162,28195,28367,28422,28453,28505,28541,28566,28595,28596,28605,28615,28640,28641,28652,28657,28667,28696,28740,28759,28779,28803,28832,28845,28852,28854,28864,28932,28950,29042,29047,29085,29147,29218,29230,29244,29288,29332,29363,29376,29440,29458,29465,29467,29492,29493,29534,29558,29560,29601,29612,29663,29740,29793,29817,29887,29906,30106,30206,30231,30276,30297,30331,30364,30378,30425,30431,30565,30588,30603,30613,30646,30663,30694,30712,30726,30772,30774,30846,30896,30955,31055,31075,31110,31134,31146,31228,31259,31269,31271,31302,31334,31392,31446,31511,31516,31518,31562,31721,31779,31813,31849,31865,31866,31906,31906,31934,31941,31954,32015,32083,32150,32205,32232,32267,32268,32294,32351,32373,32396,32400,32405,32452,32479,32480,32487,32491,32491,32561,32590,32605,32641,32716,32757};
        double r6 = 16240.00000;
        if (l2.findMedianSortedArrays(a1, b1) == r1
            && l2.findMedianSortedArrays(a2, b2) == r2
            && l2.findMedianSortedArrays(a3, b3) == r3
            && l2.findMedianSortedArrays(a4, b4) == r4
            && l2.findMedianSortedArrays(a5, b5) == r5
            && l2.findMedianSortedArrays(a6, b6) == r6
            ) return true;
        return false;
    }

    /** Return true iff Unique Binary Search Trees II passes its tests. */
    private static boolean testSL() {
        ListNode n1 = new ListNode(5);
        n1.next = new ListNode(4);
        n1.next.next = new ListNode(3);
        n1.next.next.next = new ListNode(2);
        n1.next.next.next.next = new ListNode(1);
        String s1 = "1->2->3->4->5";
        if (l2.sortList(n1).toString().equals(s1)) return true;
        return false;
    }

    /** Return true iff Two Sum passes its tests. */
    private static boolean testRL() {
        ListNode n = new ListNode(1);
        n.next = new ListNode(2);
        n.next.next = new ListNode(3);
        n.next.next.next = new ListNode(4);
        l2.reorderList(n);
        String s = "1->4->2->3";
        if (n.toString().equals(s)) return true;
        return false;
    }

    /** Return true iff Longest Substring Without Repeating Characters
     *  passes its tests. */
    private static boolean testLSWRC() {
        String s1 = "abc";
        String s2 = "bbbbb";
        if (l2.lengthOfLongestSubstring(s1) == 3
            && l2.lengthOfLongestSubstring(s2) == 1) return true;
        return false;
    }

    /** Return true iff Unique Binary Search Trees passes its tests. */
    private static boolean testATN() {
        ListNode n1 = new ListNode(2);
        n1.next = new ListNode(4);
        n1.next.next = new ListNode(3);
        ListNode n2 = new ListNode(5);
        n2.next = new ListNode(6);
        n2.next.next = new ListNode(4);
        String s1 = "7->0->8";
        ListNode n3 = new ListNode(9);
        n3.next = new ListNode(9);
        n3.next.next = new ListNode(9);
        ListNode n4 = new ListNode(1);
        String s2 = "0->0->0->1";
        if (l2.addTwoNumbers(n1, n2).toString().equals(s1)
            && l2.addTwoNumbers(n3, n4).toString().equals(s2)) return true;
        return false;
    }

    /** Return true iff Unique Binary Search Trees passes its tests. */
    private static boolean testPN() {
        if (l2.isPalindrome(9) && l2.isPalindrome(101)
            && l2.isPalindrome(10901) && !l2.isPalindrome(234857)
            && l2.isPalindrome(1874994781)) return true;
        return false;
    }

    /** Return true iff Wildcard Matching passes its tests. */
    private static boolean testWM() {
        if (!l2.isMatch("aa", "a")
            && l2.isMatch("aa","aa")
            && !l2.isMatch("aaa","aa")
            && l2.isMatch("aa", "*")
            && l2.isMatch("aa", "a*")
            && l2.isMatch("ab", "?*")
            && !l2.isMatch("aab", "c*a*b")
            && !l2.isMatch("a", "aa")
            && l2.isMatch("", "*")
            && l2.isMatch("abbaabbbbaaaaababbbaabbaabaababbaababaabbabbaabbbab", "***************b***b**")
            && l2.isMatch("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "*")
            && l2.isMatch("abc", "**")
            && l2.isMatch("abbbbbbbbbbbbbbbb", "a*b*b")
            && l2.isMatch("abbbbcabca", "a*ca")
            && l2.isMatch("hi", "*?")
            ) return true;
        return false;
    }

    /** Return true iff ZigZag Conversion passes its tests. */
    private static boolean testZC() {
        String s1 = l2.convert("PAYPALISHIRING", 3);
        String r1 = "PAHNAPLSIIGYIR";
        if (s1.equals(r1)) return true;
        return false;
    }


    /** Return true iff String to Integer (atoi) passes its tests. */
    private static boolean testSTI() {
        int n1 = l2.atoi("  -123456789               ");
        int r1 = -123456789;
        int n2 = l2.atoi("      ");
        int r2 = 0;
        int n3 = l2.atoi("   -123alksjn");
        int r3 = -123;
        int n4 = l2.atoi("18927361928374169238746");
        int r4 = Integer.MAX_VALUE;
        if (n1 == r1 && n2 == r2 && n3 == r3 && n4 == r4) return true;
        return false;
    }

    /** Return true iff Container With Most Water passes its tests. */
    private static boolean testCWMW() {
        int[] h1 = {2, 7, 2, 1, 3, 1, 5, 8};
        int r1 = 42;
        if (l2.maxArea(h1) == r1) return true;
        return false;
    }

    /** Return true iff Longest Common Prefix passes its tests. */
    private static boolean testLCP() {
        String[] s1 = {"apple", "banana"};
        String r1 = "";
        String[] s2 = {"baseball", "baserunner", "basehit"};
        String r2 = "base";
        if (l2.longestCommonPrefix(s1).equals(r1)
            && l2.longestCommonPrefix(s2).equals(r2)) return true;
        return false;
    }

    /** Return true iff 3Sum passes its tests. */
    private static boolean test3S() {
        // test two sum first
        //System.out.println(l2.twoSum(9, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)));
        int[] a1 = {-1, 0, 1, 2, -1, -4};
        //System.out.println(l2.threeSum(a1));
        int[] a2 = {0, 0, 0};
        //System.out.println(l2.threeSum(a2));
        if (true) return true;
        return false;
    }

    /** Return true iff Letter Combinations of a Phone Number passes its tests. */
    private static boolean testLCOAPN() {
        String s1 = "23";
        List<String> r1 = l2.letterCombinations(s1);
        String[] a1 = {"ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"};
        for (int i = 0; i < r1.size(); i += 1) {
            if (!a1[i].equals(r1.get(i))) return false;
        }
        return true;
    }

    /** Return true iff STH passes its tests. */
    private static boolean testGP() {
        List<String> l = l2.generateParenthesis(3);
        for (int i = 0; i < l.size(); i += 1) {
            //System.out.println(l.get(i));
        }
        return true;
    }

    /** Return true iff Swap Nodes in Pairs passes its tests. */
    private static boolean testSNIP() {
        ListNode n1 = new ListNode(1);
        n1.next = new ListNode(2);
        n1.next.next = new ListNode(3);
        n1.next.next.next = new ListNode(4);
        String r1 = "2->1->4->3";
        String o1 = l2.swapPairs(n1).toString();
        if (r1.equals(o1)) return true;
        return false;
    }

    /** Return true iff Reverse Nodes in k-Group passes its tests. */
    private static boolean testRNIKG() {
        ListNode n1 = new ListNode(1);
        n1.next = new ListNode(2);
        n1.next.next = new ListNode(3);
        n1.next.next.next = new ListNode(4);
        n1.next.next.next.next = new ListNode(5);
        n1.next.next.next.next.next = new ListNode(6);
        n1.next.next.next.next.next.next = new ListNode(7);
        //System.out.println(l2.reverseKGroup(n1, 2));
        if (true) return true;
        return false;
    }

    /** Return true iff Implement strStr() passes its tests. */
    private static boolean testIS() {
        String h1 = "kabcde", n1 = "abcd", r1 = "abcde";
        if (l2.strStr(h1, n1).equals(r1)) return true;
        return false;
    }

    /** Return true iff Divide Two Integers passes its tests. */
    private static boolean testDTI() {
        int d1 = 1234, e1 = 101, r1 = 12;
        int d2 = -1234, e2 = 101, r2 = -12;
        int d3 = -1234, e3 = -101, r3 = 12;
        int d4 = 1, e4 = 1, r4 = 1;
        int d5 = 1026117192, e5 = -874002063, r5 = -1;
        int d6 = -1010369383, e6 = -2147483648, r6 = 0;
        int d7 = 102137742, e7 = 1817624734, r7 = 0;
        int d8 = 2147483647, e8 = 2, r8 = 1073741823;
        int d9 = -2147483648, e9 = 1, r9 = -2147483648;
        int d10 = -2147483648, e10 = -1017100424, r10 = 2;
        int d11 = -2147483648, e11 = 265681027, r11 = -8;
        if (true
            && l2.divide(d1, e1) == r1
            && l2.divide(d2, e2) == r2
            && l2.divide(d3, e3) == r3
            && l2.divide(d4, e4) == r4
            && l2.divide(d5, e5) == r5
            && l2.divide(d6, e6) == r6
            && l2.divide(d7, e7) == r7
            && l2.divide(d8, e8) == r8
            && l2.divide(d9, e9) == r9
            && l2.divide(d10, e10) == r10
             ) return true;
        return false;
    }

    /** Return true iff Substring with Concatenation of All Words passes its tests. */
    private static boolean testSWCOAW() {
        String s1 = "barfoothefoobarman";
        String[] l1 = {"foo", "bar"};
        List<Integer> r1 = Arrays.asList(0, 9);
        List<Integer> q1 = l2.findSubstring(s1, l1);
        String s2 = "lingmindraboofooowingdingbarrwingmonkeypoundcake";
        String[] ll2 = {"fooo","barr","wing","ding","wing"};
        List<Integer> r2 = Arrays.asList(13);
        List<Integer> q2 = l2.findSubstring(s2, ll2);
        String s3 = "adadad";
        String[] l3 = {"ad", "ad"};
        List<Integer> r3 = Arrays.asList(0, 2);
        List<Integer> q3 = l2.findSubstring(s3, l3);
        String s4 = "sheateateseatea";
        String[] l4 = {"sea","tea","ate"};
        List<Integer> r4 = Arrays.asList(6);
        List<Integer> q4 = l2.findSubstring(s4, l4);
        String s6 = "abababab";
        String[] l6 = {"a", "b"};
        List<Integer> r6 = Arrays.asList(0, 1, 2, 3, 4, 5, 6);
        List<Integer> q6 = l2.findSubstring(s6, l6);
        String s7 = "babaabba";
        String[] l7 = {"ba", "ab"};
        List<Integer> r7 = Arrays.asList(2, 4);
        List<Integer> q7 = l2.findSubstring(s7, l7);
        if (r1.size() != q1.size()) return false;
        for (int i = 0; i < q1.size(); i += 1)
            if (q1.get(i) != r1.get(i)) return false;
        if (r2.size() != q2.size()) return false;
        for (int i = 0; i < q2.size(); i += 1)
            if (q2.get(i) != r2.get(i)) return false;
        if (r3.size() != q3.size()) return false;
        for (int i = 0; i < q3.size(); i += 1)
            if (q3.get(i) != r3.get(i)) return false;
        if (r4.size() != q4.size()) return false;
        for (int i = 0; i < q4.size(); i += 1)
            if (q4.get(i) != r4.get(i)) return false;
        if (r6.size() != q6.size()) return false;
        for (int i = 0; i < q6.size(); i += 1)
            if (q6.get(i) != r6.get(i)) return false;
        if (r7.size() != q7.size()) return false;
        for (int i = 0; i < q7.size(); i += 1)
            if (q7.get(i) != r7.get(i)) return false;
        return true;
    }

    /** Return true iff Next Permutation passes its tests. */
    private static boolean testNP() {
        int[] a1 = {1, 2, 3};
        int[] e1 = {1, 3, 2};
        l2.nextPermutation(a1);
        int[] a2 = {3, 2, 1};
        int[] e2 = {1, 2, 3};
        l2.nextPermutation(a2);
        int[] a3 = {1, 3, 2};
        int[] e3 = {2, 1, 3};
        l2.nextPermutation(a3);
        int[] a4 = {2, 3, 1};
        int[] e4 = {3, 1, 2};
        l2.nextPermutation(a4);
        for (int i = 0; i < a1.length; i += 1)
            if (a1[i] != e1[i]) return false;
        for (int i = 0; i < a2.length; i += 1)
            if (a2[i] != e2[i]) return false;
        for (int i = 0; i < a3.length; i += 1)
            if (a3[i] != e3[i]) return false;
        return true;
    }

    /** Return true iff Search for a Range passes its tests. */
    private static boolean testSFAR() {
        int[] a1 = {5, 7, 7, 8, 8, 10};
        int[] q1 = l2.searchRange(a1, 8);
        int[] q2 = l2.searchRange(a1, 2);
        int[] q3 = l2.searchRange(a1, 100);
        int[] a2 = {8, 8, 8, 8, 8};
        int[] q4 = l2.searchRange(a2, 8);
        int[] a3 = {4};
        int[] q5 = l2.searchRange(a3, 4);
        if (q1[0] == 3 && q1[1] == 4
            && q2[0] == -1 && q2[1] == -1
            && q3[0] == -1 && q3[1] == -1
            && q4[0] == 0 && q4[1] == 4
            && q5[0] == 0 && q5[1] == 0) return true;
        return false;
    }

    /** Return true iff Search Insert Position passes its tests. */
    private static boolean testSIP() {
        int[] a1 = {1, 3, 5, 6};
        if (l3.searchInsert(a1, 5) == 2
            && l3.searchInsert(a1, 2) == 1
            && l3.searchInsert(a1, 7) == 4
            && l3.searchInsert(a1, 0) == 0) return true;
        return false;
    }

    /** Return true iff Count and Say passes its tests. */
    private static boolean testCAS() {
        if (l3.countAndSay(0).equals("")
            && l3.countAndSay(1).equals("1")
            && l3.countAndSay(2).equals("11")
            && l3.countAndSay(3).equals("21")
            && l3.countAndSay(4).equals("1211")
            && l3.countAndSay(5).equals("111221")
            && l3.countAndSay(6).equals("312211")) return true;
        return false;
    }

    /** Return true iff Sudoku Solver passes its tests. */
    private static boolean testSuS() {
        char[][] board1 = 
            {{'5', '3', '4', '6', '7', '8', '9', '1', '2'}, 
             {'6', '7', '2', '1', '9', '5', '3', '4', '8'},
             {'1', '9', '8', '3', '4', '2', '5', '6', '7'},
             {'8', '5', '9', '7', '6', '1', '4', '2', '3'},
             {'4', '2', '6', '8', '5', '3', '7', '9', '1'},
             {'7', '1', '3', '9', '2', '4', '8', '5', '6'},
             {'9', '6', '1', '5', '3', '7', '2', '8', '4'},
             {'2', '8', '7', '4', '1', '9', '6', '3', '5'},
             {'3', '4', '5', '.', '8', '6', '1', '7', '9'}};
        char[][] ans1 = 
            {{'5', '3', '4', '6', '7', '8', '9', '1', '2'}, 
             {'6', '7', '2', '1', '9', '5', '3', '4', '8'},
             {'1', '9', '8', '3', '4', '2', '5', '6', '7'},
             {'8', '5', '9', '7', '6', '1', '4', '2', '3'},
             {'4', '2', '6', '8', '5', '3', '7', '9', '1'},
             {'7', '1', '3', '9', '2', '4', '8', '5', '6'},
             {'9', '6', '1', '5', '3', '7', '2', '8', '4'},
             {'2', '8', '7', '4', '1', '9', '6', '3', '5'},
             {'3', '4', '5', '2', '8', '6', '1', '7', '9'}};
        char[][] board2 = 
            {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, 
             {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
             {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
             {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
             {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
             {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
             {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
             {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
             {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        char[][] ans2 = 
            {{'5', '3', '4', '6', '7', '8', '9', '1', '2'}, 
             {'6', '7', '2', '1', '9', '5', '3', '4', '8'},
             {'1', '9', '8', '3', '4', '2', '5', '6', '7'},
             {'8', '5', '9', '7', '6', '1', '4', '2', '3'},
             {'4', '2', '6', '8', '5', '3', '7', '9', '1'},
             {'7', '1', '3', '9', '2', '4', '8', '5', '6'},
             {'9', '6', '1', '5', '3', '7', '2', '8', '4'},
             {'2', '8', '7', '4', '1', '9', '6', '3', '5'},
             {'3', '4', '5', '2', '8', '6', '1', '7', '9'}};
        char[][] board3 = 
            {{'.', '.', '9', '7', '4', '8', '.', '.', '.'}, 
             {'7', '.', '.', '6', '.', '2', '.', '.', '.'},
             {'.', '2', '.', '1', '.', '9', '.', '.', '.'},
             {'.', '.', '7', '9', '8', '6', '2', '4', '1'},
             {'2', '6', '4', '3', '1', '7', '5', '9', '8'},
             {'1', '9', '8', '5', '2', '4', '3', '6', '7'},
             {'.', '.', '.', '8', '6', '3', '.', '2', '.'},
             {'.', '.', '.', '4', '9', '1', '.', '.', '6'},
             {'.', '.', '.', '2', '7', '5', '9', '.', '.'}};
        char[][] ans3 = 
            {{'5', '1', '9', '7', '4', '8', '6', '3', '2'}, 
             {'7', '8', '3', '6', '5', '2', '4', '1', '9'},
             {'4', '2', '6', '1', '3', '9', '8', '7', '5'},
             {'3', '5', '7', '9', '8', '6', '2', '4', '1'},
             {'2', '6', '4', '3', '1', '7', '5', '9', '8'},
             {'1', '9', '8', '5', '2', '4', '3', '6', '7'},
             {'9', '7', '5', '8', '6', '3', '1', '2', '4'},
             {'8', '3', '2', '4', '9', '1', '7', '5', '6'},
             {'6', '4', '1', '2', '7', '5', '9', '8', '3'}};
        char[][] board4 = 
            {{'.', '.', '9', '7', '4', '8', '.', '.', '.'}, 
             {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
             {'.', '2', '.', '1', '.', '9', '.', '.', '.'},
             {'.', '.', '7', '.', '.', '.', '2', '4', '.'},
             {'.', '6', '4', '.', '1', '.', '5', '9', '.'},
             {'.', '9', '8', '.', '.', '.', '3', '.', '.'},
             {'.', '.', '.', '8', '.', '3', '.', '2', '.'},
             {'.', '.', '.', '.', '.', '.', '.', '.', '6'},
             {'.', '.', '.', '2', '7', '5', '9', '.', '.'}};
        char[][] ans4 = 
            {{'5', '1', '9', '7', '4', '8', '6', '3', '2'}, 
             {'7', '8', '3', '6', '5', '2', '4', '1', '9'},
             {'4', '2', '6', '1', '3', '9', '8', '7', '5'},
             {'3', '5', '7', '9', '8', '6', '2', '4', '1'},
             {'2', '6', '4', '3', '1', '7', '5', '9', '8'},
             {'1', '9', '8', '5', '2', '4', '3', '6', '7'},
             {'9', '7', '5', '8', '6', '3', '1', '2', '4'},
             {'8', '3', '2', '4', '9', '1', '7', '5', '6'},
             {'6', '4', '1', '2', '7', '5', '9', '8', '3'}};
        System.out.println("Original Board 1:");
        for (int i = 0; i < 9; i += 1) System.out.println(board1[i]);
        l3.solveSudoku(board1);
        for (int i = 0; i < 9; i += 1) {
            if (!Arrays.equals(board1[i], ans1[i])) return false;
        }
        System.out.println("Original Board 2:");
        for (int i = 0; i < 9; i += 1) System.out.println(board2[i]);
        l3.solveSudoku(board2);
        for (int i = 0; i < 9; i += 1) {
            if (!Arrays.equals(board2[i], ans2[i])) return false;
        }
        System.out.println("Original Board 3:");
        for (int i = 0; i < 9; i += 1) System.out.println(board3[i]);
        l3.solveSudoku(board3);
        for (int i = 0; i < 9; i += 1) {
            if (!Arrays.equals(board3[i], ans3[i])) return false;
        }
        System.out.println("Original Board 4:");
        for (int i = 0; i < 9; i += 1) System.out.println(board4[i]);
        l3.solveSudoku(board4);
        for (int i = 0; i < 9; i += 1) {
            if (!Arrays.equals(board4[i], ans4[i])) return false;
        }
        return true;
    }

    /** Return true iff Combination Sum II passes its tests. */
    private static boolean testCSII() {
        int[] num1 = {10, 1, 2, 7, 6, 1, 5};
        int target1 = 8;
        int[][] ans1 = {{1, 7}, {1, 2, 5}, {2, 6}, {1, 1, 6}};
        int[] num2 = {1, 1, 1, 1};
        int target2 = 2;
        int[][] ans2 = {{1, 1}};
        List<List<Integer>> result1 = l3.combinationSum2(num1, target1);
        List<List<Integer>> result2 = l3.combinationSum2(num2, target2);
        for (List<Integer> l : result1) System.out.println(l);
        for (List<Integer> l : result2) System.out.println(l);
        if (true) return true;
        return false;
    }

    /** Return true iff Trapping Rain Water passes its tests. */
    private static boolean testTRW() {
        int[] elev1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int ans1 = l3.trap(elev1);
        int[] elev2 = {4, 2, 3};
        int ans2 = l3.trap(elev2);
        System.out.println(ans1);
        if (ans1 == 6 && ans2 == 1) return true;
        return false;
    }

    /** Return true iff Permutations passes its tests. */
    private static boolean testP() {
        int[] num1 = {0, 1, 2};
        List<List<Integer>> result1 = l3.permute(num1);
        System.out.println(result1.size());
        for (List<Integer> l : result1) System.out.println(l);
        if (true) return true;
        return false;
    }

    /** Return true iff Rotate Image passes its tests. */
    private static boolean testRI() {
        int[][] matrix1 = {{1, 2, 3, 1}, {3, 1, 2, 2}, {2, 2, 1, 3}, {1, 3, 2, 1}};
        int[][] ans1 = {{1, 2, 3, 1}, {3, 2, 1, 2}, {2, 1, 2, 3}, {1, 3, 2, 1}};
        System.out.println("Matrix 1:");
        for (int i = 0; i < matrix1.length; i += 1) {
            for (int j = 0; j < matrix1.length; j += 1) System.out.printf("%s ", matrix1[i][j]);
            System.out.println();
        }
        l3.rotate(matrix1);
        System.out.println("Rotated Matrix 1:");
        for (int i = 0; i < matrix1.length; i += 1) {
            for (int j = 0; j < matrix1.length; j += 1) System.out.printf("%s ", matrix1[i][j]);
            System.out.println();
        }
        return true;
    }

    /** Return true iff Pow(x, n) passes its tests. */
    private static boolean testPow() {
        if (l3.pow(1.00000, -2147483648) == 1.00000) return true;
        return false;
    }

    /** Return true iff Word Search passes its tests. */
    private static boolean testWS() {
        char[][] board1 = {{'a', 'a', 'a', 'a'}, {'a', 'a', 'a', 'a'}, {'a', 'a', 'a', 'a'}};
        String word1 = "aaaaaaaaaaab";
        char[][] board2 = {{'b', 'b'}, {'a', 'b'}, {'b', 'a'}};
        String word2 = "a";
        if (!l3.exist(board1, word1)) return true;
        return false;
    }

    // /** Return true iff STH passes its tests. */
    // private static boolean test() {
    //     if () return true;
    //     return false;
    // }

    /* A Leetcode2 Object. */
    private static Leetcode2 l2 = new Leetcode2();

    /* A Leetcode3 Object. */
    private static Leetcode3 l3 = new Leetcode3();

    /* The number of tests that fails. */
    private static int numFails = 0;

    /* The number of tests that passes. */
    private static int numPasses = 0;
}