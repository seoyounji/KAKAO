package KAKAO;

import java.util.HashMap;

//2021 카카오 채용연계형 인턴십 / 숫자 문자열과 영단어
public class KAKAO2021Internship01 {

	public int solution(String s) {
		int index = 0;
		StringBuilder sb = new StringBuilder();
		HashMap<String, Integer> number = new HashMap<>();
		
		number.put("ze", 0);
		number.put("on", 1);
		number.put("tw", 2);
		number.put("th", 3);
		number.put("fo", 4);
		number.put("fi", 5);
		number.put("si", 6);
		number.put("se", 7);
		number.put("ei", 8);
		number.put("ni", 9);
		
		int length = s.length();
		
		while(index < length) {
			char tmp = s.substring(index, index+1).charAt(0);
			if(Character.isDigit(tmp) == true) {
				sb.append(tmp);
				index++;
			}
			else {
				String temp = s.substring(index, index+2);
				int num = number.get(temp);
				sb.append(num);
				
				switch(num) {
				case 0:
					index += 4;
					break;
				case 1:
					index += 3;
					break;
				case 2:
					index += 3;
					break;
				case 3:
					index += 5;
					break;
				case 4:
					index += 4;
					break;
				case 5:
					index += 4;
					break;
				case 6:
					index += 3;
					break;
				case 7:
					index += 5;
					break;
				case 8:
					index += 5;
					break;
				case 9:
					index += 4;
					break;
				}
			}
		}
		int answer = Integer.parseInt(sb.toString());
		
		return answer;
	}

}
