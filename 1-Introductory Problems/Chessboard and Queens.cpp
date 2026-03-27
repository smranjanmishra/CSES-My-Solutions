#include <bits/stdc++.h>

using namespace std;

#define nl '\n'
#define sp ' '

bool is_safe(vector<string>& board, int r, int c) {
    if (board[r][c] == '*') return false;
    int i = r - 1, j = c - 1;
    while (i >= 0 && j >= 0) {
        if (board[i][j] == 'q') return false;
        i--;
        j--;
    }
    j = c - 1;
    while (j >= 0) {
        if (board[r][j] == 'q') return false;
        j--;
    }
    i = r + 1, j = c - 1;
    while (i < 8 && j >= 0) {
        if (board[i][j] == 'q') return false;
        i++;
        j--;
    }
    return true;
}
void count_placements(vector<string>& board, int c, int& count) {
    if (c == 8) {
        count++;
        return;
    }
    for (int r = 0; r < 8; r++) {
        if (is_safe(board, r, c)) {
            board[r][c] = 'q';
            count_placements(board, c + 1, count);
            board[r][c] = '.';
        }
    }
}
void solve() {
    vector<string> board(8);
    for (int i = 0; i < 8; i++)
        cin >> board[i];
    int count = 0;
    int col = 0;
    count_placements(board, col, count);
    cout << count << nl;
}
int main() {
    int t = 1;
    while (t--)
        solve();
}