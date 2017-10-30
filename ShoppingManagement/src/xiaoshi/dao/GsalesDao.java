package xiaoshi.dao;

import xiaoshi.db.DbClose;
import xiaoshi.db.DbConn;
import xiaoshi.entity.Gsales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GsalesDao {
    private Connection conn=null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    /**
     * daily sales
     */
    public List<Gsales> dailyGsales(){
        List<Gsales> GsalesList=new ArrayList<>();
        conn = DbConn.getconn();
        String sql="select gname,gprice,gnum,allSum from goods,(select gid as salesid,sum(snum) as allSum from gsales where trunc(sdate) = trunc(sysdate) group by gid) where gid=salesid";
        try{
            pstmt=conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                String gname=rs.getString("gname");
                double gprice=rs.getDouble("gprice");
                int gnum=rs.getInt("gnum");
                int allSum=rs.getInt("allSum");
                GsalesList.add(new Gsales(gname,gprice,gnum,allSum));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            DbClose.queryClose(pstmt,conn,rs);
        }
        return GsalesList;
    }

    /**
     * insert items detail to sales table
     * @param gSales
     * @return boolean
     */
    public boolean shoppingSettlement(Gsales gSales){
        boolean bool = false;
        conn = DbConn.getconn();
        String sql="INSERT INTO GSALES(GID,SID,SNUM) VALUES(?,?,?)";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1, gSales.getgId());
            pstmt.setInt(2, gSales.getsId());
            pstmt.setInt(3, gSales.getsNum());
            int rs=pstmt.executeUpdate();
            if(rs>0)bool=true;
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            DbClose.addClose(pstmt,conn);
        }
        return bool;
    }
}
