package kakao2020;

import java.util.Stack;

//2020 카카오 블라인드 채용 / 괄호 변환
public class KAKAO2020Recruit02 {

	private static boolean judge(String s) {
		Stack<Character> array = new Stack<>();
		String[] tmp = s.split("");
		for (int i = 0; i < tmp.length; i++) {			
			if(array.isEmpty()) array.add(tmp[i].charAt(0));
			else {
				char t = tmp[i].charAt(0);
				char tt = array.peek();
				if(tt == '(' && t == ')') {
					array.pop();
				}
				else {
					array.add(t);
				}
			}
		}
		if(array.isEmpty()) return true;
		else return false;
	}

	public String solution(String p) {
		if(p.isEmpty()) return p;
		
		int idx = 0;
		int left = 0,right = 0;
		for (int i = 0; i < p.length(); i++) {
			if(p.charAt(i) == '(') left++;
			else right++;
			if(right==left) {
				idx = i+1;
				break;
			}
		}
		String t1 = p.substring(0,idx);
		String t2 = p.substring(idx,p.length());
		
		if(judge(t1)) return t1+solution(t2);
		else {
			String answer = "("+solution(t2)+")";
			for (int i = 1; i < t1.length()-1; i++) {
				if(t1.charAt(i) == '(') answer += ")";
				else answer += "(";
			}
			return answer;
		}
	}
}
