package pass1;

import java.util.*;
import java.io.*;

public class MacroPass1
{

    static ArrayList<String> models = new ArrayList<String>();
    static MNT mnt = new MNT();
    static ArrayList<KPDTAB> kpdt = new ArrayList<KPDTAB>();
    static ArrayList<String> PNTAB = new ArrayList<String>();
    static ArrayList<String> EVNTAB = new ArrayList<String>();
    static ArrayList<String> SSNTAB = new ArrayList<String>();
    static ArrayList<String> SSTAB = new ArrayList<String>();
    static ArrayList<String> APTAB = new ArrayList<String>();
    static ArrayList<String> EVTAB = new ArrayList<String>();
    static ArrayList<String> MDT = new ArrayList<String>();

    public static void UpdateMNT(String field)
    {
        if(field.equals("pp"))
        {
            mnt.setpp();
        }
        if(field.equals("kp"))
        {
            mnt.setkp();
        }
        if(field.equals("ev"))
        {
            mnt.setev();
        }
        if(field.equals("mdtp"))
        {
            mnt.setmdtp();
        }
        if(field.equals("kdtp"))
        {
            mnt.setkdtp();
        }
        if(field.equals("sstp"))
        {
            mnt.setsstp();
        }
    }

    public static void main(String[] args) throws IOException
    {
        File macroFile = new File("Macro.txt");//to take the macro.txt
        BufferedReader br_macro = new BufferedReader(new FileReader(macroFile));//to read the Macro.txt
        File modelFile = new File("Models.txt");//for models like ADD SUB LCL etc.
        BufferedReader br_models = new BufferedReader(new FileReader(modelFile));//to read the Models.txt

        String line,word,mdt_word=" ",prevWord="";
        int count=0,n,mntPointer = 0,SSNTAB_ptr = 0,PNTAB_ptr = 0,EVNTAB_ptr = 0,SSTAB_ptr = 0,APTAB_ptr = 0,EVTAB_ptr = 0,KPDTAB_ptr=0;
        int countToken=0,MDT_ptr=0,lineNo=0;

        while((line = br_models.readLine()) != null)
        {
            models.add(line);
        }

        while((line = br_macro.readLine()) != null)
        {
            StringTokenizer sToknizer = new StringTokenizer(line);
            n = sToknizer.countTokens();
            mdt_word = "";
            System.out.println(n);
            while(sToknizer.hasMoreTokens())
            {
                word = sToknizer.nextToken();
                word = word.replace(",","");
                if(word.equals("(") || word.equals(")") || word.equals("MACRO"))
                {
                    count++;
                    continue;
                    //skip
                }
                if(count == 1)
                {
                    mnt.setName(word);
                    
                    mntPointer++;
                    count++;
                    while(count <= n && sToknizer.hasMoreTokens())
                    {
                        word = sToknizer.nextToken();
                        word = word.replace(",","");

                        if((word.indexOf("&")) != -1)
                        {
                            if(word.contains("="))
                            {
                                String KPD = word.replace("="," ");
                                KPD = KPD.replace("&","");
                                String param = KPD.substring(0,(KPD.indexOf(" ")-1));
                                String defaultValue = KPD.substring(KPD.indexOf(" "));
                                KPDTAB kpdtrow = new KPDTAB();
                                kpdtrow.setParam(param);
                                kpdtrow.setDefaultValue(defaultValue);
                                kpdt.add(kpdtrow);
                                PNTAB.add(param);
                                KPDTAB_ptr++;
                                UpdateMNT("kp");
                            }
                            else
                            {
                                System.out.println(word);
                                word = word.replace("&","");
                                PNTAB.add(word);
                            }
                            UpdateMNT("pp");
                            PNTAB_ptr++;
                            count++;
                        }
                    }
                }   
                if(word.equals("LCL") && sToknizer.hasMoreTokens())
                {
                    
                    mdt_word = word + " ";
                    word = sToknizer.nextToken();
                    word = word.replace("&","");
                    EVNTAB.add(word);
                    EVNTAB_ptr++;
                    UpdateMNT("ev");
                    mdt_word += "(E ," + mnt.getev() + ")";
                    MDT_ptr++;
                }
                if(countToken == 2 && models.contains(word))
                {
                    SSNTAB.add(prevWord);
                    SSNTAB_ptr++;
                }
                if(countToken == 2 && SSNTAB.contains(word))
                {
                    int q = SSNTAB.indexOf(prevWord);
                    SSNTAB.add(Integer.toString(MDT_ptr));
                }
                if(lineNo > 2 && !word.equals("LCL"))
                {
                    word = word.replace("&","");
                    mdt_word = MDT_check(word,countToken,mdt_word);
                }
                countToken++;
                prevWord = word;

            }
            System.out.println(lineNo);
            if(!mdt_word.equals(" "))
            {
                MDT.add(mdt_word);
                MDT_ptr++;
            }
            lineNo++;
        }

        //System.out.println("MNT[" + macro.getName() + "," + macro.getpp() + "," + macro.getkp() + "]");
        System.out.println("MNT Table: [" + mnt.getName() + " , " + mnt.getpp() + " , " + mnt.getkp() + " , " + mnt.getev() + " , " + MDT_ptr + " , " +
         KPDTAB_ptr + " , " + SSTAB_ptr + " ]");

        System.out.println("\nPNT Table: " + PNTAB);
        count = 0;
        for(KPDTAB kpdrow : kpdt)
        {
            if(count == KPDTAB_ptr)
            {
                break;
            }
            System.out.println("KPDTAB " + count + " [" + kpdrow.getParam() + "," + kpdrow.getDefaultValue() + "]");
            count++;
        }
        System.out.println("\nEVNT Table: " + EVNTAB);
        System.out.println("\nSSTAB Table: " + SSTAB);
        System.out.println("\nSSNTAB Table: " + SSNTAB);

        for(String s:MDT)
        {
            System.out.println(s);
        }
    }

    public static String MDT_check(String word, int count, String mdt_word)
    {
        
        if(SSNTAB.contains(word))
        {
            mdt_word = "(S ," + (SSNTAB.indexOf(word) + 1) +")";
        }
        else if(PNTAB.contains(word))
        {
            mdt_word = "(P ," + (PNTAB.indexOf(word) + 1) + ")";
        }
        else if(EVNTAB.contains(word))
        {
            mdt_word = "(E ," + (EVNTAB.indexOf(word) + 1) + ")";
        }
        else{
            if(count == 1 && models.contains(word)){}
            else mdt_word = mdt_word + " " + word + " ";
        }
        return mdt_word;
    }
}