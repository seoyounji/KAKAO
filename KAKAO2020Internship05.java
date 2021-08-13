package kakao2020;

import java.util.ArrayList;
import java.util.Stack;

//2020 카카오 인턴십 / 동굴 탐험
public class KAKAO2020Internship05 {

	
	public boolean solution(int n, int[][] path, int[][] order) {
		
		ArrayList<Integer>[] list = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			list[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < path.length; i++) {
			list[path[i][0]].add(path[i][1]);
			list[path[i][1]].add(path[i][0]);
		}
		boolean[] visit = new boolean[n];
		int[] before = new int[n];
		for (int i = 0; i < order.length; i++) {
			before[order[i][1]] = order[i][0];
		}

		if(before[0] != 0) return false;
		
		visit[0] = true;
		int[] after = new int[n];
		
		Stack<Integer> st = new Stack<>();
		st.add(0);
		for(int i:list[0]) st.add(i);
		
		while(!st.isEmpty()) {
			int now = st.pop();
			
			if(visit[now]) continue;
			
			if(!visit[before[now]]) {
				after[before[now]] = now;
				continue;
			}
			
			visit[now] = true;
			for(int i:list[now]) st.add(i);
			st.add(after[now]);
		}

		for(boolean f:visit) {
			if(!f) return false;
		}
		return true;
	}

}
