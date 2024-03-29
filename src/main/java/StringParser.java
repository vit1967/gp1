import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StringParser {
    private static String s;

    enum RomanNumeral {
        I(1), IV(4), V(5), IX(9), X(10),
        XL(40), L(50), XC(90), C(100),
        CD(400), D(500), CM(900), M(1000);

        private int value;

        RomanNumeral(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static List<RomanNumeral> getReverseSortedValues() {
            return Arrays.stream(values())
                    .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                    .collect(Collectors.toList());
        }
    }

    public StringParser(String si) {
        s=si;
    }

    public static int toInt(String si) throws NoValidateTipExeption{
        if (Main.tipRA== Main.RomArab.Arab) {
            return Integer.parseInt(si);
        } else if (Main.tipRA== Main.RomArab.Rom){
            return romanToArabic(si);
        } else throw new  NoValidateTipExeption("НЕ УСТАНОВЛЕН ТИП ЧИСЛА:"+si);
    }

    public static Main.Action toAction(String si){
        switch (si){
            case "+":
                return Main.Action.Plus;
            case "-":
                return Main.Action.Minus;
            case "/":
                return Main.Action.Div;
            case "*":
                return Main.Action.Mult;
             default:
        }
        return Main.Action.Err;
    }


    private static int romanToArabic(String input) throws NoValidateTipExeption{
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new  NoValidateTipExeption("НЕ распознала, как РИМСКИЕ:"+input); //IllegalArgumentException(input + " cannot be converted to a Roman Numeral");
        }

        return result;
    }
    public static String arabicToRoman(int input) throws NoValidateTipExeption {
        String romNum=(String)input
    }

//    public static class NoRomanExeption extends Throwable {
//        public NoRomanExeption(String input) {
//        }
//    }
}
