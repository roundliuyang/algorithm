package com.yly.algorithm.DFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给出一系列 不重复的单词，找出所有用这些单词能构成的 单词矩阵。
 * 一个有效的单词矩阵是指, 如果从第 k 行读出来的单词和第 k 列读出来的单词相同(0 <= k < max(numRows, numColumns))，那么就是一个单词矩阵.
 * 例如，单词序列为 ["ball","area","lead","lady"] ,构成一个单词矩阵。因为对于每一行和每一列，读出来的单词都是相同的。
 * b a l l
 * a r e a
 * l e a d
 * l a d y
 * 注:
 * 现在至少有一个单词并且不多于1000个单词
 * 所有的单词都有相同的长度
 * 单词的长度最短为 1 最长为 5
 * 每一个单词均由小写字母组成
 */
public class 单词矩阵 {

    public static void main(String[] args) {
        单词矩阵 cla = new 单词矩阵();
        String[] arr = {"area", "lead", "wall", "lady", "ball"};
        cla.wordSquares(arr);
    }

    public List<List<String>> wordSquares(String[] words) {
        Map<String, List<String>> prefixToWords = getPrefixToWords(words);
        List<List<String>> squares = new ArrayList<>();
        List<String> square = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            square.add(words[i]);
            search(prefixToWords, square, squares);
            square.remove(square.size() - 1);
        }
        return squares;
    }

    private void search(Map<String, List<String>> prefixToWords,
                        List<String> square,
                        List<List<String>> squares) {
        int n = square.get(0).length();
        int curtIndex = square.size();
        if (curtIndex == n) {
            squares.add(new ArrayList<String>(square));
            return;
        }
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < curtIndex; i++) {
            prefix.append(square.get(i).charAt(curtIndex));
        }

        List<String> orDefault = prefixToWords.getOrDefault(prefix.toString(), new ArrayList<>());
        for (String word : orDefault) {
            square.add(word);
            search(prefixToWords, square, squares);
            square.remove(square.size() - 1);

        }

    }

    /**
     * 使用哈希表构造前缀和单词映射关系 prefix_to_words
     */
    private Map<String, List<String>> getPrefixToWords(String[] words) {
        Map<String, List<String>> prefixToWords = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                String prefix = word.substring(0, j + 1);
                if (!prefixToWords.containsKey(prefix)) {
                    prefixToWords.put(prefix, new ArrayList<String>());
                }
                prefixToWords.get(prefix).add(word);
            }
        }
        return prefixToWords;
    }

}
