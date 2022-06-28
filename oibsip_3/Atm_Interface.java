 //Api packages
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.util.*;       

class Withdraw
{
    static void withdraw(String ch)
    {
       System.out.println("-----------------------------\n");
       Scanner sc=new Scanner(System.in);
        try
        {
          // using API for fetching the specific line
          String balanceFromFile=Files.readAllLines(Path.of(ch)).get(4).substring(17);

          int makingbalanceToInteger=Integer.valueOf(balanceFromFile);
          System.out.print("Please enter the amount to withdraw: ");
          int wcash=sc.nextInt();

          if(makingbalanceToInteger>=wcash)
          {
            makingbalanceToInteger=makingbalanceToInteger-wcash;
            System.out.println("Amount Rs"+wcash+"/-withdrawn successfully");
            
            List<String> all=Files.readAllLines(Path.of(ch));
            //writing the specific line to file
            all.set(4,"Account Balance :"+makingbalanceToInteger);
            // writing each transaction to the file
            all.add(all.size(),"Amount Rs"+wcash+"/-withdrawn");
            Files.write(Paths.get(ch),all);
          }
          else
          {
              System.out.println("Insufficient Balance To Withdraw");
          }
        }
        catch(IOException e)
        {

        }
          
       System.out.println("-----------------------------\n");
       Atm_Interface.interfaceMethod(ch);
       sc.close();
    }
}

class Deposit
{
    static void deposit(String ch)
    {
       Scanner sc=new Scanner(System.in);
       System.out.println("-----------------------------\n");
       try
       {
         String balanceFromFile=Files.readAllLines(Paths.get(ch)).get(4).substring(17);
         int makingbalanceToInteger=Integer.valueOf(balanceFromFile);
       
         System.out.println("Enter amount to be deposited :");
         int dcash=sc.nextInt();
         makingbalanceToInteger=makingbalanceToInteger+dcash;
         System.out.println("Amount Rs"+dcash+"/-deposited successfully");
      
         // write to file 
         List<String> all=Files.readAllLines(Path.of(ch));
         //updating balance
         all.set(4,"Account Balance :"+makingbalanceToInteger);
         // writing each transaction to the file
         all.add(all.size(),"Amount Rs"+dcash+"/-deposited");
         Files.write(Paths.get(ch),all);

         System.out.println("-----------------------------\n");
         Atm_Interface.interfaceMethod(ch);
         sc.close();
       }
       catch(Exception e){}
    }
}

class Transfer
{
    static void transfer(String source)
    {
       System.out.println("-----------------------------");
       Scanner sc=new Scanner(System.in);

       
       System.out.println("Enter the user id of receiving account :");
       String rName=sc.nextLine();
       System.out.println("Enter the account number of receiving body: ");
       int rAccNumber=sc.nextInt();
       System.out.println("Enter the amount to be transferred :");
       int tcash=sc.nextInt();

       try
       {
         
         // getting the path of destination file and checking whether destination account exists or not
         String destination ="C:\\Users\\PRAJWAL\\OneDrive\\Desktop\\oasis intern\\oibsip_3\\"+rName+".txt";
         File fi=new File(destination);

         if(fi.exists())
         {
          // Checking account number maches or not
           String accnumberFromFile=Files.readAllLines(Paths.get(destination)).get(1).substring(16);
           int makingaccnumberToInteger=Integer.valueOf(accnumberFromFile);

           if(makingaccnumberToInteger==rAccNumber)
           {
              //  reading balance from source file
              String balanceFromSource=Files.readAllLines(Paths.get(source)).get(4).substring(17);
              int makingSbalanceToInteger=Integer.valueOf(balanceFromSource);
            
              //  reading balance from destination file
              String balanceFromDestination=Files.readAllLines(Paths.get(destination)).get(4).substring(17);
              int makingDbalanceToInteger=Integer.valueOf(balanceFromDestination);

              if(tcash<=makingSbalanceToInteger)
              {
                 // updating the balance of source file
                 makingSbalanceToInteger=makingSbalanceToInteger-tcash;
                 System.out.println("Amount Rs."+tcash+"/-tansferred successfully");
             
                 List<String> all1=Files.readAllLines(Path.of(source));
                 // writing the updated balance to source file
                 all1.set(4,"Account Balance :"+makingSbalanceToInteger);
                 // writing each transaction to the source file
                 all1.add(all1.size(),"Amount Rs"+tcash+"/-Transferred to "+rAccNumber);
                 Files.write(Paths.get(source),all1);


                 // updating the balance of destination file
                 makingDbalanceToInteger=makingDbalanceToInteger+tcash;
             
                 List<String> all2=Files.readAllLines(Path.of(destination));
                 // writing the updated balance to destination file
                 all2.set(4,"Account Balance :"+makingDbalanceToInteger);
                 // writing each transaction to the destination file
                 all2.add(all2.size(),"Amount Rs"+tcash+"/-Deposited by "+rName);
                 Files.write(Paths.get(destination),all2);
            

              }
              else 
              System.out.println("Insuffient balance to transfer");
              
            }
            else
              System.out.println("Wrong Account NUmber");
          }
         else
             System.out.println("Not An User! Access Denied!!");
       }
       catch(Exception e){}

       System.out.println("-----------------------------\n");
       Atm_Interface.interfaceMethod(source);
       sc.close();
    }
}

class Check_Balance
{
  static void checkBalance(String ch)
  {
    System.out.println("-----------------------------\n");
    try
    {
         String balanceFromFile=Files.readAllLines(Path.of(ch)).get(4).substring(17);
         int makingbalanceToInteger=Integer.valueOf(balanceFromFile);
         System.out.println(" Availabel Balance :"+makingbalanceToInteger);
    }
    catch(Exception e){}
    System.out.println("-----------------------------\n");
    Atm_Interface.interfaceMethod(ch);
  }
}

class Transaction_History
{
    static void history(String ch)
    {
       System.out.println("-----------------------------\n");
       System.out.println("Transaction History...");
       try
       {
           List<String> history=Files.readAllLines(Path.of(ch));
           for(int i=history.size()-1;i>=7;i--)
           {
              System.out.print(history.get(i)+"______\n");
              
           }
       }
       catch(Exception e){}

       System.out.println("\n-----------------------------\n");
       Atm_Interface.interfaceMethod(ch);
    }
}


public class Atm_Interface {
   static String name;
   static String user_id;
   static int password;
   static int accnumber;
   public static int balance=1000;

   public static void interfaceMethod(String ch)
   {
      Scanner sc=new Scanner(System.in);
      System.out.println("WELCOME TO BANKING SYSTEM");
      System.out.println("-----------------------------\n");
      System.out.println("Select Option:");
      System.out.println("1. Withdraw\n2. Deposit\n3. Transfer\n4. Check balance\n5. Transaction History\n6. Exit");
      System.out.print("Enter your choice :");
      int choice=sc.nextInt();
    
        switch (choice) {
            case 1:
                Withdraw.withdraw(ch);
            case 2:
                Deposit.deposit(ch);
            case 3:
                Transfer.transfer(ch);
            case 4:
                Check_Balance.checkBalance(ch);
            case 5:
                Transaction_History.history(ch);
            case 6:
                System.exit(0);
            default:
             System.out.println("Invalid Choice");
             interfaceMethod(ch);
             
        }
        sc.close();


   }
   
   public static void login()
   {
       Scanner sc=new Scanner(System.in);
       System.out.println("-----------------------------\n");
       System.out.print("\nLOGIN FIRST...\nEnter the user id:");
       String verify_user_id=sc.nextLine();
       System.out.print("Enter the pin:");
       int verify_password=sc.nextInt();
       
       //Checking file exiats or not 
       String ch="C:\\Users\\PRAJWAL\\OneDrive\\Desktop\\oasis intern\\oibsip_3\\"+verify_user_id+".txt";
       File fi=new File(ch);
       if(fi.exists())// Enter if files exists int the system
       {
          try
          {
            BufferedReader br=new BufferedReader(new FileReader(fi));
            int getPin=Integer.valueOf(br.readLine().substring(9));
            br.close();
            if(getPin==verify_password)
            {
              System.out.println("LOGIN SUCCESSFULL....\n");
              interfaceMethod(ch);
           }
           else
           {
             System.out.println("WRONG USER PIN");
             System.out.print("\nWant to login again:");
             sc.nextLine();
             String againlogin=sc.nextLine();
              if(againlogin.equalsIgnoreCase("Yes"))
                login();
              else
                System.exit(0);
           }
          }
          catch(Exception e)
          {
            
          }
          
        }
        else 
        {
        System.out.println("Not An User! Access Denied!!");
        login();
        }
        sc.close();
   }

   public static void homepage()
   {
       Scanner sc=new Scanner(System.in);
       System.out.println("\nWELCOME TO ATM INTERFACE");
       System.out.println("-----------------------------\n");
       System.out.println("Select Below Option :");
       System.out.println("1 New User(Register)\n2 for Login\n3 for Exit");
       System.out.print("Enter your choice:");
       int choice=sc.nextInt();
       switch(choice)
       {
        case 1:
        System.out.println("---------------------------");
        System.out.print("Enter your name:");
        sc.nextLine();
        name=sc.nextLine();
        System.out.print("Enter the user id:");
        user_id=sc.nextLine();
        System.out.print("Set your pin:");
        password=sc.nextInt();
        System.out.print("Enter your Account Number:");
        accnumber=sc.nextInt();
        System.out.println("\nREGISTRATION SUCCESSFULL!!");
        System.out.println("---------------------------\n");
         try
         {
           File f=new File("C:\\Users\\PRAJWAL\\OneDrive\\Desktop\\oasis intern\\oibsip_3\\"+user_id+".txt");
            Writer w=new FileWriter(f);
            w.write(("Atm PIN :"+password));
            w.write(("\nAccount Number :"+accnumber));
            w.write(("\nAccount Holder :"+name));
            w.write(("\nUser Id:"+user_id));
            w.write("\nAccount Balance :"+balance);
            w.write("\n\n.....Transaction History.....");
            w.close();
         }
         catch(Exception e)
         { 
            System.out.println("Exception Occured:"+e);
         }
        login();
        break;
        
        case 2:
              login();
              break;

         case 3:
                System.exit(0);
                break;
         
         default:
                System.out.println("Invalid Choice\n");
                homepage();

       }
       sc.close();
   }


    public static void main(String[] args) {
        homepage();
    }
}
