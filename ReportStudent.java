import java.util.*;
import java.io.*;
//import getdata;

public class ReportStudent extends Thread
{	int student ;
	public void run()
	{
		try
		{
			FileReader logReader = new FileReader("log.txt");
			BufferedReader logBF = new BufferedReader(logReader);
			FileReader exLogReader = new FileReader("exLog.txt");
			BufferedReader exLogBF = new BufferedReader(exLogReader);
			String tmpstr=logBF.readLine();	
			student = Integer.parseInt(tmpstr);
			logBF.close();
			logReader.close();
			Scanner in = new Scanner(System.in);
			String tmpstr1,tmpstr2,exname;
			float finalavg=0,avg=0,cnt=0;
			int totalab=0,totalexam=0,i,rollno,k=0,totalattend=0,outof,lastindex,tmp1;
			float tmp;
			do
			{
				System.out.print("Enter student roll no for generating report : ");
				rollno = in.nextInt();
				if(rollno<=student && rollno>0)
				{
					FileReader stdReader = new FileReader("student/roll"+rollno+".txt");
					BufferedReader stdBF = new BufferedReader(stdReader);
					System.out.println(" \tFileName \t\tPercentage/Absent");
					for (tmpstr=exLogBF.readLine(),tmpstr1=stdBF.readLine();tmpstr!=null;tmpstr=exLogBF.readLine(),tmpstr1=stdBF.readLine())
					{
						totalexam++;
						lastindex=tmpstr.lastIndexOf('c');
						System.out.print(totalexam+" \t"+tmpstr.substring(30,lastindex-5)+" \t\t\t");
						exname = tmpstr.substring(30,lastindex - 1);
						if(tmpstr1.equals("ab") || tmpstr1.equals("Ab") || tmpstr1.equals("aB") || tmpstr1.equals("AB"))
						{
							totalab++;
							System.out.println("Absent");
						}
						else
						{
							totalattend++;
							FileReader exReader = new FileReader("exam/"+exname);
							BufferedReader exBF = new BufferedReader(exReader);
							tmpstr2=exBF.readLine();
							exBF.close();
							exReader.close();
							tmp=Float.parseFloat(tmpstr1);
							outof=Integer.parseInt(tmpstr2);
							avg = (tmp / outof)*100;
							System.out.printf("%.2f",avg);
							System.out.print("%\n");
							if(cnt==0)
							{
								finalavg = avg;
								cnt=1;
							}
							else
							{
								finalavg=(finalavg+avg)/2;
							}
							//System.out.println(finalavg +"  "+avg);
						}					
					}
					if((totalexam/2)<totalab)
					{
						System.out.println("Student should have proper reason with valid proof for attending less than half of exam conducted !!!");
						System.out.println("otherwise contact to parents .....");
					}
					if(totalexam==totalattend)
					{
						System.out.println("Student is very regular ...");
					}
					System.out.println("Total exam conducted = "+totalexam);
					System.out.println("Total exam attended = "+totalattend);
					System.out.println("Total exam unattended = "+totalab);
					System.out.printf("Average mark = " + "%.2f",finalavg);
					System.out.print("%\n");
					k=1;
					tmp1=(int)finalavg/10;
					switch(tmp1)
					{
						case 10 :
						case 9 :
							System.out.println("student have shown good performance in exams....");
							break;
					
						case 8 :
						case 7 :
							System.out.println("student is clever but need some work .... ");
							break;
						
						case 6 :
							System.out.println("student need some push in subject .....");
							break;
							
						case 5 :
							System.out.println("student need some personal attention in subject .....");
							break;
						case 4 :
							System.out.println("student need some personal interaction ....");
							break ;
						
						default :
							System.out.println("faculty should contact student's parents for results ...");
					
					}
				}
				else
				{
					System.out.println("Enter correct roll no (between 1 and "+student+") !!!");
				}
			}while(k==0);
		
			exLogReader.close();
			exLogBF.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}