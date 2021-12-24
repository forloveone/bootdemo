package com.test.base.checkpojovalidate;

import com.dujinyue.utils.ValidationUtil;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * 测试 hibernate validator 的 pojo检测工具, 可以让pojo的字段检测判断的更通用
 */
public class TestPojoValidate {

    private Account account;

    @Before
    public void before() {
        Account account = new Account();
        this.account = account;

        account.setId("2");   // 测试了分组的作用

        account.setFlag(false); //被注释的元素必须为 true

        account.setUserName("王五");//不为null 且长度最大为 20
        account.setMark("我们是中国人我们是中国我们是中国");//字符串长度为[3-15]
        account.setAlias("");//不能为null且不能为空字符串
        account.setLength("111111");//字符串长度[3-5]

        account.setPassWord("A");//支持正则表达式
        account.setCreateTime("2019-01-272");//正则表达式日期校验
        account.setEmail("123");//简单email校验复杂的需要正则

        account.setLevel(11);//integer 类型范围取值必须为[1,10]
        account.setDecimal(new BigDecimal("0.2"));//BigDecimal 类型必须在[0.3-1.2]
        account.setDou(333.222);//double类型

        account.setDataStr("123");//注解满足不了要求则,自定义注解
    }

    @Test
    public void testDefaultGroup() {
        System.out.println(ValidationUtil.validateBean(account).getErrors());
    }

    /**
     * 使用null接口 作为group的区别,所以只有id是 NullInterface组的,所以只检测了它一个属性
     */
    @Test
    public void testNullInterfaceGroup() {
        System.out.println(ValidationUtil.validateBean(account, NullInterface.class).getErrors());
    }
}
