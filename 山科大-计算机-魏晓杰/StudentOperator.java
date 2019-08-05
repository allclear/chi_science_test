package training_chi.day17;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WXJ
 * @version 1.0
 * @date 2019/8/5
 */
public class StudentOperator implements StudentOperatable {
    @Override
    public void insert(Student student) {
        Connection connection = JDBCUtil.getInstance().getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            StringBuilder sql = new StringBuilder();
            sql.append("insert into student(sno,sname,ssex,sage,sdept)");
            sql.append("values(");
            sql.append("'"+student.getSno()+"'"+",");
            sql.append("'"+student.getSname()+"'"+",");
            sql.append("'"+student.getSsex()+"'"+",");
            sql.append(student.getSage()+",");
            sql.append("'"+student.getSdept()+"'");
            sql.append(")");
            System.out.println(sql.toString());
            statement.execute(sql.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(statement);
            JDBCUtil.closeResource(connection);
        }
    }

    @Override
    public int deleteById(String sno) {
        Connection connection = JDBCUtil.getInstance().getConnection();
        Statement statement = null;
        int size = 0;
        try {
            statement = connection.createStatement();
            String sizeSql = new String("select count(*) as stu_size from student where sno = "+"'"+sno+"'");
            ResultSet rs = statement.executeQuery(sizeSql);
            while (rs.next()){
                size = rs.getInt("stu_size");
            }

            StringBuilder sql = new StringBuilder();
            sql.append("delete from student where sno = "+"'"+sno+"'");
            System.out.println(sql.toString());
            statement.execute(sql.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(statement);
            JDBCUtil.closeResource(connection);
        }
        return size;
    }

    @Override
    public int updateById(Student student) {
        Connection connection = JDBCUtil.getInstance().getConnection();
        Statement statement = null;
        int size = 0;
        try {
            statement = connection.createStatement();
            StringBuilder sizeSql = new StringBuilder("select count(*) as stu_size from student where sno = "+"'"+student.getSno()+"'");
            ResultSet rs = statement.executeQuery(sizeSql.toString());
            while (rs.next()){
                size = rs.getInt("stu_size");
            }

            StringBuilder sql = new StringBuilder();
            sql.append("update student set sno = "+"'"+student.getSno()+"'");
            if (null != student.getSname()){
                sql.append(",sname = "+"'"+student.getSname()+"'");
            }
            if (null != student.getSage()){
                sql.append(",sage = "+student.getSage());
            }
            if (null != student.getSsex()){
                sql.append(",ssex = "+"'"+student.getSsex()+"'");
            }
            if (null != student.getSdept()){
                sql.append(",sdept = "+"'"+student.getSdept()+"'");
            }
            sql.append( " where sno = "+"'"+student.getSno()+"'");
            System.out.println(sql.toString());
            statement.execute(sql.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(statement);
            JDBCUtil.closeResource(connection);
        }
        return size;
    }

    @Override
    public List<Student> select(Student student) {
        Connection connection = JDBCUtil.getInstance().getConnection();
        Statement statement = null;
        List<Student> result = new ArrayList<>();
        try {
            statement = connection.createStatement();
            StringBuilder sql = new StringBuilder("select * from student where 1=1 ");
            if (null != student.getSno()){
                sql.append(" and sno = "+"'"+student.getSno()+"'");
            }
            if (null != student.getSname()){
                sql.append(" and sname = "+"'"+student.getSname()+"'");
            }
            if (null != student.getSage()){
                sql.append(" and sage = "+student.getSage());
            }
            if (null != student.getSsex()){
                sql.append(" and ssex = "+"'"+student.getSsex()+"'");
            }
            if (null != student.getSdept()){
                sql.append(" and sdept = "+"'"+student.getSdept()+"'");
            }
            ResultSet rs = statement.executeQuery(sql.toString());
            while (rs.next()) {
                Student s = new Student();
                s.setSno(rs.getString("sno"));
                s.setSsex(rs.getString("ssex"));
                s.setSage(rs.getInt("sage"));
                s.setSname(rs.getString("sname"));
                s.setSdept(rs.getString("sdept"));
                result.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
