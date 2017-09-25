package com.tyrenwilliam.lexer;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Lexer {

 static String line;
 static int idNum;
static int lineCount=1;
 static String type;
 static String token;


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
	
	System.out.println("Tok:  "+idNum+" line =" +line+ "str ="+token+ " ");
	if(type=="integer"){
		System.out.print("int= "+token );
	}
	else if(type=="float"){
		System.out.print("float= "+token );
	}

}
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	



}

  
