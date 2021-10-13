# Backtracking 백트래킹
해를 찾는 도중 해가 아니어서 막히면, 되돌아가서 다시 해를 찾아가는 알고리즘.
=> **'가능한 모든 방법을 탐색한다.'**


## DFS와 백트래킹

#### 깊이 우선 탐색(DFS)
DFS는 가능한 **모든 경로**를 탐색합니다. 하지만 이처럼 모든 곳을 방문하기 때문에 **굳이 방문하지 않아도 되는 경로**를 탐색하여 비효율적인 결과를 불러올 수도 있습니다. 

이와 같은 비효율적인 경로를 **가지치기**를 통해 차단하여 효율적으로 경로를 검사하는 방법이 **백트래킹** 알고리즘 입니다.


#### 백트래킹(Backtracking)
- **가지치기란?** (Pruning)
  불필요한 부분을 차단하고 최대한 올바른 방향으로 탐색을 하는 것. (반복문의 횟수까지 줄일 수 있다.)

이 **가지치기를 얼마나 잘하느냐**에 따라 **효율성**이 결정되게 됩니다.

## 정리
- 백트래킹은 모든 가능한 경우의 수 중 **특정한 조건을 만족하는 경우에만 살펴보는 것**입니다.
- 즉, **답이 될 만한지 판단하고 그렇지 않으면 그 부분까지 탐색하는 것을 하지 않고 가지치기하는 것**을 백트래킹이라고 생각하면 됩니다.
- 주로 문제 풀이에서는 **DFS 등으로 모든 경우의 수를 탐색**하는 과정에서, **조건문 등을 걸어 답이 절대로 될 수 없는 상황을 정의하고, 그러한 상황일 경우에는 탐색을 중단시킨 뒤 그이전으로 돌아가서 다시 다른 경우를 탐색**할 수 있도록 구현할 수 있습니다.

## 대표 문제
### 스도쿠
## Code
```
    import java.util.LinkedList;
    import java.util.Scanner;

    public class Main {
    static int[][] map = new int[9][9];
    static LinkedList<Node> list = new LinkedList();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                map[i][j] = sc.nextInt();
                // 입력받는 값이 0(빈칸)인 경우 리스트에 삽입
                if (map[i][j] == 0) {
                    list.add(new Node(i, j));
                }
            }
        }
        dfs(0);
    }

    static void dfs(int depth) {
        //빈칸 모두 채운 경우 출력 후 종료
        if (depth == list.size()) { 
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
            System.exit(0);
        }

        // 현재 위치 뽑기
        Node node = list.get(depth);
        int r = node.r;
        int c = node.c;

        for (int i = 1; i <= 9; i++) { //빈칸인 map[x][y]에 1~9 값 되는지 check
            if (check(r, c, i)) {
                map[r][c] = i; //되는 숫자면 넣고 이어서 탐색
                dfs(depth + 1);
                map[r][c] = 0;
            }
        }
    }

    static boolean check(int r, int c, int num) {
        if (map[r][c] != 0) { // 빈칸 아니면 false
            return false;
        }
        for (int i = 0; i < 9; i++) { //가로,세로줄 중복검사
            if (map[i][c] == num || map[r][i] == num) {
                return false;
            }
        }
        int r2 = (r / 3) * 3;
        int c2 = (c / 3) * 3;
        for (int i = r2; i < r2 + 3; i++) { //3x3 중복체크
            for (int j = c2; j < c2 + 3; j++) {
                if (map[i][j] == num) {
                    return false;
                }
            }
        }
        return true;

    }


    static class Node {
        int r; //행
        int c; //열

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}

```
**주요 요점** 

```
if (check(r, c, i)) {
                map[r][c] = i; 
                dfs(depth + 1);
                map[r][c] = 0;
            }
```
dfs 재귀 탐색 후 0을 다시 대입하는 부분 -> **가지치기**
- 백준 문제 예제의 경우 **'map[x][y] = 0;'** 이 코드가 존재하지 않아도 작동하는 이유는 한번의 케이스만에 정답을 찾았기 때문입니다.
- 그러나 그런 경우는 거의 없기 때문에 dfs 종료 후 뒤로 돌아온다는 건 원하는 케이스를 찾지 못했기 때문에 **처음부터 다른 케이스를 찾아봐야 함** 입니다. 
- 즉, 재탐색을 해야하기 때문에 **0을 넣어 처음의 판**으로 되돌려 줍니다.