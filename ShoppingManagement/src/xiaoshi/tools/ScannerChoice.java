package xiaoshi.tools;


import java.util.Scanner;

public class ScannerChoice {
    public static double ScannerInfo(){
        double num=0.00;
        do{
            Scanner sc=new Scanner(System.in);
            System.out.print("Keep two digits after fraction points. INPUT: ");
            String info=sc.next();
            String regex="(([1-9][0-9]*)\\.([0-9]{2}))|[0]\\.([0-9]{2})";
            if(info.matches(regex))num=Double.parseDouble(info);
            else {
                System.err.println("ERROR 404: INVALID INPUT ");
                continue;
            }
            break;
        }while(true);
        return num;
    }
    public static int ScannerNum(){
        int num=0;
        Scanner sc = new Scanner(System.in);
        do{
            String regex="(^[1-9][0-9]*)";
            System.out.print("Please input the Quantity of product. INPUT: ");
            String quantity=sc.next();
            if(quantity.matches(regex))num=Integer.parseInt(quantity);
            else{
                System.out.println("ERROR 404 INVALID INPUT");
                continue;
            }
            break;
        }while(true);
        return num;
    }
    public static String ScannerInfoString(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please INPUT: ");
        return sc.next();
    }
    public static void changedInfoNext(String oper){
        do{
            System.out.println("Continue current operation :( Y/n) ");
            String choice =ScannerInfoString();

            if(choice.equals("y")||choice.equals("Y")){

            }
        }while(1==0);
    }

}
