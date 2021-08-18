package kakao2020;

import java.util.HashSet;

//2020 카카오 블라인드 채용 / 외벽 점검
public class KAKAO2020Recruit06 {

	private static HashSet<int[]> list;
	
	private static void perm(int[] dist,int[] output,boolean[] visited,int depth,int n) {
		if(depth == n) {
			int[] tmp = new int[n];
			for (int i = 0; i < n; i++) {
				tmp[i] = output[i];
			}
			list.add(tmp);
			return;
		}
		for (int i = 0; i < n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				output[depth] = dist[i];
				perm(dist,output,visited,depth+1,n);
				visited[i] = false;
			}
		}
	}
	
	private static int find(int s,boolean[] visit,int n,boolean flag) {
		while(true) {
			if(flag) {
				s++;
				if(s>=n) s-=n;
			}
			else {
				s--;
				if(s<0) s+=n;
			}
			if(visit[s]) return s;
		}
	}
	
	public int solution(int n, int[] weak, int[] dist) {
		int distance = (weak[0]+n) - weak[weak.length-1];
		int start = weak[weak.length-1];
		int end = weak[0];
		for (int i = 0; i < weak.length-1; i++) {
			int t = weak[i+1] - weak[i];
			if(distance < t) {
				distance = t;
				start = weak[i];
				end = weak[i+1];
			}
		}
		if(start>end) {
			int t = start;
			start = end;
			end = t;
		}
		
		boolean[] visit = new boolean[n];
		for (int i = 0; i < weak.length; i++) {
			visit[weak[i]] = true;
		}
		
		boolean flag = false; //시계 반대 방향으로 해야함 , true는 시계 방향
		for (int i = start+1; i < end; i++) {
			if(visit[i]) {
				flag = true;
				break;
			}
		}
		
		list = new HashSet<>();
		int[] output = new int[dist.length];
		boolean[] visited = new boolean[dist.length];
		perm(dist,output,visited,0,dist.length);
		
		int answer = Integer.MAX_VALUE;
		
		for(int[] i:list) {
			int now = start;
			for (int j = 0; j < i.length; j++) {
				
				if(flag) { 
					now += i[j];
					if(now>=n) now-=n;
				}
				else { 
					now -= i[j];
					if(now<0) now+=n;
				}
				now = find(now,visit,n,flag);
				if(now==start) {
					answer = Math.min(answer, j+1);
					break;
				}
			}
		}
		if(answer == Integer.MAX_VALUE) answer = -1;

		return answer;
	}

}
