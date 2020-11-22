package pass1;

public class KPDTAB
{
    String param;
    String defautlValue;

    KPDTAB()
    {
        this.param = "";
        this.defautlValue = "";
    }

    public void setParam(String value)
    {
        this.param = value;
    }

    public void setDefaultValue(String value)
    {
        this.defautlValue = value;
    }

    public String getParam()
    {
        return this.param;
    }

    public String getDefaultValue()
    {
        return this.defautlValue;
    }
}