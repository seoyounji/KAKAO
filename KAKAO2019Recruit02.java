package kakao2019;

import java.util.ArrayList;
import java.util.Collections;

//2019 카카오 블라인드 채용 / 실패율
public class KAKAO2019Recruit02 {

	private static class point implements Comparable<point>{
		int num; float failrate;
		point(int num,float failrate) {
			this.num = num;
			this.failrate = failrate;
		}
		@Override
		public int compareTo(point o) {
			if(this.failrate == o.failrate) return this.num-o.num;
			if(this.failrate > o.failrate) return -1;
			else return 1;
		}
	}
	
	public int[] solution(int N, int[] stages) {
        int[] under = new int[N+1];
		int[] up = new int[N+1];
		ArrayList<point> list = new ArrayList<>();
		for (int i = 0; i < stages.length; i++) {
			if(stages[i] == N+1) {
				for (int j = 1; j <= N; j++) {
					under[j]++;
					up[j]++;
				}
			} else {
				for (int j = 1; j <= stages[i]; j++) {
					under[j]++;
				}
				for (int j = 1; j < stages[i]; j++) {
					up[j]++;
				}
			}
		}
		int[] clear = new int[N+1];
		for (int i = 1; i <= N; i++) {
			clear[i] = under[i] - up[i];
		}
		for (int i = 1; i <= N; i++) {
			if(under[i]==0) list.add(new point(i,0));
			else list.add(new point(i,(float)clear[i]/under[i]));
		}
		Collections.sort(list);
        int[] answer = new int[N];
        int idx = 0;
        for(point n:list) answer[idx++] = n.num;
        return answer;
    }
}
