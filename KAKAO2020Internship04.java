package kakao2020;

import java.util.LinkedList;
import java.util.Queue;

//2020 카카오 인턴십 / 경주로 건설
public class KAKAO2020Internship04 {

	private static int answer;
	
	private static class point {
		int x; int y; boolean[][] visited; int cost; int direction;
		point(int x,int y,boolean[][] visited,int cost,int direction) {
			this.x = x;
			this.y = y;
			this.visited = visited;
			this.cost = cost;
			this.direction = direction;
		}
	}
	
	private static boolean[][] copy(boolean[][] origin,int N) {
		boolean[][] result = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				result[i][j] = origin[i][j];
			}
		}
		return result;
	}
	private static void BFS(int[][] board,int N,int[][] map) {
		int[] dx = {-1,0,1,0};
		int[] dy = {0,-1,0,1};
		Queue<point> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		visited[0][0] = true;
		if(board[0][1]==0) {
			boolean[][] v1 = copy(visited,N);
			v1[0][1] = true;
			map[0][1] = 100;
			queue.offer(new point(0,1,v1,100,0));
		}
		if(board[1][0]==0) {
			boolean[][] v2 = copy(visited,N);
			v2[1][0] = true;
			map[1][0] = 100;
			queue.offer(new point(1,0,v2,100,1));
		}
		
		while(!queue.isEmpty()) {
			point now = queue.poll();
			if(now.cost >= answer) continue;
			if(now.x == N-1 && now.y == N-1) {
				answer = Math.min(answer, now.cost);
			}
			else {
				for (int i = 0; i < 4; i++) {
					int x1 = now.x + dx[i];
					int y1 = now.y + dy[i];
					if(x1<0 || y1<0 || x1 >=N || y1>=N) continue;
					if(now.visited[x1][y1]) continue;
					if(board[x1][y1]==1) continue;
					boolean[][] v = copy(now.visited,N);
					v[x1][y1] = true;
					switch(i) {
					case 0:
						if(now.direction==0) {
							if(map[x1][y1]==0 || map[x1][y1]>=now.cost+600) {
								map[x1][y1] = now.cost+600;
								queue.offer(new point(x1,y1,v,now.cost+600,1));
							}
						}
						else {
							if(map[x1][y1]==0 || map[x1][y1]>=now.cost+100) {
								map[x1][y1] = now.cost+100;
								queue.offer(new point(x1,y1,v,now.cost+100,1));
							}
						}
						break;
					case 1:
						if(now.direction==0) {
							if(map[x1][y1]==0 || map[x1][y1]>=now.cost+100) {
								map[x1][y1] = now.cost+100;
								queue.offer(new point(x1,y1,v,now.cost+100,0));
							}
						}
						else {
							if(map[x1][y1]==0 || map[x1][y1]>=now.cost+600) {
								map[x1][y1] = now.cost+600;
								queue.offer(new point(x1,y1,v,now.cost+600,0));
							}
						}
						break;
					case 2:
						if(now.direction==0) {
							if(map[x1][y1]==0 || map[x1][y1]>=now.cost+600) {
								map[x1][y1] = now.cost+600;
								queue.offer(new point(x1,y1,v,now.cost+600,1));
							}
						}
						else {
							if(map[x1][y1]==0 || map[x1][y1]>=now.cost+100) {
								map[x1][y1] = now.cost+100;
								queue.offer(new point(x1,y1,v,now.cost+100,1));
							}
						}
						break;
					case 3:
						if(now.direction==0) {
							if(map[x1][y1]==0 || map[x1][y1]>=now.cost+100) {
								map[x1][y1] = now.cost+100;
								queue.offer(new point(x1,y1,v,now.cost+100,0));
							}
						}
						else {
							if(map[x1][y1]==0 || map[x1][y1]>=now.cost+600) {
								map[x1][y1] = now.cost+600;
								queue.offer(new point(x1,y1,v,now.cost+600,0));
							}
						}
						break;
					}
				}
			}
		}
	}
	
	public int solution(int[][] board) {
		int N = board.length;
		answer = Integer.MAX_VALUE;
		int[][] map = new int[N][N];
		BFS(board,N,map);
		return answer;
	}

}
