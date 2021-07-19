package kakao2019;

import java.util.HashMap;

//2019 카카오 블라인드 채용 / 오픈채팅방
public class KAKAO2019Recruit01 {

	public String[] solution(String[] record) {
        HashMap<String, String> map = new HashMap<>();
		int size = 0;
		for (int i = 0; i < record.length; i++) {
			String tmp = record[i];
			String[] line = tmp.split(" ");
			if(line[0].equals("Enter")) {
				map.put(line[1], line[2]);
				size++;
			}
			else if(line[0].equals("Leave")) {
				size++;
			}
			else if(line[0].equals("Change")) {
				map.put(line[1], line[2]);
			}
		}
		String[] result = new String[size];
		int idx = 0;
		for (int i = 0; i < record.length; i++) {
			String tmp = record[i];
			String[] line = tmp.split(" ");
			if(line[0].equals("Enter")) {
				String t = map.get(line[1]) + "님이 들어왔습니다.";
				result[idx] = t;
				idx++;
			}
			else if(line[0].equals("Leave")) {
				String t = map.get(line[1]) + "님이 나갔습니다.";
				result[idx] = t;
				idx++;
			}
		}
        return result;
    }
}
