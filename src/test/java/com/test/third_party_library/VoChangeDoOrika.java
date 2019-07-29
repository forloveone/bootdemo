package com.test.third_party_library;

import com.test.pojo.OrikaDo;
import com.test.pojo.OrikaVo;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Test;

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
        mapperFactory2.classMap(OrikaVo.class,OrikaDo.class).
                field("nameVo","nameDo").
                field("ageVo","ageDo").byDefault().register();
        MapperFacade mapper2 = mapperFactory2.getMapperFacade();
        OrikaDo map1 = mapper2.map(vo, OrikaDo.class);
        System.out.println(map1);
    }
}
