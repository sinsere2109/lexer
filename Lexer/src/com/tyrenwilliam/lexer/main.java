package com.tyrenwilliam.lexer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class main {

	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String everything = null;
		BufferedReader br = new BufferedReader(new FileReader("a2-sample-2.txt"));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    everything = sb.toString();
		    
		} finally {
		    br.close();
		}
		System.out.println(everything);
		Lexer lex = new Lexer(everything);
		
	}

}
