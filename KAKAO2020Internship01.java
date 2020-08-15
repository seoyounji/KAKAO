package KAKAO;

//2020 카카오 인턴십 / 키패드 누르기
public class KAKAO2020Internship01 {

	public String solution(int[] numbers, String hand) {
		
		StringBuilder sb = new StringBuilder();
		
		int[] right = new int[] {3,2};
		int[] left = new int[] {3,0};
		
		for(int i : numbers) {
			if(i==1 || i==4|| i==7) {
				sb.append('L');
				left[1] = 0;
				if(i == 1) {
					left[0] = 0;
				} else if(i == 4) {
					left[0] = 1;
				} else {
					left[0] = 2;
				}
			}
			else if(i==3 || i==6 || i==9) {
				sb.append('R');
				right[1] = 2;
				if(i == 3) {
					right[0] = 0;
				} else if(i == 6) {
					right[0] = 1;
				} else {
					right[0] = 2;
				}
			}
			else {
				int idx = 0;
				if(i == 2) {
					idx = 0;
				} else if(i == 5) {
					idx = 1;
				} else if(i == 8) {
					idx = 2;
				} else {
					idx = 3;
				}
				int r = Math.abs(right[0]-idx) + Math.abs(right[1]-1);
				int l = Math.abs(left[0]-idx) + Math.abs(left[1]-1);
				
				if(r == l) {
					if(hand.equals("right")) {
						sb.append('R');
						right[1] = 1;
						right[0] = idx;
					} else {
						sb.append('L');
						left[1] = 1;
						left[0] = idx;
					}
				}
				else if(r > l) {
					sb.append('L');
					left[1] = 1;
					left[0] = idx;
				} else {
					sb.append('R');
					right[1] = 1;
					right[0] = idx;
				}
			}
		}
		String answer = sb.toString();
		return answer;
	}

}
