package com.springboot.fanxing;

import com.springboot.pojo.Student;
import com.springboot.pojo.Teacher;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Field;

/**
 * 应对一个需求
 */
public class Fanxing {
    private Student student;
    private Teacher teacher;

    @Before
    public void before() {
        Student student = new Student();
        student.setAge(11);
        student.setName("wangwu");
        this.student = student;

        Teacher teacher = new Teacher();
        teacher.setAge(100);
        teacher.setName("历史");
        this.teacher = teacher;
        student.setTeacher(teacher);
    }

    @Test
    public void test() throws Exception {
        Student student1 = (Student) BeanUtils.cloneBean(student);
        System.out.println(student.hashCode());
        System.out.println(student1.hashCode());


        StringBuffer sb = new StringBuffer();
//        sb.append("<a>"+student.getName()+"</a>");
        test(sb, student);
        test(sb, teacher);

    }

    @Test
    /**
     * 反射获得一个属性的值
     */
    public <T> void reflect(T obj) throws Exception{
        Field field = obj.getClass().getDeclaredField("name");
        field.setAccessible(true);
        Object val = field.get(obj);
        System.out.println(val.toString());
    }


    /**
     * 泛型+反射   可以完成特殊的代码公用需求,
     * 例如:
     * 同一个模板(String,或硬拼接的xml模板),两个类共同使用,而且两个类中的属性名基本相同
     */
    private <T> void test(StringBuffer sb, T obj) throws Exception {
        System.out.println(BeanUtils.getProperty(obj, "age"));
        System.out.println(PropertyUtils.getProperty(obj, "age"));
        System.out.println(BeanUtils.getProperty(obj, "name"));
//        sb.append("<a>" + obj.getName() + "</a>");
    }
}
