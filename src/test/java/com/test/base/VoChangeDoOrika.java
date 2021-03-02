package com.test.base;

import com.test.base.pojo.DestinationB;
import com.test.base.pojo.OrikaDo;
import com.test.base.pojo.OrikaVo;
import com.test.base.pojo.SourceA;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Orika 框架 快速的高效的pojo映射框架 是深复制
 */
public class VoChangeDoOrika {
    @Test
    public void test() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        MapperFacade mapper = mapperFactory.getMapperFacade();

        OrikaVo vo = new OrikaVo();
        vo.setAddress("address");
        vo.setAgeVo(11);
        vo.setNameVo("voname");

        //属性名称相同可以直接复制
        OrikaDo map = mapper.map(vo, OrikaDo.class);
        System.out.println(map);

        //属性名称不同时,通过自定义映射来复制
        MapperFactory mapperFactory2 = new DefaultMapperFactory.Builder().build();
        mapperFactory2.classMap(OrikaVo.class, OrikaDo.class).
                field("nameVo", "nameDo").
                field("ageVo", "ageDo").byDefault().register();
        MapperFacade mapper2 = mapperFactory2.getMapperFacade();
        OrikaDo map1 = mapper2.map(vo, OrikaDo.class);
        System.out.println(map1);
    }

    @Test
    public void test2() {
        OrikaVo vo = new OrikaVo();
        vo.setAddress("address");
        vo.setAgeVo(11);
        vo.setNameVo("voname");

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        Map map = mapper.map(vo, Map.class);
        System.out.println(map);

        OrikaVo map1 = mapper.map(map, OrikaVo.class);
        System.out.println();


        MapperFactory mapperFactory2 = new DefaultMapperFactory.Builder().build();
        mapperFactory2.classMap(Map.class, OrikaDo.class).
                field("nameVo", "nameDo").
                field("ageVo", "ageDo").byDefault().register();
        MapperFacade mapper2 = mapperFactory2.getMapperFacade();
        OrikaDo map3 = mapper2.map(map, OrikaDo.class);
        System.out.println(map3);
    }

    @Test
    public void test3() {
        //orika 两个属性不同转换
        MapperFactory factory = new DefaultMapperFactory.Builder().build();

        ConverterFactory converterFactory = factory.getConverterFactory();
        converterFactory.registerConverter("dateConverter", new MyConverter());


        factory.classMap(SourceA.class, DestinationB.class)
                    .fieldMap("date", "dateb").converter("dateConverter").add()
                .register();
        MapperFacade mapperFacade = factory.getMapperFacade();
        SourceA sourceA = new SourceA();
        DestinationB map = mapperFacade.map(sourceA, DestinationB.class);
        System.out.println(map);
    }

    class MyConverter extends BidirectionalConverter<String, Date> {

        private SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        @Override
        public Date convertTo(String s, Type<Date> type) {
            try {
                return sdf.parse(s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public String convertFrom(Date date, Type<String> type) {
            return null;
        }
    }
}
