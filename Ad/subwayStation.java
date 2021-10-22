import java.util.Scanner;

/*
입력
Testcase 개수
-테스트 개수만큼 
N(지하철역 개수 8-20)
하루이용객 수 N개(1-1000)

출력
#(testcase) 가장 높은 타당도

문제
N개의 순환하는 지하철 역이 있을 때 각각의 역마다 하루 이용객수가 주어진다. 타당도가 제일 높게 2개의 직통노선을 추가로 건설하고, 그 타당도를 출력하시오.

->A와 B를 연결하는 직통노선, C와 D를연결하는 직통노선을 건설했을 때 
타당도 공식 : (A+B)^2+(C+D)^2

제약조건
- 직통 노선 2개는 교차될 수 없다. 
- 인접한 역끼리는 직통노선으로 선택할 수 없다. 
- 직통노선은 인접한 두역에서 출발하거나 도착할 수 없다. 
- 1개의 역에 2개의 직통노선이 있을 수 없다. 


input

5
10
80 90 65 55 90 60 40 35 30 25
8
30 25 70 55 95 75 90 20
10
60 85 45 25 15 70 55 70 85 35
15
80 30 35 95 45 85 30 25 100 85 10 60 80 30 5
20
45 30 5 85 55 85 10 5 75 60 15 65 45 50 75 80 15 10 50 90

output

#1 38425
#2 44225
#3 37925
#4 64850
#5 57850
*/

public class subwayStation {
	
	static long ans=Long.MIN_VALUE;
	static int N, st[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T= sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N= sc.nextInt();
			ans=Long.MIN_VALUE;
			
			st= new int[N+1];
			for (int i = 1; i < N+1; i++) {
				st[i]=sc.nextInt();
			}

			permutation(0,new int[4],new boolean[N+1]); 
			System.out.println("#"+tc+" "+ans);
		}
	}

	private static void permutation(int idx, int[] sel, boolean[] v) {
		//4개다 뽑았을 시
		if(idx==4) {
			
			if(sel[0]>sel[2]&&sel[1]<sel[2]&&sel[0]<sel[3]||sel[1]>sel[3]) {//cross
				return;
				
			}
			if(sel[0]>sel[3]&&sel[1]<sel[3]&&sel[0]<sel[2]||sel[1]>sel[2]) {//cross
				return;		
			}
			
			if(ans<(st[sel[0]]+st[sel[1]])*(st[sel[0]]+st[sel[1]])+(st[sel[2]]+st[sel[3]])*(st[sel[2]]+st[sel[3]])){//답일 가능성 없으면
				ans=(st[sel[0]]+st[sel[1]])*(st[sel[0]]+st[sel[1]])+(st[sel[2]]+st[sel[3]])*(st[sel[2]]+st[sel[3]]);

			}
			return;
			
		}
		
		for (int i = 1; i < N+1; i++) {
			if(!v[i]) {
				if(idx>0) {//인접했을 경우 스킵
					boolean isNear=false;
					for (int j = 0; j < idx; j++) {
						if(Math.abs(i-sel[j])==1||Math.abs(i-sel[j])==N-1) {//하나라도 인접할 경우
							isNear=true;
							break;
						}
					}
					if(isNear) {
						continue;
					}
				}
				v[i]=true;
				sel[idx]=i;
				permutation(idx+1,sel,v);
				v[i]=false;
			}
		}
		
	}

}


