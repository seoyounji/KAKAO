package kakao2019;

import java.util.HashSet;
import java.util.LinkedHashSet;

//2019 카카오 개발자 겨울 인턴십 - 불량 사용자
public class KAKAO2019Internship03 {
	
	private static HashSet<HashSet<String>> result;
	
	private static void DFS(String[] user,String[] ban,HashSet<String> set) {
		if(set.size() == ban.length) {
			if(same(set,ban) && !result.contains(set)) {
				result.add(new HashSet<>(set));
			}
			return;
		}
		for(String i:user) {
			if(!set.contains(i)) {
				set.add(i);
				DFS(user,ban,set);
				set.remove(i);
			}
		}
	}
	
	private static boolean same(HashSet<String> set, String[] ban) {
		int index = 0;
		boolean flag = true;
		outer:
		for(String i:set) {
			String compare = ban[index++];
			if(i.length() != compare.length()) {
				flag = false;
				break;
			}
			for (int j = 0; j < i.length(); j++) {
				if(compare.charAt(j)!='*' && compare.charAt(j)!=i.charAt(j)) {
					flag = false;
					break outer;
				}
			}
		}
		if(flag) return true;
		else return false;
	}

	public int solution(String[] user_id, String[] banned_id) {
        result = new HashSet<>();
		DFS(user_id,banned_id,new LinkedHashSet<>());
        return result.size();
    }
}
