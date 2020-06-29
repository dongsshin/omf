package com;

import java.util.HashSet;
import java.util.Set;

class Solution1
{
	public static void main(String[] args) {
		System.out.print(solution(1,100));
	}
	public static int solution(int n, int m) {
        int answer = 0;
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        
        for(int i = n; i <= m; i++){
        	if(i == reverse(i)) answer++;
        }
        
        System.out.println("answer : " + answer);

        return answer;
    }
    private static int reverse(int num) {
    	String numStr = num + "";
    	StringBuffer strBuf = new StringBuffer();
    	char[] chrArray = numStr.toCharArray();
    	for(int i = chrArray.length - 1; i >= 0; i--) {
    		strBuf.append(chrArray[i]);
    	}
    	return Integer.parseInt(strBuf.toString());
    }
}
