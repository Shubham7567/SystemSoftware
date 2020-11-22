class ParserLL {

    public static String[][] rules ={ {"E","TA"},{"A", "+TA"}, {"T", "VB"}, {"B", "*VB"}, {"V", "i"}};//static rules
    public static String[] operator = new String[3];//to store operators to compare with result
    public static int opPtr=0;//pointer of operator array
    public static String[] predictions = new String[30];
    public static int predictionPtr = 0;
    
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
            }
            System.out.println((index +1)+". |-"+steps[index] + "-|"+ " \t\t| " + predictions[index]);
        }
    }
    public static void main(String[] args) {
        //to set the operators
        operator[0] = "+";
        operator[1] = "*";     

        int dataPointer = 0;//pointer to the array of data
        String[] data =new String[30];
        data[0] = "E";//to set the initial equation

        dataPointer++;
        //a+b*c
        for(;!(data[dataPointer-1].contains("+") && data[dataPointer-1].contains("*") && data[dataPointer-1].length() == 5);dataPointer++)
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
            
        }
        displayProcess(data,dataPointer);
    }
}