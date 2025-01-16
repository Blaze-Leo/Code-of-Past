import java.io.*;
class Comp
{
    public static void main(String args[])
    {
        try
        {
            FileWriter o=new FileWriter("C:\\Users\\Anurag Gupta\\Downloads\\output.txt");
            for(int i=1;i<=16;i++)
            {
                o.write("\nQuestion - "+(i)+"\n\nCode\n\n");
                FileReader r=new FileReader("C:\\Users\\Anurag Gupta\\Downloads\\Class 12 Project\\Q"+i+".java");
                BufferedReader lr= new BufferedReader(r);
                String line="";
                while((line=lr.readLine())!=null)
                  o.write(line+"\n");
                lr.close();
                r.close();
            }
            o.close();
        }
        catch(Exception e){}
    }
}