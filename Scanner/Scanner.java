import java.io.*;

class Scanner
{
    public static void main(String[] args) throws IOException
    {
        File scannerFile = new File("ScannerFile.txt");//to take the ScannerFile.txt
        BufferedReader br_macro = new BufferedReader(new FileReader(scannerFile));//to read the ScannerFile.txt
        String line;
        boolean flag = true;
        while((line = br_macro.readLine()) != null)
        {
            if(line.contains("//") && line.indexOf("/") == 0)
            {
                flag = true;
            }
            else if(line.contains("/*") && line.indexOf("*") == 1 && line.contains("*/") && (line.indexOf("*/") == (line.length() - 2)))
            {
                flag = true;
            }
            else
            {
                flag = false;
            }
            if(flag)
            {
                System.out.println("Valid String : "+line);
            }
            else{
                System.out.println("Invalid String : "+line);
            }
        }
    }
}