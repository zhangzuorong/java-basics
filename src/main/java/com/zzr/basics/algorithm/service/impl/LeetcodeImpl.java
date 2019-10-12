package com.zzr.basics.algorithm.service.impl;

import com.zzr.basics.algorithm.service.LeetcodeService;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 开发公司：山东海豚数据技术有限公司
 * 版权：山东海豚数据技术有限公司
 * <p>
 * LeetcodeController
 *
 * @author zzr
 * @created Create Time: 2019/8/28
 */
@Service
public class LeetcodeImpl implements LeetcodeService{

    /**
     * 有序数组的 Two Sum
     * Input: numbers={2, 7, 11, 15}, target=9  Output: index1=1, index2=2
     * 题目描述：在有序数组中找出两个数，使它们的和为 target。
     */
    public int[] getTwoSum(int[] numbers,int target){
        if (numbers.length == 0){
            return null;
        }
        int a = 0;
        int b = numbers.length - 1;
        while (a < b){
            int sum = numbers[a] + numbers[b];
            if(sum == target){
                return new int[]{numbers[a],numbers[b]};
            }else if (sum > target) {
                b--;
            }else {
                a++;
            }
        }
        return null;
    }

    /**
     * Input: 5 Output: True Explanation: 1 * 1 + 2 * 2 = 5
     * 题目描述：判断一个数是否为两个数的平方和。
     */
    @Override
    public boolean judgeSquareSum(int num) {
        int i = 0;
        int j = (int)Math.sqrt(num);
        while (i <= j){
            int sum = i * i + j * j;
            if(sum == num){
                return true;
            }else if(sum > num){
                j--;
            }else {
                i++;
            }
        }
        return false;
    }

    @Test
    public void haha(){
        int[] intArr = new int[]{9,8,7,6,5,4,3,2,1};
        test(intArr);
    }

    public void test(int[] intArr){
        if (intArr == null || intArr.length == 0){
            System.out.println("请输入要排序的数组");
        }
        int n = intArr.length;
        System.out.println(Arrays.toString(intArr) + "数组长度为："+n);

        for (int i = 0 ; i < n ; i ++) {
            boolean success = true;
            for (int j = 0; j < n - 1 - i; j ++) {
                if (intArr[j] > intArr[j + 1]) {
                    int end = intArr[j];
                    intArr[j] = intArr[j + 1];
                    intArr[j + 1] = end;
                    success = false;
                }
            }
            if (success){
                break;
            }
            System.out.println(Arrays.toString(intArr));
        }
    }
}
