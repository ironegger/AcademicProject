package xiaoshi.page;

import xiaoshi.dao.GsalesDao;
import xiaoshi.entity.Gsales;
import xiaoshi.tools.ScannerChoice;


import java.util.List;

public class GsalesPage {
    public static void dailySaleGoodsPage()
    {
        System.out.println("\tLISTING DAILY SALES\n");
      List<Gsales> GsalesList = new GsalesDao().dailyGsales();

        if (GsalesList.size() <= 0)
        {
            System.err.println("\tNO SALES TODAY");
            MainPage.commodityManagementPage();
        }else
        {
            System.out.println("\t\t\t\tSale on Today\n");
            System.out.println("\tNAME\t\tPRICE\t\tNUMBER\t\tSALES QUANTITY\t\tNOTE\n");

            for (int i = 0,length = GsalesList.size(); i < length; i++)
            {
                //��ȡ�۳���Ʒ��gname,gprice,gnum, allSum (������Ʒ�������ܺ�)
                Gsales gSales = GsalesList.get(i);
                System.out.print("\t"+gSales.getgName()+"\t\t"+gSales.getgPrice()+" $\t\t"+gSales.getgNum()+"\t\t"+gSales.getAllSum());
                int gNUm = gSales.getgNum();
                if (gNUm==0)
                {
                    System.out.println("\t\tSOLD OUT");
                }else if (gNUm<10)
                {
                    System.out.println("\t\tLESS THAN 10 REMAINS   ");
                }else
                {
                    System.out.println("\t\t-");
                }
                System.out.println("\t");
            }
            do
            {
                System.out.println("\n\nENTER 0 TO GO BACK");
                String choice = ScannerChoice.ScannerInfoString();
                if ("0".equals(choice))
                {
                    MainPage.salesManManagementPage();
                }
                MainPage.commodityManagementPage();
            } while (true);
        }
    }
}
