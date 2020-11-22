package assembler;

public class MNT
{
    String macroName;
    int pp;
    int kp;
    int ev;
    int mdtp;
    int kdtp;
    int sstp;

    MNT()
    {
        macroName = "";
        pp = 0;
        kp = 0;
        ev = 0;
        mdtp = 0;
        kdtp = 0;
        sstp = 0;
    }

    public void setMacroName(String value)
    {
        this.macroName = value;
    }

    public String getMacroName()
    {
        return this.macroName;
    }

    public void setPP(int value)
    {
        this.pp += value;
    }

    public int getPP()
    {
        return this.pp;
    }

    public void setKP(int value)
    {
        this.kp += value;
    }
    public void setEV(int value)
    {
        this.ev += value;
    }
    public void setMDTP(int value)
    {
        this.mdtp += value;
    }
    public void setKDTP(int value)
    {
        this.kdtp += value;
    }
    public void setSSTP(int value)
    {
        this.sstp += value;
    }

    public int getKP(int value)
    {
        return this.kp;
    }
    public int getEV(int value)
    {
        return this.ev;
    }
    public int getMDTP(int value)
    {
        return this.mdtp;
    }
    public int getKDTP(int value)
    {
        return this.kdtp;
    }
    public int getSSTP(int value)
    {
        return this.sstp;
    }

}