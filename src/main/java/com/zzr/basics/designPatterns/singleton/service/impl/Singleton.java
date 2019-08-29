package com.zzr.basics.designPatterns.singleton.service.impl;

import org.springframework.stereotype.Service;

/**
 * SingletonImpl
 * 单利模式
 * 确保一个类只有一个实例，并提供该实例的全局访问点。
 * 使用一个私有构造函数、一个私有静态变量以及一个公有静态函数来实现。
 * 私有构造函数保证了不能通过构造函数来创建对象实例，只能通过公有静态函数返回唯一的私有静态变量。
 * @author zzr
 * @created Create Time: 2019/8/29
 */
@Service
public class Singleton {
    /**
     * 懒汉式-线程不安全
     * 以下代码中私有静态变量singletonI只有在被使用时才会被实例化，因此节约资源
     *
     * 这个实现在多线程下是不安全的，如果多个现成同时进入if (singletonI == null)，并且此时singletonI为ull,那么将导致实例化多次singletonI
     */
    public static class SingletonI{
        private SingletonI (){}

        private static SingletonI singletonI;

        public static SingletonI getSingleton(){
            if (singletonI == null) {
                singletonI = new SingletonI();
            }
            return singletonI;
        }
    }

    /**
     * 饿汉式-线程安全
     * 线程不安全问题主要是由于 singletonII 被实例化多次，采取直接实例化 singletonII 的方式就不会产生线程不安全问题。
     * 但是直接实例化的方式也丢失了延迟实例化带来的节约资源的好处。
     */
    public static class SingletonII{
        private SingletonII () {}
        private static SingletonII singletonII = new SingletonII();

        public static SingletonII getSingletonII(){
            return singletonII;
        }
    }

    /**
     * 懒汉式-线程安全
     * 在getSingletonIII() 上加锁，会保证同一时刻只有一个线程能够进入该方法，从而避免了多次实例化singletonIII，
     * 但是当一个线程进入该方法之后，其它试图进入该方法的线程都必须等待，即使singletonIII已经被实例化，这会让线程阻塞时间过长，有效率问题，不推荐使用
     */
    public static class SingletonIII{
        private SingletonIII () {}
        private static SingletonIII singletonIII;

        public static synchronized SingletonIII getSingletonIII(){
            if (singletonIII == null) {
                singletonIII = new SingletonIII();
            }
            return singletonIII;
        }
    }

    /**
     * 双重校验锁-线程安全
     */
    public static class SingletonIV{
        private SingletonIV(){}
        private volatile static SingletonIV singletonIV;

        public static SingletonIV getSingletonIV(){
            if (singletonIV == null) {
                synchronized (SingletonIV.class){
                    if (singletonIV == null) {
                        singletonIV = new SingletonIV();
                    }
                }
            }
            return singletonIV;
        }
    }
}