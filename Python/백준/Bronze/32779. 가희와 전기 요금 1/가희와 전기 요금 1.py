# 가희와 전기 요금 1
import sys


N = int(sys.stdin.readline())
ans = []

for _ in range(N):
    a, m = map(int, sys.stdin.readline().split())

    ans.append(int(a * m * 0.00176))

print(*ans, sep='\n')
