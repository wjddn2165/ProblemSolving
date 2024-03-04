import sys
input = sys.stdin.readline

def flip_row(board, row):
    n = len(board)
    for i in range(n):
        board[row][i] ^= 1

def flip_col(board):
    n = len(board)
    ret = 0
    for j in range(n):
        cnt = 0
        for i in range(n):
            if board[i][j]:
                cnt += 1

        if cnt * 2 < n:
            ret += cnt
        else:
            ret += n - cnt

    return ret


def solution():
    answer = int(1e9)
    n = int(input())
    board = []
    for i in range(n):
        board.append([1 if s == 'H' else 0 for s in input().rstrip()])

    for bit in range(1 << n):
        new_board = [row[:] for row in board]
        for i in range(n):
            if bit & (1 << i):
                flip_row(new_board, i)

        answer = min(answer, flip_col(new_board))

    print(answer)

solution()