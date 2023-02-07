public class Act {




    public static int expr(LexemeBuffer lexemes){
        Lexeme lexeme = lexemes.next();
       if(lexeme.type == LexemeType.EOF){
           return 0;
       }else {
           lexemes.back();
           return plusminus(lexemes);
       }
    }

    public static int plusminus(LexemeBuffer lexemes) {
        int value = multdiv(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case OP_PLUS:
                    value += multdiv(lexemes);
                    break;
                case OP_MINUS:
                    value -= multdiv(lexemes);
                    break;
                default:
                    lexemes.back();
                    return value;
            }
        }
    }

    public static int multdiv(LexemeBuffer lexemes){
        int value = factor(lexemes);
        while(true){
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case OP_MUL:
                    value *= factor(lexemes);
                    break;
                case OP_DIV:
                    value /= factor(lexemes);
                    break;
                default:
                    lexemes.back();
                    return value;
            }
        }
    }

    public static int factor(LexemeBuffer lexemes){
        Lexeme lexeme = lexemes.next();
        switch (lexeme.type){
            case NUMBER:
                return Integer.parseInt(lexeme.value);
            case LEFT_BRACKET:
                int value = expr(lexemes);
                lexeme = lexemes.next();
                if(lexeme.type != LexemeType.RIGHT_BRACKET){
                    throw new RuntimeException("Unexpected character " + lexeme.value
                            + " at position: " + lexemes.getPos());
                }
                return value;
            default:
                throw new RuntimeException("Unexpected character" + lexeme.value
                        + "at position: " + lexemes.getPos());
        }
    }

    public static String calculator(String str){
        if(Lexeme.selection(str)){
            LexemeBuffer buffer = new LexemeBuffer(Lexeme.lexAnalyzeArab(str));
            return String.valueOf(expr(buffer));
        }else{
            LexemeBuffer buffer = new LexemeBuffer(Converter.preparingRim(Lexeme.lexAnalyzeRim(str)));
            return Converter.arabicToRoman(expr(buffer));
        }

    }



}
