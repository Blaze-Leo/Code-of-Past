import java.util.*;
import java.io.*;
class File_Copier
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter File name : ");
        File fo=new File(sc.next());
        System.out.println("Enter name of new file :");
        File fn=new File(sc.next());
        try
        {
            FileWriter fw=new FileWriter(fn);
            FileReader fr=new FileReader(fo);
            BufferedReader br=new BufferedReader(fr);
            String line;
            while((line=br.readLine())!=null)
              fw.write(line+"\n");
            fr.close();
            fw.close();
            sc.close();
            //if(fo.delete()){}
        }
        catch(IOException e){}
    }
}