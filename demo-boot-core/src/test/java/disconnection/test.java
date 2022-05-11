package disconnection;

import org.junit.Test;

import java.util.*;

public class test {

    @Test
    public void testStr() {
        new HeiJie();
    }

    @Test
    public void testRandom(){
        int a = 10 >> 1;
        int b = a++;
        int c = ++a;
        int d = b * a++;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
    }

    class HeiJie extends School{
        String name = "黑寡妇";
        public HeiJie(){
            getName();
        }

        public void getName() {
            System.out.println(name);
            System.out.println(super.name);
        }
    }

    class School{
        String name = "传智学校";
        public School(){
            getName();
        }

        public void getName() {
            System.out.println(name);
        }
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i< nums.length; i++) {
            if(map.containsKey(target - nums[i])) {
                return new int[] {map.get(target-nums[i]),i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    @Test
    public void tt(){

    }
}
