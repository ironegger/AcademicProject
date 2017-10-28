package xiaoshi.entity;

public class Gsales {
    private int gId;
    private int sId;
    private int sNum;

    private String gName;
    private double gPrice;
    private int gNum;

    public int getgId() {
        return gId;
    }

    public void setgId(int gId) {
        this.gId = gId;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public int getsNum() {
        return sNum;
    }

    public void setsNum(int sNum) {
        this.sNum = sNum;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public double getgPrice() {
        return gPrice;
    }

    public void setgPrice(double gPrice) {
        this.gPrice = gPrice;
    }

    public int getgNum() {
        return gNum;
    }

    public void setgNum(int gNum) {
        this.gNum = gNum;
    }

    public int getAllSum() {
        return allSum;
    }

    public void setAllSum(int allSum) {
        this.allSum = allSum;
    }

    private int allSum;

    public Gsales(int gId,int sId,int sNum){
        this.gId = gId;
        this.sId = sId;
        this.sNum = sNum;
    }
    public Gsales(String gName,double gPrice,int gNum,int allSum){
        this.gName = gName;
        this.gPrice=gPrice;
        this.gNum=gNum;
        this.allSum=allSum;
    }
}
