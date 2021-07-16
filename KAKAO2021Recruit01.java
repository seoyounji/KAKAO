package KAKAO;

//2021 카카오 블라인드 채용 / 신규 아이디 추천
public class KAKAO2021Recruit01 {

	public String solution(String new_id) {
		new_id = new_id.toLowerCase();
		new_id = new_id.replaceAll("[^0-9a-z-_.]", "");
		new_id = new_id.replaceAll("[.]{2,}", ".");
		if(new_id.charAt(0) == '.') new_id = new_id.substring(1, new_id.length());
		if(!new_id.isEmpty() && new_id.charAt(new_id.length()-1) == '.') new_id = new_id.substring(0, new_id.length()-1);
		if(new_id.isEmpty()) new_id = "a";
		if(new_id.length() >= 16) new_id = new_id.substring(0, 15);
		if(!new_id.isEmpty() && new_id.charAt(new_id.length()-1) == '.') new_id = new_id.substring(0, new_id.length()-1);
		System.out.println(new_id);
		if(new_id.length() <= 2) {
			char tmp = new_id.charAt(new_id.length()-1);
			while(new_id.length() < 3) new_id += tmp;
		}
		return new_id;
	}

}
