package com.tyrenwilliam.lexer;



import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TestLexer {

    static String PLAYER_STATES_1 = "Q|S|\\s+";
    static String PLAYER_STATES_2 = "X|!|\\s+";
    static String PLAYER_NAME = ":?[a-zA-Z0-9]*";
    static String PLAYER_RANK = "(?:[0-9_]*)(?:[k,d,p])([\\?,\\*,\\+,\\s])|NR|\\?\\?\\?";
    static String PLAYER_TIME = "?:[s,m,h,d]";

    public static enum TokenType {
        // http://www.pandanet.co.jp/English/commands/hwho.html
        PLAYER("("+PLAYER_STATES_1+")("+PLAYER_STATES_2+")()(\\d+|.--)(\\s+)(\\d+|.--)(\\s+)("+PLAYER_NAME+")(\\s+)(\\d+)("+PLAYER_TIME+")(\\s+)("+PLAYER_RANK+")"),
        WHO("(\\d+)(\\sPlayers\\s)(\\d+)(\\sTotal Games\\s)"),

        // http://www.pandanet.co.jp/English/commands/hgames.html
        GAME("(\\d+)("+PLAYER_NAME+")(.+)(\\d+)("+PLAYER_RANK+")(.+)("+PLAYER_NAME+")(.+)(\\d+)("+PLAYER_RANK+")(.+)(\\d+)(\\s+)(\\d+)(\\s+)(\\d+)(\\s+)(:?[\\d+,\\.,\\-\\d+]*)(\\s+)(\\d+)(\\s+)(I)(\\)\\s+\\(\\s+)(\\d+)"),

        WHITESPACE("[ \t\f\r\n]+");

        public final String pattern;

        private TokenType(String pattern) {
            this.pattern = pattern;
        }
    }

    public static class Token {
        public TokenType type;
        public String data;

        public Token(TokenType type, String data) {
            this.type = type;
            this.data = data;
        }

        public String getData(){
            return data;
        }

        @Override
        public String toString() {
            return String.format("(%s %s)", type.name(), data);
        }
    }

    public static ArrayList<Token> lex(String input) {
        // The tokens to return
        ArrayList<Token> tokens = new ArrayList<Token>();

        // Lexer logic begins here
        StringBuffer tokenPatternsBuffer = new StringBuffer();
        for (TokenType tokenType : TokenType.values())
            tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
        Pattern tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring(1)));

        // Begin matching tokens
        Matcher matcher = tokenPatterns.matcher(input);
        while (matcher.find()) {

            if (matcher.group().matches(TokenType.WHO.pattern)) {
                tokens.add(new Token(TokenType.WHO, matcher.group()));
                //System.out.println("WHO: "+matcher.group()+"\n");
                continue;
            } else if (matcher.group().matches(TokenType.PLAYER.pattern)) {
                tokens.add(new Token(TokenType.PLAYER, matcher.group()));
                continue;
            } else if (matcher.group().matches(TokenType.GAME.pattern)) {
                tokens.add(new Token(TokenType.GAME, matcher.group()));
                continue;
            } else if (matcher.group().matches(TokenType.WHITESPACE.pattern)) {
                continue;
            }
        }

        return tokens;
    }

    public static void main(String[] args) {
        //String input = "QX744   -- kkikmkkkyk  3m     6d* |   X --   -- PaulChan86  2m     6d*";
        String input =
                "QX133   -- guest3515  17s     NR  |    263   -- guest3516  15s     NR" +
                "S! --   -- corgo       3s     2d* |     --  151 jokerez     5s     2k*" +
                " X --  206 xorg        1s    13k* |  Q  --  259 ZAJARI      5s     7k*" +
                "Q  --  168 schr       12s     9k* |  Q  --  163 kyou1812    3s    10k*" +
                "QX --   -- h246810    20s     3k* |   !133   -- mulan8      1m     2d*" +
                "[113]      uncle7 [ 5k*] vs.      kinogo [ 6k*] ( 23   19  0 -5.5 10  I) (  0)"+
                "                ******** 24 Players 1098 Total Games ********\n";


        // Create tokens and print them
        ArrayList<Token> tokens = lex(input);
        for (Token token : tokens)
            System.out.println(token);
    }
}