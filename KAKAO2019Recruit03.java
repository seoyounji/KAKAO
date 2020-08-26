package kakao2019;

import java.util.ArrayList;
import java.util.HashSet;

//2019 카카오 블라인드 채용 / 후보키
public class KAKAO2019Recruit03 {

	private static ArrayList<HashSet<Integer>> list;
	
	private static void sol(String[][] relation,int N,HashSet<Integer> selects,int pos) {
		if(pos==N) {
			for (int n:selects) System.out.print(n+" ");
			System.out.println();
			for (int i = 0; i < list.size(); i++) {
				if(selects.containsAll(list.get(i))) return;
			}
			HashSet<String> tmp = new HashSet<>();
			for (int i = 0; i < relation.length; i++) {
				String t = "";
				for(int j:selects) t += relation[i][j] +",";
				if(tmp.contains(t)) return;
				tmp.add(t);
			}

			list.add(selects);
			return; 
		}
		
		HashSet<Integer> copy = new HashSet<Integer>();
        HashSet<Integer> copy2 = new HashSet<Integer>();
        for (int val : selects) {
            copy.add(val);
            copy2.add(val);
        }
        sol(relation,N,copy2,pos+1);
        copy.add(pos);
        sol(relation,N,copy,pos+1);
	}
	
	public int solution(String[][] relation) {
        int N = relation[0].length;
		list = new ArrayList<>();
		HashSet<Integer> selects = new HashSet<>();
		sol(relation,N,selects,0);
		return(list.size());
    }
}
