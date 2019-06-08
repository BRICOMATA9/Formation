//export CLASSPATH=$CLASSPATH:/home/aghiles/Programme/java/java_SQL/Pilote/mysql.jar
//mysql -u root -h localhost -p
//mysql -u root -p
//grant all privileges on gentoo.* to aghiles@localhost identified by "aghilesDJ35B";
//flush privileges;
//INSERT INTO developers (job, name) VALUES('outsourced', 'Jane Doe');
//INSERT INTO developers VALUES('Joe Smith', 'joesmith@gentoo.org', 'toolchain');

//export CLASSPATH=$CLASSPATH:/usr/share/java/mysql-connector-java.jar
//export ORACLE_HOME=/usr/lib/oracle/11.2/client
//export LD_LIBRARY_PATH=$ORACLE_HOME/lib
//export PATH=$PATH:$ORACLE_HOME/bin
//export ORACLE_BASE=/usr/lib/oracle
//export ORACLE_OWNR=aghiles
//xhost +SI:localuser:oracle

import java.sql.*;
import java.util.Properties;

public class test1 {

  private static final String dbClassName = "com.mysql.jdbc.Driver";
  private static final String CONNECTION = "jdbc:mysql://localhost";

  public static void main(String[] args) throws ClassNotFoundException,SQLException {

    System.out.println(dbClassName);
    Class.forName(dbClassName);

    Properties p = new Properties();
    p.put("user","aghiles");
    p.put("password","aghilesDJ35B");

    Connection c = DriverManager.getConnection(CONNECTION,p);

    System.out.println("It works !");
    c.close();
    }
}
