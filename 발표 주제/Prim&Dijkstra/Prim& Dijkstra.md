# Prim& Dijkstra

---

# Prim Alg

- 무향 연결 그래프일때
- 최소 스패닝 트리(모든 점을 연결하는 최단거리의 그래프)라는 서브 그래프를 찾는 알고리즘
- Kruscal 알고리즘과 같은 용도
- O(n^2)의 시간 복잡도를 가진다.

## 알고리즘

1. 임의의 정점을 선택하여 비어있는 tree에 포함시킨다.
2. T(tree)에 있는 노드와 T에 없는 노드 사이의 간선 중 가중치가 최소인 간선을 찾는다. 
3. 찾은 간선을 연결하는 두 노드중, T에 없던 노드를 T에 포함시키고, 추가된 노드에 따른 간선정보를 update한다. 
4. 모든 노드가 T에 포함 될 때까지 2,3을 반복한다.

![prim-example.png](Prim&%20Dijkstra%201408ff1d0fbd48e491095078f1149ce4/prim-example.png)

## 구현

dist[i]=T에 들어간 노드와 i노드 사이의 간선 가중치 중 최소값

v[i]= i노드가 T에 들어가 있으면 true, 아니면 false

```java
boolean[] v=new boolean[V];//방문배열
int[] dist= new int[V];//거리배열(정점비용)
Arrays.fill(dist, Integer.MAX_VALUE);
```

1. 임의의 정점을 선택하여 비어있는 T에 포함시킨다. 

```java

dist[0] = 0;             

```

1. T에 있는 노드와 T에 없는 노드 사이의 간선중 가중치가 최소인 간선을 찾는다. 

```java
			int minD=Integer.MAX_VALUE;
			int minIdx=-1;
			for (int i = 0; i < dist.length; i++) {//연결가능한 정점 검사
				if(!v[i]&&dist[i]<minD) {//방문하지 않은애중에 적은애 
					minD=dist[i];
					minIdx=i;
				}
			}
```

1. 찾은 간선이 연결하는 두 노드중, T에 없던 노드를 T에 포함시키고 추가된 노드에 따른 간선정보를 update한다. 

```java
v[minIdx]=true;//방문표시
for (int i = 0; i < V; i++) {
				if(adj[minIdx][i]!=0&&!v[i]&&dist[i]>adj[minIdx][i]) {//0이아니어야하고, 방문하지 않아야 하며
					dist[i]=adj[minIdx][i];
				}
			}
```

1. 모든 노드가 T에 포함될 때까지 1,2를 반복

### 전체코드

```java
public class prim {
	static int V,E;
	static int[][] adj;
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("mst.txt"));
		Scanner sc = new Scanner(System.in);
		V=sc.nextInt();
		E=sc.nextInt();
		adj=new int[V][V];
		for (int i = 0; i < E; i++) {
			int a =sc.nextInt();
			int b= sc.nextInt();
			int c =sc.nextInt();
			adj[a][b]=c;
			adj[b][a]=c;
		}
		//prim
		//일단 두개의 일차원배열: 방문배열/거리배열
		boolean[] v=new boolean[V];//방문배열
		int[] dist= new int[V];//거리배열(정점비용)
		//거리배열 무한으로 초기화
		Arrays.fill(dist, Integer.MAX_VALUE);
		//아무거나 시작정점 선택, 부모가없고 거리가 0
		dist[0]=0;
		
		//정점을 선택하는 반복이이어짐 
		for (int cnt = 0; cnt < V-1; cnt++) {//마지막정점은 이미 모든 방문배열을 거쳐오기때문에 -1해도 된다.
			//비용이 가장 적은 정점을 선택
			int minD=Integer.MAX_VALUE;
			int minIdx=-1;
			for (int i = 0; i < dist.length; i++) {//연결가능한 정점 검사
				if(!v[i]&&dist[i]<minD) {//방문하지 않은애중에 적은애 
					minD=dist[i];
					minIdx=i;
				}
			}
			//minIdx: 현재 방문하지 않은 정점중 weight가 가장 작은 정점
			//minIdx에 연결되어있는 정점을 탐색해서 dist배열을 update하고 그중 가장 적은 비용의 정점으로 이동한다. 
			v[minIdx]=true;//방문표시 
			for (int i = 0; i < V; i++) {
				if(adj[minIdx][i]!=0&&!v[i]&&dist[i]>adj[minIdx][i]) {//0이아니어야하고, 방문하지 않아야 하며
					dist[i]=adj[minIdx][i];
				}
			}	
		}
		System.out.println(Arrays.toString(dist));//모든 값들을 더하면 최소비용
		
	}
	private static void print(int[][] adj) {
		for (int i = 0; i < adj.length; i++) {
			for (int j = 0; j < adj[i].length; j++) {
				System.out.print(adj[i][j]+"\t");
			}System.out.println();
		}
		
	}
}
```

# Dijkstra Alg

- 단일 출발점이고, 가중치가 음수가 아닌 경우 다른 모든 정점으로 가는 최단경로를 탐색하는 알고리즘.
- 하나의 최단 거리를 구할 때 그 이전까지 구했던 최단거리 정보를 그대로 사용한다.

## 알고리즘

1. 출발노드를 설정한다. 
2. 방문하지 않은 노드 중 가장 비용이 적은 노드를 선택한다.
3. 특정 노드를 거쳐서 가는 경우를 고려하여 최소비용을 갱신한다.—이 부분만 다름
4. 2,3 번 을 반복한다.

## 전체 코드

```java
import java.util.Arrays;
import java.util.Scanner;

public class Dijkstra {
	static int V,E;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		V=sc.nextInt();
		E=sc.nextInt();
		
		int[][] adj=new int[V+1][V+1];
		int start=sc.nextInt();
		
		for (int i = 0; i < V; i++) {
			int r=sc.nextInt();
			int c=sc.nextInt();
			adj[r][c]=sc.nextInt();
		}
		//----------------------------------------------------------------------
		int[] dist=new int[V+1];
		boolean[] v=new boolean[V+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dist[start]=0;
		for (int cnt = 0; cnt < V-1; cnt++) {
			
			int minD=Integer.MAX_VALUE;
			int minIdx=-1;
			for (int i = 1; i < dist.length; i++) {
				if(!v[i]&&minD>dist[i]) {
					minD=dist[i];
					minIdx=i;
				}
			}
			if(minIdx==-1) {
				break;
			}
			v[minIdx]=true;
			for (int i = 1; i < V; i++) {
				if(adj[minIdx][i]!=0&&!v[i]&&dist[i]>dist[minIdx]+adj[minIdx][i]) {
					dist[i]=adj[minIdx][i]+dist[minIdx]**;**
				}
			}
		}
	  //----------------------------------------------------------------------
		for (int i = 1; i < dist.length; i++) {
			if(dist[i]==Integer.MAX_VALUE) {
				System.out.println("INF");
			}else {
				System.out.println(dist[i]);
			}
		}
	}
}
```

[1753번: 최단경로](https://www.acmicpc.net/problem/1753)

## 문제 풀이 코드

```java
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Dijkstra {
	static int V,E;
	static class edge{
		int v,weight;
		public edge(int v,int weight) {
			this.v=v;
			this.weight=weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

		V=Integer.parseInt(st.nextToken());
		E=Integer.parseInt(st.nextToken());
		int start=Integer.parseInt(br.readLine());
		
		//인접리스트 생성
		List<edge>[] adj=new ArrayList[V+1];
		for (int i = 0; i <= V; i++) {
			adj[i]=new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			int weight=Integer.parseInt(st.nextToken());
			adj[x].add(new edge(y,weight));
		}
		
		int[] dist=new int[V+1];
		boolean[] v=new boolean[V+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dist[start]=0;
		for (int cnt = 0; cnt < V-1; cnt++) {
			
			int minD=Integer.MAX_VALUE;
			int minIdx=-1;
			for (int i = 1; i < dist.length; i++) {
				if(!v[i]&&minD>dist[i]) {
					minD=dist[i];
					minIdx=i;
				}
			}

			if(minIdx==-1) {
				break;
			}

			v[minIdx]=true;
			for (edge e:adj[minIdx]) {
				if(!v[e.v]&&dist[e.v]>dist[minIdx]+e.weight) {
					dist[e.v]=dist[minIdx]+e.weight;
				}
			}
		}

		StringBuffer ans=new StringBuffer();
		for (int i = 1; i < dist.length; i++) {
			if(dist[i]==Integer.MAX_VALUE) {
				ans.append("INF \n");
			}else {
				ans.append(Integer.toString(dist[i])+"\n");
			}
		}
		System.out.println(ans);
	}
}
```
