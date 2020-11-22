class Parser{
    //global variable
    public static String[][] rules ={ {"E","TA"},{"A", "+E"}, {"T", "VB"}, {"B", "*T"}, {"V", "i"}};//static rules
    public static String[] operator = new String[2];//to store operators to compare with result
    public static int opPtr=0;//pointer of operator array
    
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
                        return "";
                    }
                    else if(operator[opPtr].equals(Character.toString(ch))){//if found one is operator and satify the rule for operator  
                        opPtr++;
                    }
                    else{
                        return null;
                    }
                }
                return rules[j][1];//if it doesn't contain any operator
            } 
            
        }
        return s1;//if it is not replacable
    }

    //to display the process of tracking
    public static void displayProcess(String[] steps,int dataPointer)
    {
        for(int index = 0;index < dataPointer; index++)
        {
            steps[index] = steps[index].contains("i")?steps[index].replace("i", "<id>"):steps[index];
            steps[index] = steps[index].contains("A")?steps[index].replace("A", "E\""):steps[index];
            steps[index] = steps[index].contains("B")?steps[index].replace("B", "T\""):steps[index];
            System.out.println((index +1)+". "+steps[index]);
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