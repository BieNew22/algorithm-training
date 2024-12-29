from collections import defaultdict
import sys
sys.setrecursionlimit(10**7)

def solution(edges):
    G_out = defaultdict(list)
    G_in = defaultdict(int)
    visited = set()
    shapes = {"stick": 0, 'donut': 0, 'eight': 0}

    # 1. 그래프 만들기
    for a, b in edges:
        G_out[a].append(b)
        G_in[b] += 1

    # 2. root 찾기 (out이 2개 이상이고 in이 0개인 노드는 root 밖에 없음)
    root = [
        node
        for node in G_out
        if len(G_out[node]) >= 2 and G_in[node] == 0
    ][0]

    # 3. 모양 확인 함수 정의
    def find_shape(now: int):
        if now in visited:
            return 'donut'
        if not G_out[now]:
            return 'stick'
        if len(G_out[now]) == 2:
            return 'eight'
        visited.add(now)
        return find_shape(G_out[now][0])

    # 4. root로부터 연결된 그래프 모양 확인
    for next in G_out[root]:
        shape = find_shape(next)
        shapes[shape] += 1

    return [root, shapes['donut'], shapes['stick'], shapes['eight']]