"""
이변마작 1

문제 정리
- 중복 값이 4번 이상 등장 하는 최초의 시점을 구하는 문제
"""

from collections import defaultdict

N = int(input())
data = input().split()

DB = defaultdict(int)

ans = 0
for i, v in enumerate(data):
    DB[v] += 1

    if DB[v] >= 5:
        ans = i + 1
        break

print(ans)