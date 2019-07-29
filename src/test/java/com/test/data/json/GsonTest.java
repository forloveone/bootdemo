package com.test.data.json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.springboot.utils.RuntimeTypeAdapterFactory;
import com.test.class_relationship.JsonF;
import com.test.class_relationship.JsonS;
import com.test.class_relationship.JsonS2;
import com.test.data.enums.EnumJSON;
import com.test.pojo.*;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;

//java对象和json格式数据 的转换
public class GsonTest {
    private GsonPojo pojo;
    private Gson json = new Gson();
    private List<RequestPojo> list;

    @Before
    public void init() {
        List<RequestPojo> list = new ArrayList<>();
        RequestPojo pojo1 = new RequestPojo();
        pojo1.setAddress("add1");
        pojo1.setName("t1");
        Student s1 = new Student();
        s1.setAge(1);
        s1.setName("s");
        s1.setTeacher(new Teacher("t1", 22));
        List<Student> lst = new ArrayList<>();
        lst.add(s1);
        pojo1.setStudents(lst);

        list.add(pojo1);
        RequestPojo pojo2 = new RequestPojo();
        pojo2.setName("t2");
        pojo2.setAddress("add2");
        list.add(pojo2);
        pojo = new GsonPojo(1, 2, "王五", new Date(), new BigDecimal("1.1"), true,
                new GsonPojo(1, 2, "王五", new Date(), new BigDecimal("1.1"), true, new GsonPojo(), list), list);
        this.list = list;
    }

    /**
     * toJson 把java对象转成json对象
     */
    @Test
    public void toJson() {
        //支持pojo里面套对象,套list<pojo> list里再套list
        String t = json.toJson(pojo);
        System.out.println(t);
    }

    /**
     * fromJson 把json转成Pojo类
     */
    @Test
    public void fromJson() {
        String test = "{\"id\":1,\"no\":2,\"name\":\"王五\",\"addTime\":\"May 30, 2019 10:22:46 AM\",\"much\":1.1,\"flag\":true,\"pojo\":{\"id\":1,\"no\":2,\"name\":\"王五\",\"addTime\":\"May 30, 2019 10:22:46 AM\",\"much\":1.1,\"flag\":true,\"pojo\":{\"id\":0,\"flag\":false},\"list\":[{\"name\":\"t1\",\"address\":\"add1\",\"students\":[{\"name\":\"s\",\"age\":1,\"teacher\":{\"name\":\"t1\",\"age\":22}}]},{\"name\":\"t2\",\"address\":\"add2\"}]},\"list\":[{\"name\":\"t1\",\"address\":\"add1\",\"students\":[{\"name\":\"s\",\"age\":1,\"teacher\":{\"name\":\"t1\",\"age\":22}}]},{\"name\":\"t2\",\"address\":\"add2\"}]}\n";
        //支持pojo里面套对象,套list<pojo> 再套list
        GsonPojo entity = json.fromJson(test, GsonPojo.class);

        //List 套 实体类 直接转成list的情况
        String list = "[{\"name\":\"kjjl\",\"address\":\"hahha\"},{\"name\":\"哈哈\",\"address\":\"test\"}]";
        Type founderListType = new TypeToken<ArrayList<RequestPojo>>() {
        }.getType();
        List<RequestPojo> ll = json.fromJson(list, founderListType);

        System.out.println(entity);
        System.out.println(ll);
    }

    @Test
    public void map() {
        RequestPojo pojo1 = new RequestPojo();
        pojo1.setAddress("add1");
        pojo1.setName("t1");
        Student s1 = new Student();
        s1.setAge(1);
        s1.setName("s");
        s1.setTeacher(new Teacher("t1", 22));
        List<Student> lst = new ArrayList<>();
        lst.add(s1);
        pojo1.setStudents(lst);
        Map map = new HashMap();
        map.put("map1", pojo1);
        map.put("map2", pojo1);
        List list = new ArrayList();
        list.add(map);

        String mapStr = json.toJson(map);
        System.out.println(mapStr);

        String mapTest = "{\"map2\":{\"name\":\"t1\",\"address\":\"add1\",\"students\":[{\"name\":\"s\",\"age\":1,\"teacher\":{\"name\":\"t1\",\"age\":22}}]},\"map1\":{\"name\":\"t1\",\"address\":\"add1\",\"students\":[{\"name\":\"s\",\"age\":1,\"teacher\":{\"name\":\"t1\",\"age\":22}}]}}\n";
        Type amountCurrencyType = new TypeToken<HashMap<String, RequestPojo>>() {
        }.getType();
        Map map1 = json.fromJson(mapTest, amountCurrencyType);
        System.out.println();

        String listMap = "[{\"map2\":{\"name\":\"t1\",\"address\":\"add1\",\"students\":[{\"name\":\"s\",\"age\":1,\"teacher\":{\"name\":\"t1\",\"age\":22}}]},\"map1\":{\"name\":\"t1\",\"address\":\"add1\",\"students\":[{\"name\":\"s\",\"age\":1,\"teacher\":{\"name\":\"t1\",\"age\":22}}]}}]\n";
        Type type = new TypeToken<ArrayList<Map<String, RequestPojo>>>() {
        }.getType();
        List list1 = json.fromJson(listMap, type);
        System.out.println(list1);
    }

    @Test
    public void set() {
        RequestPojo pojo1 = new RequestPojo();
        pojo1.setAddress("add1");
        pojo1.setName("t1");
        Student s1 = new Student();
        s1.setAge(1);
        s1.setName("s");
        s1.setTeacher(new Teacher("t1", 22));
        List<Student> lst = new ArrayList<>();
        lst.add(s1);
        pojo1.setStudents(lst);
        Set<RequestPojo> set = new HashSet<>();
        set.add(pojo1);

        String setString = json.toJson(set);
        System.out.println(setString);

        Type type = new TypeToken<HashSet<RequestPojo>>() {
        }.getType();
        Set<RequestPojo> setColl = json.fromJson(setString, type);
        System.out.println(setColl);
    }

    /*
        Gson 会自动忽略空值
        反序列化会忽略未匹配的属性
     */
    @Test
    public void nullValue() {
        Student student = new Student();
        student.setTeacher(null);
        student.setName(null);
        student.setAge(11);

        String str = json.toJson(student);
        System.out.println(str);

        String str2 = "{\"age\":11,\"age2\":22}";
        Student student1 = json.fromJson(str2, Student.class);
        System.out.println(student1);
    }

    @Test
    public void annotation() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation().create();

        GsonPojo2 t = new GsonPojo2("1", "2", "3", 11, "1990", "1991");

        String tStr = gson.toJson(t);
        System.out.println(tStr);

        String str2 = "{\"userName\":\"1\",\"teacherName\":\"2\",\"password\":\"2\",\"name\":\"3\",\"age\":11}\n";
        GsonPojo2 teacher = gson.fromJson(str2, GsonPojo2.class);
        System.out.println(teacher);
    }

    /*
        自定义Gson 以适用特殊情况
     */
    @Test
    public void customGson() {
        //默认方式
        Gson defalut = new Gson();
        //自定义方式
        GsonBuilder gsonBuilder = new GsonBuilder();
        //将修改生成的 JSON 中的字段名，格式将全部变成小写，并且每个单词用“_” 分割
//        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        //将修改生成的 JSON 中的字段名，格式将全部变成小写，并且每个单词用“-” 分割
//        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES);
        //每个单词的第一个字母都要大写，其他不变
//        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE);

        FieldNamingStrategy m = new FieldNamingStrategy() {
            @Override
            public String translateName(Field f) {
                return f.getName().replace("_", "");
            }
        };
        gsonBuilder.setFieldNamingStrategy(m);

        Gson gson = gsonBuilder.create();

        GsonPojo2 t = new GsonPojo2("1", "2", "3", 11, "1990", "1991");

        String s = gson.toJson(t);
        System.out.println(s);
    }

    /**
     * 不忽略null值
     */
    @Test
    public void transferNull() {
        Student student = new Student();
        student.setTeacher(null);
        student.setName(null);
        student.setAge(11);

        Gson gson = new GsonBuilder().serializeNulls().create();
        String s = gson.toJson(student);
        System.out.println(s);
    }

    @Test
    public void tt() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        //对序列化和反序列化都有效果
        gsonBuilder.setExclusionStrategies(new ExclusionStrategy() {
            //忽略了字段中包含_的
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getName().contains("_");
            }

            //忽略了Date和 boolean类型
            @Override
            public boolean shouldSkipClass(Class<?> incomingClass) {
                return incomingClass == Date.class || incomingClass == boolean.class;
            }
        });
        Gson gson = gsonBuilder.create();

        String pojoStr = gson.toJson(pojo);
        System.out.println(pojoStr);

        GsonPojo2 t = new GsonPojo2("1", "2", "3", 11, "1990", "1991");
        String str2 = gson.toJson(t);
        System.out.println(str2);
    }

    @Test
    public void floatAndDouble() {
        //无穷大 java特殊的值,但是在json在规范中没有,会报错
        System.out.println(Float.POSITIVE_INFINITY);
        //正常的数据没有问题 枚举类型也是自动转化的
        FloatAndDouble fd = new FloatAndDouble(1.1f, 2.2, 3.3f, 4.5, "test", EnumJSON.FRIDAY);
        String s = json.toJson(fd);
        System.out.println(s);

        FloatAndDouble floatAndDouble = json.fromJson(s, FloatAndDouble.class);
        System.out.println();

        GsonBuilder gsonBuilder = new GsonBuilder();
        //可以避免 Float和Double的特殊值
        gsonBuilder.serializeSpecialFloatingPointValues();
        Gson gson = gsonBuilder.create();

        FloatAndDouble fd2 = new FloatAndDouble(Float.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 3.3f, 4.5, "tset", EnumJSON.FRIDAY);
        String s1 = gson.toJson(fd2);
        System.out.println(s1);
    }

    /**
     * POJO<T>
     */
    @Test
    public void pojo_T() {
        GsonPojo3<Teacher> pojo = new GsonPojo3<>();
        Teacher teacher = new Teacher();
        teacher.setAge(11);
        teacher.setName("123");
        pojo.setTemp(teacher);

        String s = json.toJson(pojo);
        System.out.println(s);

        Type type = new TypeToken<GsonPojo3<Teacher>>() {
        }.getType();
        GsonPojo3<Teacher> pojo3 = json.fromJson(s, type);
        System.out.println(pojo3);
    }

    /**
     * 自定义序列化
     *
     * @Expose 是能解决一部分问题，但是存在局限性，现在我们使用自定义来解决这些问题，作法不干涉原类，只在干涉序列化过程
     */
    @Test
    public void customSer() {
        String s = json.toJson(list.get(0));
        System.out.println(s);
        //{"name":"t1","address":"add1","students":[{"name":"s","age":1,"teacher":{"name":"t1","age":22}}]}

        GsonBuilder gsonBuilder = new GsonBuilder();
        JsonSerializer<RequestPojo> customSerializer = new JsonSerializer<RequestPojo>() {
            @Override
            public JsonElement serialize(RequestPojo src, Type typeOfSrc, JsonSerializationContext context) {
                JsonObject object = new JsonObject();
                object.add("name", new JsonPrimitive(src.getName()));
                object.add("address", new JsonPrimitive(src.getAddress()));
                List<Student> list = src.getStudents();
                JsonArray array = new JsonArray();
                JsonObject stude = new JsonObject();
                for (Student s : list) {
                    stude.add("name", new JsonPrimitive(s.getName()));
                    stude.add("age", new JsonPrimitive(s.getAge()));
                }
                array.add(stude);
                object.add("studets", array);
                return object;
            }
        };
        gsonBuilder.registerTypeAdapter(RequestPojo.class, customSerializer);
        Gson gson = gsonBuilder.create();
        String s1 = gson.toJson(list.get(0));
        System.out.println(s1);


        String str = "{\"name\":\"t1\",\"address\":\"add1\",\"students\":[{\"name\":\"s\",\"age\":1,\"teacher\":{\"name\":\"t1\",\"age\":22}}]}\n";
        GsonBuilder gsonBuilder2 = new GsonBuilder();
        JsonDeserializer<RequestPojo> de = new JsonDeserializer<RequestPojo>() {
            @Override
            public RequestPojo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                JsonObject object = json.getAsJsonObject();

                RequestPojo pojo = new RequestPojo();
                pojo.setName(object.get("name").getAsString());
                return pojo;
            }
        };
        gsonBuilder2.registerTypeAdapter(RequestPojo.class, de);
        Gson gson1 = gsonBuilder2.create();
        RequestPojo pojo = gson1.fromJson(str, RequestPojo.class);
        System.out.println(pojo);
    }

    @Test
    public void extendTest() {
        JsonF f = new JsonS();
        f.setFName("father name");
        f.setFAge(60);
        f.setType("JsonS");
        ((JsonS) f).setSAge(11);
        ((JsonS) f).setSName("son name");

        JsonF f2 = new JsonS2();
        f2.setFName("father name");
        f2.setFAge(60);
        f2.setType("JsonS2");
        ((JsonS2) f2).setSAge(11);
        ((JsonS2) f2).setSName("son name");
        ((JsonS2) f2).setTest("ttt");
        List<JsonF> ll = new ArrayList<>();
        ll.add(f);
        ll.add(f2);

        String s1 = json.toJson(ll);
        System.out.println(s1);

        TypeToken<List<JsonF>> type = new TypeToken<List<JsonF>>() {
        };
        //这种方式不能把子类的属性给附上值 就是默认转成了父类
        List<JsonF> listyuan = json.fromJson(s1, type.getType());
        System.out.println();

        //很特殊,需要一个表示字段 就是在父类加一个type标识, 每个子类必须能区别出来
        //可以准确的转成对应的子类
        String tet = "[{\"sName\":\"son name\",\"sAge\":11,\"fName\":\"father name\",\"fAge\":60,\"type\":\"JsonS\"},{\"sName\":\"son name\",\"sAge\":11,\"test\":\"ttt\",\"fName\":\"father name\",\"fAge\":60,\"type\":\"JsonS2\"}]";
        RuntimeTypeAdapterFactory<JsonF> typeFactory = RuntimeTypeAdapterFactory.of(JsonF.class, "type").
                registerSubtype(JsonS.class, "JsonS").
                registerSubtype(JsonS2.class, "JsonS2");
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(typeFactory).create();
        List<JsonF> list = gson.fromJson(tet, type.getType());
        System.out.println(list);
    }
}
