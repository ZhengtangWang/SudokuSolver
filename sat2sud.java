import java.util.*;
import java.io.*;

public class sat2sud{
	public static void main(String[] args) throws IOException{
		Scanner console;
		try{		
			if (args.length > 0){
				console = new Scanner(new File(args[0]));
			}else{
				System.out.println("Please enter a file name");
				console = new Scanner(System.in);
				File f=new File(console.next());
			}	
		}catch(java.io.FileNotFoundException e){
				System.out.println("Cannot find file");
				return;
		}
			
		int a = 9;
		int [] arr=new int[a*a*a];
		int num=0;		
		String s;
		String [] arr1=new String[arr.length];
		int count;
		String st=console.next();
		
		if(st.contains("SAT")){
			console.nextLine();
		}

		while(console.hasNext()){


			try{	
				
				count=Integer.parseInt(console.next());
				if(count!=0){
					arr [num] = count;
				}
				num++;
			}catch(NumberFormatException e){
				System.out.println("Error");
				return;
			}

		}
	
		File outputFile=new File("output.txt");
		try{
			PrintStream output=new PrintStream(outputFile);
			for(int i=0; i<a; i++){
				s = " ";
				for(int j=0; j<a; j++){
					for(int k=0; k<9;k++){
						if(arr[i*81+j*9+k]>=0){
							s+= (k+1);
							break;
						}					
					}
				}
			output.println(s);
			//output.println(" ");			
			}
		System.out.println("Finished, the values are stored in "+outputFile+ "" );
		} catch(java.io.FileNotFoundException e){
			System.out.println("Cannot find file");
			return;
		}
	}
}
