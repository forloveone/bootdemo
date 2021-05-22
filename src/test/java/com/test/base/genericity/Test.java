package com.test.base.genericity;

import java.util.ArrayList;
import java.util.List;

/**
 * Java 的泛型在编译器有效，在运行期被删除，也就是说所有泛型参数类型在编译后都会被清除掉
 * test(List<Number>) 方法在编译时擦除类型后的方法是test(List<E>)，它与另外一个方法重复，也就是方法签名重复
 */
public class Test {
    public static void main(String[] args) {
        List<Number> list = new ArrayList<>();
        List<Integer> listIn = new ArrayList<>();
//        test(listIn);//编译错误  编译器禁止某些泛型的使用方式
        //假设这样的做法是允许的，那么在inspect方法就可以通过list.add(Number)来向集合中添加一个Double。
        // 这样在test方法看来，其声明为List<Number>的集合中却被添加了一个Double类型的对象。
        // 这显然是违反类型安全的原则的，在某个时候肯定会抛出ClassCastException。因此，编译器禁止这样的行为
    }

    public static void test(List<Number> list) {

    }

//    public static void test(List<Integer> list){
//
//    }

    /**
     * 试图对一个带通配符的泛型类进行操作的时候，总是会出现编译错误。其原因在于通配符所表示的类型是未知的。
     * <p>
     * 因为对于List<?>中的元素只能用Object来引用，在有些情况下不是很方便。在这些情况下，可以使用上下界来限制未知类型的范围。
     * 如List<? extends Number>说明List中可能包含的元素类型是Number及其子类。
     * 而List<? super Number>则说明List中包含的是Number及其父类。当引入了上界之后，在使用类型的时候就可以使用上界类中定义的方法。
     * 比如访问 List<? extends Number>的时候，就可以使用Number类的intValue等方法。
     */
    public void testTemp(List<?> list) {
//        list.add(1); //编译错误
    }
}
