import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class RabinPrimeTest {
    private static Random rand = new Random();
    private static final BigInteger one = BigInteger.ONE;
    private static final BigInteger two = BigInteger.TWO;
    //private static final BigInteger TWO = new BigInteger("2");


    public static void main(String[] args) {
       /* Scanner input = new Scanner(System.in);
        System.out.println("Enter n > 3: ");
        BigInteger n = input.nextBigInteger();
        System.out.println("Enter s: ");
        BigInteger s = input.nextBigInteger();
        //System.out.println("Enter a: ");
        //BigInteger a=input.nextBigInteger();
        System.out.println("Enter r: ");
        int r = input.nextInt();

        double startTime = System.currentTimeMillis();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int bigN = n.intValue();
            int a = random.nextInt(bigN);
            BigInteger A = new BigInteger(a + "");

            //System.out.println(isPrimeCondOne(A,s,n));
            //System.out.println(isPrimeSecCond(A,s,n,4));

            RabinPrimeTest rabinTest = new RabinPrimeTest();
            boolean firstCondition = rabinTest.isPrimeCondOne(A, s, n);
            boolean secondCondition = rabinTest.isPrimeSecCond(A, s, n, r);

            //Om jämförelsen är sann då är a en witness, dvs att n är inte primtal, annars primtal.
            if (firstCondition==true && secondCondition==true ) {
                System.out.println("n is not prime for a = " + a);
            } else {
                System.out.println("n is prime for a = " + a);
            }
            double endTime=System.currentTimeMillis();
            System.out.println("Execution time is: " + (endTime-startTime));

        }*/

        for (BigInteger big: generatePrimes(5000)){
            System.out.println(big);
            System.out.println("------");
        }

    }

    /**
     * Checks if a number "a" is a witness for an odd integer "n",
     * i.e. determine if "n" is a prabably prime number or for sure composite.
     * @param a
     * @param s
     * @param n
     * @return true if the condition is fulfilled
     * r^exp not mod 1
     */

    public static boolean isPrimeCondOne(BigInteger a, BigInteger s, BigInteger n){
        return !a.modPow(s,n).equals(one.mod(n)); //enligt första villkoret
        /*BigInteger count=a.modPow(s,n);
        System.out.println("!!!"+one.mod(n));
        if(count.equals(one.mod(n))){
            return false;
        }
        System.out.println(count);
        return true;*/
    }


    /**
     * Checks if r is a witness for an odd integer "n",
     * i.e. determine if "n" is composite or prime
     * @param a
     * @param s
     * @param n
     * @param r
     * @return true if the condition passes the test
     * r^(2^j)^exp not mod -1
     */
    public static boolean isPrimeSecCond( BigInteger a, BigInteger s, BigInteger n, int r){
        double exp;
        BigInteger bigExp;
        for(int j=1; j<r; j++){
            exp=Math.pow(2,j)*s.doubleValue();
            bigExp= BigDecimal.valueOf(exp).toBigInteger();
            BigInteger x=a.modPow(bigExp,n);
            if (n.signum() >= 1) {
                if(x.equals(one.mod(n.subtract(one)))){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 	create an array with 100 places for random generated BigInteger numbers
     * @return filled bigArray
     */
    public static ArrayList<BigInteger> generatePrimes(int nbrOfBits) {
        //en lista med 100 platser för slumpmässiga BigInteger värden
        double start=System.currentTimeMillis();
        ArrayList<BigInteger> list=new ArrayList<>();
        for(int i=0; i< 1; i++) {
            list.add(BigInteger.probablePrime(nbrOfBits, rand));
        }
        double end=System.currentTimeMillis();
        System.out.println("Exekveringstiden är: " + (end-start));
        return list;
    }
}



