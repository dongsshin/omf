package com;

import java.util.HashSet;
import java.util.Set;

class Solution
{
	public static void main(String[] args) {
		int[] arr = new int[4];
		arr[0] = 0;
		arr[1] = 1;
		arr[2] = 3;
		arr[3] = 2;
		
		System.out.print(solution(arr));
	}
	public static boolean solution(int[] arr) {
	        boolean answer = true;

	        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
	        System.out.println("Hello Java");
	        Set<Integer> set = new HashSet<Integer>();
	        int min = 0;
	        int max = 0;
	        for(int i = 0; i <  arr.length; i++) {
	        	if(min > arr[i] || arr[i] == 1) min = arr[i];
	        	if(max < arr[i]) max = arr[i];
	        	if(set.contains(arr[i])) {
	        		answer = false;
	        		break;
	        	}
	        	set.add(arr[i]);
	        }
	        if(min != 1) return false;
	        if(max != arr.length) return false;
	        return answer;
	    }
}
