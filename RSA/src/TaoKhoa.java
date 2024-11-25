import java.util.Arrays;
import java.util.Random;

public class TaoKhoa {
	public int p;
	public int q;
	public int n;
	public int phi;
	public int e;
	public int d;
	public int[] khoaPublic;
	public int[] khoaPrivate;

	private boolean soNguyenTo(int n) {
		if (n < 2)
			return false;
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	private int randomSNT() {
		Random random = new Random();
		int snt;
		do {
			snt = random.nextInt(10000);
		} while (!soNguyenTo(snt));
		return snt;
	}

	private int[] taopq() {
		int p = randomSNT();
		int q = randomSNT();
		while (p == q) {
			q = randomSNT();
		}
		return new int[] { p, q };
	}

	private int gcd(int a, int b) {
		while (b != 0) {
			int temp = a % b;
			a = b;
			b = temp;
		}
		return a;
	}

	private int chonE(int phi) {
		Random random = new Random();
		int e;
		while (true) {
			e = random.nextInt(1, phi);
			if (gcd(e, phi) == 1)
				return e;
		}
	}

//	private int timD(int e, int phi) {
//		int x = 1;
//		int d;
//		while (true) {
//			d = (x * phi + 1) / e;
//			if (d * e % phi == 1)
//				return d;
//			x++;
//		}
//	}
	public static int[] extendedEuclideanAlgorithm(int a, int b) {
		if (b == 0) {
			return new int[] { a, 1, 0 }; // gcd, x, y
		} else {
			int[] result = extendedEuclideanAlgorithm(b, a % b);
			int gcd = result[0];
			int x = result[2];
			int y = result[1] - (a / b) * result[2];
			return new int[] { gcd, x, y };
		}
	}

	// Hàm tìm nghịch đảo modular
	public static int modularMultiplicativeInverse(int e, int phi_n) throws IllegalArgumentException {
		int[] result = extendedEuclideanAlgorithm(e, phi_n);
		int gcd = result[0];
		int x = result[1];

		if (gcd != 1) {
			throw new IllegalArgumentException("e và phi không cùng nguyên tố với nhau,  nghịch đảo modular không tồn tại.");
		} else {
			return (x % phi_n + phi_n) % phi_n;
		}
	}

	private static int modPow(int c, int exp, int mod) {
		int result = 1;
		c %= mod; 
		while (exp > 0) {
			if ((exp & 1) == 1) { 
				result = (int) ((long) result * c % mod); 
			}
			c = (int) ((long) c * c % mod); 
			exp >>= 1; 
		}
		return result;
	}

	public int[] MaHoaRSA(int n, int e, String plaintext) {
		return plaintext.chars() 
				.map(c -> modPow(c, e, n)) 
				.toArray(); 
	}

	public String GiaiMaRSA(int n, int d, int[] ciphertext) {
		return Arrays.stream(ciphertext) 
				.map(c -> modPow(c, d, n)) 
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append) 
				.toString();
	}

	public void Taokhoanew() {
		TaoKhoa rsa = new TaoKhoa();
		int[] pq = rsa.taopq();
		p = pq[0];
		q = pq[1];
		n = p * q;
		phi = (p - 1) * (q - 1);

		e = rsa.chonE(phi);

		d = rsa.modularMultiplicativeInverse(e, phi);
		
		khoaPublic = new int[] {n,e};
		khoaPrivate = new int[] {n,d};
	}

	

}