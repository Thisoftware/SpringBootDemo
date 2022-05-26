package disconnection;

import io.swagger.models.auth.In;
import org.apache.commons.lang3.RandomUtils;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.tree.TreeNode;
import java.text.DecimalFormat;
import java.util.*;

public class LeCode {

    private final List<List<Integer>> res = new ArrayList<>();

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
//        String s = "abc435cba";
//        int max = 0;
//        String any = "";
//        /**
//         *双指针遍历找到最长子串
//         */
//        for (int i = 0; i < s.length(); i++) {
//            for (int j = s.length(); j > i; j--) {
//                String toBeJudged = s.substring(i, j);
//                if (toBeJudged.equals(new StringBuilder(toBeJudged).reverse().toString()) && (j - i) > max) {
//                    max = Math.max(max, j - i);
//                    any = toBeJudged;
//                }
//            }
//        }
//        System.out.println(any);

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
        /**
         * 整数反转
         */
//        int x = -1230;
//        int rev = 0;
//        while (x != 0) {
//            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
//                System.out.println(0);
//                break;
//            }
//            int digit = x % 10;
//            x /= 10;
//            rev = rev * 10 + digit;
//        }
//        System.out.println(rev);
        /**
         * 字符串加解密
         */
//        String str = "cATu0";
//        String en = encode(str);
//        System.out.println(en);
//        System.out.println(decode(en));
        /**
         * ********************
         * 异或交换法（相同为0，不同为1）
         */
//        int a = 1;
//        int b = 3;
//        a = a ^ b;
//        b = a ^ b;
//        a = a ^ b;
//        System.out.println(a);
//        System.out.println(b);
        /**
         * 找出数组中一个出现奇数次的数
         */
//        int[] arr = {1,1,2,3,2,3,1,2,3,3};
//        int eor = 0;
//        for (int i : arr){
//            eor ^= i;
//        }
//        System.out.println(eor);
        /**
         * 找出数组中两个出现奇数次的数
         */
//        int[] arr = {5,5,2,3,2,3,7,5,2,7,2,3,3,7};
//        int eor = arr[0];
//        for(int i = 1; i < arr.length; i ++){
//            eor ^= arr[i];
//        }
//        int rightOne = eor & (~eor + 1);//取出最右侧的1，～为去反
//        int onlyOne = 0;
//        for (int j : arr){
//            if ((j & rightOne) == 0){
//                onlyOne ^= j;
//                break;
//            }
//        }
//        System.out.println(onlyOne);
//        System.out.println(eor ^ onlyOne);
        /**
         * 对数器
         */
//        int testTime = 500;
//        int maxSize = 100;
//        int maxValue = 100;
//        boolean succeed = true;
//        for (int i = 0; i < testTime; i++){
//            int[] arr1 = generateRandomArray(maxSize,maxValue);
//            int[] arr2 = copyArray(arr1);
//            //TODO 某个排序算法
//            Arrays.sort(arr2);
//            if (!isEqual(arr1, arr2)){
//                succeed = false;
//                break;
//            }
//        }
//        System.out.println(succeed ? "Nice!" : "What fuck?");
        /**
         * 求数组中的最大值
         */
//        int[] arr = {5,7,8,3,13,23,17};
//        System.out.println(process(arr, 0, arr.length - 1));]);
        /**
         * 插入排序、快速排排***********
         */
//        int[] arr = {3,7,3,6,8,5,4,2,1};
//        //insertSort(arr);
//        quickSort(arr);
//        System.out.println(Arrays.toString(arr));
        /**
         * 堆排序
         * 左节点=2*i+1，右节点=2*i+2，父节点=(i-1)/2；（i为下标）
         */
//        int[] arr = {3,7,3,6,8,5,4,2,1};
//        heapInsert(arr, 1);
//        System.out.println(Arrays.toString(arr));
        /**
         * 组合总数/木板拼接问题
         */
        int[] arr = {2,2,2,2,2,3,3,3,3,3,5,5,5,5,5};
        Arrays.sort(arr);
        List<Integer> path = new ArrayList<>();
        backtrack(path,arr,16,0,0);
        System.out.println(res);
        System.out.println(res.size() + "种解法");
    }

    // 组合总数/木板拼接问题
    private void backtrack(List<Integer> path,int[] candidates,int target,int sum,int begin) {
        if(sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i = begin;i < candidates.length;i++) {
            if(i > begin && candidates[i] == candidates[i-1]){
                continue;
            }
            int rs = candidates[i] + sum;
            if(rs <= target) {
                path.add(candidates[i]);
                backtrack(path,candidates,target,rs,i+1);
                path.remove(path.size()-1);
            } else {
                break;
            }
        }
    }

    //加密函数
    private static String encode(String code){
        char[] t = code.toCharArray();    //将String对象转换为字符数组
        for(int i=0; i < t.length; i++){
            if(t[i]>='a' && t[i]<'z')
                t[i] = (char)(t[i] - 'a' + 'A' + 1);
            else if(t[i] == 'z')
                t[i] = 'A';
            else if(t[i]>='A' && t[i]<'Z')
                t[i] = (char)(t[i] - 'A' + 'a' + 1);
            else if(t[i] == 'Z')
                t[i] = 'a';
            else if(t[i]>='0' && t[i]<'9')
                t[i] = (char)(t[i]+1);
            else if(t[i] == '9')
                t[i] = '0';
        }
        return String.valueOf(t);
    }
    //解密函数
    private static String decode(String code){
        char[] t = code.toCharArray();
        for (int i = 0; i < t.length; i++){
            if (t[i] > 'a' && t[i] <= 'z')
                t[i] = (char)(t[i] - 'a' + 'A' - 1);
            else if (t[i] == 'a')
                t[i] = 'Z';
            else if (t[i] > 'A' && t[i] <= 'Z')
                t[i] = (char)(t[i] - 'A' + 'a' - 1);
            else if (t[i] == 'A')
                t[i] = 'z';
            else if(t[i]>'0' && t[i]<='9')
                t[i] = (char)(t[i]-1);
            else if(t[i] == '0')
                t[i] = '9';
        }
        return String.valueOf(t);
    }
    //获取随机数组
    public static int[] generateRandomArray(int maxSize, int maxValue){
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];//长度随机
        for (int i = 0; i < arr.length; i++){
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue) * Math.random());
        }
        return arr;
    }
    //复制数组中的数
    public static int[] copyArray(int[] arr){
        if (arr == null){
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++){
            res[i] = arr[i];
        }
        return res;
    }
    //对比数组中的数是否相等
    public static boolean isEqual(int[] arr1, int[] arr2){
        if (arr1 == null || arr2 == null || arr1.length != arr2.length){
            return false;
        }
        for (int i = 0; i < arr1.length; i++){
            if (arr1[i] != arr2[i]){
                return false;
            }
        }
        return true;
    }
    //求数组中的最大值
    public static int process(int[] arr, int L, int R){
        if (L == R){
            return arr[L];
        }
        int mid = L + ((R - L) >> 1);//中间位置
        int leftMax = process(arr, L, mid);
        int rightMax = process(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);
    }
    //快排-----start------
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }
    public static void quickSort(int[] arr, int L, int R) {
        if (L < R) {
            //先随机取出一个数放到最后
            swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
            int[] p = partition(arr, L, R);
            quickSort(arr, L, p[0] - 1);
            quickSort(arr, p[1] + 1, R);
        }
    }
    public static int[] partition(int[] arr, int l, int r) {
        int less = l - 1;
        int more = r;
        while (l < more) {
            if (arr[l] < arr[r]) {
                swap(arr, ++less, l++);
            } else if (arr[l] > arr[r]) {
                swap(arr, --more, l);
            } else {
                l++;
            }
        }
        swap(arr, more, r);
        return new int[] { less + 1, more };
    }
    public static void swap(int[] arr, int i, int j) {
//        arr[i] = arr[i] ^ arr[j];
//        arr[j] = arr[i] ^ arr[j];
//        arr[i] = arr[i] ^ arr[j];
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }//快排-----end------
    public static void insertSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        for (int i = 1; i < arr.length; i++){
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--){
                arr[j] = arr[j] ^ arr[j + 1];
                arr[j + 1] = arr[j] ^ arr[j + 1];
                arr[j] = arr[j] ^ arr[j + 1];
            }
        }
    }

    //堆排序----------start------------
    //1、按大分堆方式整理数组
    public static void heapInsert(int[] arr, int index){
        while (arr[index] > arr[(index - 1) / 2]){
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }
    //2、移除最大节点数重新整理堆
    public static void heapify(int[] arr, int index, int heapSize){
        int left = index * 2 + 1;//左孩子的下表
        while (left < heapSize){//下方还有孩子的时候
            //两个孩子中，谁的值大，把下标给largest
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            //父和孩子之间谁的值大把下标给largest
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index){
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }
    //3、移除最大节点排序
    public static void heapSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        for (int i = 0; i < arr.length; i ++){
            heapInsert(arr, i);
        }
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0){
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }
    //堆排序----------end------------
    //系统提供小分堆整理数组
    public static void smallHeap(){
        //PriorityQueue<Integer> queue = new PriorityQueue<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>(new AComp());//系统提供大分堆整理数组
        queue.add(4);
        queue.add(3);
        queue.add(10);
        queue.add(8);
        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }
    }
    //自定义比较器
    public static class AComp implements Comparator<Integer>{
        @Override
        public int compare(Integer t1, Integer t2) {
            return t2 - t1;
        }
    }

}
