
#  Greedy Algorithm( 탐욕 알고리즘)

## 00. 정의
- **현재 상황에서 지금 당장 좋은 것만 고르는 방법**
- 매 순간 가장 좋아 보이는 것을 선택하며, 현재의 선택이 나중에 미칠 영향에 대해서는 고려하지 않는다. -> 각 선택 시점에서 이루어지는 결정은 지역적으로 최적이지만, 그 선택들을 계속 수집하여 최종적인 해답을 만들었을 때, **그것이 최적이라는 보장은 없다**.

## 01. 해결방법
1. 머리 속에 떠오르는 최소한의 아이디어를 생각한다
2. 정당성을 검토한다

❗주의❗ 다익스트라의 경우는 암기하자.

## 대표적인 문제 : 거스름돈
문제 : 거슬러 줘야 할 동전의 최소 개수를 구하여라.

<a href="https://ibb.co/TMY89k0"><img src="https://i.ibb.co/3m4Ng7F/1.png" alt="1" border="0"></a>

1. 머리 속에 떠오르는 최소한의 아이디어 : 가장 큰 화폐 단위부터 돈을 거슬러준다
2. 정당성 검토 : 가지고 있는 동전 중에서 큰 단위가 항상 작은 단위의 배수이므로 작은 단위의 동전들을 종합해 다른 해가 나올 수 없다. (case 2의 경우 그렇지 않음)


## 백준 문제 풀이 아이디어
1. 최소한의 아이디어 : 이전 선택의 종료 시간과 이후 선택의 시작 시간이 서로 겹치지 않고, 최대한 많은 선택을 한다 -> 최대한 많은 선택을 할려면 종료 시간이 빨라야한다. 
즉, 서로 겹치지 않고, 선택의 종료 시간이 빠르면 더 많은 회의실을 선택할 수 있다
2. 정당성 평가 : 문제에서 [단, 회의는 한번 시작하면 중간에 중단될 수 없으며..] 하나의 활동을 완료하기 전까지는 다른 활동을 선택할 수 없다. 즉, 하나의 활동을 선택하면, 이후의 결과에 영향을 미치지 않기에 그리디로 풀어도 된다.


![enter image description here](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https://blog.kakaocdn.net/dn/cewRBZ/btqJ92Ggx03/1lekgEQnnITL75Kcls4M0k/img.png)
↑ 주어진 값 그래프로 표현
![enter image description here](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https://blog.kakaocdn.net/dn/kWMC8/btqJ3iJ7hIS/GteyzJuATQuqKajK9Qj0Qk/img.png)
↑ 종료 시간을 기준으로 정렬
![enter image description here](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https://blog.kakaocdn.net/dn/culRjv/btqKfp2amld/fRbwvDQgtpsTrYejNECQk1/img.png)
↑ 겹치지 않는 활동을 선택한다.

## 백준 문제 풀이(code)

    package Greedy;

    import java.io.*;
    import java.util.*;
 
    public class bj_1931_회의실배정 {
 
		public static void main(String[] args) throws Exception{
	 
			//System.setIn(new FileInputStream("bj_1931_input.txt"));
			Scanner in = new Scanner(System.in);
			
			/** 입력 **/
			int N = in.nextInt(); // 회의의 수
			int[][] time = new int[N][2]; // 회의 시간 정보를 저장하는 배열
		
			for(int i = 0; i < N; i++) {
				time[i][0] = in.nextInt();	// 시작시간 
				time[i][1] = in.nextInt();	// 종료시간 
			}
			
			/** 정렬 **/
			// 끝나는 시간을 기준으로 정렬
			Arrays.sort(time, new Comparator<int[]>() {
				
				@Override
				public int compare(int[] o1, int[] o2) {
					// 종료시간이 같을 경우 시작시간이 빠른순으로 정렬  
					if(o1[1] == o2[1]) {
						return o1[0] - o2[0];
					}
					return o1[1] - o2[1];
				}
	 
			});
			
			/** 처리 **/
			int cnt = 0; // 회의실 최대개수를 저장
			int end = 0; // 이전 타임을 저장
			
			for(int i = 0; i < N; i++) {
				if(end <= time[i][0]) {// 이전 회의 종료시간이 다음 회의 시작 시간보다 작거나 같다면
					end = time[i][1]; //갱신해주고
					cnt++; // 개수 증가시켜준다.
				}
			}
			
			/** 출력 **/
			System.out.println(cnt);
		}
    }

