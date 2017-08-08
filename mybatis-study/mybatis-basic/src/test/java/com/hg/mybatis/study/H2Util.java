package com.hg.mybatis.study;

import org.h2.tools.RunScript;
import org.h2.tools.Server;

import java.sql.SQLException;

/**
 * Created by wangqinghui on 2017/11/6.
 */
public class H2Util {

    public static void main(String[] args) throws SQLException {

        Server server = Server.createTcpServer(args).start();
        server.start();

        new RunScript().runTool("-url","jdbc:h2:mem:test", "-user","root", "-password","root", "-script",H2Util.class.getResource("/").getPath() + "/db.sql");

        System.out.println("finish");
    }
}
