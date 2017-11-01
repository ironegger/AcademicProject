package xiaoshi.page;

import xiaoshi.dao.GoodsDao;
import xiaoshi.entity.Goods;
import xiaoshi.tools.QueryPrint;
import xiaoshi.tools.ScannerChoice;

import java.util.ArrayList;
import java.util.List;


public class GoodsPage extends ScannerChoice {
    /**
     * add items
     */
    public static void addGoodsPage(){
        System.out.println("\tNow adding item");
        System.out.println("\nItem name: ");
        String name=ScannerInfoString();
        System.out.println("\nPrice: ");
        double price = ScannerInfo();
        System.out.println("\nCount: ");
        int quantity=ScannerNum();
        System.out.println("\nProducer: ");
        String producer=ScannerInfoString();
        Goods good = new Goods(name,price,quantity,producer);
        if(new GoodsDao().addGoods(good))System.out.println("Item has been added to database");
        else System.out.println("Insertion failed");
        changedInfoNext("addGoodsPage");
    }
    public static void  upateGoodsPage() {

        System.out.println("ENTER NAME");

        //调用查找商品函数，显示将要更改的商品信息
        int gid = QueryPrint.query("updateGoodsPage"); //return the goods gid

        System.out.println("\n--------SELECT\n");
        System.out.println("\t1.CHANGE NAME");
        System.out.println("\t2.CHANGE PRICE");
        System.out.println("\t3.CHANGE QUANTITY");
        System.out.println("\nENTER 0 TO LAST PAGE");
        do
        {
            String choice = ScannerInfoString();
            String regex = "[0-3]";
            if (choice.matches(regex))
            {
                int info = Integer.parseInt(choice);
                switch (info)
                {
                    case 0:
                        MainPage.MaintenancePage();
                        break;
                    case 1:
                        System.out.println("Enter a new name");
                        String gname = ScannerInfoString();
                        Goods goodsName = new Goods(gid,gname);
                        boolean boolName = new GoodsDao().updateGoods(1, goodsName);
                        if (boolName)
                        {
                            System.out.println("\n\tUPDATE SUCCEED！!\n");
                        }else
                        {
                            System.err.println("\n\tFATAL ERROR");
                        }
                        changedInfoNext("upateGoodsPage");
                        break;
                    case 2:
                        System.out.println("Enter new price ");
                        double gprice = ScannerInfo();
                        Goods  goodsPrice = new Goods(gid,gprice);
                        boolean boolPrice = new GoodsDao().updateGoods(2,goodsPrice);

                        if (boolPrice)
                        {
                            System.out.println("\n\tUPDATE SUCCEED！！\n");
                        }else
                        {
                            System.err.println("\n\tFATAL ERROR");
                        }
                        changedInfoNext("upateGoodsPage");
                        break;
                    case 3:
                        System.out.println("Enter new quantity");
                        int gNum = ScannerNum();
                        Goods  goodsNum= new Goods(gid,gNum);
                        boolean boolNum = new GoodsDao().updateGoods(3,goodsNum);
                        if (boolNum)
                        {
                            System.out.println("\n\tUpdate succeed！！\n");
                        }else
                        {
                            System.err.println("\n\tFATAL ERROR");
                        }
                        changedInfoNext("upateGoodsPage");
                        break;
                    default:
                        System.out.println("请输入正确的选择！");
                        break;
                }
            }
            System.err.println("INVALID INPUT");
            System.out.println("REINPUT,OR 0 TO BACK");
        } while (true);
    }
    public static void deleteGoodsPage(){
        System.out.println("Enter name to delete");
        int gid= QueryPrint.query("deleteGoodsPage");

        do{
            System.out.println("CONFIRM DELETION?: Y/n");
            String choice=ScannerInfoString();
            if(choice.equals("y")||choice.equals("Y")){
                boolean b=new GoodsDao().deleteGoods(gid);
                if(b)System.out.println("Successfully deleted\n");
                else System.out.println("FAILED\t");
                changedInfoNext("deleteGoodsPage");
            }
            else if(choice.equals("N")||choice.equals("n"))MainPage.MaintenancePage();
            System.out.println("INVALID INPUT");
        }while(true);
    }
    public static void queryGoodsPage(){
        System.out.println("\t\t1.Query By Quantity");
        System.out.println("\t\t2.Query By Price");
        System.out.println("\t\t3.Query By KeyWord");
        String regex="[0-3]";
        do{
            String info=ScannerInfoString();
            if(info.matches(regex)){
                int c=Integer.parseInt(info);
                switch(c){
                    case 0:MainPage.mainPage();
                    case 1:
                    case 2:
                    case 3:
                        if(c==3) {
                            System.out.println("\nEnter KeyWord");
                        }
                            List<Goods> list = new GoodsDao().queryGoods(c);
                            if (list == null ||list.size()==0){
                                System.out.println("ITEMS NOT EXIST");
                                continue;
                            }
                            else {if(c==1)System.out.println("QUERY BY QUANTITY");
                            else System.out.println("QUERY BY PRICE");}
                        for(Goods good:list)
                            System.out.print("\t"+good.getGid()+"\t\t"+good.getGname()+"\t\t"+good.getGprice()+"\t\t"+good.getGnum());
                        break;
                    default:
                        break;
                        }

                }
                System.out.println("ENTER CHOICE");
            }while(true);
        }

    public static void displayGoodsPage(){
        System.out.println("ALL ITEMS\n");
        List<Goods> list=new GoodsDao().displayGoods();
        if(list.size()==0)System.out.println("EMPTY STOKE\n");
        else{
            for(Goods good:list)
                System.out.print("\t"+good.getGid()+"\t\t"+good.getGname()+"\t\t"+good.getGprice()+"\t\t"+good.getGnum());
        }
        do{
            System.out.println("Enter 0 to back\n");
            String choice=ScannerInfoString();
            if(choice.equals("0"))MainPage.mainPage();
            System.out.println("INVALID INPUT");
        }while(true);
    }
}
