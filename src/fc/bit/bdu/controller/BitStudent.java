package fc.bit.bdu.controller;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BitStudent {

    public static Connection getCn() {
        return cn;
    }

    public static void setCn(Connection aCn) {
        cn = aCn;
    }

    private String id;
    private String firstName;
    private String lastName;
    private String sex;
    private double gpa;
    private String department;

    public BitStudent(String id, String fname, String lname, String sex, double gpa, String department) {
        this.id = id;
        this.firstName = fname;
        this.lastName = lname;
        this.sex = sex;
        this.department = department;
        this.gpa = gpa;
    }

    public BitStudent() {
    }//default constructor without parameter

    public BitStudent(String id) {
        this.id = id;
    }

    private static Connection cn;

    public static Connection connection() {
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
            setCn(DriverManager.getConnection("jdbc:mysql://localhost:3306/adavajava", "SQL", "1221"));
            //Statement st = cn.createStatement();
            /*creating new table to the database st.executeUpdate("create table employee(firstname varchar(20),age int,salary double )");*/
            System.out.println("Connected");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return getCn();
    }

    public void register() throws SQLException {
        PreparedStatement pst;                  //`Id`, `FirstName`, `LastName`, `sex`, `Gpa`, `Departmant` 
        Statement st;
        setCn((com.mysql.jdbc.Connection) connection());
        String sql = " insert into bitstudenttable(Id,FirstName,LastName,sex,Gpa,Departmant) Values(?,?,?,?,?,?)";
        pst = getCn().prepareStatement(sql);
        pst.setString(1, getId());
        pst.setString(2, getFirstName());
        pst.setString(3, getLastName());
        pst.setString(4, getSex());
        pst.setDouble(5, getGpa());
        pst.setString(6, getDepartment());
        pst.execute();
        JOptionPane.showMessageDialog(null, "You Are Successfully Saved The Data");
    }

    public void update(String idup) throws SQLException {
        String sql = "update bitstudenttable set Id='" + getId() + "',FirstName='" + getFirstName() + "',LastName='" + getLastName() + "',sex='" + getSex() + "',Gpa='" + getGpa() + "',Departmant='" + getId() + "' where Id='" + idup + "'";

        setCn((com.mysql.jdbc.Connection) connection());
        PreparedStatement pst = cn.prepareStatement(sql);
        // PreparedStatement pst = getCn().prepareStatement(("select * from bitstudenttsble ORDER BY LastName Asc"));
        char[] idu = idup.toCharArray();
        pst.executeUpdate(sql);
        String h;
        //update id,firstname,lastname,sex,gpa,department where id='';

    }

    public void delete(String idnum) throws SQLException {
        setCn((com.mysql.jdbc.Connection) connection());
        PreparedStatement pst = null;
        Statement st = cn.createStatement();
        String h = "delete from bitstudenttable";
        st.execute(h);
//        pst = getCn().prepareStatement(h);
//        pst.execute(h);

    }

    public DefaultTableModel search(String idSearch) throws SQLException {
        setCn(connection());
        //   String se = "Select Id,FirstName,LastName,sex,Gpa,Departmant  from bitstudenttable ";
        String se = "Select *  from bitstudenttable where FirstName like '%" + idSearch + "%' ";

        String[] col = {"Id", "First Name", "Last Name", "Sex", "Gpa", "Department"};
        ResultSet rs = null;
        Statement st = getCn().createStatement();
        DefaultTableModel model = new DefaultTableModel();

        try {
            rs = st.executeQuery(se);

            model.setColumnIdentifiers(col);
            while (rs.next()) {
                this.id = rs.getString(1);
                this.firstName = rs.getString(2);
                this.lastName = rs.getString(3);
                this.sex = rs.getString(4);
                this.gpa = rs.getDouble(5);
                this.department = rs.getString(6);
                String[] result = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), "" + rs.getDouble(5), rs.getString(6)};
                model.addRow(result);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return model;
    }
// public String search(String idSearch) throws SQLException {
//        setCn(connection());
//        String se = "Select Id,FirstName,LastName,sex,Gpa,Departmant from bitstudenttable where Id='" + idSearch + "'";
//        ResultSet rs = null;
//        ArrayList array=new ArrayList();
//        Statement st = getCn().createStatement();
//        try {
//            rs = st.executeQuery(se);
//            while (rs.next()) {
//                
//                
//                setId(rs.getString(1));
//                setFirstName(rs.getString(2));
//                setLastName(rs.getString(3));
//                setSex(rs.getString(4));
//                setGpa(rs.getDouble(5));
//                setDepartment(rs.getString(6));
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//
//        }
//        return getFirstName() + " " + getLastName();
//
//    }

    public static void main(String... a) {
//        BitStudent bit = new BitStudent();
//        connection();
//        Statement st = null;
        //searching
//        try {
//            st = cn.createStatement();
//     
        //} catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }

//        try {
//            ResultSet rs = st.executeQuery("select Id,FirstName,LastName,sex,Gpa,Departmant from bitstudenttable where id='bdu0801245ur'");
//            while (rs.next()) {
//                System.out.println("the name is " + rs.getDouble(5));
//
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//this is how to use database metadata
//        try {
//          //  ResultSetMetaData rsmd=cn.getCatalog();
//            DatabaseMetaData dbmd = cn.getMetaData();
//            System.out.println(dbmd.getMaxColumnsInIndex());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
        connection();
        setCn((com.mysql.jdbc.Connection) connection());
        try {
            PreparedStatement pst = getCn().prepareStatement(("select * from bitstudenttsble Order By LastName Asc"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the gpa
     */
    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }
}
