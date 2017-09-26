package com.tyrenwilliam.lexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Lexer {

	static int idNum;	//token ID  number, ie 10 is "prog"
	static int lineCount = 1;	//line variable to keep track of lines
	static String type;	//type for int, identifier, float, or string.
	static String token;	//token for specific types of tokens, such as print and *
	static String str = "";	//string to concat
	static PrintWriter writer = null;	//writer to create output file

	public Lexer(String fileName) throws IOException {

		writer = new PrintWriter("output" + fileName, "UTF-8");	//output file
		FileReader file = new FileReader(new File(fileName));	//read input file
		BufferedReader read = new BufferedReader(file);
		String s = null;
		while ((s = read.readLine()) != null) {	//iterate through file
			for (int i = 0; i < s.length() - 1; ++i) {
				char char1 = s.charAt(i);		
				char char2 = s.charAt(i + 1);	//keeps track of one character ahead

				if (char1 == ' ') {	//if whitespace, iterate to next char.
					continue;
				}

				// String test
				if (char1 == '"') {	//strings begin with "

					++i;
					char1 = s.charAt(i);
					char2 = s.charAt(i + 1);
					while (!(char1 == '"')) {	//while next character is not ", which ends a string, continue to concatenate string.

						char1 = s.charAt(i);//iterate 
						char2 = s.charAt(i + 1);
						if (char1 == '"')//return to beginning of while loop if the character is a "
							continue;
						str += char1;
						++i;
					}
					token = str;	//set contents of string to token content
					type = "string";	//set type.
					setTokenId();	//set ID of token.
					print();	//output
					reset();	//empty out temporary variables for next use.
				}

				// Multi char test
				if (isMultiCharOperator(char1, char2)) {

					token = Character.toString(char1);	//gets first character in string
					token += char2;	//concatenates second character to first
					type = "multicharoperator";	//sets type for token
					setTokenId();	//sets token ID based on token/type declared
					print();	//print to output/console
					++i;	//iterate to next character
					reset();	//sets token, type, ID, and print to empty for next use.
				}

				// Test for Unpaired delimiters
				if (isUnpairedDelimeters(char1)) {
					token = Character.toString(char1);
					setTokenId();
					print();
					reset();
					continue;
					//test for paired delimeters
				} else if (isPairedDelimeter(char1)) {
					token = Character.toString(char1);
					type = "delimeter";
					setTokenId();
					print();
					reset();
				}
				//test for punctuation
				else if (isPunctuation(char1)) {
					if (char1 == '/' || char2 == '/') {	//if // occurs, ignore since its a comment
						break;
					} else {
						if (char2 == ' ') {	// if white space is next character, punctuation ends.
							token = Character.toString(char1);
							type = "punctuation";
							setTokenId();
							print();

						}
						reset();

					}

				}
				// test for integer
				else if (Character.isDigit(char1)) {	//if digit is found
					str += char1;	//keep track of the string of characters
					type = "integer";	
					while (Character.isDigit(char2)) {	//while next characters are also integers
						++i;	//iterate both char1 and char2, and concatenate character onto string.
						char1 = s.charAt(i);
						char2 = s.charAt(i + 1);
						str += char1;
					}
					if (char2 == '.') {	//if a period is found, the token is a float. concatenate characters
						++i;
						char1 = s.charAt(i);
						char2 = s.charAt(i + 1);
						str += char1;
						type = "float";
						while (Character.isDigit(char2)) {	//continue to concatenate characters until float is finished.
							++i;
							char1 = s.charAt(i);
							char2 = s.charAt(i + 1);
							str += char1;
						}

					}

					token = str;	//set string to token content.
					setTokenId();
					print();
					reset();
				}
				//check for alphabetical characters to determine text is keyword or identifier
				else if (Character.isAlphabetic(char1)) {
					str += char1;	//concatenate first character.

					while (Character.isAlphabetic(char2) || Character.isDigit(char2)) { //continue to concatenate if the next character is alphabetic or numerical.
						i++;
						char1 = s.charAt(i);
						char2 = s.charAt(i + 1);

						str += char1;
					}
					//check whether token is a Keyword or an Identifier
					if (isKeyword(str)) {
						token = str;
						setTokenId();
						print();
						reset();
					} else {	//if there is no keyword, it is an identifier
						type = "identifier";
						token = str;
						setTokenId();
						print();
						reset();
					}
				}

			}
			lineCount += 1;	//increment line counter at the end of line.
		}
		idNum = 0;	
		lineCount -= 2;
		print();
		writer.close();	//close writer
	}

	public static void setTokenId() {	//define types and tokens

		if (type.equals("identifier")) {
			idNum = 2;
		}
		if (type.equals("integer")) {
			idNum = 3;
		}
		if (type.equals("float")) {
			idNum = 4;
		}
		if (type.equals("string")) {
			idNum = 5;
		}
		if (token.equals(",")) {
			idNum = 6;
		}
		if (token.equals(";")) {
			idNum = 7;
		}
		if (token.equals("prog")) {
			idNum = 10;
		}
		if (token.equals("prog")) {
			idNum = 10;
		}
		if (token.equals("main")) {
			idNum = 11;
		}
		if (token.equals("fcn")) {
			idNum = 12;
		}
		if (token.equals("class")) {
			idNum = 13;
		}
		if (token.equals("float")) {
			idNum = 15;
		}
		if (token.equals("int")) {
			idNum = 16;
		}
		if (token.equals("string")) {
			idNum = 17;
		}
		if (token.equals("if")) {
			idNum = 18;
		}
		if (token.equals("elseif")) {
			idNum = 19;
		}
		if (token.equals("else")) {
			idNum = 20;
		}
		if (token.equals("while")) {
			idNum = 21;
		}
		if (token.equals("input")) {
			idNum = 22;
		}
		if (token.equals("print")) {
			idNum = 23;
		}
		if (token.equals("new")) {
			idNum = 24;
		}
		if (token.equals("return")) {
			idNum = 25;
		}
		if (token.equals("<")) {
			idNum = 31;
		}
		if (token.equals(">")) {
			idNum = 32;
		}
		if (token.equals("{")) {
			idNum = 33;
		}
		if (token.equals("}")) {
			idNum = 34;
		}
		if (token.equals("[")) {
			idNum = 35;
		}
		if (token.equals("]")) {
			idNum = 36;
		}
		if (token.equals("(")) {
			idNum = 37;
		}
		if (token.equals(")")) {
			idNum = 38;
		}
		if (token.equals("*")) {
			idNum = 41;
		}
		if (token.equals("^")) {
			idNum = 42;
		}
		if (token.equals(":")) {
			idNum = 43;
		}
		if (token.equals(".")) {
			idNum = 44;
		}
		if (token.equals("=")) {
			idNum = 45;
		}
		if (token.equals("-")) {
			idNum = 46;
		}
		if (token.equals("+")) {
			idNum = 47;
		}
		if (token.equals("/")) {
			idNum = 48;
		}
		if (token.equals(">")) {
			idNum = 51;
		}
		if (token.equals("==")) {
			idNum = 52;
		}
		if (token.equals("!=")) {
			idNum = 53;
		}
		if (token.equals("<=")) {
			idNum = 54;
		}
		if (token.equals(">=")) {
			idNum = 55;
		}
		if (token.equals("<<")) {
			idNum = 56;
		}
		if (token.equals(">>")) {
			idNum = 57;
		}
		if (type.equals("error")) {
			idNum = 99;
		}

	}
	//output results into console and output file
	public static void print() {

		System.out.print("Tok: " + idNum + " line= " + lineCount + " str= " + token + " ");
		writer.println("Tok: " + idNum + " line= " + lineCount + " str= " + token + " ");
		if (type == "integer") {
			System.out.println("int= " + token);
			writer.println("int= " + token);
		} else if (type == "float") {
			System.out.print("float= " + token);
			writer.println("float= " + token);
		}
		System.out.println();

	}
	
	//define Unpaired delimeters
	public boolean isUnpairedDelimeters(char char1) {
		return (char1 == ',' || char1 == ';');

	}
	//define keywords
	public boolean isKeyword(String word) {
		return (word == "prog" || word == "main" || word == "fcn" || word == "class" || word == "float" || word == "int"
				|| word == "string" || word == "if" || word == "elseif" || word == "else" || word == "while"
				|| word == "input" || word == "input" || word == "print" || word == "new" || word == "return");
	}
	//define paired delimeters
	public boolean isPairedDelimeter(char char1) {
		return (char1 == '<' || char1 == '>' || char1 == '{' || char1 == '}' || char1 == '[' || char1 == ']'
				|| char1 == '(' || char1 == ')');
	}
	//define punctuation
	public boolean isPunctuation(char char1) {
		return (char1 == '*' || char1 == '^' || char1 == ':' || char1 == '.' || char1 == '=' || char1 == '-'
				|| char1 == '+' || char1 == '/');
	}
	//define multi char operators
	public boolean isMultiCharOperator(char char1, char char2) {
		return ((char1 == '-' && char2 == '>') || (char1 == '=' && char2 == '=') || (char1 == '!' && char2 == '=')
				|| (char1 == '<' && char2 == '=') || (char1 == '>' && char2 == '=') || (char1 == '<' && char2 == '<')
				|| (char1 == '>' && char2 == '>'));
	}
	//reset all temp variables to 0 for next iteration.
	public static void reset() {
		idNum = 0;
		type = "";
		str = "";
		type = "";

	}
}
