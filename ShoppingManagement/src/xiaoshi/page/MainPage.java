package xiaoshi.page;

import xiaoshi.dao.SalesManDao;
import xiaoshi.entity.Goods;
import xiaoshi.entity.Gsales;
import xiaoshi.entity.SalesMan;
import xiaoshi.tools.QueryPrint;
import xiaoshi.tools.ScannerChoice;

import java.util.ArrayList;
import java.util.List;

/**
 * Main Page of the program
 */
public final class MainPage extends ScannerChoice {
    public static void main(String[] args){
        MainPage.mainPage();
    }
    public final static String deli = "#####################################";
    public static void mainPage(){
        System.out.println(deli);
        System.out.println("#####Shopping Management System######\n");
        System.out.println("  Enter Number To Choose,0 to Quit\n");

        System.out.println("\t 1. Item maintenance\n");
        System.out.println("\t 2. Register\n");
        System.out.println("\t 3. Item management\n");
        System.out.println(deli);
        System.out.println(deli);
        do{
            String choice = ScannerInfoString();
            String regex="^[0-3]$";
            if(choice.matches(regex)){
                int c=Integer.parseInt(choice);
                switch(c){
                    case 0:
                        System.out.println("Bye!");
                        System.exit(0);
                        break;
                    case 1:
                        MaintenancePage();
                        break;
                    case 2:
                        checkstandLogPage();
                        break;
                    case 3:
                        commodityManagementPage();
                        break;
                    default:
                        break;
                }
            }System.out.println("INVALID INPUT");
            System.out.println("  Enter Number To Choose,0 to Quit\n");

        }while(true);
    }
    public static void MaintenancePage(){
        System.out.println(deli);
        System.out.println("\t 1. Add Item\n");
        System.out.println("\t 2. Modify Item\n");
        System.out.println("\t 3. Delete Item\n");
        System.out.println("\t 4. Query Item\n");
        System.out.println("\t 5.Show All Items\n");

        System.out.println("Enter 0 to Go To Main Page\n");
        System.out.println(deli);
        String regex=("^[0-5]$");
        do{
        String c=ScannerInfoString();
        if(c.matches(regex)) {
            int i = Integer.parseInt(c);
            switch (i) {
                case 0:
                    mainPage();
                    break;
                case 1:
                    GoodsPage.addGoodsPage();
                    break;
                case 2:
                    GoodsPage.upateGoodsPage();
                    break;
                case 3:
                    GoodsPage.delefeGoodsPage();
                    break;
                case 4:
                    GoodsPage.queryGoodsPage();
                    break;
                case 5:
                    GoodsPage.displayGoodsPage();
                    break;
                default:
                    break;
            }
        }
            System.err.println("INVALID INPUT");
            System.out.println("Enter 0 to Go To Main Page\n");
        }while(true);
        }
        public static void checkstandLogPage(){
        System.out.println(deli);
        System.out.println();
        System.out.println("\t 1. Log in System");
        System.out.println("\t 2. Quit\n");
        System.out.println("Enter 0 to Go To Main Page\n");
        String regex="^[0-2]$";
        do{
            String c=ScannerInfoString();
            if(c.matches(regex)){
                int i=Integer.parseInt(c);
                switch(i){
                    case 0:
                        mainPage();
                        break;
                    case 1:
                        int logInChange = 3;
                        while(logInChange--!=0){

                            System.out.println("UserName: ");
                            String usr=ScannerInfoString();
                            System.out.println("PassWord: ");
                            String pswd=ScannerInfoString();

                            List<SalesMan> list=new SalesManDao().checkstandLog(usr);
                            if(list==null||list.size()==0){
                                System.err.println("\t INVALID USERNAME\n");
                                System.out.println("\t Remaining chance: "+logInChange);
                            }
                            else{
                                SalesMan salesMan = list.get(0);
                                if(pswd.equals(salesMan.getsPassword())){
                                    System.out.println("\t login succeed");
                                    shoppingSettlementPage(salesMan.getSId());
                                    break;
                                }else{
                                    System.err.println("\t INVALID PASSWORD\n");
                                    System.out.println("\t Remaining chance: "+logInChange);
                                }
                            }
                        }
                        System.err.println("0 chance left, forced QUIT");
                        System.exit(1);
                        break;
                    case 2:
                        System.out.println("Bye!\n");
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            }else{
                System.out.println("INVALID INPUT");
                System.out.println("Enter 0 to Go To Main Page\n");
            }
        }while(true);
        }
        public static void commodityManagementPage(){
            System.out.println(deli);
            System.out.println("\t 1. SalesMan management\n");
            System.out.println("\t 2. Daily Sales Log\n");
            System.out.println(deli);
            String regex=("^[0-2]$");
            do{
                System.out.println("Enter 0 to Go To Main Page\n");
                String choice=ScannerInfoString();
                if(choice.matches(regex)){
                    int i=Integer.parseInt(choice);
                    switch(i){
                        case 0:
                            mainPage();
                            break;
                        case 1:
                            salesManManagementPage();
                            break;
                        case 2:
                            GsalesPage.dailySaleGoodsPage();
                            break;
                        default:
                            break;
                    }
                }else{
                    System.err.println("INVALID INPUT");
                }

            }while(true);

        }
    public static void shoppingSettlementPage(int salsManId){
            System.out.println("\n\t*********CLEARANCE*********\n");
            do{
                System.out.println("Press S start clearance,0 return to login page");
                String input=ScannerInfoString();
                if(input.equals("0"))checkstandLogPage();
                else if(input.equals("S")||input.equals("s")){
                    System.out.println("\nEnter Key Word of Item");
                    int gid= QueryPrint.querySettlement();
                    switch(gid){
                        case -3://no such item
                            break;
                        case -1:
                            System.err.println("SORRY, THIS ITEM SOLD OUT");
                            break;
                        default:
                            System.out.println("Select item by ID");
                            int id=ScannerNum();
                            ArrayList<Goods> goodsList = new QueryPrint().queryGoodsKey(id,null);
                            if(goodsList==null||goodsList.size()==0)System.out.println("iTEM NOT FOUND");
                            else{
                                Goods goods = goodsList.get(0);
                                int gnum=goods.getGnum();
                                double price = goods.getGprice();
                                System.out.println("Enter Quantity:");
                                do{
                                    int q=ScannerNum();
                                    if(q>gnum)System.out.println("STOKE INSUFFICIENT");
                                    else{
                                        double total=Arith.mul(gnum,price);
                                        System.out.println("\t\t\tShoppint Cart:\n");
                                        System.out.println("\t\tName\tPrice\tQuantity\tTotal");
                                        System.out.println("\t\t"+goods.getGname()+"\t"+goods.getGprice()+"$\t"+q+"\t"+total+"$\t");
                                        do{
                                            System.out.println("Confirm: Y/N");
                                            String confirm=ScannerInfoString();
                                            if(confirm.equals("y")||confirm.equals("Y")){
                                                double amount=ScannerInfo();
                                                double balance=Arith.sub(amount,total);
                                                if(balance<0){
                                                    System.err.println("INSUFFICIENT BALANCE");
                                                    System.out.println("\nINPUT AGAIN");
                                                }
                                                else{
                                                    //manipulate sales table
                                                    Gsales gSales=new Gsales(goods.getGid(),salsManId,q);
                                                    boolean insert = new GsalesDao().shoppingSettlement(gSales);
                                                    //manipulate goods table
                                                    int gn=gnum-q;
                                                    Goods newGoods=new Goods(goods.getGid(),gn);


                                                }
                                            }
                                        }
                                    }
                                }
                            }

                    }
                }
            }
    }
    public static void salesManManagementPage() {
    }
}
