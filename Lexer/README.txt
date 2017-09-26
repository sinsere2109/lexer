README.txt

OVERVIEW

The lexer is written in Java. The lexer will generate tokens from the entire file and output each token's ID, line number, and contents into the console as well as in an output file.

BUGS

May crash if invalid file input is given, such as not .txt files

FEATURES

Functionality to write output to seperate text file.
Ability to take in a custom file input by the user.

COMPLETED

Lexer functionality: reads tokens and prints their ID, line number, and contents.
User input functional: user can choose file for lexer.

NOT COMPLETED

Multiple file reads at once: user is unable to continuously read files; user must start another instance to process a new file.
Unable to give custom output names: output names are generated in a hardcoded format.

USE OF PROGRAM

The user will be prompted to enter the name of a single plain text file to be read. The file must be in the Java project directory. The output will be given in the console window, and also written to a file within the directory.

FUNCTIONS/METHODS

main: 	Tyren/William
Lexer:	Tyren/William
isUnpairedDelimeter:	Tyren
isKeyword:		Tyren
isPairedDelimeter:	Tyren
isPunctuation:	William
isMultiCharOperator:	William
reset:	Tyren


