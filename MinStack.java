package com.lx.dao;

import java.util.LinkedList;

class MinStack {

    /** initialize your data structure here. */
    private LinkedList<Integer> A,B;

    public MinStack() {
        A= new LinkedList<Integer>();
        B= new LinkedList<Integer>();
    }

    public void push(int x) {

        A.addLast(x);
        if(B.isEmpty()){

            B.addLast(x);
        }
        if(!B.isEmpty()&&B.peekLast()>=x){

            B.addLast(x);


        }
        System.out.println(x);


    }

    public void pop() {
        int x= A.pollLast();

        if(x==B.peekLast().intValue()){
          int z =  B.removeLast();



            if(B.size()==1){
                B.clear();  /**LinkedList removeLast的方法remove 最后一个时去除不掉*/
            }
        }


    }

    public int top() {


        return A.peekLast()!=null?A.peekLast():0;

    }

    public int min() {
        if(B.isEmpty()){
            return B.peekLast()!=null? B.peekLast():0;
        }
        return B.peekLast();

    }
}


