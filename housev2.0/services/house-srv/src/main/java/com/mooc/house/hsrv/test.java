package com.mooc.house.hsrv;

public class test {
    public static void main(String[] args) {
        String mobiles = "137214514,56456222,55466454";
        String[] split = mobiles.split("[,:]");
        for (String mobile : split) {
            System.out.println(mobile);
        }
    }
}
