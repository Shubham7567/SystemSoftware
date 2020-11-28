//import javax.lang.model.util.ElementScanner14;

class ParserLL {

    public static String[][] rules ={ {"E","TA"},{"A", "+TA"}, {"T", "VB"}, {"B", "*VB"}, {"V", "i"}};//static rules
    public static String[] operator = new String[10];//to store operators to compare with result
    public static int opPtr=0;//pointer of operator array
    public static String[] predictions = new String[30];
    public static String[] Symbols = new String[30];
    public static int predictionPtr = 0;
    public static int limit;//for 2 operators limit=1 so for n operators limit=n-1
    
    //to check rule for per character
    public static String checkRules(String s1)
    {
        
        for(int j=0;j<5;j++)
        { 
            if(s1.equals(rules[j][0])){

                char ch = rules[j][1].charAt(0);//to extract first character of resultant rule's string
                if((ch == '*' || ch == '+'))//to compare it with allowed sings/operators
                {
                    if(opPtr>1)//to limit the operator pointer
                    {
                        predictions[predictionPtr] = (s1 + " => " + "e");
                        predictionPtr++;
                        return "";
                    }
                    else if(operator[opPtr].equals(Character.toString(ch))){//if found one is operator and satify the rule for operator  
                        opPtr++;
                    }
                    else{
                        return null;
                    }
                }
                predictions[predictionPtr] = (s1 + " => " +rules[j][1]);
                predictionPtr++;
                return rules[j][1];//if it doesn't contain any operator
            } 
            
        }

        return s1;//if it is not replacable
    }

    public static void displayProcess(String[] steps,int dataPointer)
    {

        for(int index = 0;index < dataPointer; index++)
        {
            steps[index] = steps[index].contains("i")?steps[index].replace("i", "<id>"):steps[index];
            steps[index] = steps[index].contains("A")?steps[index].replace("A", "E\""):steps[index];
            steps[index] = steps[index].contains("B")?steps[index].replace("B", "T\""):steps[index];
            if(predictions[index] != null)
            {
                predictions[index] = predictions[index].contains("i")?predictions[index].replace("i", "<id>"):predictions[index];
                predictions[index] = predictions[index].contains("A")?predictions[index].replace("A", "E\""):predictions[index];
                predictions[index] = predictions[index].contains("B")?predictions[index].replace("B", "T\""):predictions[index];
                
            if((predictions[index].startsWith("E") && !predictions[index].startsWith("E\"")) || 
            (predictions[index].startsWith("T") && !predictions[index].startsWith("T\"")) || 
            (predictions[index].startsWith("V") && !predictions[index].startsWith("V\"")))
            {
                Symbols[index] = "<id>";
            }
            else if(predictions[index].contains("+"))
            {
                Symbols[index] = "+";
            }
            else if(predictions[index].contains("*"))
            {
                Symbols[index] = "*";
            }
            else{
                Symbols[index] = "-";
            }
            }
            //System.out.format("%1$20s | %1$15s | %1$10s",steps[index],Symbols[index],predictions[index]);
            System.out.format("%-20s | %-15s | %-10s",steps[index],Symbols[index],predictions[index]);
            System.out.println();
            //System.out.println((index +1)+". |-"+steps[index] + "-|"+ " \t\t| " + Symbols[index] + "\t\t|" + predictions[index]);
        }
    }

    public static void makeOperator(String inputStr)
    {
        int cnt=0;
        for(int i=0;i<inputStr.length();i++){
            
            int ch = inputStr.charAt(i);
           
            if(ch==42 ||  ch==43 ){   // * or +
               
                if(ch==42)
                    operator[cnt] ="*";
                else
                    operator[cnt] ="+";
                cnt++;
            }
        }
        limit = cnt-1;
     
    } 

    public static void main(String[] args) {
        //to set the operators
        //operator[0] = "+";
       // operator[1] = "*";     
        makeOperator("A+B*C+D*F"); 
        int dataPointer = 0;//pointer to the array of data
        String[] data =new String[30];
        data[0] = "E";//to set the initial equation
        Symbols[0]="<id>";
        dataPointer++;
        //a+b*c
        
        for(;data[dataPointer-1].charAt(data[dataPointer-1].length()-1)!='i';dataPointer++)
        {
            String temp = data[dataPointer-1];
            int len = temp.length();
            String result;
            String res="";
            int k=0;
            while(k<len)
            {
                char ch = temp.charAt(k);
                if((result =checkRules(Character.toString(ch)))!=null){
                    res+=result;
                    if(!result.equals(Character.toString(ch)))
                    {
                        break;
                    }
                }
                
                k++;                
            }
            
            data[dataPointer] =  res + temp.substring(k+1,temp.length());  
            // if(predictions[predictionPtr].contains("E") || predictions[predictionPtr].contains("T") || predictions[predictionPtr].contains("V"))
            // {
            //     Symbols[predictionPtr] = "<id>";
            // }                                   //E"                              //T"
            // else if((predictions[dataPointer].contains("A") || predictions[dataPointer].contains("B")) && predictions[dataPointer].contains("+"))
            // {
            //     Symbols[dataPointer] = "+";
            // }
            // else if((predictions[dataPointer].contains("B")) && predictions[dataPointer].contains("*"))
            // {
            //     Symbols[dataPointer] = "*";
            // }
            // else if((predictions[dataPointer].contains("A") || predictions[dataPointer].contains("B")) && opPtr>1)
            // {
            //     Symbols[dataPointer] = "e";
            // }

        }
        displayProcess(data,dataPointer);
    }
}