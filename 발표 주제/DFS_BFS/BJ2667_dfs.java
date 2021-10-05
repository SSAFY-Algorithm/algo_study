/*
 * 백준 2667 단지번호붙이기
 * - dfs 방식
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ2667_dfs {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	
	// 단지 수 구할 변수
	static int oneCnt=0;
	
	// 각 단지에 속하는 집의 수를 저장하기 위한 배열
	static int[] bd = new int[25*25];
	
	// 사방 탐색
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);

		// 지도의 크기 
		N = scan.nextInt();

		// 지도 배열
		map = new int[N][N];
		// 방문 배열
		visited = new boolean[N][N];

		for (int i = 0; i < map.length; i++) {
			String s = scan.next();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j)-'0';
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 집이 있고(1이고) 아직 방문하지 않았을 경우
				if(map[i][j] == 1 && !visited[i][j]) {
					oneCnt++;  // 아파트 갯수 +1
					dfs(i, j); // dfs 탐색
				}
			}
		}

		Arrays.sort(bd);   // 단지에 속하는 집의 수 오름차순 정렬

		System.out.println(oneCnt);
		for (int i = 0; i < bd.length; i++) {
			if(bd[i] != 0)
				System.out.println(bd[i]);
		}
	}

	private static void dfs(int r, int c) {
		
		// 이미 방문한 경우 리턴
		if(visited[r][c])
			return;

		// 방문 처리
		visited[r][c] = true;

		// 단지에 속하는 집의 수 +1
		bd[oneCnt]++;

		// 사방 탐색
		for (int i = 0; i < dr.length; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			// 경계값 탐색 + 재탐색할 곳의 아직 방문하지 않은 경우 + 집이 있는 경우
			if(nr >= 0 && nr<N && nc>=0 && nc<N && !visited[nr][nc] && map[nr][nc] == 1) {
				// dfs 탐색
				dfs(nr, nc);
			}
		}
	}

}

