package kakao_winter;

import java.util.ArrayList;
import java.util.Collections;

//2019 카카오 개발자 겨울 인턴십 / 튜플
public class KAKAO2019Internship01 {

	private static class point implements Comparable<point>{
		int length; int[] array;
		point(int length,int[] array) {
			this.length = length;
			this.array = array;
		}
		@Override
		public int compareTo(point o) {
			return this.length - o.length;
		}
	}
	
	public int[] solution(String s) {
		s = s.substring(1, s.length()-1);
		String[] array = s.split("},");
		
		for (int i = 0; i < array.length; i++) {
			array[i] = array[i].substring(1, array[i].length());
		}
		String tmp  = array[array.length-1];
		array[array.length-1] = tmp.substring(0, tmp.length()-1);
		
		ArrayList<point> list = new ArrayList<>();
		
		for (int i = 0; i < array.length; i++) {
			String[] temp = array[i].split(",");
			int[] tt = new int[temp.length];
			for (int j = 0; j < temp.length; j++) {
				tt[j] = Integer.parseInt(temp[j]);
			}
			point ttt = new point(temp.length,tt);
			list.add(ttt);
		}
		
		Collections.sort(list);
		
		int[] answer = new int[list.get(list.size()-1).array.length];
		int index = 0;
		
		boolean[] visited = new boolean[100001];
		for(point i : list) {
			int[] t = i.array;
			for (int j = 0; j < i.length; j++) {
				int x = t[j];
				if(visited[x]) continue;
				answer[index++] = x;
				visited[x] = true;
			}
		}
		return answer;
	}

}
