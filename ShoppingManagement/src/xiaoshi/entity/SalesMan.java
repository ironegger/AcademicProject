package xiaoshi.entity;

public class SalesMan {
    private String sPassword;
    private int sId;
    private String sName;


    public SalesMan(int sId,String passWord){
        this.sId=sId;
        this.sPassword=passWord;
    }
    public SalesMan(int sId,String sName,String sPassword){
        this.sId=sId;
        this.sName=sName;
        this.sPassword=sPassword;
    }
    public SalesMan(String n,String p){
        this.sName=n;
        this.sPassword=p;
    }
    public String getsPassword() {
        return sPassword;
    }

    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }



}
