package kakao_winter;

import java.util.ArrayList;
import java.util.Stack;

//2019 카카오 개발자 겨울 인턴십 / 크레인 인형뽑기 게임
public class KAKAO2019Internship02 {
	
	public int solution(int[][] board, int[] moves) {
        int answer = 0;
		Stack<Integer> result = new Stack<>();
		ArrayList<Stack<Integer>> list = new ArrayList<Stack<Integer>>();
		for (int i = 0; i < board.length; i++) {
			Stack<Integer> tmp = new Stack<>();
			for (int j = board.length-1; j > -1; j--) {
				if(board[j][i]!=0) {
					tmp.add(board[j][i]);					
				}
			}
			list.add(tmp);
		}
		for (int i = 0; i < moves.length; i++) {
			Stack<Integer> tmp = list.get(moves[i]-1);
			if(tmp.isEmpty()) continue;
			int pick = tmp.pop();
			if(!result.isEmpty()) {
				if(result.peek()==pick) {
					result.pop();
					answer += 2;
				} else {
					result.add(pick);
				}
			} else {
				result.add(pick);				
			}
		}
        return answer;
    }
}

