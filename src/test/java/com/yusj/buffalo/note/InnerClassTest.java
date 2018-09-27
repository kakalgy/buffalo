package com.yusj.buffalo.note;

import org.junit.Test;

/**
 * * 1.静态内部类可以有静态成员(方法，属性)，而非静态内部类则不能有静态成员(方法，属性)。
 * * 2.静态内部类只能够访问外部类的静态成员,而非静态内部类则可以访问外部类的所有成员(方法，属性)。
 * * 3.实例化一个非静态的内部类的方法：
 * *  a.先生成一个外部类对象实例
 * *  OutClassTest oc1 = new OutClassTest();
 * *  b.通过外部类的对象实例生成内部类对象
 * *  OutClassTest.InnerClass no_static_inner = oc1.new InnerClass();
 * *  4.实例化一个静态内部类的方法：
 * *  a.不依赖于外部类的实例,直接实例化内部类对象
 * *  OutClassTest.InnerStaticClass inner = new OutClassTest.InnerStaticClass();
 * *  b.调用内部静态类的方法或静态变量,通过类名直接调用
 * *  OutClassTest.InnerStaticClass.static_value
 * *  OutClassTest.InnerStaticClass.getMessage()
 *
 * </p>链接：
 * <ul><li>1.https://blog.csdn.net/cd18333612683/article/details/79129503</li></ul>
 */
public class InnerClassTest {
    static int a;

    int b;

    /***
     * 构造函数
     */
    public InnerClassTest(int i) {
        // new一个非静态的内部类
        InnerClass ic = new InnerClass();
        System.out.println("OuterClass create");
    }

    public InnerClassTest() {
    }

    public static void test() {

        System.out.println("outer class static function");
    }

    public void outTest() {
        System.out.println("outer class no-static function");
    }


    public static void main(String[] args) {
//        InnerClassTest oc = new InnerClassTest();
        InnerClassTest innerClassTest1 = new InnerClassTest();
        InnerClass innerClass1 = innerClassTest1.new InnerClass();
        innerClass1.flag = 2;
        System.out.println(innerClass1.flag);
        InnerClass innerClass2 = innerClassTest1.new InnerClass();
        innerClass2.flag = 4;
        System.out.println(innerClass1.flag);
        System.out.println(innerClass2.flag);

    }

    private class InnerClass {
        // 只有在静态内部类中才能够声明或定义静态成员
//         private static String tt = "0";
        private int flag = 0;

        public InnerClass() {
            // 三.非静态内部类的非静态成员可以访问外部类的非静态变量和静态变量
            System.out.println("InnerClass create a:" + a);
            System.out.println("InnerClass create b:" + b);
            System.out.println("InnerClass create flag:" + flag);
            //
            System.out.println("InnerClass call outer static function");
            // 调用外部类的静态方法
            test();
            outTest();
        }

        public String getKey() {
            return "no-static-inner";
        }
    }

    private static class InnerStaticClass {
        // 静态内部类可以有静态成员，而非静态内部类则不能有静态成员。
        private static String static_value = "0";

        private int flag = 0;

        public InnerStaticClass() {
            System.out.println("InnerStaticClass create a:" + a);
            // 静态内部类不能够访问外部类的非静态成员
//             System.out.println("InnerClass create b:" + b);
            System.out.println("InnerStaticClass flag is " + flag);
            System.out.println("InnerStaticClass static_value is " + static_value);
        }

        public int getValue() {
            // 静态内部类访问外部类的静态方法
            test();
            return 1;
        }

        public static String getMessage() {
            return "static-inner";
        }
    }

//    @Test
//    public void testInnerClass() {
//        InnerClassTest innerClassTest1 = new InnerClassTest();
//
//        InnerClass innerClass1 = new InnerClass();
//    }
//
//    public void testInnerStaticClass() {
//    }
//
//    @Test
//    public void testBasicClass() {
//        // new一个外部类
//        InnerClassTest oc1 = new InnerClassTest();
//        System.out.println("---------------------------");
//        // 通过外部类的对象new一个非静态的内部类
//        InnerClassTest.InnerClass no_static_inner = oc1.new InnerClass();
//        // 调用非静态内部类的方法
//        System.out.println(no_static_inner.getKey());
//        System.out.println("---------------------------");
//
//
//        // 调用静态内部类的静态变量
//        System.out.println("InnerStaticClass.static_value = " + InnerClassTest.InnerStaticClass.static_value);
//        System.out.println("---------------------------");
//
//        // 不依赖于外部类实例,直接实例化内部静态类
//        InnerClassTest.InnerStaticClass inner = new InnerClassTest.InnerStaticClass();
//        // 调用静态内部类的非静态方法
//        System.out.println(inner.getValue());
//        // 调用内部静态类的静态方法
//        System.out.println(InnerClassTest.InnerStaticClass.getMessage());
//    }
}