import java.util.HashMap;
import java.util.List;

public class Converter {
    public static String arabicToRoman(int num) {
        if (num < 1) {
            throw new RuntimeException("Unexpected character");
        } else {
            String[] keys = new String[]{ "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
            int[] vals = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

            StringBuilder ret = new StringBuilder();

            for(int ind = 0; ind < keys.length; ++ind) {
                while(num >= vals[ind]) {
                    int d = num / vals[ind];
                    num %= vals[ind];

                    for(int i = 0; i < d; ++i) {
                        ret.append(keys[ind]);
                    }
                }
            }

            return ret.toString();
        }
    }

    public static String romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int end = s.length() - 1;
        char[] arr = s.toCharArray();
        int result = map.get(arr[end]);

        for(int i = end - 1; i >= 0; --i) {
            int num = map.get(arr[i]);
            if (num < map.get(arr[i + 1])) {
                result -= num;
            } else {
                result += num;
            }
        }

        return String.valueOf(result);
    }

    public static boolean isRoman(Character c) {
        Character[] arr = new Character[]{'I', 'V', 'X', 'L', 'C'};

        for(int i = 0; i <= arr.length - 1; ++i) {
            if (c == arr[i]) {
                return true;
            }
        }

        return false;
    }

    public static List<Lexeme> preparingRim(List<Lexeme> lexemes){
        for(Lexeme x : lexemes){
            if(x.type==LexemeType.RIMNUM){
                x.value = Converter.romanToInt(x.value);
                x.type = LexemeType.NUMBER;
            }

            }
        return lexemes;
        }
    }
