import com.alibaba.fastjson.JSON;
import com.dianping.cat.Cat;
import com.google.common.collect.Maps;

import java.util.Map;

public class UmeCatContext implements Cat.Context {
    public static final String HttpCatIds = "cat.ids";

    Map<String,String> map = Maps.newHashMap();

    @Override
    public void addProperty(String s, String s1) {
        map.put(s,s1);
    }

    @Override
    public String getProperty(String s) {
        return map.get(s);
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public static void main(String[] args){

        UmeCatContext context = new UmeCatContext();
        Cat.logRemoteCallClient(context);
        System.out.println(JSON.toJSONString(context.getMap()) );

    }
}
