package kakao2019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

//2019 카카오 블라인드 채용 / 매칭 점수
public class KAKAO2019Recruit06 {
	
	private static HashMap<String, Integer> map;
	private static ArrayList<String>[] list;
	
	private static int basic(String w,String compare) {
		w = w.toLowerCase();
		compare = compare.toLowerCase();
		int num = 0;
		ArrayList<String> list = new ArrayList<>();
		String[] arr = w.split("\\W");
		for(String i:arr) {
			String[] tmp = i.split("[0-9]");
			for(String j:tmp) list.add(j);
		}
		for(String i:list) {
			if(i.equals(compare)) num++;
		}
		return num;
	}
	
	private static String url(String html) {
		String pattern = "(.*)<meta property=\"(\\S+)\" content=\"(\\S+)\"\\/>(.*)";
		String[] t = html.split("\n");
		
		for(String i:t) {
			if(Pattern.matches(pattern, i)) {
				String[] t2 = i.split("content=");
				int idx = t2[1].indexOf("/>");
				String[] t3 = t2[1].substring(0,idx).split("\"");
				return t3[1];
			}
		}
		return null;
	}
	
	private static int href(int idx,String html) {
		String[] tmp = html.split("<body>");
		String body = tmp[1];
		while(body.contains("<a href=\"")) {
			System.out.println(body);
			int left = body.indexOf("<a href=\"");
			int right = body.indexOf("\">",left);
			String in = body.substring(left+9, right);
			body = body.substring(right, body.length());
			System.out.println(in);
			list[idx].add(in);
		}
		return list[idx].size();
	}
	
	public int solution(String word, String[] pages) {
		
		int size = pages.length;
		
		map = new HashMap<>();
		list = new ArrayList[size];
		for (int i = 0; i < size; i++) {
			list[i] = new ArrayList<String>();
		}
		
		int[] basicScore = new int[size];
		for (int i = 0; i < size; i++) {
			basicScore[i] = basic(pages[i],word);
		}
		int idx=0;
		for (int i = 0; i < size; i++) {
			map.put(url(pages[i]), idx++);
		}
		
		int[] outNum = new int[size];
		for (int i = 0; i < size; i++) {
			outNum[i] = href(i,pages[i]);
		}
		
		double[] linkScore = new double[size];
		for (int i = 0; i < size; i++) {
			linkScore[i] = (double)basicScore[i] / (double)outNum[i];
		}
		
		double[] result = new double[size];
		for (int i = 0; i < size; i++) {
			result[i] = (double)basicScore[i];
		}
		for (int i = 0; i < size; i++) {
			ArrayList<String> t = list[i];
			for (int j = 0; j < t.size(); j++) {
				String now = t.get(j);
				if(map.containsKey(now)) result[map.get(now)] += linkScore[i];
			}
		}
		
		int answer = 0;
		double score = 0;
		for (int i = 0; i < size; i++) {
			if(score < result[i]) {
				answer = i;
				score = result[i];
			}
		}
		return answer;
	}
}
