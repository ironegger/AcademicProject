package xiaoshi.tools;

import xiaoshi.dao.GoodsDao;
import xiaoshi.db.DbClose;
import xiaoshi.db.DbConn;
import xiaoshi.entity.Goods;
import xiaoshi.entity.SalesMan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryPrint {
    Connection conn=null;
    PreparedStatement pstmt=null;
    ResultSet rs = null;

    /**
     * fuzzy query
     * @param oper
     * @return -1 if anomaly
     */
    public static int query(String oper){
        int gid=-1;
        String  shopping =ScannerChoice.ScannerInfoString();//item name;
        List<Goods> goodsList=new QueryPrint().queryGoodsKey(-1,shopping);
        if(goodsList==null||goodsList.size()==0){
            System.err.println("\tItems not exist\n");
            ScannerChoice.changedInfoNext(oper);
        }
        else{
            Goods goods = goodsList.get(0);
            System.out.println("\t\t\t\tItem List\n\n");
            System.out.println("\tID\t\tNAME\t\tPRICE\t\tQUANTITY\t\tNOTE\n");
            System.out.print("\t"+goods.getGid()+"\t\t"+goods.getGname()+"\t\t"+goods.getGprice()+"\t\t"+goods.getGnum());
            if(goods.getGnum()==0)System.out.println("\t\tSOLD OUT\n");
            gid=goods.getGid();
        }
        return gid;
    }
    public static int querySettlement(){
        int gid=-1;
        List<Goods> goodsSettlement = new GoodsDao().queryGoods(3);
        if(goodsSettlement==null||goodsSettlement.size()==0){
            System.err.println("\tItems not exist\n");
            gid=-3;
        }
        else{
            System.out.println("\t\t\t\tItem List\n\n");
            System.out.println("\tID\t\tNAME\t\tPRICE\t\tQUANTITY\t\tNOTE\n");
            for(Goods good:goodsSettlement){
                if(goodsSettlement.size()==1)gid=good.getGid();
                else gid=-2;
                if(good.getGnum()==0)System.out.println("\t\tSOLD OUT\n");
                if(good.getGnum()>0)
                    System.out.print("\t"+good.getGid()+"\t\t"+good.getGname()+"\t\t"+good.getGprice()+"\t\t"+good.getGnum());
            }

        }
        return gid;
    }

    /**
     * query items according to gid or gname
     * @param gid
     * @param gName
     * @return
     */
    private List<Goods> queryGoodsKey(int gid, String gName) {
        List<Goods> list=new ArrayList<>();
        conn= DbConn.getconn();
        String sql = "SELECT * FROM GOODS WHERE GID=? OR GNAME=?";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,gid);
            pstmt.setString(2,gName);
            rs=pstmt.executeQuery();
            while(rs.next()){
                int gId=rs.getInt("gid");
                String gname=rs.getString("gname");
                double gprice=rs.getDouble("gprice");
                int gnum=rs.getInt("gnum");
                String prod_name=rs.getString("prod_name");
                list.add(new Goods(gId,gprice,gnum,gname,prod_name));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            DbClose.queryClose(pstmt,conn,rs);
        }
        return list;
    }

    public List<SalesMan> querySalesMan(String sName){
        List<SalesMan> list=new ArrayList<>();
        conn=DbConn.getconn();
        String sql="SELECT * FROM SALESMAN WHERE SNAME = ?";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,sName);
            rs=pstmt.executeQuery();
            while(rs.next()){
                int sid= rs.getInt("sid");
                String sname=rs.getString("sname");
                String spassword=rs.getString("spassword");
                list.add(new SalesMan(sid,sname,spassword));
            }

        }catch(SQLException e){e.printStackTrace();}
        finally{DbClose.queryClose(pstmt,conn,rs);}
        return list;
    }
}
