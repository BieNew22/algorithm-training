import sys

INF = 10 ** 10

N = int(input())
data = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]


a = [INF, data[0][0] + data[1][1], data[0][0] + data[1][2]]
b = [data[0][1] + data[1][0], INF, data[0][1] + data[1][2]]
c = [data[0][2] + data[1][0], data[0][2] + data[1][1], INF]

for y in range(2, N):

    a = [min(a[1], a[2]) + data[y][0], min(a[0], a[2]) + data[y][1], min(a[0], a[1]) + data[y][2]]
    b = [min(b[1], b[2]) + data[y][0], min(b[0], b[2]) + data[y][1], min(b[0], b[1]) + data[y][2]]
    c = [min(c[1], c[2]) + data[y][0], min(c[0], c[2]) + data[y][1], min(c[0], c[1]) + data[y][2]]
    
a[0] = b[1] = c[2] = INF
print(min(min(a), min(b), min(c)))
