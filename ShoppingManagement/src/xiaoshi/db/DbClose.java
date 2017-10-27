package xiaoshi.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Close the resource stream generated during database operation
 */
public final class DbClose {

    /**
     * close  添加功能 资源
     * @param pstmt,conn
     */
    public static void addClose(PreparedStatement pstmt,Connection conn){

        try{
            if (pstmt != null) pstmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        try{
            if(conn != null) conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * close query
     * @param pstmt,rs,conn
     */
    public static void queryClose(PreparedStatement pstmt,Connection conn,ResultSet rs){
        try{
            if (pstmt != null) pstmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        try{
            if (rs != null) rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        try{
            if (conn != null) conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
