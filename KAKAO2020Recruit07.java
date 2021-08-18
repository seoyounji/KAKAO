package kakao2020;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//2020 카카오 블라인드 채용 / 블록 이동하기
public class KAKAO2020Recruit07 {

	private static int[] spinnable(int[][] board,int[] now,int direction) {
		int n = board.length;
		int[] b = {-1,-1,-1,-1};
		switch(direction) {
		case 1:
			if(now[0]+1<n && board[now[0]+1][now[1]]==0 && board[now[0]+1][now[3]]==0) return new int[] {now[0]+1,now[1],now[0],now[1]};
			else return b;
		case 2:
			if(now[0]+1<n && board[now[0]+1][now[3]]==0 && board[now[0]+1][now[1]]==0) return new int[] {now[0]+1,now[3],now[2],now[3]};
			else return b;
		case 3:
			if(now[2]-1>=0 && board[now[2]-1][now[3]]==0 && board[now[2]-1][now[1]]==0) return new int[] {now[2],now[3],now[0]-1,now[3]};
			else return b;
		case 4:
			if(now[2]-1>=0 && board[now[2]-1][now[3]]==0 && board[now[2]-1][now[1]]==0) return new int[] {now[0],now[1],now[0]-1,now[1]};
			else return b;
		case 5:
			if(now[3]+1<n && board[now[2]][now[3]+1]==0 && board[now[0]][now[1]+1]==0) return new int[] {now[2],now[3]+1,now[2],now[3]};
			else return b;
		case 6:
			if(now[1]+1<n && board[now[0]][now[1]+1]==0 && board[now[2]][now[3]+1]==0) return new int[] {now[0],now[1]+1,now[0],now[1]};
			else return b;
		case 7:
			if(now[1]-1>=0 && board[now[0]][now[1]-1]==0 && board[now[2]][now[3]-1]==0) return new int[] {now[0],now[1],now[0],now[1]-1};
			else return b;
		case 8:
			if(now[3]-1>=0 && board[now[2]][now[3]-1]==0 && board[now[0]][now[1]-1]==0) return new int[] {now[2],now[3],now[2],now[3]-1};
			else return b;
		}
		return b;
	}
	
	private static boolean contain(HashSet<int[]> visited,int[] v) {
		for(int[] i:visited) {
			if(i[0]==v[0] && i[1]==v[1] && i[2]==v[2] && i[3]==v[3]) return true;
		}
		return false;
	}
	
	private static int BFS(int n,int[][] board) {
		HashSet<int[]> visited = new HashSet<>(); //x1,y1,x2,y2,방향
		//x+y 합이 큰걸 먼저 쓰자
		int[] start = {0,1,0,0};
		
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(start);
		visited.add(start);
		int time = 0;
		outer:
		while(!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] now = queue.poll();
				if(now[0]==n-1 && now[1]==n-1) break outer;
				
				//만약 2개 y좌표가 같다면 세로 형태, 2개 x좌표가 같다면 가로 형태
				if(now[1]==now[3]) {
					for (int j = 5; j <= 8; j++) {
						int[] spin = spinnable(board,now,j);
						if(spin[0]==-1 && spin[1]==-1 && spin[2]==-1 && spin[3]==-1) continue;
						if(contain(visited,spin)) continue;
						queue.offer(spin);
						visited.add(spin);
					}
					int[] t1 = {now[2],now[3],now[2]-1,now[3]};
					if(now[2]-1>=0 && board[now[2]-1][now[3]]==0 && !contain(visited,t1)) {
						queue.offer(t1);
						visited.add(t1);
					}
					int[] t2 = {now[0],now[1]+1,now[2],now[3]+1};
					if(now[1]+1<n && board[now[0]][now[1]+1]==0 && board[now[2]][now[3]+1]==0 && !contain(visited,t2)) {
						queue.offer(t2);
						visited.add(t2);
					}
					int[] t3 = {now[0]+1,now[1],now[0],now[1]};
					if(now[0]+1<n && board[now[0]+1][now[1]]==0 && !contain(visited,t3)) {
						queue.offer(t3);
						visited.add(t3);
					}
					int[] t4 = {now[0],now[1]-1,now[2],now[3]-1};
					if(now[1]-1>=0 && board[now[0]][now[1]-1]==0 && board[now[2]][now[3]-1]==0 && !contain(visited,t4)) {
						queue.offer(t4);
						visited.add(t4);
					}
				}
				else {
					for (int j = 1; j <= 4; j++) {
						int[] spin = spinnable(board,now,j);
						if(spin[0]==-1 && spin[1]==-1 && spin[2]==-1 && spin[3]==-1) continue;
						if(contain(visited,spin)) continue;
						queue.offer(spin);
					}
					int[] t1 = {now[0]-1,now[1],now[2]-1,now[3]};
					if(now[0]-1>=0 && board[now[0]-1][now[1]]==0 && board[now[2]-1][now[3]]==0 && !contain(visited,t1)) {
						queue.offer(t1);
						visited.add(t1);
					}
					int[] t2 = {now[0],now[1]+1,now[0],now[1]};
					if(now[1]+1<n && board[now[0]][now[1]+1]==0 && !contain(visited,t2)) {
						queue.offer(t2);
						visited.add(t2);
					}
					int[] t3 = {now[0]+1,now[1],now[2]+1,now[3]};
					if(now[0]+1<n && board[now[0]+1][now[1]]==0 && board[now[2]+1][now[3]]==0 && !contain(visited,t3)) {
						queue.offer(t3);
						visited.add(t3);
					}
					int[] t4 = {now[2],now[3],now[2],now[3]-1};
					if(now[3]-1>=0 && board[now[2]][now[3]-1]==0 && !contain(visited,t4)) {
						queue.offer(t4);
						visited.add(t4);
					}
					
				}
			}
			time++;
		}
		return time;
	}
	
	public int solution(int[][] board) {
		int n = board.length;
		
		return BFS(n,board);
	}

}
