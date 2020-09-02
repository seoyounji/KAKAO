package kakao2019;

//2019 카카오 블라인드 채용 - 무지의 먹방 라이브
public class KAKAO2019Recruit04 {

	public int solution(int[] food_times, long k) {
		int remain = food_times.length;
		int length = food_times.length;
		int answer = -1;
		int minus = (int)(k/remain);

		while(k>0 && remain>0 && minus>0) {
			for (int i = 0; i < length; i++) {
				if(food_times[i]==0) continue;
				if(food_times[i] <= minus) {
					k -= food_times[i];
					remain--;
					food_times[i] = 0;
				} else {
					k -= minus;
					food_times[i] -= minus;
				}
			}
			if(remain!=0) minus = (int) (k/remain);
		}
		if(k==0 || minus==0) {
			for (int j = 0; j < length; j++) {
				if(food_times[j]==0) continue;
				if(k==0) {
					answer = (j+1)%(length+1);
					break;
				} else {
					food_times[j]--;
					k--;
				}
			}
		}
		return answer;
	}

}
