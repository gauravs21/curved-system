package com.ahytech.kopite.curveddata;

/**
 * Created by kopite on 15/2/17.
 */

public class PrimeNumber {
    public static void main(String[] args) {
        int num =0;
        boolean flag=true;
        for (int i = 2; i < 10; i++) {


            for (int j = 2; j <= i / 2; j++) {

                if (i % j == 0) {
                    flag = false;
                }
            }
            if(flag)
            {
                num++;
                flag=true;
            }
            else
                flag=true;
        }
        System.out.println(num);
    }
}
