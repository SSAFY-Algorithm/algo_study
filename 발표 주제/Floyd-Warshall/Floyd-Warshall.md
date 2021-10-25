# 플로이드 와샬(Floyd-Warshall) 알고리즘  
모든 정점에서 모든 정점으로의 최단 경로 구하는 알고리즘  
**거쳐가는 정점을 기준으로 최단 거리 구하기**

## 다익스트라 알고리즘, 플로이드 와샬 차이점  
다익스트라 알고리즘 - 하나의 정점 출발, 하나의 정점으로부터 모든 정점으로의 최단 경로  
플로이드 와샬 - 모든 정점, 모든 정점에서 모든 정점으로의 최단 경로  

### 백준 운동(1956)  
```java
public class Main {
	static int V,E, res;
	static int[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str);
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		map = new int[V+1][V+1];
		res=987654321;
		
          
		for(int i=0; i<=V; i++) { // 맵 초기화
			for(int j=0; j<=V; j++) {
				map[i][j] = 987654321; // 오버플로우가 나지 않는 상수 설정
			}
		}
    
		for(int i=0; i<E; i++) {
			str = br.readLine();
			st = new StringTokenizer(str);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			map[a][b] = c;
		}
		
		for(int k=1; k<=V; k++) { // k 간선을 거쳐
			for(int i=1; i<=V; i++) { // i 부터
				for(int j=1; j<=V; j++) { // j 까지
					map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
					if(map[i][j]!=0 && map[j][i]!=0 && map[i][j]+map[j][i]<res) {
						res = map[i][j]+map[j][i];
					}
				}
			}
		}
		
		if(res==987654321) {
			System.out.println(-1);
		}else {
			System.out.println(res);
		}
		
	}
}
```
