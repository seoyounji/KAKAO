package kakao2019;

import java.util.HashMap;

//2019 카카오 개발자 겨울 인턴십 - 호텔 방 배정
public class KAKAO2019Internship04 {

	private static long find(long num,HashMap<Long,Long> map) {
		if(!map.containsKey(num)) {
			map.put(num, num+1);
			return num;
		} else {
			long next = find(map.get(num),map);
			map.put(num, next);
			return next;
		}
	}
	
	public long[] solution(long k, long[] room_number) {
		HashMap<Long,Long> map = new HashMap<>();
		long[] answer = new long[room_number.length];
		
		for (int i = 0; i < room_number.length; i++) {
			answer[i] = find(room_number[i],map);
		}
		return answer;
	}

}
