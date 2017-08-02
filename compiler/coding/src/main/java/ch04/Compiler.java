package ch04;

public class Compiler {

	public static void main(String[] args) {
		Lexer lexer = new Lexer();
		ch04.ImprovedParser improvedParser = new ch04.ImprovedParser(lexer);
		improvedParser.statements();
		
//		Parser parser = new Parser(lexer);
//		parser.statements();
	}
}
