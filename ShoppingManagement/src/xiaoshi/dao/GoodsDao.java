package xiaoshi.dao;
import xiaoshi.db.DbClose;
import xiaoshi.db.DbConn;
import xiaoshi.entity.Goods;

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
            case 1:
                try{
                    String sql="UPDATE GOODS SET GNAME=? WHERE GID=?";
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
                default:
                    break;
        }
        return bool;
    }
    public List<Goods> queryGoods(int key){
        conn = DbConn.getconn();
        String sql="SELECT * FROM java.goods";
        List<Goods> ls=new ArrayList<>();
        try{
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                //int gid=rs.getInt("gid");
                String name = rs.getString("gname");
                String prod = rs.getString("prod_name");
                double price=rs.getDouble("gprice");
                int q=rs.getInt("gnum");
                Goods good=new Goods(name,price,q,prod);
                ls.add(good);
            }
        }catch(SQLException e){e.printStackTrace();}
        finally{DbClose.queryClose(pstmt,conn,rs);}
        return ls;
    }
    public static void main(String[] args){
        GoodsDao gd =new GoodsDao();
        List<Goods> list = gd.queryGoods(1);
        for(Goods g : list)System.out.println(g.getGname() + " "+ g.getGprice()+ " "+ g.getProd_name());

    }

}
