
public class Q03 {
	public static void main(String[] args){
		ans1();
//		ans2();
	}
	
	public static void ans1(){
		int cardnum = 100;
		boolean[] card = new boolean[cardnum];
		for(int i = 2; i <= cardnum; i++){
			int j = i - 1;
			while(j < cardnum){
				if(card[j] == true){
					card[j] = false;
				}else{
					card[j] = true;
				}
				j += i;
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < cardnum; i++){
			if(!card[i]){
				if(sb.toString().equals("")){
					sb.append(i+1);
				}else{
					sb.append(", ");
					sb.append(i+1);
				}
			}
		}
		System.out.println(sb.toString());
	}
	
	public static void ans2(){//未完
		int cardnum = 10;
		boolean[] card = new boolean[cardnum];
		for(int i = 1; i <= cardnum; i++){
			for(int j = 1; j <= cardnum; j++){
				if(i % j == 0){
					card[i-1] = true;
				}
			}
			if(card[i-1]){
				System.out.println(i);
			}
		}
	}
}
