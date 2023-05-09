import java.util.*;
class Square
{
    public static void main (String args[])
    {
      int a[][] = new int[3][3];
      boolean s = false;
      do
      {
        s = true;
        for (int i = 0; i < 3; i++)
        {
          for (int j = 0; j < 3; j++)
            a[i][j] = (int)(Math.random() * 10);
        }
        String r1, r2, r3, c1, c2, c3;
        r1 = "" + a[0][0] + a[1][0] + a[2][0];
        r2 = "" + a[0][1] + a[1][1] + a[2][1];
        r3 = "" + a[0][2] + a[1][2] + a[2][2];
        c1 = "" + a[0][0] + a[0][1] + a[0][2];
        c2 = "" + a[1][0] + a[1][1] + a[1][2];
        c3 = "" + a[2][0] + a[2][1] + a[2][2];
        if (r1.equals(r2) || r1.equals(r3) || r3.equals(r2) || c1.equals(c2) || c1.equals(c3) || c3.equals(c2))
          s = s && false;
        int r = 0, c = 0, p = a[0][0] + a[0][1] + a[0][2];
        for (int i = 0; i < 3; i++)
        {
          c = 0;
          for (int j = 0; j < 3; j++)
          {
            c += a[i][j];
          }
          if (c != p)
            s = s && false;
        }
        for (int i = 0; i < 3; i++) {
          r = 0;
          for (int j = 0; j < 3; j++) {
            r += a[j][i];
          }
          if (r != p)
            s = s && false;
        } if (a[0][0] + a[1][1] + a[2][2] != p)
          s = s && false;
        if (a[0][2] + a[1][1] + a[2][0] != p)
          s = s && false;
      } while (!s);
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++)
          System.out.print(a[i][j]);
        System.out.println();
      }
    }
}