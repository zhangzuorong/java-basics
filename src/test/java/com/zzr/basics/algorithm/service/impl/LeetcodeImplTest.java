package com.zzr.basics.algorithm.service.impl;

import com.zzr.basics.ServiceApplication;
import com.zzr.basics.algorithm.service.LeetcodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 开发公司：山东海豚数据技术有限公司
 * 版权：山东海豚数据技术有限公司
 * <p>
 * LeetcodeImplTest
 *
 * @author zzr
 * @created Create Time: 2019/8/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class LeetcodeImplTest{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LeetcodeService leetcodeService;

    @Test
    public void getTwoSum(){
        int[] numbers = {1,2,3,7,10};
        int target = 3;
        int[] arr = leetcodeService.getTwoSum(numbers,target);
        if (arr != null) {
            logger.info("=================={"+arr[0]+","+arr[1]+"}");
        }else {
            logger.info("==================不满足条件");
        }
    }

    @Test
    public void judgeSquareSum(){
        int num = 3;
        Boolean type = leetcodeService.judgeSquareSum(num);
        if (type) {
            logger.info("是否为两个数的平方和:"+type);
        }else {
            logger.info("是否为两个数的平方和:"+type);
        }
    }
}
