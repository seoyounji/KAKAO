package kakao2020;

import java.util.HashMap;

//2020 카카오 인턴십 / 보석 쇼핑
public class KAKAO2020Internship03 {

	public int[] solution(String[] gems) {
		HashMap<String,Integer> map = new HashMap<>();
		int index = 0;
		int line = gems.length;
		
		for (int i = 0; i < line; i++) {
			if(!map.containsKey(gems[i])) map.put(gems[i], index++);
		}
		int[] array = new int[line];
		int max = 0;
		for (int i = 0; i < line; i++) {
			array[i] = map.get(gems[i]);
			max = Math.max(max, array[i]);
		}
		
		int[] numbers = new int[max+1];
		int start = 0;
		int ans1 = Integer.MAX_VALUE,ans2 = Integer.MAX_VALUE;
		int length = Integer.MAX_VALUE;
		
		for (int i = 0; i < line; i++) {
			numbers[array[i]]++;
			while(numbers[array[start]] > 1) {
				numbers[array[start]]--;
				start++;
				if(start >= i) break;
			}
			boolean flag = true;
			for (int j = 0; j <= max; j++) {
				if(numbers[j]<1) {
					flag = false;
					break;
				}
			}
			if(flag) {
				if(length > i-start) {
					length = i-start;
					ans1 = start;
					ans2 = i;
				}
			}
		}
		int[] answer = new int[2];
		answer[0] = ans1+1;
		answer[1] = ans2+1;
        return answer;
	}

}
