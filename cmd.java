package CMD;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class cmd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i;
		
		Scanner scan;
		String domain;
		String result;
		Process p;
		InputStream is;
		InputStreamReader isr;
		BufferedReader br;
		
		ArrayList list = new ArrayList();
		
		
		try{
			scan = new Scanner(System.in);
			domain = scan.nextLine();
			
			p = new ProcessBuilder("cmd", "/c", "nslookup -query=mx "+domain+" 168.126.63.1").start();
			
			is = p.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			String line=null;
			
			while((line = br.readLine()) != null){
				if(line.contains("mail exchanger =")){
					result = line.substring(line.indexOf("mail exchanger")+17);
					System.out.println(result);
					list.add(result);
				}
			}
			
			line=null;
			for (i=0; i<list.size(); i++){
				p = new ProcessBuilder("cmd", "/c", "nslookup -query=a "+list.get(i)+" 168.126.63.1").start();
				is = p.getInputStream();
				isr = new InputStreamReader(is);
				br = new BufferedReader(isr);
				while((line = br.readLine()) !=null ){
					System.out.println(line);
				}
			}
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
