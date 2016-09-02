package CMD;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class cmd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runtime rt;
		Scanner scan;
		String domain;
		String result;
		Process p;
		InputStream is;
		InputStreamReader isr;
		BufferedReader br;
		try{
			rt = Runtime.getRuntime();
			scan = new Scanner(System.in);
			domain = scan.nextLine();
			
			p = new ProcessBuilder("cmd", "/c", "nslookup -query=mx "+domain+" 168.126.63.1").start();
			
			is = p.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			String line=null;
			
			while((line = br.readLine()) != null){
				System.out.println(line);
				if(line.contains("mail exchanger")){
					result = line;
					System.out.println(result);
				}
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
