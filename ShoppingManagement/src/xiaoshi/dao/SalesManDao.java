package xiaoshi.dao;

import xiaoshi.db.DbClose;
import xiaoshi.db.DbConn;
import xiaoshi.entity.SalesMan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * operate the salesman table in Database
 */
public  class SalesManDao {
    Connection conn=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;

    /**
     *
     * @param usr
     * @return List<SalesMan>
     */
    public List<SalesMan> checkstandLog(String usr) {
        List<SalesMan> salesManInfo = new ArrayList<>();
        conn= DbConn.getconn();
        String sql="SELECT SID,SPASSWORD FROM SALESMAN WHERE SNAME=?";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,usr);
            rs=pstmt.executeQuery();
            while(rs.next()){
                String sPassWord= rs.getString("spassword");
                int sid=rs.getInt("sid");
                salesManInfo.add(new SalesMan(sid,sPassWord));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            DbClose.queryClose(pstmt,conn,rs);
        }
        return salesManInfo;
    }

    /**
     * add salesman to database
     * @param name
     * @return boolean
     */
    public boolean addSalesMan(SalesMan name){
        boolean bool = false;
        conn = DbConn.getconn();
        String sql = "INSERT INTO SALESMAN(SNAME,SPASSWORD) VALUES(?,?)";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,name.getsName());
            pstmt.setString(2,name.getsPassword());
            int rs=pstmt.executeUpdate();
            if(rs>0)bool=true;
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            DbClose.addClose(pstmt,conn);
        }
        return bool;
    }

    /**
     * update salesman information
     * @param key
     * @param sname
     * @return
     */
    public boolean updateSalesMan(int key,SalesMan sname){
        boolean bool = false;
        conn= DbConn.getconn();
        switch(key){
            case 1:
                //change name
                String sqlName="UPDATE SALESMAN SET SNAME=? WHERE SID=?";
                try{
                    pstmt=conn.prepareStatement(sqlName);
                    pstmt.setString(1,sname.getsName());
                    pstmt.setInt(2,sname.getsId());
                    int rs=pstmt.executeUpdate();
                    if(rs>0)bool=true;
                }catch(SQLException e){
                    e.printStackTrace();
                }finally{DbClose.addClose(pstmt,conn);}
                break;
            case 2:
                //change password
                String sql="UPDATE SALESMAN SET SPASSWORD=? WHERE SID=?";
                try{
                    pstmt=conn.prepareStatement(sql);
                    pstmt.setString(1,sname.getsPassword());
                    pstmt.setInt(2,sname.getsId());
                    int rs=pstmt.executeUpdate();
                    if(rs>0)bool=true;
                }catch (SQLException e){
                    e.printStackTrace();
                }finally{DbClose.addClose(pstmt,conn);}
                break;

        }return bool;

    }

    /**
     * delete salesman from database
     * @param sname
     * @return boolean
     */
    public boolean deleteSaleMan(String sname){
        boolean bool=false;
        conn=DbConn.getconn();
        String sql="DELETE FROM SALESMAN WHERE SNAME=?";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,sname);
            int rs=pstmt.executeUpdate();
            if(rs>0)bool=true;
        }catch (SQLException e){e.printStackTrace();}
        finally{DbClose.addClose(pstmt,conn);}
        return bool;
    }

    /**
     * query salesman by namepattern
     * @param sname
     * @return
     */
    public List<SalesMan> querySalesMan(String sname){
        List<SalesMan> list=new ArrayList<>();
        String pattern="%"+sname+"%";
        String sql="SELECT * FROM SALESMAN WHERE SNAME LIKE ?";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,pattern);
            rs=pstmt.executeQuery();
            while(rs.next()){
                int sid=rs.getInt("sid");
                String name=rs.getString("sname");
                String spassword=rs.getString("spassword");
                list.add(new SalesMan(sid,name,spassword));
            }

        }catch(SQLException e){e.printStackTrace();}
        finally{DbClose.queryClose(pstmt,conn,rs);}
        return list;
    }

    /**
     * query all salesman
     * @return
     */

    public List<SalesMan> displaySalesMan(){
        ArrayList<SalesMan> list=new ArrayList<>();
        String sql="SELECT * FROM SALESMAN";
        conn=DbConn.getconn();
        try{
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                int sid=rs.getInt("sid");
                String sname=rs.getString("sname");
                String spasswd=rs.getString("spassword");
                list.add(new SalesMan(sid,sname,spasswd));
            }
        }catch(SQLException e){e.printStackTrace();}
        finally{DbClose.queryClose(pstmt,conn,rs);}
        return list;
    }
}
