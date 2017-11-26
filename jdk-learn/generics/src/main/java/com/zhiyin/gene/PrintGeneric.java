package com.zhiyin.gene;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Map;
//http://blog.csdn.net/gengv/article/details/5178055
public class PrintGeneric{
    public static void main(String[] args) {
//        用来返回表示当前Class 所表示的实体（类、接口、基本类型或 void）的直接超类的Type。
        System.out.println(((ParameterizedType)new GT1().getClass().getGenericSuperclass()));

        System.out.println(((ParameterizedType)new GT1().getClass().getGenericSuperclass()).getActualTypeArguments()[0]);

        StuDaoSupport stuDaoSupport = new StuDaoSupport();
        System.out.println(stuDaoSupport.get(1L) );

        TeaDaoSupport teaDaoSupport = new TeaDaoSupport();
        System.out.println(teaDaoSupport.get(1L) );
    }
}

class StuDaoSupport extends BaseDao<Stu>{

}

class TeaDaoSupport extends BaseDao<Tea>{

}

abstract class BaseDao<T>{
    private Class<T> entityClass;
    public BaseDao() {
        entityClass =(Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }
    public T get(Long id) {
        T o = (T) get(entityClass, id);
        return o;
    }

    static Map<String,Object> map = Maps.newHashMap();
    static {
        map.put("Stu1",new Stu(1L,"hg"));
        map.put("Tea1",new Tea(1L,"pang"));
    }

    public Object get(Class clazz,Long id){
        String cN = clazz.getSimpleName();
        return map.get(cN+id);
    }
}

@Data
@AllArgsConstructor
class Stu implements Serializable {
    private Long id;
    private String name;
}
@Data
@AllArgsConstructor
class Tea implements Serializable {
    private Long id;
    private String name;
}

class GT1 extends GT2<Integer>{
}

class GT2<T> {

}

