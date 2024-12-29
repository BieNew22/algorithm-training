"""
나는 이 우마를 지배할 수 있다

문제 정리
- 브루트 포스 문제.
"""

import sys


def get_ans(goal_person: int, goal_place: int, person_score: dict) -> list:
    # d1, d2, d3, d4 각 등수의 우마 점수
    for d4 in range(-100, 101):
        for d3 in range(d4, 101):
            for d2 in range(d3, 101):
                d1 = -(d4 + d3 + d2)

                # 1등 우마 점수는 2등 우마 점수 보다 커야 함.
                # 우마 점수의 범위가 -100 ~ 100
                if d1 < d2 or d1 > 100 or d1 < -100:
                    continue

                # res[i] = [i + 1 번째 사람 점수, 사람 번호]
                res = [[person_score[p][0], p] for p in range(1, 5)]

                # 기본 점수에 우마 점수 더하기
                for p in range(1, 5):
                    res[p - 1][0] += sum(a * b for a, b in zip(person_score[p][1], [d1, d2, d3, d4]))
                
                # 최종 순위 구하기, 점수 기준 내림 차순, 사람 번호 오름 차순
                res.sort(key=lambda a: (a[0], -a[1]), reverse=True)
                
                if res[goal_place][1] == goal_person:
                    return [d1, d2, d3, d4]

    return [-1]


N, K, M = map(int, sys.stdin.readline().split())

# score 구조) key: 사람 번호(1 ~ 5), val: [점수, [각 등 수 횟수(0 ~ 4)]]
score = {p: [0, [0, 0, 0, 0]] for p in range(1, 5)}

for _ in range(N):
    # data[:4] : 1, 2, 3, 4 등 한 사람
    # data[4:] : 1, 2, 3, 4 등이 획득 한 점수
    data = list(map(int, sys.stdin.readline().split()))

    # 각 사람의 기본 점수와 등수의 횟수를 기록
    for i in range(4):
        # 점수 더하기
        score[data[i]][0] += data[4 + i]

        # 해당 라운드 순위 기록
        score[data[i]][1][i] += 1

print(*get_ans(K, M - 1, score))
