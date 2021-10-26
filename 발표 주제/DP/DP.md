# Dynamic Programming

## 정의

문제를 단 한번만 풀게하는 알고리즘. 즉 한번 푼것을 여러번 풀게 하지 않도록 값을 따로 저장하여 효율적으로 푸는 방법.

## 사용조건
    1. 큰 문제는 작은 문제로 쪼갤 수 있다.
    2. 작은 문제는 서로 중복된다. 

**--> 해당 조건을 만족한다면, 점화식을 찾아서 푸는것이 관건이다.**


# 예) 피보나치 수열

## 점화식

$$
f(n) = f(n-1)+f(n-2)
$$

$$
f(7) = f(6)+f(5)
$$


<img width="702" alt="스크린샷 2021-10-26 오전 12 00 57" src="https://user-images.githubusercontent.com/16666658/138723735-74ff87b1-656e-4354-bf69-2b3c67489caf.png">

즉, 작은 문제로 쪼갤 수 있고 그 작은 문제가 서로 중복되기 때문에 피보나치 수열은 아래와 같이 DP로 풀수 있다.

<img width="870" alt="스크린샷 2021-10-26 오전 2 03 17" src="https://user-images.githubusercontent.com/16666658/138739044-f639ba49-33f8-4cbb-b928-194cc0ab64cb.png">



1. 배열에 값을 계산값을 저장해둔다.
2. 계산할 값이 배열에 있다면 -> 해당 값 리턴
3. 계산할 값이 배열에 없다면 -> 배열에 값 추가


그런데, 우린 피보나치 수열은 DP가 아니더라도
단순 재귀로도 풀 수 있다고 알고있습니다.
***

## Recursive

```java
    // n = 7
    private static int recursive(int n) {
		
        //피보나치의 초기값. 0은 0이고, 1은 1이다.
		if (n == 0) {
			return 0;
		} 
		
		if (n == 1) {
			return 1;
		}
		
		return recursive(n-1)+recursive(n-2);
	}

    //결과 : 13, 수행시간 0ms
```

근데 만약, n이 더 큰수로 들어온다면?
```java 
    //n = 40
    private static int recursive(int n) {
		
		if (n == 0) {
			return 0;
		} 
		
		if (n == 1) {
			return 1;
		}
		
		return recursive(n-1)+recursive(n-2);
	}
    //결과 : 9227465, 수행시간 : 507ms (약 5초)
```

n이 40만 되어도 상당한 시간이 걸리는 원인은, 앞서 트리 그림대로 중복된 연산을 그대로 사용하기 때문입니다.

이를 개선하기 위해 DP로 풀어봅시다.

***


## DP (top-down)

<img width="870" alt="스크린샷 2021-10-26 오전 2 03 17" src="https://user-images.githubusercontent.com/16666658/138739044-f639ba49-33f8-4cbb-b928-194cc0ab64cb.png">


```java
        // 값을 저장할 초기 배열 설정
        arr = new ArrayList<Integer>();
		arr.add(0);
		arr.add(1);
		System.out.println(dp(40));
```

```java
        // n = 40
        private static int dp(int n) {

            // n이 배열의 길이보다 작으면, 해당 값이 배열 안에 있으므로, 해당 값 리턴
            if ( n < arr.size() ) {
                return arr.get(n);
            } else {
            // n이 배열의 길이보다 크면, 아직 계산되지 않은 값이므로 계산하여 배열에 추가
                int num = dp(n-1)+dp(n-2);
                arr.add(num);
                return num;
            }
        }

        //결과 : 9227465, 실행속도 : 0ms
```
앞서 일반 재귀로 돌렸을때보다 훨씬 빠른 속도를 보여줍니다.
그러면, 훨씬 큰 수를 넣었을때는 어떨까요?

```java
		// n = 10000
        System.out.println(dp(10000));

        // 결과 : 런타임에러 (stackoverflow)
```

<img width="679" alt="스크린샷 2021-10-26 오전 1 53 37" src="https://user-images.githubusercontent.com/16666658/138738033-7a486e6d-43c3-4008-b9a1-18cf7dd58e20.png">


해당 코드는 10000부터 9999+9998 .... 으로 스택 콜이 점점 쌓이게 되는데, 이는 초기값인 1을 찾을때까지 쌓입니다. 이 과정에서 스택오버플로우가 발생합니다.

위에서부터 아래로 점점 호출하는 방식이기때문에
이 방법을 하향식(top-down) 이라고 합니다.
직관적으로 이해할 수 있고 구현하기 쉬우나, 위와같은 단점이 있습니다.

그렇다면, 10000부터 1을 찾아서 스택콜을 쌓지말고, 1부터 10000까지 재귀 없이 배열을 채워봅시다.

***

## DP (bottom-up)

```java

    //값을 저장할 초기 배열 설정
    arr = new ArrayList<Integer>();
    arr.add(0);
    arr.add(1);
    System.out.println(dp(10000));
```

```java

    // n = 10000
    private static int dp(int n) {
		
        // 배열이 채워지지않은 2부터 n까지 반복문을 실행합니다. 
		for (int i =2; i <= n; i++) {
            
            //해당 인덱스에 점화식 배열을 저장해나갑니다. 이렇게 n까지 저장될것입니다.
			arr.add(arr.get(i-1)+arr.get(i-2));
		}
		
        // 마지막 n 인덱스의 값을 출력해주면 답이 됩니다.
		return arr.get(n);
	}

    //실행결과 : 1242044891, 실행시간 : 3ms
```

아래에서부터 n값을 찾아서 가는 해당 방식을 상향식 (bottom-up) 이라고 합니다.
스택콜이 쌓이지 않기 때문에 스택오버플로우를 방지할 수 있는 장점이 있습니다.

그럼 DP에 대해 정의하였으니, 문제를 풀어보겠습니다.


# 백준 17026 - 타일링

<img width="998" alt="스크린샷 2021-10-26 오전 2 15 06" src="https://user-images.githubusercontent.com/16666658/138740854-83c29e27-96dc-44d2-bc60-f7149de3fd1e.png">

모든 타일은 1 * 2, 2 * 1의 부분 타일로 구성 된다고 합니다.


즉 큰 문제를 작은 문제로 쪼갤 수 있고, 부분 타일은 여러번 중복되어 나오게 되므로 해당 문제는 DP로 풀이가 가능합니다.

그렇다면 점화식을 세우는것이 관건이기 때문에, 규칙성을 찾아서 점화식을 세워봅시다.

<img width="736" alt="스크린샷 2021-10-26 오전 2 15 33" src="https://user-images.githubusercontent.com/16666658/138740846-e96a582c-1c5a-4865-aa11-f237e279cbc5.png">

<img width="788" alt="스크린샷 2021-10-26 오전 2 15 48" src="https://user-images.githubusercontent.com/16666658/138740869-03c879f1-7bcb-4188-86c3-a7befa417d55.png">

n = 1 일때 1

n = 2 일때 2

n = 3 일때 3

n = 4 일때 5

...

<img width="490" alt="스크린샷 2021-10-26 오전 2 17 26" src="https://user-images.githubusercontent.com/16666658/138740876-e17ea98c-6f1d-4fa8-b4b8-2cb6fa21b829.png">

<img width="574" alt="스크린샷 2021-10-26 오전 2 17 33" src="https://user-images.githubusercontent.com/16666658/138740883-d33d78fd-185d-4c12-9633-7e523a5ab573.png">



f(n) = f(n-1)+f(n-2) 라는걸 알 수 있습니다.
앞서 설명한 피보나치 수열의 점화식과 같죠?
위에서 설명한 DP 코드로 풀어보겠습니다.

```java

//상향식 DP 풀이. 하향식은 n=9부터 스택오버플로우 납니다.

public class bj_11726 {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		// 최대 n의 범위가 1000이므로 배열할당
		int[] arr = new int[1001];
		
		arr[1] = 1; 
		arr[2] = 2; 
		
		for (int i=3; i <= n; i++) {
			arr[i] = (arr[i-1]+arr[i-2]) % 10007;
		}
		System.out.println(arr[n]);
	}
}

```


# DP 문제 추천 링크 

https://stonejjun.tistory.com/24





 
