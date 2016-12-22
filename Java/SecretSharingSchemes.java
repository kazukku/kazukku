package fall2015;

public class SecretSharingSchemes {
	public static void main(String[] args) {
		new SecretSharingSchemes().start();
	}

	final int k = 3;
	final int n = 5;
	final int p = 257;
	int s = setRandom(p);

	void start() {
		int[] a = new int[k - 1];
		int kotae = 0;
		setint(a, p);
		int[] share = new int[n + 1];
		share[0] = 0;
		for (int i = 1; i < 6; i++) {
			share[i] = makeshare(a, i);
			System.out.println("share[" + i + "] = " + share[i]);
		}
		int select = 1;// /この値でシェアを選択できる。
		for (int i = 1 + select; i < k + 1 + select; i++) {
			int i_shita = 1;
			int i_ue = 1;
			for (int j = 1 + select; j < k + 1 + select; j++) {
				if (i != j) {
					i_ue *= hanten(0 - j);
					i_shita *= hanten(i - j);
				}
			}
			kotae += share[i] * ((i_ue % p) * inv(i_shita, p));
		}
		kotae = kotae % p;
		System.out.println("s = \t" + s);
		System.out.println("ans = \t" + kotae);
	}

	int hanten(int x) {
		while (x < 0) {
			x += p;
		}
		return x;
	}

	int makeshare(int[] a, int i) {
		int ans = s + a[0] * i + a[1] * i * i;
		ans = ans % p;
		return ans;
	}

	int setRandom(int p) {
		int num;
		while (true) {
			num = (int) (Math.random() * 1000);
			if (num < p) {
				break;
			}
		}
		return num;
	}

	void setint(int[] a, int p) {
		for (int i = 0; i < k - 1; i++) {
			a[i] = setRandom(p);
		}
	}
	///////////////
	///乗法逆元
	/////////////////
	int inv(int x, int p) {
		int[] A = new int[2];
		int[] B = new int[2];
		int b = 0, q;
		A[0] = p;
		A[1] = x;
		B[0] = 0;
		B[1] = 1;
		while (A[1 - b] != 1) {
			q = A[b] / A[1 - b];
			A[b] = A[b] % A[1 - b];
			B[b] = (B[b] - q * B[1 - b]) % p;
			b = 1 - b;
		}
		if (B[1 - b] < 0) {
			B[1 - b] = B[1 - b] + p;
		}
		return B[1 - b];
	}
}