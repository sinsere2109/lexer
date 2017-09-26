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

static int idNum;
static int lineCount=1;
static String type;
static String token;
static String str="";
static PrintWriter writer=null;
public Lexer(String fileName) throws IOException{
	
writer = new PrintWriter("output"+fileName,"UTF-8");
FileReader file = new FileReader(new File(fileName));	
BufferedReader read = new BufferedReader(file);
String s=null;
while((s= read.readLine()) != null){
for(int i =0; i<s.length()-1;++i)
{
	char char1=s.charAt(i);
	char char2=s.charAt(i+1);
	
	if(char1==' '){
		continue;
	}
	
	//String test
	if(char1== '"')
	{
		
		++i;
		char1= s.charAt(i);
		char2=s.charAt(i+1);
	while(!(char1 == '"')){
		
		char1=s.charAt(i);
		char2=s.charAt(i+1);
		if(char1 == '"') continue;
		str +=char1;
		++i;
		}
	token=str;
	type="string";
	setTokenId();
	print();
	reset();
	}
	
	//Multi char test
	if(isMultiCharOperator(char1,char2))
	{
		
		token=Character.toString(char1);
		token += char2;
		type="multicharoperator";
		setTokenId();
		print();
		++i;
		reset();
	}

		//Test for Unpaired delimiters
	if(isUnpairedDelimeters(char1)){
		token=Character.toString(char1);
		setTokenId();
		print();
		reset();
		continue;
	}
	else if(isPairedDelimeter(char1)){
		token=Character.toString(char1);
		type = "delimeter";
		setTokenId();
		print();
		reset();
	}
	
	else if(isPunctuation(char1)){
		if(char1 =='/' || char2=='/' ){
			break;
		}
		else {
			if(char2 == ' '){
				token = Character.toString(char1);
				type= "punctuation";
				setTokenId();
				print();
				
				}
			reset();
			
		}
		
	}
	// test for integer
	else if(Character.isDigit(char1)){
		str +=char1;
		type="integer";
		while(Character.isDigit(char2)){
			++i;
			char1=s.charAt(i);
			char2=s.charAt(i+1);
			str +=char1;
		}
		if( char2 == '.'){
			++i;
			char1=s.charAt(i);
			char2=s.charAt(i+1);
			str +=char1;
			type="float";
			while(Character.isDigit(char2)){
				++i;
				char1=s.charAt(i);
				char2=s.charAt(i+1);
				str +=char1;
			}
			
			
		}
	
	token=str;
	setTokenId();
	print();
	reset();
	}

	else if(Character.isAlphabetic(char1)){
		str+= char1;

		while(Character.isAlphabetic(char2) || Character.isDigit(char2)){
			i++;
			char1=s.charAt(i);
			char2=s.charAt(i+1);

			str +=char1;
		}
		if(isKeyword(str)){
			token=str;
			setTokenId();
			print();
			reset();
		}
		else{
			type="identifier";
			token=str;
			setTokenId();
			print();
			reset();
		}
	}
	
}
	lineCount +=1;
}
	idNum = 0;
		lineCount -= 2;
		print();
		writer.close();
}


public static void setTokenId(){
	
	if(type.equals("identifier")){
		idNum=2;
	}
	if(type.equals("integer")){
		idNum=3;
	}
	if(type.equals("float")){
		idNum=4;
	}
	if(type.equals("string")){
		idNum=5;
	}
	if(token.equals(",")){
		idNum=6;
	}
	if(token.equals(";")){
		idNum=7;
	}
	if(token.equals("prog")){
		idNum=10;
	}
	if(token.equals("prog")){
		idNum=10;
	}
	if(token.equals("main")){
		idNum=11;
	}
	if(token.equals("fcn")){
		idNum=12;
	}
	if(token.equals("class")){
		idNum=13;
	}
	if(token.equals("float")){
		idNum=15;
	}
	if(token.equals("int")){
		idNum=16;
	}
	if(token.equals("string")){
		idNum=17;
	}
	if(token.equals("if")){
		idNum=18;
	}
	if(token.equals("elseif")){
		idNum=19;
	}
	if(token.equals("else")){
		idNum=20;
	}
	if(token.equals("while")){
		idNum=21;
	}
	if(token.equals("input")){
		idNum=22;
	}
	if(token.equals("print")){
		idNum=23;
	}
	if(token.equals("new")){
		idNum=24;
	}
	if(token.equals("return")){
		idNum=25;
	}
	if(token.equals("<")){
		idNum=31;
	}
	if(token.equals(">")){
		idNum=32;
	}
	if(token.equals("{")){
		idNum=33;
	}
	if(token.equals("}")){
		idNum=34;
	}
	if(token.equals("[")){
		idNum=35;
	}
	if(token.equals("]")){
		idNum=36;
	}
	if(token.equals("(")){
		idNum=37;
	}
	if(token.equals(")")){
		idNum=38;
	}
	if(token.equals("*")){
		idNum=41;
	}
	if(token.equals("^")){
		idNum=42;
	}
	if(token.equals(":")){
		idNum=43;
	}
	if(token.equals(".")){
		idNum=44;
	}
	if(token.equals("=")){
		idNum=45;
	}
	if(token.equals("-")){
		idNum=46;
	}
	if(token.equals("+")){
		idNum=47;
	}
	if(token.equals("/")){
		idNum=48;
	}
	if(token.equals(">")){
		idNum=51;
	}
	if(token.equals("==")){
		idNum=52;
	}
	if(token.equals("!=")){
		idNum=53;
	}
	if(token.equals("<=")){
		idNum=54;
	}
	if(token.equals(">=")){
		idNum=55;
	}
	if(token.equals("<<")){
		idNum=56;
	}
	if(token.equals(">>")){
		idNum=57;
	}
	if(type.equals("error")){
		idNum=99;
	}
																										
																																																						
}

public static void print(){
	
	System.out.print("Tok: "+idNum+" line= " +lineCount+ " str= "+token+ " ");
	writer.println("Tok: "+idNum+" line= " +lineCount+ " str= "+token+ " ");
	if(type=="integer"){
		System.out.println("int= "+token );
		writer.println("int= "+token );
	}
	else if(type=="float"){
		System.out.print("float= "+token );
		writer.println("float= "+token );
	}
System.out.println();

}
public boolean isUnpairedDelimeters(char char1) {
	return (char1==',' || char1 == ';' );
	
}
public boolean isKeyword(String word) {
	return (word =="prog" ||word == "main"|| word == "fcn" || word == "class" || word == "float" || word == "int" || word == "string"
			|| word == "if" || word == "elseif" || word == "else" || word =="while" || word =="input"
			|| word == "input" ||  word == "print" || word =="new"|| word == "return");
}

public boolean isPairedDelimeter(char char1) {
	return (char1=='<' || char1=='>' || char1=='{' || char1=='}' || char1=='[' || char1==']' || char1 =='(' || char1==')');
}

public boolean isPunctuation(char char1) {
	return (char1=='*' || char1== '^' || char1 == ':' || char1 == '.' || char1 == '=' || char1 == '-' || char1 == '+' || char1 == '/' );
}


public boolean isMultiCharOperator(char char1, char char2) {
	return( (char1 == '-' && char2 == '>') || (char1 == '=' && char2 == '=') || (char1 == '!' && char2 == '=')
			|| (char1 == '<' && char2 == '=') || (char1 == '>' && char2 == '=') || (char1 == '<' && char2 == '<')
			|| (char1 == '>' && char2 == '>'));
}






 
 public static void reset(){
	 idNum=0;
	 type="";
	 str="";
	 type="";
	 
 }
 }


