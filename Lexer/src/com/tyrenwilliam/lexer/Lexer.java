package com.tyrenwilliam.lexer;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Lexer {

 static int idNum;
static int lineCount=1;
 static String type;
 static String token;

 static String str="";


public Lexer(String s){
	
for(int i =0; i< s.length()-1;++i)
{
	
	char c=s.charAt(i);
	char c2=s.charAt(i+1);
	
	if(c==' '){
		continue;
	}
	//String test
	if(c== '"')
	{
		
		++i;
		c= s.charAt(i);
		c2=s.charAt(i+1);
	while(!(c == '"')){
		
		c=s.charAt(i);
		c2=s.charAt(i+1);
		if(c == '"')
			continue;
		str +=c;
		++i;
		}
	token=str;
	type="string";
	setTokenId();
	print();
	reset();
	}
	
	//Multi char test
	if(isMultiCharOperator(c,c2))
	{
		
		token=Character.toString(c);
		token += c2;
		type="multicharoperator";
		setTokenId();
		print();
		++i;
		reset();
	}

		//Test for Unpaired delimiters
	if(isUnpairedDelimiters(c)){
		token=Character.toString(c);
		setTokenId();
		print();
		reset();
		continue;
	}
	else if(isPairedDelimeter(c)){
		token=Character.toString(c);
		type = "delimeter";
		setTokenId();
		print();
		reset();
	}
	
	else if(isPunctuation(c)){
		if(isComment(c,c2)){
			break;
		}
		else {
			if(c2 == ' '){
				token = Character.toString(c);
				type= "punctuation";
				setTokenId();
				print();
				
				}
			reset();
			
		}
		
	}
	// test for integer
	else if(Character.isDigit(c)){
		str +=c;
		type="interger";
		while(Character.isDigit(c2)){
			++i;
			c=s.charAt(i);
			c2=s.charAt(i+1);
			str +=c;
		}
		if( c2 == '.'){
			++i;
			c=s.charAt(i);
			c2=s.charAt(i+1);
			str +=c;
			type="float";
			while(Character.isDigit(c2)){
				++i;
				c=s.charAt(i);
				c2=s.charAt(i+1);
				str +=c;
			}
			
			
		}
	
	token=str;
	setTokenId();
	print();
	reset();
	}

	else if(Character.isAlphabetic(c)){
		str+= c;
		boolean willBreak = false;
		while(Character.isAlphabetic(c) || Character.isDigit(c)){
			i++;
			c=s.charAt(i);
			if(i+1 >= s.length()+1 )
			{
				willBreak = true;
			}
			c2=s.charAt(i+1);
			if(willBreak)
			{
				break;
			}
			str +=c;
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
	else{
		break;
	}
}
	lineCount +=1;
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
	if(type.equals(",")){
		idNum=6;
	}
	if(type.equals(";")){
		idNum=7;
	}
	if(type.equals("prog")){
		idNum=10;
	}
	if(type.equals("prog")){
		idNum=10;
	}
	if(type.equals("main")){
		idNum=11;
	}
	if(type.equals("fcn")){
		idNum=12;
	}
	if(type.equals("class")){
		idNum=13;
	}
	if(type.equals("float")){
		idNum=15;
	}
	if(type.equals("int")){
		idNum=16;
	}
	if(type.equals("string")){
		idNum=17;
	}
	if(type.equals("if")){
		idNum=18;
	}
	if(type.equals("elseif")){
		idNum=19;
	}
	if(type.equals("else")){
		idNum=20;
	}
	if(type.equals("while")){
		idNum=21;
	}
	if(type.equals("input")){
		idNum=22;
	}
	if(type.equals("print")){
		idNum=23;
	}
	if(type.equals("new")){
		idNum=24;
	}
	if(type.equals("return")){
		idNum=25;
	}
	if(type.equals("<")){
		idNum=31;
	}
	if(type.equals(">")){
		idNum=32;
	}
	if(type.equals("{")){
		idNum=33;
	}
	if(type.equals("}")){
		idNum=34;
	}
	if(type.equals("[")){
		idNum=35;
	}
	if(type.equals("]")){
		idNum=36;
	}
	if(type.equals("(")){
		idNum=37;
	}
	if(type.equals(")")){
		idNum=38;
	}
	if(type.equals("*")){
		idNum=41;
	}
	if(type.equals("^")){
		idNum=42;
	}
	if(type.equals(":")){
		idNum=43;
	}
	if(type.equals(".")){
		idNum=44;
	}
	if(type.equals("=")){
		idNum=45;
	}
	if(type.equals("-")){
		idNum=46;
	}
	if(type.equals("+")){
		idNum=47;
	}
	if(type.equals("/")){
		idNum=48;
	}
	if(type.equals(">")){
		idNum=51;
	}
	if(type.equals("==")){
		idNum=52;
	}
	if(type.equals("!=")){
		idNum=53;
	}
	if(type.equals("<=")){
		idNum=54;
	}
	if(type.equals(">=")){
		idNum=55;
	}
	if(type.equals("<<")){
		idNum=56;
	}
	if(type.equals(">>")){
		idNum=57;
	}
	if(type.equals("error")){
		idNum=99;
	}
																										
																																																						
	
	
	
}

public static void print(){

	
	System.out.println("Tok:  "+idNum+" line =" +lineCount+ "str ="+token+ " ");
	if(type=="integer"){
		System.out.print("int= "+token );
	}
	else if(type=="float"){
		System.out.print("float= "+token );
	}

}


 
public boolean isComment(char c1, char c2) {
	return((c1=='/') && (c2=='/'));
}

public boolean isUnpairedDelimiters(char c) {
	return (c==',' || c == ';' );
	
}

public boolean isPunctuation(char c) {
	return (c=='*' || c== '^' || c == ':' || c == '.' || c == '=' || c == '-' || c == '+' || c == '/' );
}

public boolean isPairedDelimeter(char c) {
	return (c=='<' || c=='>' || c=='{' || c=='}' || c=='[' || c==']' || c =='(' || c==')');
}

public boolean isMultiCharOperator(char c1, char c2) {
	return( (c1 == '-' && c2 == '>') || (c1 == '=' && c2 == '=') || (c1 == '!' && c2 == '=')
			|| (c1 == '<' && c2 == '=') || (c1 == '>' && c2 == '=') || (c1 == '<' && c2 == '<')
			|| (c1 == '>' && c2 == '>'));
}

public boolean isKeyword(String word) {
	return (word =="prog" || word == "fcn" || word == "class" || word == "float" || word == "int" || word == "string"
			|| word == "if" || word == "elseif" || word == "else" || word =="while" || word =="input"
			|| word == "input" ||  word == "print");
}





 
 public static void reset(){
	 idNum=0;
	 type="";
	 str="";
	 type="";
	 
 }
 
 




	
	
	
	
	
	
	
	
	
	
	
	
	
	



}


