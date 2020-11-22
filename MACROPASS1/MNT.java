package pass1;

public class MNT
{
    String name;
    int pp;
    int kp;
    int ev;
    int mdtp;
    int kdtp;
    int sstp;

    MNT()
    {
        this.pp = 0;
        this.kp = 0;
        this.ev = 0;
        this.mdtp = 0;
        this.kdtp = 0;
        this.sstp = 0;
    }

    public void setName(String value)
    {
        this.name = value;
    }

    public String getName()
    {
        return this.name;
    }

    public int getpp()
    {
        return this.pp;
    }

    public int getkp()
    {
        return this.kp;
    }

    public int getev()
    {
        return this.ev;
    }

    public int getmdtp()
    {
        return this.mdtp;
    }

    public int getkdtp()
    {
        return this.kdtp;
    }

    public int getsstp()
    {
        return this.sstp;
    }

    public void setpp()
    {
        this.pp += 1;
    }

    public void setkp()
    {
        this.kp += 1;
    }

    public void setev()
    {
        this.ev += 1;
    }

    public void setmdtp()
    {
        this.mdtp += 1;
    }

    public void setkdtp()
    {
        this.kdtp += 1;
    }

    public void setsstp()
    {
        this.sstp += 1;
    }
}