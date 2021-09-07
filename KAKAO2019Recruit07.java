package kakao2019;

import java.util.LinkedList;
import java.util.Queue;

//2019 카카오 블라인드 채용 / 블록 게임
public class KAKAO2019Recruit07 {

	private static Queue<Integer> del;
	
	private static int judge(int[][] map,int x,int y,int num,int N,boolean[] numbers) {
		if(x+1<N && y+2<N && map[x+1][y]==num && map[x+1][y+1]==num && map[x+1][y+2]==num) {
			for (int i = 0; i < x+1; i++) {
				if(map[i][y+1]!=0) {
					if(numbers[map[i][y+1]]) return 2;
					else {
						del.offer(map[i][y+1]);
						return 1;
					}
				}
				if(map[i][y+2]!=0) {
					if(numbers[map[i][y+2]]) return 2;
					else {
						del.offer(map[i][y+2]);
						return 1;
					}
				}
			}
			return 11;
		}
		if(x+2<N && y-1>=0 && map[x+1][y]==num && map[x+2][y]==num && map[x+2][y-1]==num) {
			for (int i = 0; i < x+2; i++) {
				if(map[i][y-1]!=0) {
					if(numbers[map[i][y-1]]) return 2;
					else {
						del.offer(map[i][y-1]);
						return 1;
					}
				}
			}
			return 11;
		}
		if(x+2<N && y+1<N && map[x+1][y]==num && map[x+2][y]==num && map[x+2][y+1]==num) {
			for (int i = 0; i < x+2; i++) {
				if(map[i][y+1]!=0) {
					if(numbers[map[i][y+1]]) return 2;
					else {
						del.offer(map[i][y+1]);
						return 1;
					}
				}
			}
			return 11;
		}
		if(x+1<N && y-2>=0 && map[x+1][y]==num && map[x+1][y-1]==num && map[x+1][y-2]==num) {
			for (int i = 0; i < x+1; i++) {
				if(map[i][y-1]!=0) {
					if(numbers[map[i][y-1]]) return 2;
					else {
						del.offer(map[i][y-1]);
						return 1;
					}
				}
				if(map[i][y-2]!=0) {
					if(numbers[map[i][y-2]]) return 2;
					else {
						del.offer(map[i][y-2]);
						return 1;
					}
				}
			}
			return 11;
		}
		if(x+1<N && y-1>=0 && y+1<N && map[x+1][y]==num && map[x+1][y-1]==num && map[x+1][y+1]==num) {
			for (int i = 0; i < x+1; i++) {
				if(map[i][y-1]!=0) {
					if(numbers[map[i][y-1]]) return 2;
					else {
						del.offer(map[i][y-1]);
						return 1;
					}
				}
				if(map[i][y+1]!=0) {
					if(numbers[map[i][y+1]]) return 2;
					else {
						del.offer(map[i][y+1]);
						return 1;
					}
				}
			}
			return 11;
		}
		return -1;
	}
	
	public int solution(int[][] board) {
		
		int answer = 0;
		int N = board.length;
		del = new LinkedList<>();
		
		int big = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				big = Math.max(big, board[i][j]);
			}
		}
		boolean[] numbers = new boolean[big+1];
		boolean flag = false;
		
		for (int row = 0; row < N; row++) {
			while(true) {				
				flag = false;
				for (int i = 0; i < N; i++) {
					int tmp = board[row][i];
					
					if(tmp!=0 && !numbers[tmp]) {
						
						if(judge(board,row,i,tmp,N,numbers)==11) {
							for (int j = 0; j < N; j++) {
								for (int j2 = 0; j2 < N; j2++) {
									if(board[j][j2]==tmp) board[j][j2]=0;
								}
							}
							answer++;
						}
						else if(judge(board,row,i,tmp,N,numbers)==1){
							flag = true;
						}
						else numbers[tmp] = true;
					}
				}
				while(!del.isEmpty()) {
					int now = del.poll();
					numbers[now] = true;
				}
				if(!flag) break;
			}
		}

		return answer;
	}

}
