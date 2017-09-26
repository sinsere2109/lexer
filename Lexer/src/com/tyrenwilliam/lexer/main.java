package com.tyrenwilliam.lexer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Enter file name");
		System.out.println("Hint: example.txt");
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		String line = reader.nextLine();
		Lexer lex = new Lexer(line);	
	}

}
