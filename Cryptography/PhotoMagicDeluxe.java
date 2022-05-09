package Cryptography;

public class PhotoMagicDeluxe extends PhotoMagic {
    public static void main(String[] args) {
        Picture p = new Picture("/Users/despicablemonkey/Desktop/Development/CS3/src/Cryptography/mystery.png");
        LFSR l = new LFSR((getRegister("OPENSESAME")), 58);
        Picture o = transform(p, l);
        o.show();
    }
    private static String getRegister(String s) {
        String base64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        String reg = "";
        for(char c : s.toCharArray())
            reg += String.format("%6s", Integer.toBinaryString((short)base64.indexOf(c))).replace(' ', '0');;
        return reg;
    }
}