package disconnection;

import org.junit.Test;

public class ReverseLinkedList {

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(String.valueOf(val));
            ListNode next = this.next;
            while (next != null) {
                sb.append(next.val);
                next = next.next;
            }
            return sb.toString();
        }
    }

    /**
     * 迭代法
     */
    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode tmp;
        while (cur != null) {
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    /**
     * 递归法
     */
    public static ListNode reverseList2(ListNode pre, ListNode cur) {
        if (cur == null) {
            return pre;
        }
        ListNode tmp = cur.next;
        cur.next = pre;
        pre = cur;
        cur = tmp;
        return reverseList2(pre, cur);
    }

    @Test
    public void test(){
        ListNode head = new ListNode(3, new ListNode(5, new ListNode(8, new ListNode(9))));
        System.out.println("初始链表：" + head);
        System.out.println("使用迭代法反转链表：" + reverseList(head));
    }
}
