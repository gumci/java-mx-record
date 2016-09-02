package CMD;

import java.awt.List;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class cmd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan;
		String domain;
		String result;
		String substring;
		Process p;
		InputStream is;
		InputStreamReader isr;
		BufferedReader br;
		
		List list = new List();
		
		
		
		try{
			scan = new Scanner(System.in);
			domain = scan.nextLine();
			
			p = new ProcessBuilder("cmd", "/c", "nslookup -query=mx "+domain+" 168.126.63.1").start();
			
			is = p.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			String line=null;
			
			while((line = br.readLine()) != null){
				System.out.println(line);
				if(line.contains("mail exchanger =")){
					result = line.substring(line.indexOf("mail exchanger")+17);
					System.out.println(result);
				}
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
