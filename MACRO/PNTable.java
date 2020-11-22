package assembler;

public class PNTable
{
    String PNTAB[] = new String[20];
    int PNTAB_ptr=0;

    PNTable()
    {
        PNTAB_ptr = 0;
    }

    public Boolean isParameter(String param)
    {
        if(param.indexOf("&") != -1)
        {
            return true;
        }
        return false;
    }

    public void setParameter(String param)
    {
        PNTAB[PNTAB_ptr] = param;
        PNTAB_ptr++;
    }

    public Boolean checkExistParameter(String param)
    {
        int jindex = 0;
        for(jindex = 0; jindex < PNTAB_ptr; jindex++)
        {
            if(PNTAB[jindex] == param)
            {
                return true;
            }
        }
        return false;
    }
}