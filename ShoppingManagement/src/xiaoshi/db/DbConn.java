package xiaoshi.db;


import java.sql.*;

/**
 * connect to mysql database
 */
public final class DbConn {


    public static Connection getconn() {
        Connection conn=null;
         String user = "root";
         String passwd = "xu890822";
         String url = "jdbc:mysql://localhost:3306?useSSL=false";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,passwd);
        }catch (SQLException e){
            System.out.println("Connection to database failed");
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            System.out.println("Driver Class Not Found");
        }
        return conn;
    }
    public static void main(String[] args){
        Connection c=getconn();
        ResultSet rs=null;
        PreparedStatement pst = null;
        if(c==null)System.out.println("failed");
        String s1="use exercise";
        String sql="SELECT * FROM exercise.products ORDER BY prod_price DESC;";
        try {
            pst = c.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String name=rs.getString("prod_name");
                int price=rs.getInt("prod_price");
                System.out.println(name+" : "+price);
            }
        }catch(SQLException e){
            System.out.println("error");
        }finally{
            try{
                c.close();
                rs.close();
                pst.clearParameters();
            }catch (SQLException e){

            }
        }

    }
}
