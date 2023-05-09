import java.util.*;
class Flood
{
   int box_count =16;
   int flood[][];
   byte global[][];
   void refill()
{
  flood =new int[box_count+2][box_count+2];
  global =new byte[box_count][box_count];
  for(int i=0;i<box_count+2;i++)
    {for(int j=0;j<box_count+2;j++)
      flood[i][j]=-1;}
  for(int i=0;i<box_count;i++)
  {for(int j=0;j<box_count;j++)
    {global[i][j]=0;

    if(i==0)
    global [i][j]=8;

    if(i==box_count-1)
    global [i][j]=1;

    if(j==0)
    global [i][j]=2;

    if(j==box_count-1)
    global [i][j]=4;


    if(i==0 && j==0)
    global[i][j]=10;
    if(i==0 && j==box_count-1)
    global[i][j]=12;
    if(i==box_count-1 && j==0)
    global[i][j]=3;
    if(i==box_count-1 && j==box_count-1)
    global[i][j]=5;
    }
  }
/*////////////////////////////////
  global [box_count-1][1]=15;
   global [box_count-1][0]=7;
   global [box_count-1][2]=3;
   global [box_count-2][1]=1;
 *//////////////////////////////
 add_wall(box_count-1,1,'N');add_wall(box_count-1,1,'E');add_wall(box_count-1,1,'W');add_wall(box_count-1,1,'S');
 add_wall(box_count-1,2,'N');add_wall(box_count-1,2,'E');add_wall(box_count-1,2,'W');add_wall(box_count-1,2,'S');
 add_wall(box_count-1,0,'W');add_wall(box_count-1,0,'E');
 add_wall(box_count-1,3,'W');
 add_wall(box_count-2,1,'S');
 add_wall(box_count-2,2,'S');

  flood[box_count/2][box_count/2]=0;
  flood[1+box_count/2][box_count/2]=0;
  flood[box_count/2][1+box_count/2]=0;
  flood[1+box_count/2][1+box_count/2]=0;

  for(int i=0;flood[1][box_count]==-1;i++)
  refill_cont(i);

  for(int i=0;i<box_count+2;i++)
  {flood[i][0]=-1;flood[0][i]=-1;}
  for(int i=0;i<box_count+2;i++)
  {flood[i][box_count+1]=-1;flood[box_count+1][i]=-1;}

  for(int i=0;i<box_count+2;i++)
    {for(int j=0;j<box_count+2;j++)
      {System.out.print(flood[i][j]+"");
    if(flood[i][j]<10 && flood[i][j]!=-1)
    System.out.print(" ");
  System.out.print("|  ");
  }
    //System.out.println("\n---------------------------------------------------------------------");
      System.out.println();}
      
}
void add_wall(int i, int j, char w)
{
  byte m=global[i][j];
  boolean wall[]={false,false,false,false};

  if (m - 8 >= 0)
  {m -= 8;wall[0]=true;}
  if (m - 4 >= 0)
  {m -= 4;wall[1]=true;}
  if (m - 2 >= 0)
  {m -= 2;wall[2]=true;}
  if (m - 1 >= 0)
  {wall[3]=true;}

  switch(w)
  {
    case 'N':wall[0]=true;break;
    case 'E':wall[1]=true;break;
    case 'W':wall[2]=true;break;
    case 'S':wall[3]=true;break;
  }
  
  int s=0;

  if(wall[0])
    s+=8;
  if(wall[1])
    s+=4;
  if(wall[2])
    s+=2;
  if(wall[3])
    s+=1;

  global[i][j]=(byte)(s);
}
 void refill_cont(int n)
{
  for (int i = 1; i < box_count+1; i++)
  {
    for (int j = 1; j < box_count+1; j++)
    {
      if (flood[i][j] == n)
      {
        byte m=global[i-1][j-1];
        m=(byte)(15-m);
        if (m - 8 >= 0)
         {
           m -= 8;
           flood[i-1][j]=flood[i-1][j]==-1?n+1:flood[i-1][j];
         }
         if (m - 4 >= 0)
         {
           m -= 4;
           flood[i][j+1]=flood[i][j+1]==-1?n+1:flood[i][j+1];
         }
         if (m - 2 >= 0)
         {
           m -= 2;
           flood[i][j-1]=flood[i][j-1]==-1?n+1:flood[i][j-1];
         } 
         if (m - 1 >= 0)
         {
           flood[i+1][j]=flood[i+1][j]==-1?n+1:flood[i+1][j];
           
         }
      }
    }
  }
}


    public static void main(String args[])
    {
        Flood ss=new Flood();
        ss.refill();
    }
}