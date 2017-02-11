
import java.math.BigInteger;
import java.util.Random;


public class RSA {
	public static void main(String[] args){
		new RSA().start();
	}
	BigInteger p;	BigInteger q;
	BigInteger n;	BigInteger l;
	BigInteger e;	BigInteger d;
	BigInteger m;	BigInteger c;
	BigInteger ans;
/*
	RSA暗号
	2つの大きな素数p,qをランダムに選びn = p * qとする。
	l = lcm(p-1, q-1)と互いに素な数e(1 <= e < l)を選ぶ。
	e * d = 1 (mod l)となるd(1 <= d < l)を計算する。
	公開鍵を(e, n),秘密鍵をdとする。

	暗号化:メッセージM(0 <= m <= n)に対する暗号文c
		c = m^e (mod n)
	複号:暗号文C(0 <= C <= n)に対する平文m
		m = c^d (mod n)
*/

	void start(){
		m = new BigInteger("12344588");	///////////元の文章
		e = new BigInteger("65537"); //////大きな素数
//		set_PrimeNumber();
		set_using_library();
		set_l();
		set_d();
		c = m.modPow(e, n);
		ans = c.modPow(d, n);
		System.out.println("n\t= " + n);
		System.out.println("m\t= "+m);
		System.out.println("c\t= "+c);
		System.out.println("ans\t= " +ans);
	}
	
	//////////////
	///素数の選択(未完)
	//////////////
	void set_PrimeNumber(){
		while(true){
			int n = (int)(Math.random()*100);
			if(checkPrimeNumber(n)){
				String input = String.valueOf(n);
				p = new BigInteger(input);
				break;
			}
		}
		while(true){
			int n = (int)(Math.random()*100);
			if(checkPrimeNumber(n)){
				String input = String.valueOf(n);
				q = new BigInteger(input);
				break;
			}
		}
		System.out.println("p\t= " + p);
		System.out.println("q\t= " + q);
		n = p.multiply(q);
	}
	

	///////////////////////
	/////素数であることの確認
	/////////////////////////
	boolean checkPrimeNumber(int n){
		for(int i = 1; i < 5; i++){
			int a = n-i;
			int x = (int)(Math.pow(a, n-1));
			if(x%n != 1){
				return false;
			}
		}
		return true;
	}
	

	/////////////////////////
	///ライブラリを使って1024ビットの素数を生成
	///////////////////////
	void set_using_library(){
		Random x = new Random();
		p = BigInteger.probablePrime(1024, x);
		q = BigInteger.probablePrime(1024, x);
		n = p.multiply(q);
		System.out.println("p\t= " + p);
		System.out.println("q\t= " + q);
	}
	
	/////////////////
	//lを作成
	////////////////
	void set_l(){
		p = p.subtract(new BigInteger("1"));
		q = q.subtract(new BigInteger("1"));
		BigInteger g = p.gcd(q);
		BigInteger ab = p.multiply(q);
		l = ab.divide(g);
	}
		
	////////////////
	////dを作成
	////////////////
	void set_d(){
		d = e.modInverse(l);
	}
}
