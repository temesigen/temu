package studentregistrationapp;

import java.sql.*;

public class StudentRegistrationApp {

    static Connection cn;

    public static void main(String[] args) throws ClassNotFoundException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advancedprogrammingdb", "ro", "123");
            System.out.println("Connected");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    public void register(String id, String fname, String lname, String sex, double gpa, String department) throws SQLException {
        PreparedStatement pst;
        String sql = " insert into employee(Id,FirstName,LastName,sex,Gpa,Department) Values(?,?,?,?,?,?)";
        pst = cn.prepareStatement(sql);
        pst.setString(1, id);
        pst.setString(2, fname);
        pst.setString(3, lname);
        pst.setString(4, sex);
        pst.setDouble(5, gpa);
        pst.setString(6, department);
        pst.execute();
    }

    
}
