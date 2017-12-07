package com.zhiyin.gene;

public class Main<T> {
    public T create(Class<T> type) {
        try {
            return type.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        Main<String> m = new Main<String>();
        String s = m.create(String.class);
    }
}