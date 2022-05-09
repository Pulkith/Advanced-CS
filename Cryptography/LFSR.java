package Cryptography;

import java.util.Arrays;

public class LFSR
{
    private int   N;   //number of bits in the LFSR
    private int[] reg; //reg[i] = ith bit of LFSR, reg[0] is leftmost bit
    private int   tap; //index of the tap bit

    private int regBit;

    /** constructor to create LFSR with the given initial seed and tap */
    public LFSR(String seed, int tap)
    {
        this.N = seed.length();
        this.tap = N - tap - 1;
        this.reg = new int[N];
        for(int i = 0; i < N; ++i)
            reg[i] = (seed.charAt(i) - '0');
//        this.regBit = Integer.parseInt(seed, 2);
    }

    /** simulate one step and return the new bit as 0 or 1 */
    public int step()
    {
        int bit = reg[tap] ^ reg[0];
        if (N - 1 >= 0) System.arraycopy(reg, 1, reg, 0, N - 1);
        reg[N-1] = bit;
        return bit;
    }
    public int stepBitwise() {
//        int bitLow = (regBit & (1 << (N - 1))) > 0 ? 1 : 0;
//        int bitTap = (regBit & (1 << (tap - 1))) > 0 ? 1 : 0;
        int bitLow = (regBit >> (N-1)) & 1;
        int bitTap = (regBit >> (tap-1)) & 1;
        int bit = bitLow ^ bitTap;
        System.out.println(bitLow + " " + bitTap + " " + bit);
        regBit -= (bitLow << (N-1));
        regBit *= 2;
        regBit += bit;
        return bit;
    }

    /** simulate k steps and return k-bit integer */
    public int generate(int k)
    {
        int res = 0;
        for(int i = 0; i < k; ++i)
            res = res * 2 + step();
        return  res;
    }

    @Override
    public String toString()  {
        String k = Arrays.toString(reg);
        return k.replaceAll("\\[|\\]|,| ","");
//        return String.format("%" + N + "s", Integer.toBinaryString(regBit)).replaceAll(" ", "0");
    }


    public static void main(String[] args)
    {
        //In Eclipse, Ctrl + / (front slash) toggles comments of highlighted portion
    	test01();
    	test02();
//    	test03();
//    	test04();
    }

    /** test toString() and constructor */
    public static void test01()
    {
        LFSR lfsr = new LFSR("01101000010", 8);
        System.out.println(lfsr + "\n");

        //should output: 01101000010
    }

    /** test step() method */
    public static void test02()
    {
        LFSR lfsr = new LFSR("01101000010", 8);

        for (int i = 0; i < 10; i++) {
//            int bit = lfsr.stepBitwise();
            int bit = lfsr.step();
            System.out.println(lfsr + " " + bit);
        }
    	/*
    	   should output:

    	    11010000101 1
			10100001011 1
			01000010110 0
			10000101100 0
			00001011001 1
			00010110010 0
			00101100100 0
			01011001001 1
			10110010010 0
			01100100100 0
    	 */
    }

    /** test generate() method */
    public static void test03()
    {
        System.out.println();

        LFSR lfsr = new LFSR("01101000010", 8);

        for (int i = 0; i < 10; i++) {
            int r = lfsr.generate(5);
            System.out.println(lfsr + " " + r);
        }
    	/*
    	   should output:

    	    00001011001 25
			01100100100 4
			10010011110 30
			01111011011 27
			01101110010 18
			11001011010 26
			01101011100 28
			01110011000 24
			01100010111 23
			01011111101 29
    	 */
    }

    /** test generate() method again */
    public static void test04()
    {
        System.out.println();

        LFSR lfsr = new LFSR("01101000010100010000", 16);

        for (int i = 0; i < 10; i++) {
            int r = lfsr.generate(8);
            System.out.println(lfsr + " " + r);
        }
    	/*
    	    should output:
    	    01010001000000101010 42
			00000010101011011001 217
			10101101100100010111 23
			10010001011111000001 193
			01111100000100011010 26
			00010001101010011100 156
			10101001110010011100 156
			11001001110011100111 231
			11001110011110000111 135
			01111000011110111101 189
		*/
    }
}
