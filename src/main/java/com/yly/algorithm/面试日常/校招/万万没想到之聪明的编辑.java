package com.yly.algorithm.面试日常.校招;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
1. 三个同样的字母连在一起，一定是拼写错误，去掉一个的就好啦：比如 helllo -> hello
2. 两对一样的字母（AABB型）连在一起，一定是拼写错误，去掉第二对的一个字母就好啦：比如 helloo -> hello
3. 上面的规则优先“从左到右”匹配，即如果是AABBCC，虽然AABB和BBCC都是错误拼写，应该优先考虑修复AABB，结果为AABCC

输入描述:
第一行包括一个数字N，表示本次用例包括多少个待校验的字符串。
后面跟随N行，每行为一个待校验的字符串。

输出描述:
N行，每行包括一个被修复后的字符串。
 */
public class 万万没想到之聪明的编辑 {
	  public static void main(String[] args) throws Exception{
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        int n = Integer.parseInt(br.readLine());
	        for(int i = 0; i<n; i++){
	            String str = br.readLine();
	            str = check(str);
	            System.out.println(str);
	        }
	    }
	    public static String check(String s){
	        char[] arr = s.toCharArray();
	        int index = 0;
	        for(int i = 0; i<arr.length; i++){
	            if(i>1 && arr[i] == arr[index-1] && arr[index-1] == arr[index-2]){
	            }else if(i>2&&arr[i] == arr[index-1] && arr[index-2] == arr[index-3]){
	            }else{
	                arr[index] = arr[i];
	                index++;
	            }   
	        }
	        
	        return String.valueOf(arr).substring(0,index);
	    }

}
