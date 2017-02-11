//チェックディジット
//クレジットカード番号は16桁の番号で表すことができますが、この番号は以下の性質を持っています。
//
//一番右の桁を1桁目として、
//
//・偶数桁の数字をそれぞれ2倍し総和をとったものをeven 
//（ただし、2倍したあと2桁の数字になるものは、1の位と10の位の数を足して1桁の数字にしたあと、総和をとる）
//・奇数桁の数字の総和をとったものをodd
//とすると、even + odd は10 で必ず割り切れます。
//
//1桁目がX と書かれた16桁の番号が複数与えられるので、それぞれに対し、上記性質をみたすようにX に入る数字を求めてください。
//入力される値
//入力は以下のフォーマットで与えられます。
//
//n　　　#入力されるクレジットカードの総数
//a_1　　#1番目のクレジットカード番号
//a_2　　#....
//a_3　　#....
//...
//a_n　　#n番目のクレジットカード番号
//期待する出力
//それぞれのa_i に対し、X に入る数字を一行に出力してください。出力は全部でn 行になります。
//
//最後は改行し、余計な文字、空行を含んではいけません。
//条件
//すべてのテストケースで以下の条件を満たします。
//
//1 ≦ n ≦ 100
//a_i (1 ≦ i ≦ n) は長さ16の文字列です。
//1文字目から15文字目は0から9までのいずれかの数字が書かれており、16文字目はX (アルファベット大文字のエックス) が書かれています。

import java.util.Scanner;
public class B012 {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        int num = Integer.parseInt(line);
        for(int i = 0; i < num; i++){
            line = sc.nextLine();
            String[] cardnum = line.split("");
            int[] i_cardnum = new int[17];
            for(int j = 1; j < cardnum.length; j++){
                i_cardnum[j+1] = Integer.parseInt(cardnum[15-j]);
            }
            int even = 0;
            for(int j = 1; j < 9; j++){
                i_cardnum[j*2] = 2 * i_cardnum[j*2];
                if(i_cardnum[j*2] >= 10){
                    i_cardnum[j*2] = i_cardnum[j*2] - 9;
                }
                even += i_cardnum[j*2];
            }
            int odd = 0;
            for(int j = 1; j < 8; j++){
                odd += i_cardnum[j*2+1];
            }
            int result = (even + odd)%10;
            if(result != 0){
                result = 10 - result;
            }
            System.out.println(result);
        }
	}
}
