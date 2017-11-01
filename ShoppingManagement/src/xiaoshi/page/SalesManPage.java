package xiaoshi.page;

import xiaoshi.dao.SalesManDao;
import xiaoshi.entity.SalesMan;
import xiaoshi.tools.QueryPrint;
import xiaoshi.tools.ScannerChoice;

import java.util.ArrayList;
import java.util.List;

public final class SalesManPage extends ScannerChoice
{
    /**
     * 1.����ۻ�Ա���� ��ʵ�֣�
     */
    public static void  addSalesManPage()
    {
        System.out.println("\t����ִ������ۻ�Ա����\n");

        System.out.println("\n����ۻ�Ա-����");
        String sName = ScannerInfoString();

        System.out.println("\n����ۻ�Ա-����");
        String sPssswd = ScannerInfoString();

        SalesMan salesMan = new SalesMan(sName,sPssswd);
        boolean bool = new SalesManDao().addSalesMan(salesMan);

        if (bool)
        {
            System.out.println("\n\t!���ѳɹ�����ۻ�Ա�����ݿ�!");
        }else
        {
            System.out.println("����ۻ�Աʧ��");
        }
        choiceSalesManNext("addSalesMan");
    }

    /**
     * 2.�����ۻ�Ա����
     */
    public static void updateSalesManPage()
    {
        System.out.println("\t����ִ�и����ۻ�Ա����\n");
        System.out.println("��������Ҫ���ĵ��ۻ�Ա����");
        String sName = ScannerInfoString();

        //���þ�ȷ�����ۻ�Ա����
        List<SalesMan> salesManList = new QueryPrint().querySalesMan(sName);
        if (salesManList.size() <= 0)
        {
            System.err.println("\t�������޴��ˣ���");
            choiceSalesManNext("updateSalesMan");
        }else
        {
            //��ʾ��Ҫ���ĵ��ۻ�Ա��Ϣ
            System.out.println("\t\t\t�ۻ�Ա��Ϣ\n\n");
            System.out.println("\t�ۻ�Ա���\t\t�ۻ�Ա����\t\t�ۻ�Ա����");

            SalesMan salesMan = salesManList.get(0); //����ľ�ȷ�����У�ֻ�ܷ���һ����ֵ�����������
            System.out.println("\t"+salesMan.getsId()+"\t\t\t"+salesMan.getsName()+"\t\t\t"+salesMan.getsPassWord());
            System.out.println();

            //ѡ������ۻ�Ա����
            System.out.println("\n--------��ѡ����Ҫ���ĵ�����\n");
            System.out.println("\t1.�����ۻ�Ա-����");
            System.out.println("\t2.�����ۻ�Ա-����");
            do
            {
                String choice = ScannerInfoString();
                String regex  = "[0-2]";
                if (choice.matches(regex))
                {
                    int info = Integer.parseInt(choice);
                    switch (info)
                    {
                        case 0:
                            MainPage.salesManManagementPage();
                            break;
                        case 1:
                            System.out.println("�����ۻ�Ա-������");
                            String sNewName = ScannerInfoString();

                            SalesMan salesManName = new SalesMan(salesMan.getsId(),sNewName,null);
                            boolean boolsName = new SalesManDao().updateSalesMan(1, salesManName);

                            if (boolsName)
                            {
                                System.out.println("\n\t�����ɹ������ۻ�Ա���������ݿ⣡��\n");
                            }else
                            {
                                System.err.println("\n\t���������ۻ�Ա����ʧ������");
                            }
                            choiceSalesManNext("updateSalesMan");
                            break;
                        case 2:
                            System.out.println("�����ۻ�Ա-������");
                            String sNewPasswd = ScannerInfoString();

                            SalesMan salesManPasswd = new SalesMan(salesMan.getsId(),null,sNewPasswd);
                            boolean boolsPasswd = new SalesManDao().updateSalesMan(2, salesManPasswd);

                            if (boolsPasswd)
                            {
                                System.out.println("\n\t�����ɹ������ۻ�Ա���������ݿ⣡��\n");
                            }else
                            {
                                System.err.println("\n\t���������ۻ�Ա����ʧ������");
                            }
                            choiceSalesManNext("updateSalesMan");
                            break;
                        default:
                            break;
                    }
                }
                System.out.println("\t!��������!");
                System.out.println("\n��ѡ��ѡ��.���߰� 0 ������һ���˵�.");
            } while (true);
        }
    }

    /**
     * 3.ɾ���ۻ�Ա����
     */
    public static void deleteSalesManPage()
    {

        System.out.println("\t����ִ�� ɾ���ۻ�Ա ����\n");
        System.out.println("��������Ҫɾ�����ۻ�Ա����");
        String sName = ScannerInfoString();

        //���þ�ȷ�����ۻ�Ա����
        List<SalesMan> salesManList = new QueryPrint().querySalesMan(sName);
        if (salesManList.size() <= 0)
        {
            System.err.println("\t�������޴��ˣ���");
            choiceSalesManNext("deleteSalesMan");
        }else
        {
            //��ʾ��Ҫɾ�����ۻ�Ա��Ϣ
            System.out.println("\t\t\tɾ���ۻ�Ա��Ϣ\n\n");
            System.out.println("\t�ۻ�Ա���\t\t�ۻ�Ա����\t\t�ۻ�Ա����");

            for (int i = 0,length = salesManList.size(); i < length; i++)
            {
                SalesMan salesMan = salesManList.get(i);
                System.out.println("\t"+salesMan.getsId()+"\t\t\t"+salesMan.getsName()+"\t\t\t"+salesMan.getsPassWord());
                System.out.println();
            }
            //ȷ���Ƿ����ɾ����
            do
            {
                System.out.println("\nȷ��ɾ�����ۻ�Ա��Y/N");
                String choice = ScannerInfoString();
                if ("y".equals(choice) || "Y".equals(choice))
                {
                    //���Єh��-���ݿ����
                    boolean boolDeleteSalesMan = new SalesManDao().deleteSalesMan(sName);//�{�Äh������

                    if (boolDeleteSalesMan)
                    {
                        System.err.println("\t�����ѳɹ��h�����ۻ�Ա����\n");
                    }else
                    {
                        System.err.println("\t�����h�����ۻ�Աʧ������");
                    }
                    choiceSalesManNext("deleteGoods");
                }else if ("N".equals(choice) || "n".equals(choice))
                {
                    MainPage.salesManManagementPage();
                }
                System.err.println("\t!!��������,����������!!");
            } while (true);
        }
    }


    /**
     * 4.��ѯ�ۻ�Ա���� ��ʵ�֣�
     */
    public static void querySalesManPage()
    {
        System.out.println("\t\t  ����ִ�в�ѯ�ۻ�Ա����\n");
        System.out.println("Ҫ��ѯ���ۻ�Ա�ؼ���");
        String sName = ScannerInfoString();

        List<SalesMan> salesManList = new SalesManDao().querySalesMan(sName);

        if (salesManList.size() <=0)
        {
            System.err.println("\t��û����Ա���ϲ�ѯ������");
        }else
        {
            System.out.println("\t\t\t�����ۻ�Ա�б�\n\n");
            System.out.println("\t�ۻ�Ա���\t\t�ۻ�Ա����\t\t�ۻ�Ա����");

            for (int i = 0,length = salesManList.size(); i < length; i++)
            {
                SalesMan salesMan = salesManList.get(i);
                System.out.println("\t"+salesMan.getsId()+"\t\t\t"+salesMan.getsName()+"\t\t\t"+salesMan.getsPassWord());
                System.out.println();
            }
        }
        choiceSalesManNext("querySalesMan"); //param��������
    }

    /**
     * 5.��ʾ�����ۻ�Ա����
     */
    public static void displaySalesManPage()
    {
        List<SalesMan> salesManList = new SalesManDao().displaySalesMan();
        if (salesManList.size() <= 0)
        {
            System.err.println("\t�����ۻ�Ա�б�Ϊ�գ���");
            MainPage.salesManManagementPage();
        }else
        {
            System.out.println("\t\t\t�����ۻ�Ա�б�\n\n");
            System.out.println("\t�ۻ�Ա���\t\t�ۻ�Ա����\t\t�ۻ�Ա����");

            for (int i = 0,length = salesManList.size(); i < length; i++)
            {
                SalesMan salesMan = salesManList.get(i);
                System.out.println("\t"+salesMan.getsId()+"\t\t\t"+salesMan.getsName()+"\t\t\t"+salesMan.getsPassWord());
                System.out.println();
            }
            do
            {
                System.out.println("\n\n���� 0 ������һ���˵�");
                String choice = ScannerInfoString();

                if ("0".equals(choice))
                {
                    MainPage.salesManManagementPage();
                }
                System.err.print("\t��������");
            } while (true);
        }
    }
}