package com.springboot.deep_clone;

import com.google.gson.Gson;
import com.springboot.pojo.Student;
import com.springboot.pojo.Teacher;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * 浅拷贝是用的java的clone,慎用 Object 的 clone 方法来拷贝对象。对象的 clone 方法默认是浅拷贝，
 * 若想实现深拷贝需要重写 clone 方法实现属性对象的拷贝。
 *
 * 利用串行化来做深复制（主要是为了避免重写比较复杂对象的深复制的clone（）方法，也可以程序实现断点续传等等功能）
 把对象写到流里的过程是串行化（Serilization）过程，但是在Java程序师圈子里又非常形象地称为“冷冻”或者“腌咸菜（picking）”过程；而把对象从流中读出来的并行化（Deserialization）过程则叫做 “解冻”或者“回鲜(depicking)”过程。
 应当指出的是，写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面，因此“腌成咸菜”的只是对象的一个拷贝，Java咸菜还可以回鲜。
 在Java语言里深复制一个对象，常常可以先使对象实现Serializable接口，然后把对象（实际上只是对象的一个拷贝）写到一个流里（腌成咸菜），再从流里读出来（把咸菜回鲜），便可以重建对象。
 */
public class DeepClone {
    private Student student;
    @Before
    public void befor(){
        Teacher t = new Teacher("tangliang", 30);
        Student student = new Student("zhangsan", 18, t);
        this.student = student;
    }
    @Test
    public void test() throws Exception{
        //第一种使用deepClone方法,自定义的
        Student studentClone = (Student) student.deepClone();
        studentClone.getTeacher().setName("tony");
        studentClone.getTeacher().setAge(40);
        System.out.println(student.hashCode()+"     "+studentClone.hashCode());

        //另一种思路是,通过转成json再转换回来
        Gson json = new Gson();
        String pojo = json.toJson(student);
        Student gsonClone = json.fromJson(pojo, Student.class);
        gsonClone.getTeacher().setName("gson");
        System.out.println(student.hashCode()+"        "+gsonClone.hashCode());

        //第三种工具类 推荐这种高效而且简单
        Student serializationUtilsClone = SerializationUtils.clone(student);
        serializationUtilsClone.getTeacher().setName("serializationUtilsClone");
        System.out.println(student.hashCode()+"        "+serializationUtilsClone.hashCode());

        //注意!! 这种是浅克隆
        Student beanUtilsClone = (Student) BeanUtils.cloneBean(student);
        beanUtilsClone.getTeacher().setName("BeanUtis");
        System.out.println(student.hashCode()+"        "+beanUtilsClone.hashCode());
    }
}
