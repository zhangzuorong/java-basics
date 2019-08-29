package com.zzr.basics.designPatterms.singleton.service;

import com.zzr.basics.ServiceApplication;
import com.zzr.basics.designPatterns.singleton.service.impl.Singleton;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 开发公司：山东海豚数据技术有限公司
 * 版权：山东海豚数据技术有限公司
 * <p>
 * SingletonTest
 *
 * @author zzr
 * @created Create Time: 2019/8/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class SingletonTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void getSingletonI(){
        Singleton.SingletonI singletonOne = Singleton.SingletonI.getSingleton();
        Singleton.SingletonI singletonTwo = Singleton.SingletonI.getSingleton();
        logger.info("singletonOne"+singletonOne.hashCode()+" singletonTwo"+singletonTwo.hashCode());
    }

    @Test
    public void getSingletonII(){
        Singleton.SingletonII singletonOne = Singleton.SingletonII.getSingletonII();
        Singleton.SingletonII singletonTwo = Singleton.SingletonII.getSingletonII();
        logger.info("singletonOne"+singletonOne.hashCode()+" singletonTwo"+singletonTwo.hashCode());
    }
}
