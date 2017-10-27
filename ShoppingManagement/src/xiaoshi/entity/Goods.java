package xiaoshi.entity;

/**
 * Goods
 */
public class Goods {



    //prime key of Goods Table
    private int gid;
    private String gname;
    private double gprice;
    private int gnum;
    private String prod_name;

    /**
     * add specific information
     */
    public Goods(String gname,double price,int gnum,String prod_name){
        this.gname         =gname;
        this.prod_name     =prod_name;
        this.gprice        =price;
        this.gnum          =gnum;
    }
    public Goods(int gid,double price,int num,String name,String p_name){
        this.gname=name;
        this.gid=gid;
        this.gnum=num;
        this.prod_name=p_name;
        this.gprice=price;
    }
    public Goods(int gid,int num){
        this.gid=gid;
        this.gnum=num;
    }
    public Goods(int gid,double price){
        this.gid=gid;
        this.gprice=price;
    }
    public Goods(int gid,String name){
        this.gid=gid;
        this.gname=name;
    }
    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public double getGprice() {
        return gprice;
    }

    public void setGprice(double gprice) {
        this.gprice = gprice;
    }

    public int getGnum() {
        return gnum;
    }

    public void setGnum(int gnum) {
        this.gnum = gnum;
    }

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }
}
