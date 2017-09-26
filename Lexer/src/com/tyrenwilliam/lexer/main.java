package com.tyrenwilliam.lexer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws IOException {
		System.out.println("Enter file name"); //take in user input
		System.out.println("Hint: example.txt");
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		String line = reader.nextLine();
		Lexer lex = new Lexer(line);	//passing file to lexer
	}

}
