/*Time.txt - Format
* <start time>;<endtime>;<Notes>
* Time should be entered in military time format
*/
import java.io.*;
import java.util.*;
import java.text.*;
class Routine
{
    String space(int total,String pr,int minus)
    {
        String dash="";
        for(int i=0;i<(total-minus);i++)
          dash+=pr;
        return dash;
    }
    String read(String i)
    {
        String s="";
        String intime=i.substring(0,4),outtime=i.substring(5,9);
        String notes1="",notes2="";
        i=i.substring(10);
        String inti=Integer.parseInt(intime)<1200?(intime.substring(0,2)+":"+intime.substring(2)+" A.M."):(((Integer.valueOf(intime.substring(0,2)))-12)+":"+intime.substring(2)+" P.M.");
        String outti=Integer.parseInt(outtime)<1200?(outtime.substring(0,2)+":"+outtime.substring(2)+" A.M."):(((Integer.valueOf(outtime.substring(0,2)))-12)+":"+outtime.substring(2)+" P.M.");
        inti=(inti.charAt(1)==':'?"0":"")+inti;
        outti=(outti.charAt(1)==':'?"0":"")+outti;
        notes1=i.length()>40?i.substring(0,40):i;
        notes2=i.length()>40?i.substring(40):"";
        s+="\n\n";
        s+="|     "+inti+"     |     "+notes1+space(45," ",notes1.length())+"|";
        s+="\n|     "+outti+"     |     "+notes2+space(45," ",notes2.length())+"|";
        return s;
    }
    public static void main(String args[])
    {
        SimpleDateFormat formatter=new SimpleDateFormat("HH-mm-ss");
        Date date=new Date();
        String dat=formatter.format(date);
        dat="Routine_"+dat+"_.txt";
        System.out.println(dat);
        File routine=new File(dat);
        File i=new File("Time.txt");
        String o="";
        Routine rr=new Routine();
        String line="";
        try
        {
            BufferedReader r= new BufferedReader(new FileReader(i));
            FileWriter w = new FileWriter(routine);
            while((line=r.readLine())!=null)
            {
                if(line.length()==8)
                {
                    String d=line;
                    d=d.substring(0,2)+":"+d.substring(2,4)+":"+d.substring(4);
                    o+=rr.space(27," ",0)+"DATE - "+d+rr.space(27," ",0);
                    o+="\n\n";
                    o+="|        TIME        |"+rr.space(22," ",0)+"To  Do"+rr.space(22," ",0)+"|";
                }
                else
                {
                    o+=rr.read(line);
                }
            }
            System.out.println(o);
            w.write(o);
            r.close();
            w.close();
        }
        catch(Exception e){}
    }
}
