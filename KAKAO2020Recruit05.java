package kakao2020;

import java.util.ArrayList;
import java.util.Collections;

//2020 카카오 블라인드 채용 / 기둥과 보 설치
public class KAKAO2020Recruit05 {

	private static class point {
		boolean t; boolean b; boolean l; boolean r;
		point(boolean t,boolean b,boolean l,boolean r) {
			this.t = t;
			this.b = b;
			this.l = l;
			this.r = r;
		}
	}
	
	private static class num implements Comparable<num>{
		int x;int y;int w;
		num(int x,int y,int w) {
			this.x = x;
			this.y = y;
			this.w = w;
		}
		@Override
		public int compareTo(num o) {
			if(this.x == o.x) {
				if(this.y == o.y) return this.w-o.w;
				return this.y-o.y;
			}
			return this.x-o.x;
		}
	}
    private static point[][] copy(point[][] map,int n) {
		point[][] cp = new point[n+1][n+1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				cp[i][j] = new point(map[i][j].t,map[i][j].b,map[i][j].l,map[i][j].r);
			}
		}
		return cp;
	}
	private static boolean possible(point[][] map,int[] tmp,int n) {
		int x = n-tmp[1];
		int y = tmp[0];
		int a = tmp[2];
		int b = tmp[3];
		
		if(b==1) {
			if(a==0) { 
				//바닥에 설치하는가?
				if(x==n) return true;
				//보 끝 위에 설치하는가?
				if(map[x][y].l || map[x][y].r) return true;
				//다른 기둥 위에 설치하는가?
				if(map[x][y].b) return true;
				//아니라면 실패
				return false;
			}
			else {
				//기둥 위에 설치하는가?
				if(map[x][y].b) return true;
				if(y+1<=n && map[x][y+1].b) return true;
				//양쪽에 다른 보가 있는가?
				if(map[x][y].l && y+1<=n && map[x][y+1].r) return true;
				//아니라면 실패
				return false;
			}
		}
		else { 
			point[][] cp = copy(map,n);
			if(a==0) { //기둥 삭제
				cp[x][y].t = false;
				cp[x-1][y].b = false;
			}
			else { //보 삭제
				cp[x][y].r = false;
				cp[x][y+1].l = false;
			}
			boolean flag = false;
			for (int i = 0; i <= n; i++) {
				for (int j = 0; j <= n; j++) {
					flag = true;
					if(cp[i][j].t) { //기둥이라면
						flag = false;
						if(i==n) flag = true;
						if(cp[i][j].l || cp[i][j].r) flag = true;
						if(cp[i][j].b) flag = true;
					}
					if(cp[i][j].r) {
						flag = false;
						if(cp[i][j].b) flag = true;
						if(j+1<=n && cp[i][j+1].b) flag = true;
						if(cp[i][j].l && j+1<=n && cp[i][j+1].r) flag = true;
					}
					if(!flag) return false;
				}
			}
			return true;
		}
	}
	
    public int[][] solution(int n, int[][] build_frame) {
        point[][] map = new point[n+1][n+1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				map[i][j] = new point(false,false,false,false);
			}
		}
		
		for (int i = 0; i < build_frame.length; i++) {
			int[] tmp = build_frame[i];
			int x = n-tmp[1];
			int y = tmp[0];
			int a = tmp[2];
			int b = tmp[3];
			
			if(possible(map,tmp,n)) {
				if(b==0) { //삭제
					if(a==0) { //기둥
						map[x][y].t = false;
						map[x-1][y].b = false;
					}
					else { //보
						map[x][y].r = false;
						map[x][y+1].l = false;
					}
				}
				else { //설치
					if(a==0) { //기둥
						map[x][y].t = true;
						map[x-1][y].b = true;
					}
					else { //보
						map[x][y].r = true;
						map[x][y+1].l = true;
					}
				}
			}
		}
		
		ArrayList<num> ret = new ArrayList<>();
		
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				if(map[i][j].t) ret.add(new num (j,n-i,0));
				if(map[i][j].r) ret.add(new num (j,n-i,1));
			}
		}
		
		Collections.sort(ret);
		
		int[][] answer = new int[ret.size()][3];
		for (int i = 0; i < ret.size(); i++) {
			answer[i] = new int[] {ret.get(i).x,ret.get(i).y,ret.get(i).w};
		}
        return answer;
    }
    
}
