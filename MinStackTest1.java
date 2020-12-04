package com.lx.dao;

public class MinStackTest1 {

    public static void main(String[] args){


        MinStack s =new MinStack();
/*
输入：
["MinStack","push","push","push","top","pop","min","pop","min","pop","push","top","min","push","top","min","pop","min"]
[[],[2147483646],[2147483646],[2147483647],[],[],[],[],[],[],[2147483647],[],[],[-2147483648],[],[],[],[]]
输出：
[null,null,null,null,2147483647,null,2147483646,null,2147483646,null,null,2147483647,2147483646,null,-2147483648,-2147483648,null,2147483646]
预期：
[null,null,null,null,2147483647,null,2147483646,null,2147483646,null,null,2147483647,2147483647,null,-2147483648,-2147483648,null,2147483647]
*/


        s.push(2147483646);
        s.push(2147483646);
        s.push(2147483647);
        System.out.println(s.top());
        s.pop();
        System.out.println(s.min());
        s.pop();
        System.out.println(s.min());
        s.pop();
        s.push(2147483647);
        System.out.println(s.top());
        System.out.println(s.min());
        s.push(-2147483648);
        System.out.println(s.top());
        System.out.println(s.min());
        s.pop();
        System.out.println(s.min());




    }
}
