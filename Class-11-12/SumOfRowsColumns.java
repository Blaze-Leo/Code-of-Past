public class SumOfRowsColumns{
    int a[][], i, j;
    int col[], row[];
    //constructor
    SumOfRowsColumns(){
        a=new int[][]{{3,4,5},{4,6,8},{5,9,10},{7,2,1}};
    }
    
    void displayArray(){
        for(i=0;i<4;i++){
            for(j=0;j<3;j++){
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
    }
    
    //sum of rows
    int[] sumOfRows(){
        row=new int[4];
        for(i=0;i<4;i++){
            for(j=0;j<3;j++){
                row[i]=row[i]+a[i][j];
            }
        }
        return row;
    }
    //sum of cols
    int[] sumOfCols(){
        col=new int[3];
        for(i=0;i<3;i++){
            for(j=0;j<4;j++){
                col[i]=col[i]+a[j][i];
            }
        }
        return col;
    }
    
    //display the sum
    void displaySum(){
        System.out.println("Sum of Rows");
        for(i=0;i<4;i++)
        System.out.print(row[i]+" ");
        System.out.println("\nSum of Cols");
        for(i=0;i<3;i++)
        System.out.print(col[i]+" ");

 

    }
    void display()
    {
        row=sumOfRows();
        col=sumOfCols();
        for(i=0;i<4;i++){
            for(j=0;j<3;j++){
                System.out.print(a[i][j]+"   ");
            }
            System.out.println(" = "+row[i]);
        }
        for(int i = 0;i<3;i++)
          System.out.print("||  ");
        System.out.println();
        for(int i = 0;i<3;i++)
        System.out.print(col[i]+"  ");

    }
 

    public static void main(String args[]){
        SumOfRowsColumns s=new SumOfRowsColumns();
        s.display();
    }
}