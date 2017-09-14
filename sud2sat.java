import java.io.*;
import java.util.*;

public class sud2sat{

	public static void main(String[] args){
		Scanner s = new Scanner("");
		File input;
		try{
			if(args.length > 0){
				input = new File(args[0]);
				s = new Scanner(input);
			}else{
				System.out.println("Please enter file name! ");
				return;
			}
		}catch(FileNotFoundException e){
				System.out.println("File Not Found!");
				return;
		}
		
		int[][] cnf = new int[9][9];
		
		File outputTemp = new File("outputTemp.txt");
		File outputDec = new File("outputDec.txt");
		File inputSAT = new File("inputSAT.txt");
		int clauses = 0;
		try{
			PrintStream printDec = new PrintStream(outputDec);
			Scanner sd  = new Scanner(outputDec);
			PrintStream printer = new PrintStream(outputTemp);
			PrintStream printSAT = new PrintStream(inputSAT);
		   	 printSAT.print("p cnf 729 ");
			//printDec.print("p cnf 729 ")
			String current = s.nextLine();
	        	Scanner s1 = new Scanner(outputTemp);
			String val;
			//read values in input, convert all illegal values
			int ss = 0;
		        while(ss < 9){
		            if(current.contains("G")){
		                current = s.nextLine();
		            }
		            
			        for(int j = 0; j < 9; j++) {
				        char num = current.charAt(j);
					String numS = num + "";
				        if(numS=="."||numS=="?"||numS=="*"||numS=="0"){
					        numS="0";
				        }
				        printer.print(numS);
				        printer.println();
					int k = Integer.parseInt(numS);
					//convert non-zero values into decimal
				        if(k != 0){
						int decimalNum = 81*ss + 9*j + (k-1) + 1;
						printDec.print(decimalNum+ " ");
						printDec.print("0");
				           	printDec.println();
				        
				        }
			        }   
			        current = s.nextLine();
				ss++;
		        }
			//Every cell contains at least one number
			//xijk
		    	for(int i=0;i<9;i++){
				for(int j = 0; j < 9; j++){
					for(int k = 1; k < 10; k++){
						int decimal = 81 * i + 9 * j + (k-1) + 1;
						printDec.print(decimal+ " ");
				    	}   
				printDec.print("0");
				printDec.println();
			    }
		    	}
			// Each number appears at most once in every column
			//(¬xijk∨¬xjk)
		    	for(int i = 0; i < 9; i++) {			
			    for(int k = 1; k < 10; k++) {
				    for(int j=0;j<8;j++){
					    for(int l=j+1;l<9;l++){
						    int n1 = 81 * i + 9 * j + (k-1) + 1;
						    int n2 = 81 * i + 9 * l + (k-1) + 1;
						    printDec.print("-" + n1 + " -" + n2 + " 0");
						    printDec.println();
					    }
				    }
			    }
		    }		
			//Each number appears at most once in every row 
			//(¬xijk∨¬xi`k)
		    	for(int j = 0; j < 9; j++) {
			    for(int k = 1; k < 10; k++) {
				    for(int i=0;i<8;i++){
					    for(int l=i+1;l<9;l++){
						    int num1 = 81 * i + 9 * j + (k-1) + 1;
						    int num2 = 81 * l + 9 * j + (k-1) + 1;
						    printDec.print("-" + num1 + " -" + num2+ " 0");
						    printDec.println();
					    }
				    }
			    }
		    	}

		
			// Each number appears at most one in every 3x3 sub-grid
			//(¬x(3a+u)(3b+v)k∨¬x(3a+u)(3b+w)k)
			//(¬x(3a+u)(3b+v)k∨¬x(3a+w)(3b+t)k)

		    	for(int k = 1; k < 10; k++) {
			    for(int a = 0; a < 3; a++){
				    for(int b = 0; b < 3; b++){
					    for(int u = 0; u < 3; u++){
						    for(int v=0; v<3; v++){
							    for(int w = v+1; w < 3; w++){
							        int n1 = 81*(a*3+u) + 9 * (b*3+v) + (k-1) + 1;
						            int n2 = 81*(a*3+u) + 9 * (b*3+w) + (k-1) + 1;
								    printDec.print("-" +n1 + " -" + n2 + " 0");
						            printDec.println();
							    }
							    for(int w = u+1; w < 3; w++){
								    for(int t = 0; t < 3; t++){
								        int n1 = 81*(a*3+u) + 9 * (b*3+v) + (k-1) + 1;
						                int n2 = 81*(a*3+w) + 9 * (b*3+t) + (k-1) + 1;
								        printDec.print("-" + n1 + " -" + n2 + " 0");
						                printDec.println();
								    }
							    }
						    }
					    }
				    }
			    }
		    }
		//copy outputDec into inputSAT
		    while(sd.hasNextLine()){
		            clauses ++;
		            sd.nextLine();
		    }
		    printSAT.print(clauses);
		    printSAT.println();
		    Scanner s2 = new Scanner(outputDec);
		    while(s2.hasNextLine()){
		        printSAT.println(s2.nextLine());  
		    }
		}catch(FileNotFoundException e){
			System.out.println("File Not Found!");
			return;
		}
	}
}
