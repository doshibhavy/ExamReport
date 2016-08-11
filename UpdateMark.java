import java.util.*;
import java.io.*;

public class UpdateMark extends  Thread
{	int student ;
	//getData gd = new getData();
	public void run()
	{
		try
		{
			FileReader logReader = new FileReader("log.txt");
			BufferedReader logBF = new BufferedReader(logReader);
			String tmpstr=logBF.readLine();	
			logBF.close();
			logReader.close();
			student=Integer.parseInt(tmpstr);
			int examno,lastindex;
			String sf[];
			Scanner in = new Scanner(System.in);
			Scanner in1 = new Scanner(System.in);
			
			//tmpstr=logBF.readLine();	
			int rollno ;
			int k=1,choice,j=0;
			Float tmp1,tmp;
			String tmpstr1,tmpstr2,newmark,pqr1;
			do
			{
				FileReader exLogReader = new FileReader("exLog.txt");
				BufferedReader exLogBF = new BufferedReader(exLogReader);
				System.out.print("Enter roll no whose mark you want to change : ");
				rollno = in.nextInt();
				if (rollno > student || rollno <= 0)
				{
					System.out.println("Enter correct roll no !!!");
				}
				else
				{
					k=0;
					examno =1 ;
					sf= new String[20];
					FileReader stdReader = new FileReader("student/roll"+rollno+".txt");
					BufferedReader stdBF = new BufferedReader(stdReader);
					for (tmpstr=exLogBF.readLine(),tmpstr1=stdBF.readLine(),examno=1;tmpstr!=null;tmpstr=exLogBF.readLine(),tmpstr1=stdBF.readLine())
					{	
						lastindex=tmpstr.lastIndexOf('c');
						System.out.println(examno+" "+tmpstr.substring(30,lastindex-5)+" \t"+tmpstr1+"\n");
						sf[examno-1]=tmpstr.substring(30,lastindex - 1);
						//System.out.println(sf[examno-1]);
						examno++;
					}
					System.out.print("\nEnter choice number : ");
					choice=in.nextInt();
					if(choice <=examno-1 && choice >0)
					{
						do
						{
							examno=choice;
							System.out.print("\nEnter mark that want to change : ");
							newmark = in1.nextLine();
							pqr1=rollno+".txt";
							FileReader exReader = new FileReader("exam/"+sf[examno-1]);
							BufferedReader exBF = new BufferedReader(exReader);
							tmpstr2=exBF.readLine();
							exBF.close();
							exReader.close();
							if(newmark.equals("ab") || newmark.equals("Ab") || newmark.equals("aB") || newmark.equals("AB"))
							{
								k=0;
								j=1;
							}
							else
							{
								tmp = Float.parseFloat(tmpstr2);	
								try
								{
									tmp1=Float.parseFloat(newmark);
									//tmp = Float.parseFloat(tmpstr2);	
									if(tmp1>-1 && tmp1<=tmp)
									{
										k=0;
										j=1;
									}
									else
									{
										k=1;
										System.out.println("Enter ab/AB/Ab/aB    or    MARK less than "+tmp+"!!!");
									}
								}
								catch(Exception e)
								{
									System.out.println("Enter ab/AB/Ab/aB    or    MARK less than "+tmp+"!!!");
								}
							}
						}while(j==0);
						String pqr = "student/roll"+pqr1;
						stdReader.close();
						stdBF.close();
						exLogBF.close();
						exLogReader.close();
						if(k==0)
						{
							ChangeFile(pqr,examno-1,newmark);
							ChangeFile("exam/"+sf[examno-1],rollno,newmark);
						}
					}
					else
					{
						k=1;
						System.out.println("Enter correct choice !!!");
						stdReader.close();
						stdBF.close();
						exLogBF.close();
						exLogReader.close();
					}
			
				}				
			}while(k!=0);
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void ChangeFile(String filename,int line,String newmark)
	{
		try
		{
			String tmpstr ;
			FileReader fileReader = new FileReader(filename);
			BufferedReader fileBF = new BufferedReader(fileReader);
			FileWriter fWrt = new FileWriter(filename+".tmp",true);
			for(int i=0;i<line;i++)
			{
				tmpstr = fileBF.readLine();
				fWrt.write(tmpstr+"\r\n");
			}
			tmpstr = fileBF.readLine();
			fWrt.write(newmark+"\r\n");
			for(tmpstr = fileBF.readLine();tmpstr!=null;tmpstr=fileBF.readLine())
			{
				fWrt.write(tmpstr+"\r\n");
			}
			fileReader.close();
			fileBF.close();
			File realfile = new File(filename);
			realfile.delete();
			fWrt.close();
			File f = new File(filename+".tmp");
			f.renameTo(realfile);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}