import java.util.Scanner;
public class SecondStage {
    public static void main(String[] args){

                Scanner sc = new Scanner(System.in);
                String cat = sc.nextLine();

                System.out.println("---------");
                System.out.println("| "+cat.charAt(0)+" "+cat.charAt(1)+" "+cat.charAt(2)+" |");
                System.out.println("| "+cat.charAt(3)+" "+cat.charAt(4)+" "+cat.charAt(5)+" |");
                System.out.println("| "+cat.charAt(6)+" "+cat.charAt(7)+" "+cat.charAt(8)+" |");
                System.out.println("---------");

                sc.close();

    }
}
