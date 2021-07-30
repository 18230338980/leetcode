package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: convert
 * @date: 2020/6/29 9:31
 * @author: zsz
 * <p>
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * <p>
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 * 示例 1:
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * class Solution {
 *     public String convert(String s, int numRows) {
 *
 *         if (numRows == 1) return s;
 *
 *         List<StringBuilder> rows = new ArrayList<>();
 *         for (int i = 0; i < Math.min(numRows, s.length()); i++)
 *             rows.add(new StringBuilder());
 *
 *         int curRow = 0;
 *         boolean goingDown = false;
 *
 *         for (char c : s.toCharArray()) {
 *             rows.get(curRow).append(c);
 *             if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
 *             curRow += goingDown ? 1 : -1;
 *         }
 *
 *         StringBuilder ret = new StringBuilder();
 *         for (StringBuilder row : rows) ret.append(row);
 *         return ret.toString();
 *     }
 * }
 *
 */
public class Convert {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        //声明一个存储非空行得数组
        List<StringBuffer> rowList = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rowList.add(new StringBuffer());
        }

        //存储当前元素得行（从上到下：0->n）
        int currRow = 0;
        //是否向下（只有向下和向上两种情况）
        boolean goDown = false;

        for (char c : s.toCharArray()) {
            rowList.get(currRow).append(c);
            if (currRow == 0 || currRow == numRows - 1) {
                goDown = !goDown;
            }
            currRow += goDown ? 1 : -1;
        }

        StringBuffer result = new StringBuffer();
        for (StringBuffer row : rowList) {
            result.append(row);
        }
        return result.toString();
    }

}
