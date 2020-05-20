package com.test.checkpojovalidate;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * @Null 被注释的元素必须为 null
 * @NotNull 被注释的元素必须不为 null
 * @AssertTrue 被注释的元素必须为 true
 * @AssertFalse 被注释的元素必须为 false
 * @Min(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 * @Max(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 * @DecimalMin(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 * @DecimalMax(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 * @Size(max, min)	被注释的元素的大小必须在指定的范围内
 * @Digits (integer, fraction)    被注释的元素必须是一个数字，其值必须在可接受的范围内
 * @Past 被注释的元素必须是一个过去的日期
 * @Future 被注释的元素必须是一个将来的日期
 * @Pattern(value) 被注释的元素必须符合指定的正则表达式
 * @Email 被注释的元素必须是电子邮箱地址
 * @Length 被注释的字符串的大小必须在指定的范围内
 * @NotEmpty 被注释的字符串的必须非空
 * @Range 被注释的元素必须在合适的范围内
 */
@Data
public class Account {
    @Null(groups = {NullInterface.class}) //分组的作用是,当一个pojo对应多个不同业务时,可以用来分组,每个组别互不影响,不指定极为默认分组
//    @Null
    private String id;

    @AssertTrue
    private boolean flag;

    @NotNull
    @Length(max = 20)
    private String userName;

    @NotNull
    @Pattern(regexp = "[A-Za-z0-9]")
    private String passWord;

    @NotNull
    @Pattern(regexp = "((?:19|20)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])", message = "日期格式必须为yyyy-mm-dd")
    private String createTime;

    @NotEmpty(message = "alias不能为空,且不能为空字符串")
    private String alias;

    @Max(10)
    @Min(1)
    private Integer level;

    @DecimalMin(value = "0.3")
    @DecimalMax(value = "1.2")
    private BigDecimal decimal;

    private Integer vip;

    @Size(min = 3, max = 15, message = "长度应该在3-15位")
    private String mark;

    @Length(min = 3, max = 5)
    private String length;

    @Email
    private String email;//只能校验很简单的,复杂的需要正则

    @Digits(integer = 3, fraction = 2)
    private double dou;

    @DateValidator(dateFormat = "yyyy-MM-dd")
    private String dataStr;
}