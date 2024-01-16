package com.yly.algorithm.字典树;


import java.util.HashMap;
import java.util.Map;

/**
 * 给定一组n个仅包含小写字母的字符串，为每个字符串找出能够唯一识别该字符串的最小前缀
 * 即可以识别A串的最小前缀Ap，不会是其他n-1个字符串的前缀
 * <p>
 * 输入:["aaa","bbc","bcd"]
 * 输出:["a","bb","bc"]
 * 解释:"a"仅是"aaa" 的前缀
 * "bb"仅是"bbc"的前缀
 * "bc"仅是"bcd"的前缀
 */
public class 识别字符串 {
    public String[] shortPrefix(String[] stringArray) {
        Trie2 trie2 = new Trie2();
        String[] results = new String[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            trie2.insert(stringArray[i]);
        }
        for (int i = 0; i < stringArray.length; i++) {
            results[i] = getUniquePrefix(trie2.getRoot(), stringArray[i]);
        }
        return results;
    }

    String getUniquePrefix(TrieNode2 root, String word) {
        TrieNode2 node = root;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (node.prefixCount == 1) {
                return word.substring(0, i);
            }
            node = node.children.get(letter);
        }
        return word;
    }

}

class TrieNode2 {
    public Map<Character, TrieNode2> children;
    public boolean isWord;
    public String word;
    public int prefixCount;

    public TrieNode2() {
        this.children = new HashMap<Character, TrieNode2>();
        isWord = false;
        word = null;
        prefixCount = 0;
    }
}

class Trie2 {
    private TrieNode2 root;

    public Trie2() {
        root = new TrieNode2();
    }

    public TrieNode2 getRoot() {
        return root;
    }

    public void insert(String word) {
        TrieNode2 node = root;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (!node.children.containsKey(letter)) {
                node.children.put(letter, new TrieNode2());
            }
            node = node.children.get(letter);
            node.prefixCount++;
        }
        node.isWord = true;
        node.word = word;
    }

}