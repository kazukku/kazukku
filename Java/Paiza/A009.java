//内部が格子状の正方形の区画に分けられ、一部の区画に鏡が配置された箱を考えます。
//今、その箱を上部から眺めているものとし、箱の高さは考えないことにします。
//鏡は区画の対角線上に配置され、1つの区画には1枚の鏡を配置することができます。
//
//鏡のない区画を'_' 、端点が区画の右上および左下の頂点であるような対角線の上に配置された鏡を'/'、もう一方の対角線上に配置された鏡を'\' で表すこととする。
//
//高さが3, 幅が5 の箱の例として次があげられます。
//(環境によりバックスラッシュが円マークで表示される可能性があります)
//__\_/
//___/_
//\/\_/
//さて、箱の左上の区画に対し、箱の左側の外部から箱の内部に向けてビームを撃つことを考えます。
//
//まず、ビームは右に向かって真っ直ぐ飛び、鏡がない区画は通過します。
//鏡がある区画に入るとビームは鏡の向きに従って反射し、90°角度を変えて同様に進み、反射後も必ず真っ直ぐ進みます。
//また、ビームは箱の外周上に到達すると外へ飛び出すようになっており、箱の内部に打ち込まれた後、外部に飛び出るまで反射を続け飛び回ります。
//
//
//箱の高さHと幅W、および箱の内部の状態が与えられるので、箱の内部に向けてビームが撃たれてから箱の外部に飛び出るまでにビームが箱の中の区画を通過する回数を答えてください。
//
//期待する出力
//左側から左上の箱の内部に向けてビームが撃たれてから箱の外部に飛び出るまでにビームが箱の中の区画を通過する回数を出力して下さい。
//
//最後は改行し、余計な文字、空行を含んではいけません。
//条件
//すべてのテストケースで以下の条件を満たします。
//
//1 ≦ H ≦ 100
//1 ≦ W ≦ 100



import java.util.Scanner;

public class A009 {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] split = line.split(" ");
        int[] scale = {Integer.parseInt(split[0]), Integer.parseInt(split[1])};
//       System.out.println(scale[0] + " " + scale[1]);
        String[][] data = new String[scale[0]][scale[1]];
        for(int j = 0; j < scale[0]; j++){
            line = sc.nextLine();
            String[] ireru = line.split("");
            for(int i = 0; i < scale[1]; i++){
                data[j][i] = ireru[i];
            }
        }
        
        int result = 0;
        int gyou = 0;
        int retsu = 0;
        String muki = "migi";
        while(true){
            result++;
            if(data[gyou][retsu].equals("_") && muki.equals("migi")){
                retsu++;
            }else if(data[gyou][retsu].equals("_") && muki.equals("shita")){
                gyou++;
            }else if(data[gyou][retsu].equals("_") && muki.equals("hidari")){
                retsu--;
            }else if(data[gyou][retsu].equals("_") && muki.equals("ue")){
                gyou--;
            }
            
            else if(data[gyou][retsu].equals("/") && muki.equals("migi")){
                muki = "ue";
                gyou--;
            }else if(data[gyou][retsu].equals("/") && muki.equals("shita")){
                muki = "hidari";
                retsu--;
            }else if(data[gyou][retsu].equals("/") && muki.equals("hidari")){
                muki = "shita";
                gyou++;
            }else if(data[gyou][retsu].equals("/") && muki.equals("ue")){
                muki = "migi";
                retsu++;
            }
            
            else if(muki.equals("migi")){
                muki = "shita";
                gyou++;
            }else if(muki.equals("shita")){
                muki = "migi";
                retsu++;
            }else if(muki.equals("hidari")){
                muki = "ue";
                gyou--;
            }else if(muki.equals("ue")){
                muki = "hidari";
                retsu--;
            }
            
            if(gyou < 0 || gyou >= scale[0] || retsu < 0 || retsu >= scale[1]){
                break;
            }
        }
        System.out.println(result);
	}
}
