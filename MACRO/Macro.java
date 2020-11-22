package assembler;

import java.util.*;
import java.io.*;

public class Macro
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("Macro.txt");
        BufferedReader macroReader = new BufferedReader(new FileReader(file));

        ArrayList<String> models = new ArrayList<String>();
        String line=null,word,line1;
        MNTable mnt = new MNTable();

        File modelFile = new File("Models.txt");
        BufferedReader modelReader = new BufferedReader(new FileReader(modelFile));

        int count = 0;

        while((line1 = modelReader.readLine()) != null)
        {
            models.add(line1);
            System.out.println(line1);
        }

        while((line = macroReader.readLine()) != null)
        {
            StringTokenizer st = new StringTokenizer(line);
            while(st.hasMoreTokens())
            {
                word = st.nextToken();
                if(word.equals("MACRO"))
                {
                    count++;
                    continue;
                }
                if(count == 1)
                {
                    mnt.generateMNT(word);
                    count++;
                    mnt.printMNT();
                }
            }
        }
    }
}