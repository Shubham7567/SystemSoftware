import java.util.*;
import java.io.*;

class macropass1{
	public static String MDT_check(String word,ArrayList<String> SSNTAB,ArrayList<String> EVT,ArrayList<String> PNT,String mdt_word,int count,ArrayList<String> MODEL)
	{
	    if(SSNTAB.contains(word))
			mdt_word=mdt_word+"(S ," +(SSNTAB.indexOf(word)+1)+")";
		else if(PNT.contains(word.substring(1,word.length())))
			mdt_word=mdt_word+"(P ," +(PNT.indexOf(word.substring(1,word.length()))+1)+")";
		else if(EVT.contains(word.substring(1,word.length())))
			mdt_word=mdt_word+"(E ," +(EVT.indexOf(word.substring(1,word.length()))+1)+")";
		else
		{
		    if(count==1 && !MODEL.contains(word)){}
			else
			mdt_word=mdt_word+" "+word+" ";
			
		}
		return mdt_word;
	}
	public static void main(String args[])throws IOException{
		File f1=new File("Macro.txt");
		BufferedReader br_macro=new BufferedReader(new FileReader(f1));
		
		String line=null,word,mdt_word=null,prev_word=null,line1;
		
		int count=0,pos_count=0,key_count=0,ev_count=0,n,count_token=0,ssntab_count=0,line_no=1,mdt_ptr=1;
		
		ArrayList<String> MNT=new ArrayList<String>();
		ArrayList<String> PNT=new ArrayList<String>();
		ArrayList<String> KPD=new ArrayList<String>();
		ArrayList<String> EVT=new ArrayList<String>();
		ArrayList<String> MDT=new ArrayList<String>();
		ArrayList<String> Model=new ArrayList<String>();
		ArrayList<String> SSNTAB=new ArrayList<String>();
		ArrayList<Integer> SSTAB=new ArrayList<Integer>();
		
		File f2=new File("Models.txt");
		BufferedReader br=new BufferedReader(new FileReader(f2));
		
		while((line1=br.readLine())!=null)
		{
			Model.add(line1);
		}
		while((line=br_macro.readLine())!=null)
		{
			count_token=1;
			mdt_word=" ";
			StringTokenizer st1= new StringTokenizer(line);
			n=st1.countTokens();
			while(st1.hasMoreTokens())
			{
				word=st1.nextToken();
				if(word.equals("MACRO"))
				{
					count++;
					continue;
				}
				if(count==1)
				{
					MNT.add(word);
					while(count<n)
					{
						word=st1.nextToken();
					    if(word.contains("="))
						{
						   key_count++;
						   String[] str=word.split("=",2);
						   String key_par;
						   key_par=str[0].substring(1,str[0].length());
						   PNT.add(key_par);
						   KPD.add(key_par);
						   KPD.add(str[1]);
						}
					    else
						{
							String pos_par;
							pos_par=word.substring(1,word.length()-1);
							PNT.add(pos_par);
							pos_count++;
						}
					   count++;
					}
				}
				if(word.equals("LCL"))
				{
					mdt_word=mdt_word+word+" ";
					word=st1.nextToken();
					EVT.add(word.substring(1,word.length()));
					ev_count++;
					mdt_word=mdt_word+"(E ,"+ev_count+")";
				}
				if(count_token==2 && Model.contains(word))
				{
					ssntab_count++;
					SSNTAB.add(prev_word);
				}
				if(count_token==2 && SSNTAB.contains(prev_word))
				{
					int q=SSNTAB.indexOf(prev_word);
					SSTAB.add(mdt_ptr);
				}
				if(line_no>3 && !word.equals("LCL"))
				{
					mdt_word=MDT_check(word,SSNTAB,EVT,PNT,mdt_word,count_token,Model);
				}
				count_token++;
				prev_word=word;
			}
			if(!mdt_word.equals(" "))
			{
			MDT.add(mdt_word);
			mdt_ptr++;
			}
			line_no++;
		}
		MNT.add(Integer.toString(pos_count));
		MNT.add(Integer.toString(key_count));
		MNT.add(Integer.toString(ev_count));
		MNT.add("1");
		MNT.add("1");
		MNT.add("1");
		
	    System.out.println("\nMNT table: "+MNT);
		System.out.println("\nPNT table: "+PNT);
		System.out.println("\nKPD table: "+KPD);
		System.out.println("\nEV table: "+EVT);
		System.out.println("\nSSTAB table: "+SSTAB);
		System.out.println("\nSSNTAB table: "+SSNTAB);
		for(String s:MDT)
		{
			System.out.println(s);
		}
	}
}