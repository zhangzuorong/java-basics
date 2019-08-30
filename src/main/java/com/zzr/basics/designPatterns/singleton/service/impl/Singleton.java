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
     * 双重校验锁先判断singletonIV是否已经被实例化，如果没有被实例化，那么才对实例化代码进行加锁
     *
     * 双重校验也就是使用两个if
     * 第一个 if 语句用来避免 singletonIV 已经被实例化之后的加锁操作，而第二个 if 语句进行了加锁，
     * 所以只能有一个线程进入，就不会出现 singletonIV == null 时两个线程同时进行实例化操作。
     */
    public static class SingletonIV{
        private SingletonIV(){}

        /**
         * singletonIV 采用 volatile 关键字修饰也是很有必要的， singletonIV = new SingletonIV(); 这段代码其实是分为三步执行：

         为 singletonIV 分配内存空间
         初始化 singletonIV
         将 singletonIV 指向分配的内存地址
         但是由于 JVM 具有指令重排的特性，执行顺序有可能变成 1>3>2。指令重排在单线程环境下不会出现问题，但是在多线程环境下会导致一个
         线程获得还没有初始化的实例。例如，线程 T1 执行了 1 和 3，此时 T2 调用 getSingletonIV() 后发现 singletonIV 不为空，
         因此返回 singletonIV，但此时 singletonIV 还未被初始化。

         使用 volatile 可以禁止 JVM 的指令重排，保证在多线程环境下也能正常运行。
         */
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

    /**
     * 当 SingLetonV 类被加载时，静态内部类 SingLetonVHolder 没有被加载进内存。
     * 只有当调用 getSingLetonV() 方法从而触发 SingLetonVHolder.singLetonv 时 SingLetonVHolder 才会被加载，
     * 此时初始化 singLetonv 实例，并且 JVM 能确保 INSTANCE 只被实例化一次。
     *
     * 这种方式不仅具有延迟初始化的好处，而且由 JVM 提供了对线程安全的支持。
     */
    public static class SingLetonV{

        private SingLetonV() {}

        private static class SingLetonVHolder{
            private static final SingLetonV singLetonv = new SingLetonV();
        }

        public static SingLetonV getSingLetonV(){
            return SingLetonVHolder.singLetonv;
        }
    }
}