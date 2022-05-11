package disconnection;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.tree.TreeNode;
import java.text.DecimalFormat;
import java.util.*;

public class LeCode {
    @Test
    public void test() {
        /**
         * 判断最后一位单词的长度
         */
//        方法一
//        String str = "hello nowcoder";
//        int i = 1;
//        while(true){
//            String a = str.substring(str.length() - i);
//            if(a.contains(" ")){
//                System.out.println(i - 1);
//                break;
//            }
//            i++;
//        }
//        方法二
//        String str = "hello nowcoder";
//        String[] s = str.split(" "); //正则表达式实用性更强( str.split("\\s+"))
//        int length = s[s.length - 1].length();
//        System.out.println(length);
        /**
         * 计算某字符出现的次数
         */
//        String str = "ABCabcsdagAdrahAd";
//        System.out.println(str.length() - str.toLowerCase().replaceAll("a", "").length());
        /**
         * 明明的随机数从重并排序
         */
//        int n = 5;
//        Set<Integer> set = new TreeSet<>();
//        for(int i =0 ; i < n ;i++){
//            int randomNum = RandomUtils.nextInt(1, 500);
//            set.add(randomNum);
//        }
//        //输出
//        for (Integer integer : set) {
//            System.out.println(integer);
//        }
        /**
         * 分割字符长度为8，长度不够补0
         */
//        String s = "afgkdhaseyrujdfbxdgseh";
//        while(s.length()>=8){
//            System.out.println(s.substring(0,8));
//            s=s.substring(8);
//        }
//        if(s.length()<8 && s.length()>0){
//            s+="0000000";
//            System.out.println(s.substring(0,8));
//        }
        /**
         * 四则运算
         */
//        Scanner scan = new  Scanner(System.in);
//        String input = scan.nextLine();
//        input = input.replace("[","(");
//        input = input.replace("{","(");
//        input = input.replace("}",")");
//        input = input.replace("]",")");
//        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");
//        System.out.println(scriptEngine.eval(input));
        /**
         * 最长回文字符
         */
//        String s = "cdabbacc";
//        int max = 0;
//        /**
//         *双指针遍历找到最长子串
//         */
//        for (int i = 0; i < s.length(); i++) {
//            for (int j = s.length(); j > i; j--) {
//                String toBeJuged = s.substring(i, j);
//                if (isPalindromeString(toBeJuged)) {
//                    max = Math.max(max, j - i);
//                }
//            }
//        }
//        System.out.print(max);
//
//    }
//
//    static boolean isPalindromeString(String s) {
//        String i = new StringBuilder(s).reverse().toString();
//        return s.equals(i);
//    }

//        方法二
//        String s = "cdabbacc";
//        while(s != null && s.length() != 0){
//            int ans = 0;
//            char[] c= s.toCharArray();
//            for (int i=0;i<c.length;i++){
//                ans = Math.max(ans,way(c,i,i));
//                if (i>0){
//                    ans = Math.max(ans,way(c,i-1,i));
//                }
//            }
//            System.out.println(ans);
//            break;
//        }
//
//    }
//
//    public static int way(char[] c,int left,int right){
//        while(left >= 0 && right < c.length && c[left] == c[right]){
//            left--;
//            right++;
//        }
//        return right-left-1;
        /**
         * 字符串逆序
         */
//        String str = "I am a student";
//        StringBuilder temp = new StringBuilder();
//        for (int i = 1; i <= str.length(); i++){
//            temp.append(str.charAt(str.length() - i));
//        }
//        System.out.println(temp);
        /**
         * 判断ip是否合法
         */
        //Scanner sc = new Scanner(System.in);
        //while(sc.hasNext()){
            //String str = sc.nextLine();
//            String str = "10.137.17.1000";
//            String [] arr = str.split("\\.");
//            boolean flag = true;
//            for( String s : arr){
//                int n = Integer.parseInt(s);
//                if(n < 0 || n > 255){
//                    flag = false;
//                }
//            }
//            if(flag){
//                System.out.println("YES");
//            }else{
//                System.out.println("NO");
//
//            }
        //}
        /**
         * 等差数列 2，5，8，11，14。。。。
         * （从 2 开始的 3 为公差的等差数列）
         * 输出求等差数列前n项和
         */
//        int length = 3;
//        int first = 2, last = 3 * length - 1;
//        System.out.println((first + last) * length / 2);
        /**
         * 两数之和
         */
//        int[] nums = {2,7,11,15};
//        int target = 9;
//        Map<Integer, Integer> map = new HashMap<>();
//        for(int i = 0; i< nums.length; i++) {
//            if(map.containsKey(target - nums[i])) {
//                System.out.println(Arrays.toString(new int[]{map.get(target - nums[i]), i}));
//            }
//            map.put(nums[i], i);
//        }
//        throw new IllegalArgumentException("No two sum solution");
        /**
         * 字符串排序，不分大小写，非英文字母放在原位
         */
//        String str = "Ty p.e";
//        List<Character> letters = new ArrayList<>();
//        for (char ch : str.toCharArray()) {
//            if (Character.isLetter(ch)) {
//                letters.add(ch);
//            }
//        }
//        // 将英文字母先排序好
//        letters.sort(Comparator.comparingInt(Character::toLowerCase));
//        // 若是非英文字母则直接添加
//        StringBuilder result = new StringBuilder();
//        for (int i = 0, j = 0; i < str.length(); i++) {
//            if (Character.isLetter(str.charAt(i))) {
//                result.append(letters.get(j++));
//            } else {
//                result.append(str.charAt(i));
//            }
//        }
//        System.out.println(result);

    }
}
