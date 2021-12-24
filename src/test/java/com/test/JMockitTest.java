package com.test;

import com.dujinyue.HelloJMockit;
import mockit.*;
import org.junit.Test;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


/**
 * @ Mocked @Tested, @Injectable,@Capturing
 * 测试属性,就是autowried的类,
 * 它的实例化，属性赋值，方法调用的返回值全部由JMockit来接管，接管后，helloJMockit的行为与HelloJMockit类定义的不一样了，而是由录制脚本来定义了
 * <p>
 * <p>
 * Record-Replay-Verification 是JMockit测试程序的主要结构。
 * Record: 即先录制某类/对象的某个方法调用，在当输入什么时，返回什么。
 * Replay: 即重放测试逻辑。
 * Verification: 重放后的验证。比如验证某个方法有没有被调用，调用多少次。
 * @ Injectable 也表示一个Mocked对象，相比@Mocked，只不过只影响类的一个实例。而@Mocked默认是影响类的所有实例。
 * @ Tested表示被测试对象。如果该对象没有赋值，JMockit会去实例化它，若@Tested的构造函数有参数，
 * 则JMockit通过在测试属性&测试参数中查找@Injectable修饰的Mocked对象注入@Tested对象的构造函数来实例化，
 * 不然，则用无参构造函数来实例化。除了构造函数的注入，JMockit还会通过属性查找的方式，把@Injectable对象注入到@Tested对象中。
 * <p>
 * 当我们需要手工管理被测试类的依赖时,就需要用到@Tested & @Injectable。两者搭配起来用，JMockit就能帮我们轻松搞定被测试类及其依赖注入细节
 * @ Capturing
 * 我们只知道父类或接口时，但我们需要控制它所有子类的行为时，子类可能有多个实现（可能有人工写的，也可能是AOP代理自动生成时）。就用@Capturing。
 */
public class JMockitTest {

    /**
     * 录制规范 可以写多个
     * new Expectations() {
     * // 这是一个Expectations匿名内部类
     * {
     * // 这是这个内部类的初始化代码块，我们在这里写录制脚本，脚本的格式要遵循下面的约定
     * //方法调用(可是类的静态方法调用，也可以是对象的非静态方法调用)
     * //result赋值要紧跟在方法调用后面
     * //...其它准备录制脚本的代码
     * //方法调用
     * //result赋值
     * }
     * };
     * <p>
     * 主要使用方式:
     * 1.通过引用外部类的Mock对象(@Injectabe,@Mocked,@Capturing)来录制
     * 2.// 把待Mock的类传入Expectations的构造函数，可以达到只mock类的部分行为的目的
     * new Expectations(Calendar.class) {
     * {
     * // 只对get方法并且参数为Calendar.HOUR_OF_DAY进行录制
     * cal.get(Calendar.HOUR_OF_DAY);
     * result = 7;// 小时永远返回早上7点钟
     * }
     * };
     * <p>
     * // 把待Mock的对象传入Expectations的构造函数，可以达到只mock类的部分行为的目的，但只对这个对象影响(传入的是对象)
     * new Expectations(cal) {
     * {
     * // 只对get方法并且参数为Calendar.HOUR_OF_DAY进行录制
     * cal.get(Calendar.HOUR_OF_DAY);
     * result = 7;// 小时永远返回早上7点钟
     * }
     * };
     */
    @Test
    public void testSayHelloAtChina() {
        // 录制
        new Expectations(Locale.class) {
            {
                Locale.getDefault();
                result = Locale.UK;
            }
        };
        //重放 + 验证
        System.out.println(new HelloJMockit(10).sayHello());
    }

    /**
     * MockUp 录制
     * 使用mockup可以减少重复代码
     */
    @Test
    public void test2() {
        // 只需要把Calendar类传入MockUp类的构造函数即可
        //mockup的第一种用法
        new MockUp<Calendar>(Calendar.class) {
            // 想Mock哪个方法，就给哪个方法加上@Mock， 没有@Mock的方法，不受影响
            @Mock
            public int get(int unit) {
                if (unit == Calendar.YEAR) {
                    return 2017;
                }
                if (unit == Calendar.MONDAY) {
                    return 12;
                }
                if (unit == Calendar.DAY_OF_MONTH) {
                    return 25;
                }
                if (unit == Calendar.HOUR_OF_DAY) {
                    return 7;
                }
                return 0;
            }
        };

        // 从此Calendar的get方法，就沿用你定制过的逻辑，而不是它原先的逻辑。
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        System.out.println(cal.get(Calendar.YEAR) == 2017);
        System.out.println(cal.get(Calendar.MONDAY) == 12);
        System.out.println(cal.get(Calendar.DAY_OF_MONTH) == 25);
        System.out.println(cal.get(Calendar.HOUR_OF_DAY) == 7);
        // Calendar的其它方法，不受影响
        System.out.println((cal.getFirstDayOfWeek() == Calendar.MONDAY));
    }

    @Test
    public void test3() {
        new CalendarMock();
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        System.out.println(cal.get(Calendar.YEAR) == 2017);
        System.out.println(cal.get(Calendar.MONDAY) == 12);
        System.out.println(cal.get(Calendar.DAY_OF_MONTH) == 25);
        System.out.println(cal.get(Calendar.HOUR_OF_DAY) == 7);
        // Calendar的其它方法，不受影响
        System.out.println((cal.getFirstDayOfWeek() == Calendar.MONDAY));
    }

    /**
     * mockUp的第二种用法
     */
    public static class CalendarMock extends MockUp<Calendar> {
        @Mock
        public int get(int unit) {
            if (unit == Calendar.YEAR) {
                return 2017;
            }
            if (unit == Calendar.MONDAY) {
                return 12;
            }
            if (unit == Calendar.DAY_OF_MONTH) {
                return 25;
            }
            if (unit == Calendar.HOUR_OF_DAY) {
                return 7;
            }
            return 0;
        }
    }

    @Test
    public void test4() {
        new MockLocal();
        HelloJMockit pojo = new HelloJMockit(111);
        System.out.println(pojo.getI());
        System.out.println(pojo.j);
    }

    public class MockLocal extends MockUp<HelloJMockit> {
        /**
         * $init 可以屏蔽掉构造函数中有很多逻辑必须执行,且不执行会报错的.
         */
        @Mock
        public void $init(int i) {
        }

        /**
         * 这个是屏蔽掉静态初始化块
         */
        @Mock
        public void $clinit() {
        }
    }

    /**
     * 申明参数invocation，表示老方法的调用
     */
    @Test
    public void testMockUp() {
        // 对Java自带类Calendar的get方法进行定制
        new MockUp<Calendar>(Calendar.class) {
            // 申明参数invocation，表示老方法的调用
            @Mock
            public int get(Invocation invocation, int unit) {
                // 只希望时间是早上7点
                if (unit == Calendar.HOUR_OF_DAY) {
                    return 7;
                }
                // 其它时间（年份，月份，日，分，秒均不变)
                return invocation.proceed(unit);
            }
        };
        Calendar now = Calendar.getInstance();
        // 只有小时变成Mock方法
        System.out.println(now.get(Calendar.HOUR_OF_DAY) == 7);
        // 其它的还是走老的方法
        System.out.println(now.get(Calendar.MONTH));
        System.out.println(now.get(Calendar.DAY_OF_MONTH));
    }

    //同一方法返回时序结果
    // 一个类所有实例的某个方法，返回时序结果。
    // 适用场景：每次调用，期望返回的数据不一样。比如从tcp数据流中拿数据
    @Test
    public void testInputStreamSequence() {
        try {
            // 依据地址创建URL
            URL url = new URL("http://jmockit.cn");
            // 获得urlConnecion
            URLConnection connection = url.openConnection();
            // 打开连接
            connection.connect();
            InputStream in = connection.getInputStream();
            //现在对jmockit.cn服务器返回的数据进行mock
            new Expectations(in) {
                {
                    in.read();
                    // -1表示流数据结束了
                    result = new int[]{1, 2, 3, 4, 5, -1};
                }
            };
            // 读取jmockit.cn服务器返回的内容，如果没有上面的mock,返回将是实际的内容
            System.out.println(in.read() == 1);
            System.out.println(in.read() == 2);
            System.out.println(in.read() == 3);
            System.out.println(in.read() == 4);
            System.out.println(in.read() == 5);
            System.out.println(in.read() == -1);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //打招呼的接口
    interface ISayHello {
        // 性别：男
        int MALE = 0;
        // 性别：女
        int FEMALE = 1;

        /**
         * 打招呼
         *
         * @param who    向谁说
         * @param gender 对方的性别
         * @return 返回打招呼的内容
         */
        String sayHello(String who, int gender);

        /**
         * 向多个人打招呼
         *
         * @param who    向谁说
         * @param gender 对方的性别
         * @return 返回向多个人打招呼的内容
         */
        List<String> sayHello(String[] who, int[] gender);

    }

    class SayHello implements ISayHello {
        @Override
        public String sayHello(String who, int gender) {
            // 性别校验
            if (gender != FEMALE) {
                if (gender != MALE) {
                    throw new IllegalArgumentException("illegal gender");
                }
            }
            // 根据不同性别，返回不同打招呼的内容
            switch (gender) {
                case FEMALE:
                    return "hello Mrs " + who;
                case MALE:
                    return "hello Mr " + who;
                default:
                    return "hello  " + who;
            }
        }

        @Override
        public List<String> sayHello(String[] who, int[] gender) {
            // 参数校验
            if (who == null || gender == null) {
                return null;
            }
            if (who.length != gender.length) {
                throw new IllegalArgumentException();
            }
            //把向每个人打招呼的内容，保存到result中。
            List<String> result = new ArrayList<String>();
            for (int i = 0; i < gender.length; i++) {
                result.add(this.sayHello(who[i], gender[i]));
            }
            return result;
        }
    }

    /**
     * 如果想根据入参，返回结果的内容，怎么办呢
     *
     * 也可以根据入参做不同的调用,返回不同的值
     */
    @Test
    public void testDelegate() {
        new Expectations(SayHello.class) {
            {
                SayHello instance = new SayHello();
                instance.sayHello(anyString, anyInt);
                result = new Delegate() {
                    // 当调用sayHello(anyString, anyInt)时，返回的结果就会匹配delegate方法，
                    // 方法名可以自定义，当入参和返回要与sayHello(anyString, anyInt)匹配上
                    @SuppressWarnings("unused")
                    String delegate(Invocation inv, String who, int gender) {
                        // 如果是向动物鹦鹉Polly问好，就说hello,Polly
                        if ("Polly".equals(who)) {
                            return "hello,Polly";
                        }
                        // 其它的入参，还是走原有的方法调用
                        return inv.proceed(who, gender);
                    }
                };
            }
        };

        SayHello instance = new SayHello();
        System.out.println(instance.sayHello("david", ISayHello.MALE).equals("hello Mr david"));
        System.out.println(instance.sayHello("lucy", ISayHello.FEMALE).equals("hello Mrs lucy"));
        System.out.println(instance.sayHello("Polly", ISayHello.FEMALE).equals("hello,Polly"));
    }

}
