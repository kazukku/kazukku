//幸運な一日
//新年を迎えたあなたは毎日占いをすることに決めました。
//
//あなたは 0 から 9 までの数字が書かれたカード 10 枚を 4 セット (計 40 枚) 持っており、毎朝これらのカードを引いてその日の運勢を占います。
//
//
//4 セットのカードの束から 1 枚ずつカードを引いたとき、出た数字を並べ替えてその日の日付が作れるとき、その日はあなたにとって幸運な一日になるでしょう。なお、日付を作る際には 1 桁の月日は 0 埋め 2 桁で表すものとします。
//
//
//たとえば、2 月 14 日に引いたカードの数字が 2, 4, 1, 0 だった場合、これらの数字を並べ替えて 0214 が作れるので、その日は幸運になります。
//
//また、11 月 22 日に引いたカードの数字が 1, 2, 1, 1 だった場合、これらの数字を並べ替えても 1122 を作ることができないので、その日は幸運ではありません。
//
//この占いは何度やり直しても構いません。
//
//すなわち、幸運な一日を手に入れるために、何度でもカードを引き直すことができるのです。
//
//
//あなたは、幸運な一日を手に入れるためにカードを何回引けばいいのかが気になりました。
//
//今日の日付と引いたカードの数字を表す擬似乱数が与えられるので、カードを何回引けば幸運になれるのかを計算するプログラムを書いてください。
//
//なお、10,000 回以内の引き直しで幸運になれる場合のみが入力で与えられるものとします。
//
//
//引いたカードの数字を表す擬似乱数は次のように生成されます。
//
//4 種類の数列 w_n, x_n, y_n, z_n (n = 0, 1, 2, ...) を次のように定めます。
//
//・w_{n+1} = (a_1 * w_n + b_1) mod m_1
//
//・x_{n+1} = (a_2 * x_n + b_2) mod m_2
//
//・y_{n+1} = (a_3 * y_n + b_3) mod m_3
//
//・z_{n+1} = (a_4 * z_n + b_4) mod m_4
//
//
//ここで、a_i, b_i, m_i (1 ≦ i ≦ 4) は入力で与えられる整数で、w_0 = x_0 = y_0 = z_0 = 0 とします。
//
//また、mod m_i は m_i で割った余りを表します。
//
//このとき、n 回目に引いた 4 枚のカードの数字はそれぞれ w_n mod 10, x_n mod 10, y_n mod 10, z_n mod 10 で与えられます。(n = 1, 2, ...)
//入力される値
//入力は以下のフォーマットで与えられます。
//
//M D
//a_1 a_2 a_3 a_4
//b_1 b_2 b_3 b_4
//m_1 m_2 m_3 m_4
//・1 行目に今日の日付を表す二つの整数 M, D がこの順で半角スペース区切りで与えられます。
//　これは今日の日付が M 月 D 日であることを表します。
//・続く 2〜4 行目には引いたカードの数字を計算するための擬似乱数のパラメータが与えられます。
//　・2 行目には 4 つの整数 a_1, a_2, a_3, a_4 がこの順で半角スペース区切りで与えられます。
//　・3 行目には 4 つの整数 b_1, b_2, b_3, b_4 がこの順で半角スペース区切りで与えられます。
//　・3 行目には 4 つの整数 m_1, m_2, m_3, m_4 がこの順で半角スペース区切りで与えられます。
//・入力は合計で 4 行となり、入力値最終行の末尾に改行が１つ入ります。

//期待する出力
//幸運になるために必要なカードを引く回数を出力してください。
//
//
//入力の最後に改行を 1 つ入れ、余計な文字、空行を含んではいけません。
//
//条件
//すべてのテストケースにおいて、以下の条件をみたします。
//
//・入力されるすべての数値は整数
//・1 ≦ M ≦ 12
//・1 ≦ D ≦ 31
//・M 月 D 日は実際に存在する日付である
//・1 ≦ a_i ≦ 100 (1 ≦ i ≦ 4)
//・0 ≦ b_i ≦ 100 (1 ≦ i ≦ 4)
//・1 ≦ m_i ≦ 10,000 (1 ≦ i ≦ 4)
//・カードを 10,000 回引き直す間に必ず幸運になれる

import java.util.Scanner;

public class B037 {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
        String line= sc.nextLine();
        String[] splitted = line.split(" ");
        int[] date = {Integer.parseInt(splitted[0]), Integer.parseInt(splitted[1])};

        line = sc.nextLine();
        splitted = line.split(" ");
        int[] a = new int[splitted.length];
        for(int i = 0; i < a.length; i++){
            a[i] = Integer.parseInt(splitted[i]);
        }

        line = sc.nextLine();
        splitted = line.split(" ");
        int[] b = new int[splitted.length];
        for(int i = 0; i < b.length; i++){
            b[i] = Integer.parseInt(splitted[i]);
        }

        line = sc.nextLine();
        splitted = line.split(" ");
        int[] m = new int[splitted.length];
        for(int i = 0; i < m.length; i++){
            m[i] = Integer.parseInt(splitted[i]);
        }
        
        int[] w = new int[10000];
        int[] x = new int[10000];
        int[] y = new int[10000];
        int[] z = new int[10000];
        w[0] = 0;   x[0] = 0;   y[0] = 0;   z[0] = 0;
        
        for(int i = 1; i < 10000; i++){
            w[i] = (w[i-1] * a[0] + b[0]) % m[0];
            x[i] = (x[i-1] * a[1] + b[1]) % m[1];
            y[i] = (y[i-1] * a[2] + b[2]) % m[2];
            z[i] = (z[i-1] * a[3] + b[3]) % m[3];
            int modw = w[i]%10;
            int modx = x[i]%10;
            int mody = y[i]%10;
            int modz = z[i]%10;
            if(
            (10 * modw + modx == date[0] && 10 * mody + modz == date[1]) ||
            (10 * modw + modx == date[0] && 10 * modz + mody == date[1]) ||
            (10 * modw + mody == date[0] && 10 * modx + modz == date[1]) ||
            (10 * modw + mody == date[0] && 10 * modz + modx == date[1]) ||
            (10 * modw + modz == date[0] && 10 * mody + modx == date[1]) ||
            (10 * modw + modz == date[0] && 10 * modx + mody == date[1]) ||
            
            (10 * modx + modw == date[0] && 10 * mody + modz == date[1]) ||
            (10 * modx + modw == date[0] && 10 * modz + mody == date[1]) ||
            (10 * modx + mody == date[0] && 10 * modw + modz == date[1]) ||
            (10 * modx + mody == date[0] && 10 * modz + modw == date[1]) ||
            (10 * modx + modz == date[0] && 10 * modw + mody == date[1]) ||
            (10 * modx + modz == date[0] && 10 * mody + modw == date[1]) ||
            
            (10 * mody + modw == date[0] && 10 * modx + modz == date[1]) ||
            (10 * mody + modw == date[0] && 10 * modz + modx == date[1]) ||
            (10 * mody + modx == date[0] && 10 * modw + modz == date[1]) ||
            (10 * mody + modx == date[0] && 10 * modz + modw == date[1]) ||
            (10 * mody + modz == date[0] && 10 * modw + modx == date[1]) ||
            (10 * mody + modz == date[0] && 10 * modx + modw == date[1]) ||
            
            (10 * modz + modw == date[0] && 10 * modx + mody == date[1]) ||
            (10 * modz + modw == date[0] && 10 * mody + modx == date[1]) ||
            (10 * modz + modx == date[0] && 10 * modw + mody == date[1]) ||
            (10 * modz + modx == date[0] && 10 * mody + modw == date[1]) ||
            (10 * modz + mody == date[0] && 10 * modw + modx == date[1]) ||
            (10 * modz + mody == date[0] && 10 * modx + modw == date[1])){
                System.out.println(i);
                break;
            }
        }
	}
}
