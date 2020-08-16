package KAKAO;

//2020 카카오 블라인드 채용 / 문자열 압축
public class KAKAO2020Recruit01 {

	public int solution(String s) {
		
		int length = s.length()/2 + 1;
		int lengthLong = s.length();
		int answer = Integer.MAX_VALUE;
		
		for (int i = 1; i <= length; i++) {
			StringBuilder sb = new StringBuilder();
			int idx = 0;
			String x = s.substring(idx, idx+i);
			idx += i;
			int num = 1;
			while(idx + i <= lengthLong) {
				String y = s.substring(idx, idx+i);
				if(x.equals(y)) num++;
				else {
					if(num == 1) sb.append(x);
					else {
						sb.append(num);
						sb.append(x);
						num = 1;
					}
					x = y;
				}
				idx += i;
			}
			if(num == 1) sb.append(x);
			else {
				sb.append(num);
				sb.append(x);
			}
			sb.append(s.substring(idx, lengthLong));
			
			String result = sb.toString();
			answer = Math.min(answer, result.length());
		}
		return answer;
	}

}
