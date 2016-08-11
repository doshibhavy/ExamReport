import java.util.*;
import java.io.*;

public class NewExam extends Thread
{
	public void run()
	{	Scanner in1 = new Scanner(System.in);
		try
		{
			Calendar cal ;
			Scanner in = new Scanner(System.in);
			File exLog = new File("exlog.txt");
			if(exLog.exists() == false)
			{
				exLog.createNewFile();
				FileWriter log = new FileWriter("log.txt",true);
				cal = Calendar.getInstance();
				log.write(cal.getTime()+" Exam Log file created !!!\r\n");
			}
			FileWriter exLogWrt = new FileWriter("exlog.txt",true);
			System.out.print("\nEnter File name : ");
			String fname = in.nextLine();
			FileWriter exwrt = new FileWriter("exam/"+fname+".txt",true);
			cal = Calendar.getInstance();
			exLogWrt.write(cal.getTime()+"  "+fname+".txt created\r\n");
			exLogWrt.close();
			
			/* for get Student */
			FileReader logReader = new FileReader("log.txt");
			BufferedReader logBF = new BufferedReader(logReader);
			String tmpstr = logBF.readLine();	
			int student = Integer.parseInt(tmpstr);	
			logBF.close();
			logReader.close();
			String mark;
			FileWriter stdwrt ;
			System.out.print("Enter out of mark : ");
			int i1 = in1.nextInt();
			exwrt.write(i1+"\r\n");
			System.out.print("Enter mark or AB(absent) : ");
			int k=0;
			float tmp;
			for (int i = 1 ; i<=student ; i++)
			{	
				stdwrt = new FileWriter("student/roll"+i+".txt",true);
				do
				{
					k=0;
					System.out.print("\n"+i+" : ");
					mark = in.nextLine();
					if(mark.equals("ab") || mark.equals("Ab") || mark.equals("aB") || mark.equals("AB"))
					{
						k=1;
					}
					else
					{
						try
						{
							tmp=Float.parseFloat(mark);
						
							if(tmp>-1 && tmp <=i1)
							{
								k=1;
							}
						}
						catch(Exception e)
						{
							System.out.println("Enter ab/AB/Ab/aB    or    MARK !!!!");
						}
					}
					if(k==1)
					{
						exwrt.write(mark+"\r\n");	
						stdwrt.write(mark+"\r\n");
						stdwrt.close();
					}
					if(k==0)
					{
						System.out.println("Enter correct mark !!!");
					}
				}while(k==0);
			}
			exwrt.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}