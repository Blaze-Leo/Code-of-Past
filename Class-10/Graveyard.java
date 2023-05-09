import java.util.*;
public class Graveyard
{
    public static void main(String[] args)
    {
      Graveyard gg = new Graveyard();

 

      String country[] = {"United Arab Emirates", "Nigeria", "Ghana", "Pitcairn Islands", "Ethiopia", "Algeria",
                          "Niue", "Jordan", "Netherlands", "Andorra", "Turkey", "Madagascar", "Samoa", "Turkmenistan", "Eritrea",
                          "Kazakhstan", "Paraguay", "Greece", "Cook Islands", "Iraq", "Azerbaijan", "Mali", "Brunei",
                          "Thailand", "Central African Republic", "Gambia", "Saint Kitts and Nevis", "China",
                          "Lebanon", "Serbia", "Belize", "Germany", "Switzerland", "Kyrgyzstan",
                          "Guinea-Bissau", "Colombia", "Brazil", "Slovakia", "Republic of the Congo", "Barbados", "Belgium"
                         };
      String capital[] = {"Abu Dhabi", "Abuja", "Accra", "Adamstown", "Addis Ababa", "Algiers", "Alofi",
                          "Amman", "Amsterdam", "Andorra la Vella", "Ankara", "Antananarivo", "Apia", "Ashgabat", "Asmara",
                          "Astana", "Asunción", "Athens", "Avarua", "Baghdad", "Baku", "Bamako", "Bandar Seri Begawan", "Bangkok",
                          "Bangui", "Banjul", "Basseterre", "Beijing", "Beirut", "Belgrade", "Belmopan", "Berlin", "Bern",
                          "Bishkek", "Bissau", "Bogotá", "Brasília", "Bratislava", "Brazzaville", "Bridgetown", "Brussels"
                         };
      int l = country.length;
      int score = 0;
      String capitalEntry = " ";

 

      do {
        int r;
        r = (int)(Math.random() * ((l - 1) - 0 + 1) + 0);
        System.out.println("What is the capital of " + country[r]);
        capitalEntry = gg.oh_yeah();
        if (capitalEntry.compareTo(capital[r]) == 0)
        {
          System.out.println("Matched");
          score++;
        }
      } while (capitalEntry.compareTo("No") != 0);
      System.out.println("Your Score is " + score);
    }

 

 

    String oh_yeah()
    {
      long start=System.currentTimeMillis(),end=System.currentTimeMillis();
      String s = " ";
      Scanner sc = new Scanner(System.in);
      while(end-start <= 10){end=System.currentTimeMillis();if(sc.hasNext()){s=sc.nextLine();break;}}
      return s;
    }

 

}