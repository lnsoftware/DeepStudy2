package com.demo3;

public class TestService{

    public Response getTestList(String type){
        list = getListbyType(type);

        return response.entity(list);
    }

    private List getListbyType(String type){
          ...
          ..
          TestDAO testdao = new Testdao(sqlconn);
          list = testdao.getListfromDB(type)
          return list;
   }
}