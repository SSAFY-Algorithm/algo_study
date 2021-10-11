# brute force 알고리즘
완전탐색 알고리즘으로 가능한 모든 경우의 수를 모두 탐색하면서 요구조건에 충족되는 결과만을 가져오는 알고리즘  
장점 : 예외 없이 100% 확률로 정답 출력이 가능, 구현이 간단  
단점 : 시간적인 측면에서 효율이 떨어짐  

완전탐색 기법 활용 방법
1. 해결하고자 하는 문제의 가능한 경우의 수를 대략적으로 계산(컴퓨터는 일반적으로 1초에 1억번 연산)
2. 가능한 모든 방법을 고려(if/for문을 활용, 순열/조합, 재귀, 비트마스크, bfs,dfs
3. 실제 답을 구할 수 있으면 적용

##### 예시
* 1부터 100까지 합 구하기
  * 1+2+3+ ... +100 (100번 연산)  

* 10글자 암호의 구하기(소문자 26개 + 숫자 10개)
  * a, b, c ... z, 1, 2, 3 ... 10 (36가지) * a, b, c ... z, 1, 2, 3 ... 10 (36가지) -> (36의 10제곱 = 3656158440062976번 연산)

**꼭 시간적 측면을 고려해서 사용**  

---

### 탐색 알고리즘

* brute force 알고리즘은 모든 자료를 탐색해야 할 상황이 생기기 때문에 탐색 알고리즘 사용  
  * 선형 구조를 탐색하는 **순차탐색**  
     * 배열에서 원하는 값 찾기
     ```java
     for(int i=0; i<arr.length; i++){
        if(arr[i] == value){
            system.out.println(i)
        }
     }
     ```
     * for문을 이용한 탐색 (n개중에 r개 선택)
     ```java
    for(int i=0; i <n; i++) {
      for(int j=i+1; j<n; j++)  {
        for(int k=j+1; k<n; k++) {
        ... (r개의 for문..)
     ```  
    * 재귀를 이용한 탐색
    ```java
    static void combination(int[] arr, boolean[] visited, int start, int n, int r) {
      if(r == 0) { // 기저조건
          print(arr, visited, n);
         return;
      } 
  
      for(int i=start; i<n; i++) {
          visited[i] = true;
          combination(arr, visited, i + 1, n, r - 1);
          visited[i] = false;
      }
    }
    ```  
  * 비선형 구조를 탐색하는 **DFS** **BFS**
     * dfs
     ```java
    public static void dfs(int i) {
       visit[i] = true;
       System.out.print(i + " ");
        for(int j=0 j<n; j++) {
          if(map[i][j] == 1 && !visit[j]) {
             dfs(j);
           }
         }
      } 
     ```
     
     * bfs
     ```java
     	public static void bfs(int i) {
	      	Queue<Integer> q = new LinkedList<>();
	      	q.offer(i);
	       	visit[i] = true;
		      while (!q.isEmpty()) {
	        		int temp = q.poll();
	        		System.out.print(temp + " ");
		        	for (int j = 1; j < n + 1; j++) {
		          		if (map[temp][j] == 1 && visit[j] == false) {
			          		q.offer(j);
				          	visit[j] = true;
			              }
		               }
		           }
       	  }
       ```
     * 2차원 배열(map) 탐색 하기  
     탐색 배열 선언 후 dfs나 bfs 사용하기
     ```java
     static int dr[] = {-1,1,0,0}
     static int dc[] = {0,0,-1,1}  
     ```  
---
 
### 문제풀이  
백준 부분수열의 합  
**비스마스킹**  
AND 연산 = 대응하는 두 비트가 모두 1일 때 1 반환 ex) 1010 & 1111 = 1010  
OR 연산 = 대응하는 두 비트가 하나라도 1일때 1 반환 ex) 1010 | 1111 = 1111  
XOR 연산 = 대응하는 두 비트가 서로 다르면 1 반환 ex) 1010 | 1111 = 0101  
NOT 연산 = 비트의 값을 반전하여 반환 ex) ~1010 = 0101  
시프트 연산 = 비트를 <<, >> 방향으로 이동 ex) 00001010 << 2 = 00101000


```java
import java.util.*;

public class Main {
	static int N,S, sum, res=0;
	static int[] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		S = sc.nextInt();
		arr = new int[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}
		
		for(int i=1; i<(1<<N); i++) { // 0001을 N만큼 <<이동  -> ex) 10 0000 == 32
			sum = 0;
			for(int j=0; j<N; j++) { // N만큼 반복 -> ex) 00 0001, 00 0010, 00 0100 ... 10 0000
				if((i & (1<<j)) != 0) {
					sum += arr[j];
				}
			}
			
			if(sum == S) {
				res++;
			}
		}
		
		System.out.println(res);
	}
}
```

     
