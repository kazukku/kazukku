
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
	RSA�Í�
	2�̑傫�ȑf��p,q�������_���ɑI��n = p * q�Ƃ���B
	l = lcm(p-1, q-1)�ƌ݂��ɑf�Ȑ�e(1 <= e < l)��I�ԁB
	e * d = 1 (mod l)�ƂȂ�d(1 <= d < l)���v�Z����B
	���J����(e, n),�閧����d�Ƃ���B

	�Í���:���b�Z�[�WM(0 <= m <= n)�ɑ΂���Í���c
		c = m^e (mod n)
	����:�Í���C(0 <= C <= n)�ɑ΂��镽��m
		m = c^d (mod n)
*/

	void start(){
		m = new BigInteger("12344588");	///////////���̕���
		e = new BigInteger("65537"); //////�傫�ȑf��
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
	///�f���̑I��(����)
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
	/////�f���ł��邱�Ƃ̊m�F
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
	///���C�u�������g����1024�r�b�g�̑f���𐶐�
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
	//l���쐬
	////////////////
	void set_l(){
		p = p.subtract(new BigInteger("1"));
		q = q.subtract(new BigInteger("1"));
		BigInteger g = p.gcd(q);
		BigInteger ab = p.multiply(q);
		l = ab.divide(g);
	}
		
	////////////////
	////d���쐬
	////////////////
	void set_d(){
		d = e.modInverse(l);
	}
}
