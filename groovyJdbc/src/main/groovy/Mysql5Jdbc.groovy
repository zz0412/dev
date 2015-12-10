

import groovy.sql.Sql

public class Mysql5Jdbc {

    public int executeUpdate(String sql, String url, String user, String pwd) {

        try {
            def sqlInstance = Sql.newInstance(url, user, pwd, "com.mysql.jdbc.Driver")

            return sqlInstance.executeUpdate(sql)
        } catch (Exception e) {
            e.printStackTrace()
            return 0;
        }
    }
}
