
public class Q06 {
	public static void main(String[] args){
		int result = 0;
		int num = 10000;
		for(int i = 1; i < num; i = i+2){
			int n = i+1;
			n = n * 3 + 1;
			while(true){
				if(n % 2 == 0){
					n /= 2;
				}else{
					n = n * 3 + 1;
				}
				if(n == i+1){
					result++;
					break;
				}else if(n == 1){
					break;
				}
			}
		}
		System.out.println(result);
	}
}
