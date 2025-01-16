/*
The file "time.txt" should be in the following format:

< In - Time >;< Out-Time >;< Employee Designation >;< Name >;< Employee ID >

and time should be mention in Military Time Format
For example, 0145 means 01:45 a.m. and 1734 means 05:34 p.m. 
*/

import java.io.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 
class Blue_Soft_Technologies
{
    double calculate(int ed,int it, int ot,boolean overtime)
    {
        if(ot<it)
          ot+=2400;
        double rate,ai,salary,ta;
        int time=(ot-it+30)/100;
        if(ed==0)
        {
            rate=200;
            ai=5;
            ta=2.6;
        }
        else 
        {
            rate=150;
            ai=1.5;
            ta=2.3;
        }
        salary=time*rate;
        if(overtime)
          salary+=(time-8)*ai*rate;
        if(ot>2100)
          salary+=(ot<=3200?11:(ot-2100))*ta*rate;
        return (double)((int)((salary*1000+5)/10))/100;
    }
    String space(int total,String pr,int minus)
    {
        String dash="";
        for(int i=0;i<(total-minus);i++)
          dash+=pr;
        return dash;
    }
    String display(String eid, String name, int ed, double salary)
    {
        String total="\n\n";
        total+="\n"+" "+space(100,"_",0);
        total+="\n"+("|"+space(100," ",0)+"|");
        String s="|"+space(40," ",0)+"BLUE SOFT TECHNOLOGY"+space(40," ",0)+"|";
        s+="\n|"+space(35," ",0)+"Near Don Bosco School, Liluah "+space(35," ",0)+"|";
        total+="\n"+(s);
        total+="\n"+("|"+space(100,"-",0)+"|");
        total+="\n"+("|  Employee ID : "+eid+space(91," ",16+Integer.toString(ed).length())+"|" );
        total+="\n"+("|"+space(100,"-",0)+"|");
        s=" Name                       | Designation      | Hourly Rate | Add. Incentive | TA    | Total ";
        total+="\n"+("|"+s+space(100," ",s.length())+"|" );
        total+="\n"+("|"+space(100,"-",0)+"|");
        String set1="| Project Leader   | Rs. 200/-   | 5.0 %          | 2.6 % | ";
        String set2="| Developer        | Rs. 150/-   | 1.5 %          | 2.3 % | ";
        s=" "+name+space(27," ",name.length())+((ed==0)?set1:set2)+Double.toString(salary);
        total+="\n"+("|"+s+space(100," ",s.length())+"|" );
        total+="\n"+("|"+space(100,"-",0)+"|");
        total+="\n"+("| Signature : "+space(100," ",13)+"|");
        total+="\n"+("|"+space(100," ",0)+"|");
        total+="\n"+("|"+space(100," ",0)+"|");
        total+="\n"+("|"+space(100,"-",0)+"|");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        String[] dt=(dtf.format(now)).split(" ",0);
        total+="\n"+("| Date : "+dt[0]+space(100," ",18)+"|");
        total+="\n"+("| Time : "+dt[1]+space(100," ",16)+"|");  
        total+="\n"+"|"+space(100,"_",0)+"|";

        return total;
    }
    public static void main(String args[])
    {
        Blue_Soft_Technologies bst = new Blue_Soft_Technologies();
        File time=new File("time.txt");
        File salary=new File("salary.txt");
        int it,ot,ed;
        boolean leader=true;
        String slip="",line,name,eid;
        try
        {
            BufferedReader r= new BufferedReader(new FileReader(time));
            FileWriter w = new FileWriter(salary,true);
            while((line=r.readLine())!=null)
            {
                String[] l=line.split(";",0);
                it=Integer.valueOf(l[0]);
                ot=Integer.valueOf(l[1]);
                ed=l[2].equals("developer")?1:0;
                if(leader)
                  leader=ot<it || ot-it >=800;
                name=l[3];
                eid=l[4];
                if (ed == 1)
                {
                  slip+=bst.display(eid,name,ed,bst.calculate(ed, it, ot, ot<it || ot-it >=800))+"\n";
                }   
            }
            r.close();
            BufferedReader lr= new BufferedReader(new FileReader(time));
            while((line=lr.readLine())!=null)
            {
                String[] l=line.split(";",0);
                it=Integer.valueOf(l[0]);
                ot=Integer.valueOf(l[1]);
                ed=l[2].equals("developer")?1:0;
                name=l[3];
                eid=l[4];
                if (ed == 0)
                {
                    slip+=bst.display(eid,name,ed,bst.calculate(ed, it, ot, leader))+"\n";
                }
            }
            w.write(slip+"\n");
            w.close();
            lr.close();
        }
        catch(Exception e) {}
        try
        {  
          BufferedReader list= new BufferedReader(new FileReader(salary));
          while((line=list.readLine())!=null)
            System.out.println(line);
          list.close();
        }
        catch (Exception e){}
    }
}

