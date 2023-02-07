import java.util.ArrayList;
import java.util.List;

public class Lexeme {
    LexemeType type;
    String value;

    public Lexeme(LexemeType type, String value) {
        this.type = type;
        this.value = value;
    }

    public Lexeme(LexemeType type, Character value) {
        this.type = type;
        this.value = value.toString();
    }

    public static List<Lexeme> lexAnalyzeArab(String text) {
        List<Lexeme> lexemes = new ArrayList<>();
        int ind = 0;

        while(ind< text.length()) {
            while(ind < text.length()) {
                char c = text.charAt(ind);
                switch (c) {
                    case '(':
                        lexemes.add(new Lexeme(LexemeType.LEFT_BRACKET, c));
                        ++ind;
                        continue;
                    case ')':
                        lexemes.add(new Lexeme(LexemeType.RIGHT_BRACKET, c));
                        ++ind;
                        continue;
                    case '*':
                        lexemes.add(new Lexeme(LexemeType.OP_MUL, c));
                        ++ind;
                        continue;
                    case '+':
                        lexemes.add(new Lexeme(LexemeType.OP_PLUS, c));
                        ++ind;
                        continue;
                    case '-':
                        lexemes.add(new Lexeme(LexemeType.OP_MINUS, c));
                        ++ind;
                        continue;
                    case '/':
                        lexemes.add(new Lexeme(LexemeType.OP_DIV, c));
                        ++ind;
                        continue;
                    default:
                        if (c <= '9' && c >= '0') {
                            StringBuilder sb = new StringBuilder();
                            do {
                                sb.append(c);
                                ++ind;
                                if (ind >= text.length()) {
                                    break;
                                }
                                c = text.charAt(ind);
                            } while(c <= '9' && c >= '0');
                            lexemes.add(new Lexeme(LexemeType.NUMBER, sb.toString()));
                        }
                        else{
                            if (c != ' ') {
                                throw new RuntimeException("Unexpected character: " + c);
                            }

                            ++ind;
                        }
                        break;
                }
            }

        }
        lexemes.add(new Lexeme(LexemeType.EOF, ""));
        return lexemes;
    }


    public static List<Lexeme> lexAnalyzeRim(String text) {
        List<Lexeme> lexemes = new ArrayList<>();
        int ind = 0;

        while(ind< text.length()) {
            while(ind < text.length()) {
                char c = text.charAt(ind);
                switch (c) {
                    case '(':
                        lexemes.add(new Lexeme(LexemeType.LEFT_BRACKET, c));
                        ++ind;
                        continue;
                    case ')':
                        lexemes.add(new Lexeme(LexemeType.RIGHT_BRACKET, c));
                        ++ind;
                        continue;
                    case '*':
                        lexemes.add(new Lexeme(LexemeType.OP_MUL, c));
                        ++ind;
                        continue;
                    case '+':
                        lexemes.add(new Lexeme(LexemeType.OP_PLUS, c));
                        ++ind;
                        continue;
                    case '-':
                        lexemes.add(new Lexeme(LexemeType.OP_MINUS, c));
                        ++ind;
                        continue;
                    case '/':
                        lexemes.add(new Lexeme(LexemeType.OP_DIV, c));
                        ++ind;
                        continue;
                    default:
                        if (Converter.isRoman(c)) {
                            StringBuilder sb = new StringBuilder();
                            do {
                                sb.append(c);
                                ++ind;
                                if (ind >= text.length()) {
                                    break;
                                }
                                c = text.charAt(ind);

                            } while(Converter.isRoman(c));

                            lexemes.add(new Lexeme(LexemeType.RIMNUM, sb.toString()));
                        } else {
                            if (c != ' ') {
                                throw new RuntimeException("Unexpected character: " + c);
                            }
                            ++ind;
                        }
                       break;
                }
            }

        }
        lexemes.add(new Lexeme(LexemeType.EOF, ""));
        return lexemes;
    }
    public static boolean selection(String str){
        boolean result = true;
        for (int i = 0; i < str.length(); i++) {
            if (Converter.isRoman(str.charAt(i))) {
                result = false;
                break;
            }
        }
        return result;
    }
}
