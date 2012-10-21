package ch.zhaw.minipc.util;

public class Tools {
    // Takes a two's complement binary string and returns a decimal int
    public static int convertToDec(String binary) {
        final boolean minus = binary.charAt(0) == '1';
        final String value = binary.substring(1);
        if (minus) {
            return (int) -(Math.pow(2, value.length()) - Math.abs(Integer.parseInt(value, 2)));
        } else {
            return Integer.parseInt(value, 2);
        }
    }

    // Takes a decimal int and returns a two's complement i bit string
    public static String convertToBin(int value, int i) {
        String a = Integer.toBinaryString(value & 0xFFFF);

        return String.format("%" + i + "s", a).replace(' ', '0');
    }

}
