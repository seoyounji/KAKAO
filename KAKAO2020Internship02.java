package kakao2020;

import java.util.LinkedList;

//2020 카카오 인턴십 / 수식 최대화
public class KAKAO2020Internship02 {

	private static long answer;
	private static LinkedList<Long> Number;
	private static LinkedList<Character> Operation;
	
	private static void permu(int[] arr, int[] output, boolean[] visited, int depth) {
		if(depth == 3) {
			sol(output);
			return;
		}
		for (int i = 0; i < 3; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			output[depth] = arr[i];
			permu(arr,output,visited,depth+1);
			visited[i] = false;
		}
	}
	private static LinkedList<Long> copy1(LinkedList<Long> input) {
		LinkedList<Long> number = new LinkedList<>();
		for (int i = 0; i < input.size(); i++) {
			number.add(input.get(i));
		}
		return number;
	}
	private static LinkedList<Character> copy2(LinkedList<Character> input) {
		LinkedList<Character> operation = new LinkedList<>();
		for (int i = 0; i < input.size(); i++) {
			operation.add(input.get(i));
		}
		return operation;
	}
	private static void sol(int[] arr) {
		LinkedList<Long> number = copy1(Number);
		LinkedList<Character> operation = copy2(Operation);
		char[] oper = {'+','-','*'};
		for (int i = 0; i < 3; i++) {
			char op = oper[arr[i]];
			int num = operation.size();
			for (int j = 0; j < num; j++) {
				char now = operation.poll();
				long n1 = number.poll();
				long n2 = number.poll();
				if(now == op) {
					if(now == '+') {
						number.addFirst(n1+n2);
					} else if(now == '-') {
						number.addFirst(n1-n2);
					} else {
						number.addFirst(n1*n2);
					}
				}
				else {
					operation.add(now);
					number.add(n1);
					number.addFirst(n2);
				}
			}
			long t = number.poll();
			number.add(t);
		}
		answer = Math.max(answer, Math.abs(number.poll()));
	}
	
	public long solution(String expression) {
		answer = 0;
		int index = -1;
		Number = new LinkedList<>();
		Operation = new LinkedList<>();
		
		for (int i = 0; i < expression.length(); i++) {
			char t = expression.substring(i,i+1).charAt(0);
			if(t == '+' || t == '-' || t == '*') {
				Operation.add(t);
				Number.add((long) Integer.parseInt(expression.substring(index+1,i)));
				index = i;
			}
		}
		Number.add((long) Integer.parseInt(expression.substring(index+1,expression.length())));
		
		int[] arr = {0,1,2};
		int[] output = new int[3];
		boolean[] visited = new boolean[3];
		permu(arr,output,visited,0);

		return answer;
	}

}
