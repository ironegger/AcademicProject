package xiaoshi.page;

import xiaoshi.dao.GoodsDao;
import xiaoshi.entity.Goods;
import xiaoshi.tools.ScannerChoice;


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
    public static void  upateGoodsPage()
    {
        System.out.println("\t正在执行 更改商品 操作\n");
        System.out.println("请输入想要更改的商品名字");

        //调用查找商品函数，显示将要更改的商品信息
        int gid = QueryPrint.query("updateGoodsPage"); //return the goods gid

        System.out.println("\n--------请选择您要更改的内容\n");
        System.out.println("\t1.更改商品-名称");
        System.out.println("\t2.更改商品-价格");
        System.out.println("\t3.更改商品-数量");
        System.out.println("\n请输入选项,或者按0返回上一级菜单.");
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
                        System.out.println("请输入商品-新名称");
                        String gname = ScannerInfoString();
                        Goods goodsName = new Goods(gid,gname);
                        boolean boolName = new GoodsDao().updateGoods(1, goodsName);
                        if (boolName)
                        {
                            System.out.println("\n\t！！成功更新商品名至数据库！！\n");
                        }else
                        {
                            System.err.println("\n\t！！更新商品名失敗！！");
                        }
                        changedInfoNext("upateGoodsPage");
                        break;
                    case 2:
                        System.out.println("请输入商品-新价格 ");
                        double gprice = ScannerInfo();
                        Goods  goodsPrice = new Goods(gid,gprice);
                        boolean boolPrice = new GoodsDao().updateGoods(2,goodsPrice);

                        if (boolPrice)
                        {
                            System.out.println("\n\t！！成功更新商品价格至数据库！！\n");
                        }else
                        {
                            System.err.println("\n\t！！更新商品价格失敗！！");
                        }
                        changedInfoNext("upateGoodsPage");
                        break;
                    case 3:
                        System.out.println("请输入商品-新数量 ");
                        int gNum = ScannerNum();
                        Goods  goodsNum= new Goods(gid,gNum);
                        boolean boolNum = new GoodsDao().updateGoods(3,goodsNum);
                        if (boolNum)
                        {
                            System.out.println("\n\t！！成功更新商品数量至数据库！！\n");
                        }else
                        {
                            System.err.println("\n\t！！更新商品数量失敗！！");
                        }
                        changedInfoNext("upateGoodsPage");
                        break;
                    default:
                        System.out.println("请输入正确的选择！");
                        break;
                }
            }
            System.err.println("！输入有误！");
            System.out.println("请重新选择,或者按0返回上一级菜单.");
        } while (true);
    }
}
