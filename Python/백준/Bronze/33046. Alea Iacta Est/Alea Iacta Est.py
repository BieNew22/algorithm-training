"""
Alea Iacta Est

문제 정리
- 모듈려 연산을 이용한 문제
"""

# 좌동 -> 가동 구하기
roll1 = sum(map(int, input().split()))
fake_east = 4 if roll1 % 4 == 0 else roll1 % 4

# 가동 -> 진동 구하기
roll2 = sum(map(int, input().split()))
total = fake_east + roll2 - 1

print(4 if total % 4 == 0 else total % 4)