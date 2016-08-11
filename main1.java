import java.util.*;
import java.io.*;

public class main1
{
	public static void main(String args[])
	{				
		int student ;
		try
		{			
			Calendar dt;
			
			Scanner in = new Scanner(System.in);		

			/*Authentication*/
			String tmpstr;

			System.out.print("Welcome !!\nEnter Username :- ");
			String uname = in.nextLine();
			System.out.print("\nEnter Password :- ");
			String pwd = in.nextLine();
			File logf = new File("log.txt");

			if ( uname.equals("user") && pwd.equals("user"))
			{
				if (logf.exists())
				{	
					/*File dir = new File("stud/");
					dir.mkdir();
					//dir.close();*/
					FileReader logReader = new FileReader("log.txt");
					BufferedReader logBF = new BufferedReader(logReader);
					tmpstr=logBF.readLine();	
					student = Integer.parseInt(tmpstr);	
					logBF.close();
					logReader.close();
				}
				else
				{
					File f;
					int stdcnt = 0;
					do{
					System.out.print("Enter no of student : ");
					student = in.nextInt();
					if(student >0 )
					{
						FileWriter wrt = new FileWriter("log.txt",true);
						wrt.write(student+"\r\n");
						stdcnt = 1;
					}
					else
					{
						System.out.println("Enter no of student ! greater than 0");
					}
					
					}while(stdcnt==0);
					
					for ( int i=1; i <= student ;i++)
					{
						f = new File("student/roll"+i+".txt");
						f.createNewFile();
					}
				}
			
		
				FileWriter wrt = new FileWriter("log.txt",true);
				dt = Calendar.getInstance();
				System.out.println("\nWelcome , "+uname+"...");
				wrt.write(dt.getTime()+" Successfully login in System !!!\r\n");
				int choice;
				do
				{
					System.out.print("\n1.New exam entry\n2.Update student mark\n3.Generate report - overall\n4.Generate report - perticular student\n5.Exit\n\nEnter your choice :- ");
					choice=in.nextInt();
					switch(choice)
					{	case 1 :
							NewExam n1 = new NewExam();
							n1.start();
							try
							{
								n1.join();
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
							break;
						case 2 :
							UpdateMark um1 = new UpdateMark();
							um1.start();
							try
							{
								um1.join();
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
							break;
						case 3 :
							ReportOverAll roa1 = new ReportOverAll();
							roa1.start();
							try
							{
								roa1.join();
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
							break;
						case 4 :
							ReportStudent rs1 = new ReportStudent();
							rs1.start();
							try
							{
								rs1.join();
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
							break;
							
						case 5 :
							System.out.println("Thank for using this software !!!");
							break;
						
						default:
							System.out.println("Enter Correct Choice !!!");
					}
				}while(choice!=5);	
				wrt.close();
			}
			else
			{	
				System.out.println("\nWrong Password or Username !!!\n");
				//wrt.write(dt.getTime()+"Login FAILED !!!\r\n");
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Something gone Wrong !!!");
			e.printStackTrace();
		}
	}
}