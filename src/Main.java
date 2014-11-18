import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		int N = 100000;

		new Main(N);
	}

	public Main(int N) {

		// double p = probability(10, N);

		int cAmount = 0;
		double C = 0;

		for (int n = 5; n < N; n++) {

			double sqrtN = Math.sqrt(n);
			Random rnd = new Random();

			int i = 0;

			double prob;
			while (true) {
				i++;
				prob = probability(i, n);
				if (prob >= .77) {
					break;
				}
			}

			double c = i / sqrtN;

			C += c;
			cAmount++;
			// System.out.println(i);
			// System.out.println(c);

		}

		C /= cAmount;

		System.out.println(C);

	}

	private double probability(int T, int N) {
		BigInteger N_T = BigInteger.ONE;

		for (int i = 1; i <= N - T; i++) {
			N_T = N_T.multiply(new BigInteger("" + i));
		}

		BigInteger Nfac = new BigInteger(N_T.toString());

		for (int i = (N - T) + 1; i <= N; i++) {
			Nfac = Nfac.multiply(new BigInteger("" + i));
		}

		BigInteger bigN = new BigInteger("" + N);

		BigInteger NtoT = BigInteger.ONE;

		for (int i = 0; i < T; i++) {
			NtoT = NtoT.multiply(bigN);
		}

		BigDecimal t = new BigDecimal(Nfac);
		BigDecimal b = new BigDecimal(N_T.multiply(NtoT));

		BigDecimal div = t.divide(b, 2, RoundingMode.HALF_UP);
		// BigInteger div = Nfac.divide();

		return (1 - div.doubleValue());
	}

}
