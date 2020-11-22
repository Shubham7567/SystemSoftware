//package assembler;
import assembler.*;

public class MNTable
{
    MNT mnt1[] = new MNT[10];
    int MNT_ptr;

    MNTable()
    {
        this.MNT_ptr = 0;
    }

    public void generateMNT(String macroName)
    {
        this.mnt1[MNT_ptr] = new MNT();
        this.mnt1[MNT_ptr].setMacroName(macroName);
    }

    public void printMNT()
    {
        for(int index=0;index < this.MNT_ptr; index++)
        {
            System.out.println("MNT - [" + mnt1.getMacroName() + ", " + mnt1.getPP() + ", " + mnt1.getKP() + ", " + mnt1.getEV() + ", " + mnt1.getMDTP + ", " + mnt1.getKDTP() + ", " + mnt1.getSSTP() + "]");
        }
    }
}