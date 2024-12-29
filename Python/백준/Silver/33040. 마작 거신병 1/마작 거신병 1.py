"""
마작 거신병 1

문제 정리
- 뭔가 뭔가 하다 ... (쉽게 정리하기 힘듦.)
- 수학 문제 같음.
"""


H, W = map(int, input().split())
one, nine = map(int, input().split())

flag = True

# case1 : 9를 층 마다 다르게 배치 할 수 없는 경우
# Si < Sj 조건을 만족 하는 최소의 경우는
# 각 행에 9를 0, 1, 2, 3 ... H - 1 개를 추가할 수 있어야 함.

# case1-1 : W는 H - 1 보다는 커야 함.
if W < H - 1:
    flag = False

# case1-2 : 추가로 알 수 있는 것은 9 가 1 ~ H - 1 합 보다 작으면 안 됨.
if nine < H * (H - 1) // 2:
    flag = False

# case2 : 9가 너무 많은 경우
# 각 행에 W - H + 1, ... ,W - 3, W - 2, W - 1, W 합 보다 많으면 안 됨.
if nine > H * (2 * W - H + 1) // 2:
    flag = False


if not flag:
    print(-1)
else:
    # 거신병을 소환 할 수 있음을 의미
    ans = [[] for _ in range(H)]

    # 9 를 최소 개수 만 큼 넣기
    for i in range(H):
        for j in range(i):
            ans[i].append(9)
            nine -= 1

    # 9 가 다 소모 될 때 까지 아래 에서 부터 하나씩 넣기
    idx = H - 1
    while nine > 0:
        ans[idx].append(9)
        nine -= 1
        idx -= 1

        if idx == -1:
            idx = H - 1

    # 남은 W 만큼 1을 추가하기
    for i in range(H):
        while len(ans[i]) < W:
            ans[i].append(1)

    # 결과 출력
    for a in ans:
        print(*a)