package kakao2019;

import java.util.ArrayList;
import java.util.Collections;

//2019 카카오 블라인드 채용 / 길 찾기 게임
public class KAKAO2019Recruit05 {

	static ArrayList<Integer> pre;
	static ArrayList<Integer> post;
	
	static class node implements Comparable<node>{
		int x;int y; int num; node left; node right;
		node(int x,int y,int num) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.left = null;
			this.right = null;
		}
		@Override
		public int compareTo(node o) {
			return o.y-this.y;
		}
	}
		
	static class BT {
		node root = null;
		
		public void insert(node num) {
			if(root == null) root = new node(num.x,num.y,num.num);
			else {
				node parent = root;
				node current;
				
				while(true) {
					current = parent;
					if(parent.x > num.x) {
						parent = parent.left;
						if(parent == null) {
							current.left = new node(num.x,num.y,num.num);
							break;
						}
					}
					else {
						parent = parent.right;
						if(parent == null) {
							current.right = new node(num.x,num.y,num.num);
							break;
						}
					}
				}
			}
		}
		public void preorder(node root) {
			if (root != null) {
				pre.add(root.num);
	            preorder(root.left);
	            preorder(root.right);
	        }
		}
		public void postorder(node root) {
	        if (root != null) {
	            postorder(root.left);
	            postorder(root.right);
	            post.add(root.num);
	        }
	    }
	}
	
	public int[][] solution(int[][] nodeinfo) {		
		pre = new ArrayList<>();
		post = new ArrayList<>();
		
		int idx = 1;
		ArrayList<node> list = new ArrayList<>();
		for (int i = 0; i < nodeinfo.length; i++) {
			list.add(new node (nodeinfo[i][0],nodeinfo[i][1],idx++));
		}
		Collections.sort(list);
		
		BT tree = new BT();
		for (int i = 0; i < list.size(); i++) {
			node now = list.get(i);
			tree.insert(new node(now.x,now.y,now.num));
		}
		
		tree.preorder(tree.root);
		tree.postorder(tree.root);
		
		int[][] answer = new int[2][idx-1];
		for (int i = 0; i < pre.size(); i++) {
			answer[0][i] = pre.get(i);
			answer[1][i] = post.get(i);
		}
		return answer;
	}

}
