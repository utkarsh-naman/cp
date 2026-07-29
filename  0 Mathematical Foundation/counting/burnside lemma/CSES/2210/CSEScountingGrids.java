// import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;


public class CSEScountingGrids{
    static long MOD = 1_000_000_007;
    static long INV4 = 25_000_000_2;


    static long modPow(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
    
        while (exp > 0) {
            if ((exp & 1) == 1)
                result = (result * base) % mod;
    
            base = (base * base) % mod;
            exp >>= 1;
        }
    
        return result;
    }
    
    static long modInverse(long n, long mod) {
        // Works only if mod is prime and gcd(n, mod) = 1
        return modPow(n, mod - 2, mod);
    }
    
    private static long power(long a, long b, long mod){
        if (b == 0) return 1;
        if (a == 0) return 0;
        if (b == 1) return a;

        long halfRes = (power(a, b/2, mod))%mod;
        long fullRes = (halfRes*halfRes)%mod;
        return (b%2 == 0)? (fullRes): (a*fullRes)%mod;
    }

    private static long solution(long n){
        if (n == 1) return 2;
        long n2 = n*n;
        long result = 0;
        if ((n&1) == 0){ // even n
            long quarter = power(2, n2/4, MOD);
            result = (result+2*quarter) %MOD; // for 90 and 270 degree
            long half = (quarter*quarter) % MOD;
            result = (result+half) %MOD; // for 180 degree
            result = (result+ ((half*half)%MOD) ) %MOD; // for 0 degree
            return (result*INV4)%MOD;
        }
        // odd n
        result = power(2, n2, MOD); // for 0 degree
        long quarter = power(2, (n2-1)/4, MOD);
        result = (result+4*quarter) %MOD; // 90 degree and 270 degree
        result = (result+ 2*(quarter*quarter)%MOD) %MOD; // 180 degree
        return (result*INV4)%MOD;
        
    }
    public static void main(String[] args) throws IOException{
        // if n is even (Math.pow(2, n*n) + 2*Math.pow(2, n*n/4) + Math.pow(2, n*n/2))/4
        // if n is odd (Math.pow(2, n*n) + 2*(2*Math.pow(2, (n*n-1)/4) + Math.pow(2, (n*n-1)/2) )/4

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        String line = br.readLine();
        if (line == null) return;
        
        long n = Long.parseLong(line.trim());
        out.println(solution(n));
        out.flush();
    }
}