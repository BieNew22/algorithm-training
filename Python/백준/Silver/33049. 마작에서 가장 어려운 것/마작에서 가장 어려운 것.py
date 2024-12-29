"""
마작에서 가장 어려운 것

문제 정리
- 배수 약수 가지고 노는 수학 문제 느낌
"""

p3, p4, no_care = map(int, input().split())

ans_p3 = p3 // 3
need_p3 = 0 if p3 % 3 == 0 else 3 - p3 % 3

ans_p4 = p4 // 4
need_p4 = 0 if p4 % 4 == 0 else 4 - p4 % 4

# 필요한 사람 보다 상관 없는 사람이 적은 경우 -> 모두 만족 불가
if need_p3 + need_p4 > no_care:
    no_care = -1
else:
    if need_p3 != 0:
        ans_p3 += 1

    if need_p4 != 0:
        ans_p4 += 1
        
    no_care -= (need_p4 + need_p3)

# 상관 없는 사람을 적절 하게 분배 하기
# 이때 3인 보다 4인 우선 순위가 높음.
while no_care > 0:
    if no_care % 4 == 0 or no_care % 4 == 3:
        # 남은 인원 수가 4x + 3k 인 경우
        ans_p4 += no_care // 4
        ans_p3 += (no_care % 4) // 3
        no_care = 0
    else:
        # 그 외 : 3인 테이블에 우선으로 할 당.
        ans_p3 += 1
        no_care -= 3


if no_care < 0:
    # 모두 만족 시키지 못한 경우
    print(-1)
else:
    print(ans_p3, ans_p4)