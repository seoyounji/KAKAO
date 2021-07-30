package kakao2020;

//2020 카카오 블라인드 채용 / 자물쇠와 열쇠
public class KAKAO2020Recruit03 {

	private static int[][] rotate(int[][] matrix,int N) {
		for (int i = 0; i < N / 2; i++) {
	        for (int j = 0; j < N; j++) {
	            int temp = matrix[i][j];
	            matrix[i][j] = matrix[N - i - 1][j];
	            matrix[N - i - 1][j] = temp;

	        }
	    }
	    for (int i = 0; i < N; i++) {
	        for (int j = i; j < N; j++) {
	            int temp = matrix[i][j];
	            matrix[i][j] = matrix[j][i];
	            matrix[j][i] = temp;
	        }
	    }
	    return matrix;
	}
	
	private static int[][] copy(int[][] origin,int N) {
		int[][] result = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				result[i][j] = origin[i][j];
			}
		}
		return result;
	}
	
	private static boolean judge(int[][] map,int start,int size) {
		for (int i = start; i < start+size; i++) {
			for (int j = start; j < start+size; j++) {
				if(map[i][j]==0) return false;
			}
		}
		return true;
	}
	
	public boolean solution(int[][] key, int[][] lock) {
		int M = key.length;
		int N = lock.length;
		int A = N+(2*M)-2;
		int[][] map = new int[A][A];
		for (int i = 0; i < A; i++) {
			for (int j = 0; j < A; j++) {
				if(i>=M-1 && i<M-1+N && j>=M-1 && j<M-1+N) map[i][j] = lock[i-M+1][j-M+1];
				else map[i][j] = 1;
			}
		}
		
		for (int r = 0; r < 4; r++) {
			for (int i = 0; i <= M-1+N-1; i++) {
				for (int j = 0; j <= M-1+N-1; j++) {
					int[][] copy = copy(map,A);
					for (int k = 0; k < M; k++) {
						for (int k2 = 0; k2 < M; k2++) {
							if(copy[i+k][j+k2] == key[k][k2]) copy[i+k][j+k2] = 0;
							else copy[i+k][j+k2] = 1;
						}
					}
					if(judge(copy,M-1,N)) return true;
				}
			}
			key = rotate(key,M);
		}
		return false;
	}

}
