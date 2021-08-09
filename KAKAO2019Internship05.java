package kakao2019;

//2019 카카오 개발자 겨울 인턴십 - 징검다리 건너기
public class KAKAO2019Internship05 {
	
	private static boolean possible(int[] stones,int k,int limit) {
		int num = 0;
		int size = 0;
		for (int i = 0; i < stones.length; i++) {
			if(stones[i]<k) {
				num++;
			} else {
				size = Math.max(size, num);
				num = 0;
			}
		}
		size = Math.max(size, num);
		return size<limit;
	}
	
	public static void main(String[] args) {
		int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
		int k = 3;
		
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(int i:stones) {
			min = Math.min(min, i);
			max = Math.max(max, i);
		}
		int answer = min;
		
		while(min<=max) {
			int mid = (min+max)/2;
			if(possible(stones,mid,k)) {
				answer = mid;
				min = mid+1;
			}
			else max = mid-1;
		}

		System.out.println(answer);
	}

}
