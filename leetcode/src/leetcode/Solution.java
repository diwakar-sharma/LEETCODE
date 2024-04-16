package leetcode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {

	public static int lengthOfLongestSubstring(String s) {

		String lss= "";

		int si=0,ei=0,length=0;
		int cur=0;
		Set ls = new HashSet();
		while(true) {
			char c=  s.charAt(cur);
			if(!ls.contains(c)) {
				++cur;
				ei=cur;
				lss = s.substring(si,ei);
				ls.add(c);
			} else {
				char c2=  s.charAt(si);
				if(c2==c) {
					ls.removeAll(ls);
					ei=cur+1;
					si++;
					lss = s.substring(si,ei); 
					for(int j=si;j<ei-1;j++)
						ls.add(s.charAt(j));
				} else {
					si++;
				}

			}

			if(length<lss.length())
				length = lss.length();

			if(ei==s.length()) {
				break;
			}
		}

		return length;
	}

	public static boolean isPalindrome(String str) {
		int left = 0, right = str.length() - 1;

		while(left < right)
		{
			if(str.charAt(left) != str.charAt(right))
			{
				return false;
			}
			left++;
			right--;
		}
		return true;
	} 

	public String longestPalindrome(String s) {
		int l =0;
		String rs="";
		if(isPalindrome(s)){
			return s;
		}

		for (int i = 0; i<=s.length();i++ ) {
			if(l>((s.length())-i)){
				break;
			}

			for (int j = s.length(); (j>i)&&(j-i>l);j-- ) {
				String ss = s.substring(i, j); 
				if (l<ss.length()) {
					if(isPalindrome(ss)) {
						l=ss.length();
						rs =ss;
					}
				}
			}

		}

		return rs;


	}

	public static String intToRoman(int num) {
		if (num>=1000) {
			num=num-1000;
			return ("M"+intToRoman(num));
		} else if (num>=500) {
			num=num-500;
			return ("D"+intToRoman(num));

		} else if (num>=100) {
			num=num-100;
			return ("C"+intToRoman(num));

		} else if (num>=50) {
			num=num-50;
			return ("L"+intToRoman(num));

		} else if (num>=10) {
			num=num-10;
			return ("X"+intToRoman(num));

		} else if (num>=5) {
			num=num-5;
			return ("V"+intToRoman(num));

		}else if (num>=1) {
			num=num-1;
			return ("I"+intToRoman(num));
		} else 
			return "";
	}

	public static List<String> letterCombinations(String digits) {
		Map map  = new HashMap();
		map.put('2',"abc");
		map.put('2',"abc");
		map.put('3',"def");
		map.put('4',"ghi");
		map.put('5',"jkl");
		map.put('6',"mno");
		map.put('7',"pqrs");
		map.put('8',"tuv");
		map.put('9',"wxyz");

		List<String> retlist =  new ArrayList();
		List list =  new ArrayList();


		for(int i = 0 ;  i <digits.length();  i++){
			char c =  digits.charAt(i);

			if(map.containsKey(c)) {
				String val  = (String) map.get(c);

				List<String> templist =  retlist;
				retlist =  new ArrayList();
				if(templist.size()==0) {
					for(int j=0 ;j<val.length(); j++) {
						retlist.add(String.valueOf(val.charAt(j)));
					}

				}
				else  {
					for(int k=0 ;k<templist.size(); k++) {

						String s=  templist.get(k);

						for(int j=0 ;j<val.length(); j++) {

							retlist.add(s+String.valueOf(val.charAt(j)));
						}

					}
				}
			}
		}


		return retlist;

	}


	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode() {}
	 *     ListNode(int val) { this.val = val; }
	 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	 * }
	 */
	public ListNode mergeKLists(ListNode[] lists) {

		List<Integer> lst  =  new ArrayList();
		for(int i = 0 ; i <lists.length ; i++) {
			ListNode  lstnode  =  lists[i];
			while(lstnode!=null) {
				lst.add(lstnode.val);
				lstnode = lstnode.next;
			}
		}

		List<Integer> lst1 =lst.stream().sorted().collect(Collectors.toList());

		//	    	ListNode  lstnode  =  null;
		ListNode  lstnode  =  new ListNode(lst1.get(0));
		ListNode  lastlstnode  =  lstnode;

		for(int i = 1 ; i <lst1.size() ; i++) {
			lastlstnode.next = new ListNode(lst1.get(i));
			lastlstnode = lastlstnode.next;
		}

		return lstnode;
	}


	public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		if((list2 != null || list1 != null )){
			ListNode lst3 = new ListNode();
			ListNode lst1 =lst3;

			while(list1 != null || list2 != null ) {
				if((list1 != null && list2 == null )||((list1 != null && list2 != null )&&(list1.val<list2.val))) {
					lst3.val = list1.val;
					list1 =list1.next;
				} else if((list2 != null && list1 == null )||(list1 != null || list2 != null )) {
					lst3.val = list2.val;
					list2 =list2.next;
				}
				if((list2 != null || list1 != null )){
					lst3.next= new ListNode();
					lst3 =lst3.next;
				}
			}
			return lst1;
		}
		return null;
	}


	public boolean checkValidString(String s) {
		Stack<Character> stack = new Stack<Character>();

		for(int i=0 ; i<s.length() ;i++) {
			char c = s.charAt(i);
			if(c=='(' || c=='*' ) {
				stack.push(c);	
			}else if (c==')' || c=='*' ) {
				if(stack.size()>0){
					char c1 = stack.peek();
					if(c==')'&& (c1=='('||c1=='*')){
						stack.pop(); 
						if(c1=='*' && (stack.size()>0) )
							stack.pop(); 
					} 
				}

			}
		}
		if(stack.size()==0)
			return true;
		else if(stack.size()==1) {
			char c1 = stack.peek();
			if(c1=='*') {
				return true;
			}
		}

		return false;   
	}


	public static int trap(int[] h) {
		int x1i=0,x2i=0,s=0;
		//int x1=h[x1i],x2=h[x2i] ,s=0;
		for(int i=1 ; i <h.length;i++) {
			if(x2i==x1i){
				x2i=i;
			} else if(x2i>x1i) {
				if(h[x2i]<h[x1i]) {
					x2i=i;

				} else {
					s =s+(i-x1i)*h[i];
					x2i=i;
					x1i=i;
				}
			}
		}	
		return s;
	}

	public static int maxProfit(int[] prices) {

		int buy  = prices[0];
		int profit  = 0;

		for(int i=1; i <prices.length ;i++) {
			if(prices[i]<buy) {
				buy =prices[i];
			} else if(prices[i]-buy>profit)
				profit = prices[i]-buy;
		}
		return 0;
	}

	public List<Integer> getRow(int rowIndex) {

		List<Integer> li = new ArrayList();
		li.add(1);
		for() 


			return null;

	}


	public static int maxProfit1(int[] prices) {

		int b = prices[0],g= 0;

		for(int i=1; i<prices.length ;i++) {
			if(prices[i]<b) {	
				b = prices[i];
			} else {
				g = g+prices[i]-b;
				b = prices[i];

			}
		}
		return g;


	}

	/*   
    Input: board = 
    [
    ["5","3",".",".","7",".",".",".","."],
    ["6",".",".","1","9","5",".",".","."],
    [".","9","8",".",".",".",".","6","."],
    ["8",".",".",".","6",".",".",".","3"],
    ["4",".",".","8",".","3",".",".","1"],
    ["7",".",".",".","2",".",".",".","6"],
    [".","6",".",".",".",".","2","8","."],
    [".",".",".","4","1","9",".",".","5"],
    [".",".",".",".","8",".",".","7","9"]
    ]
	 */  
	public  List<Character>  checkRow(char[][] board , int row ) {
		List<Character> ls = new ArrayList();
		for(int i = 0 ;i<9 ;i++) {
			char c = board[row][i];
			if(c!='.') {
				ls.add(c);
			}

		}
		return ls;
	}

	public  List<Character>  checkCOl(char[][] board , int col ) {
		List<Character> ls = new ArrayList();
		for(int i = 0 ;i<9 ;i++) {
			char c = board[i][col];
			if(c!='.') {
				ls.add(c);
			}
		}
		return ls;
	}

	public List<Character> checkSqr(char[][] board , int col , int row) {
		List<Character> ls = new ArrayList();
		int startc = (col>=0&&col<3)?0:(col>=3&&col<6)?3:6;
		int startr = (row>=0&&row<3)?0:(row>=3&&row<6)?3:6;
		List<Character> ls = new ArrayList();
		for(int i = startr ;i<startr+3 ;i++) {
			for(int j = startc ;j<startc+3 ;j++) {
				char c = board[i][j];
				if(c!='.') {
					ls.add(c);
				}
			}
		}
		return ls;
	}

	public char getMissingVal(char[] charls ,List<Character> ls1,List<Character> ls2,List<Character> ls3) {
		for(int i = 0;i<charls.length;i++) {
			char c = charls[i];
			if((!ls1.contains(c)) && (!ls2.contains(c)) && (!ls3.contains(c))) {
				return c ;
			}
		}
		return '.';
	}
	public void solveSudoku(char[][] board) {
		char[] charA= {'1','2','3','4','5','6','7','8','9'};
		for(int i = 0 ;i<9;i++) {
			for(int j = 0;j<9 ;j++) {
				char c = board[i][j];
				if(c=='.') {
					List<Character> ls1 =   checkRow(board ,  i ); 
					List<Character> ls2 =  	checkCOl(board ,  j ); 
					List<Character> ls3 =  	checkSqr(board ,  i,j );
					char c1 = getMissingVal(charA,ls1,ls2,ls3);
					board[i][j] = c1;
				}
			}
		}

	}

	 public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		 int[] c = IntStream.concat(Arrays.stream(nums1), Arrays.stream(nums2)).sorted().toArray();
		 
		 return 0;
	        
	    }
	   
	   public boolean isMatch(String s, String p) {
		   
		   if(p!=null && (p.contains(".")|| (p.contains("*")))) {
				   if(p.contains(".")){
					   String[] sarr =   p.split(".");
					  
				   } else if (p.contains("*")) {
					   String[] sarr2 =   p.split("*");
				   }
				   
			   
		   } else {
			   if(s.equals(p)) {
				   return true;
			   } 
		   }
		   
		return false;
	        
	    }
	public static void main(String[] args) {

		//System.out.println(lengthOfLongestSubstring("dvdf"));
		//System.out.println(lengthOfLongestSubstring("abcabcbb"));
		//System.out.println(intToRoman(3));
		//System.out.println(intToRoman(58));
		//System.out.println(letterCombinations("2356"));

		//int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
		int[] height = {0,1,0,2};

		//System.out.println(trap(height));
		System.out.println(maxProfit1(new int []{7,1,5,3,6,4}));

	}

}

class ListNode {
	int val;
	ListNode next;
	ListNode() {}
	ListNode(int val) { this.val = val; }
	ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	@Override
	public String toString() {		return "ListNode [val=" + val + ", next=" + next + "]";	}
}
