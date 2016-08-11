import java.util.*;
import java.io.*;

class ReportOverAll extends Thread
{
	public void run()
	{
		try
		{
			int choice,examno,lastindex,k=0;
			int totalab=0,totalpresent=0,totalstd=0,outof;
			float avg,mark,totalmark=0;
			String tmpstr,sf[];
			Scanner in = new Scanner(System.in);
			do
			{
				examno=0;
				sf=new String[20];
				FileReader exLogReader = new FileReader("exLog.txt");
				BufferedReader exLogBF = new BufferedReader(exLogReader);
				for (tmpstr=exLogBF.readLine();tmpstr!=null;tmpstr=exLogBF.readLine())
				{	
					examno++;
					lastindex=tmpstr.lastIndexOf('c');
					System.out.println(examno+" "+tmpstr.substring(30,lastindex-5));
					sf[examno-1]=tmpstr.substring(30,lastindex - 1);
				}
				System.out.print("\nEnter choice number for generate report for that subject : ");
				choice=in.nextInt();
				if(choice <=examno && choice >0)
				{
					examno=choice;
					k=1;
					FileReader exReader = new FileReader("exam/"+sf[examno-1]);
					BufferedReader exBF = new BufferedReader(exReader);
					tmpstr = exBF.readLine();
					outof = Integer.parseInt(tmpstr);
					System.out.println("Outof = " + outof);
					System.out.println("Roll No \t\t Mark/Absent\n");
					for(tmpstr = exBF.readLine();tmpstr!=null;tmpstr = exBF.readLine())
					{
						totalstd++;
						System.out.println(totalstd+"\t\t"+tmpstr);
						if(tmpstr.equals("aB") || tmpstr.equals("Ab") || tmpstr.equals("ab") || tmpstr.equals("AB"))
						{
							totalab++;
						}
						else
						{
							totalpresent++;
							mark = Float.parseFloat(tmpstr);
							totalmark = totalmark + mark ;
						}
					}
					System.out.println("Total student present in exam = "+totalpresent);
					System.out.println("Total student absent = "+totalab);
					System.out.println("Total student = "+totalstd);
					avg=((totalmark/totalpresent)/outof)*100;
					System.out.print("Average result of "+sf[examno-1]+" is ");
					System.out.printf("%.2f",avg);
					System.out.println("%");
				}
				else
				{
					System.out.println("Enter correct choice !!!");
				}
			}while(k==0);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}