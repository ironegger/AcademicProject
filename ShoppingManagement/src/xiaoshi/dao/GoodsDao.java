package xiaoshi.dao;
import xiaoshi.db.DbClose;
import xiaoshi.db.DbConn;
import xiaoshi.entity.Goods;
import xiaoshi.tools.ScannerChoice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * database goods table operation
 *
 * */
public final class GoodsDao {
    Connection        conn  = null;
    PreparedStatement pstmt = null;
    ResultSet         rs    = null;

    /**
     * 1.Insert good into goods table
     * @param goods object
     * @return boolean
     */
    public boolean addGoods(Goods goods){
        boolean bool = false;
        conn = DbConn.getconn();
        String sql="INSERT INTO java.goods(GNAME,GPRICE,GNUM,PROD_NAME) VALUES(?,?,?,?)";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,goods.getGname());
            pstmt.setDouble(2,goods.getGprice());
            pstmt.setInt(3,goods.getGnum());
            pstmt.setString(4,goods.getProd_name());
            int rs=pstmt.executeUpdate();
            if(rs>0)bool=true;
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            DbClose.addClose(pstmt,conn);
        }
        return bool;
    }
    public boolean updateGoods(int key,Goods goods){
        boolean bool = false;
        conn = DbConn.getconn();
        switch(key){
            case 1://change name
                try{
                    String sql="UPDATE JAVA.GOODS SET GNAME=? WHERE GID=?";
                    pstmt=conn.prepareStatement(sql);
                    pstmt.setString(1,goods.getGname());
                    pstmt.setInt(2,goods.getGid());
                    int rs = pstmt.executeUpdate();
                    if(rs>0)bool=true;


                }catch (SQLException e){}
                finally{
                    DbClose.addClose(pstmt,conn);
                }
                break;
            case 2://change price
                String sqlPrice="UPDATE JAVA.GOODS SET GPRICE =? WHERE GID =?";
                try{
                    pstmt=conn.prepareStatement(sqlPrice);
                    pstmt.setDouble(1,goods.getGprice());
                    pstmt.setInt(2,goods.getGid());
                    int rs=pstmt.executeUpdate();
                    if(rs>0)bool=true;

                }catch (SQLException e){
                    e.printStackTrace();
                }finally{
                    DbClose.addClose(pstmt,conn);
                }
                break;
            case 3://change num
                String sqlNum="UPDATE JAVA.GOODS SET GNUM=? WHERE GID=?";
                try{
                    pstmt=conn.prepareStatement(sqlNum);
                }catch (SQLException e){
                    e.printStackTrace();
                }finally{
                    DbClose.addClose(pstmt,conn);
                }
            default:
                    break;
        }
        return bool;
    }
    public boolean deleteGoods(int gid){
        boolean bool=false;
        conn = DbConn.getconn();
        String sql="DELETE FROM JAVA.GOODS WHERE GID = ?";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,gid);
            int rs=pstmt.executeUpdate();
            bool= rs>0?true:false;

        }catch(SQLException E){
            E.printStackTrace();
        }finally{
            DbClose.addClose(pstmt,conn);
        }
        return bool;
    }
    public List<Goods> queryGoods(int key){
        List<Goods> list =new ArrayList<>();
        conn = DbConn.getconn();
        switch(key){
            case 1: //item num ascend
                String sql="SELECT * FROM JAVA.GOODS ORDER BY GNUM";
                try{
                    pstmt=conn.prepareStatement(sql);
                    rs=pstmt.executeQuery();
                    while(rs.next()){
                        int gid=rs.getInt("gid");
                        String gname=rs.getString("gname");
                        double gprice=rs.getDouble("gprice");
                        int gnum=rs.getInt("gnum");
                        String pname=rs.getString("prod_name");
                        list.add(new Goods(gid,gprice,gnum,gname,pname));
                    }
                }catch(SQLException e){
                    e.printStackTrace();
                }finally{
                    DbClose.queryClose(pstmt,conn,rs);
                }
                break;
            case 2://items by ascending price
                String sqlPrice="SELECT * FROM JAVA.GOODS ORDER BY GPRICE";
                try{
                    pstmt=conn.prepareStatement(sqlPrice);
                    rs=pstmt.executeQuery();
                    while(rs.next()){
                        int gid=rs.getInt("gid");
                        String gname=rs.getString("gname");
                        double gprice=rs.getDouble("gprice");
                        int gnum=rs.getInt("gnum");
                        String pname=rs.getString("prod_name");
                        list.add(new Goods(gid,gprice,gnum,gname,pname));
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }finally{
                    DbClose.queryClose(pstmt,conn,rs);
                }
                break;
            case 3:
                String nameGet= ScannerChoice.ScannerInfoString();
                String sqlGname="SELECT * FROM JAVA.GOODS LIKE '%||?||%'";
                try{
                    pstmt=conn.prepareStatement(sqlGname);
                    pstmt.setString(1,nameGet);
                    rs=pstmt.executeQuery();
                    while(rs.next()){
                        int gid=rs.getInt("gid");
                        String gname=rs.getString("gname");
                        double gprice=rs.getDouble("gprice");
                        int gnum=rs.getInt("gnum");
                        String pname=rs.getString("prod_name");
                        list.add(new Goods(gid,gprice,gnum,gname,pname));
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }finally{
                    DbClose.queryClose(pstmt,conn,rs);
                }
                break;
            default:
                break;
        }
        return list;
    }
    public List<Goods> displayGoods(){
        List<Goods> list=new ArrayList<>();
        conn=DbConn.getconn();
        String sql="SELECT * FROM JAVA.GOODS";

        try{
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                int gid=rs.getInt("gid");
                String gname=rs.getString("gname");
                double gprice=rs.getDouble("gprice");
                int gnum=rs.getInt("gnum");
                String pname=rs.getString("prod_name");
                list.add(new Goods(gid,gprice,gnum,gname,pname));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            DbClose.queryClose(pstmt,conn,rs);
        }
    return list;
    }


}
