#include <bits/stdc++.h>

using namespace std;
#define nl '\n'
#define vi vector<int>

void solve() {
    int n, m;
    cin >> n >> m;
    unordered_map<int, int> pos;
    pos[0] = 0;
    pos[n + 1] = n + 1;
    vi arr(n + 1);
    for (int i = 1; i <= n; i++) {
        cin >> arr[i];
        pos[arr[i]] = i;
    }
    int rounds = 1;
    for (int i = 2; i <= n; i++) {
        if (pos[i] < pos[i - 1]) rounds++;
    }
    while (m--) {
        int i, j;
        cin >> i >> j;
        if (i > j) swap(i, j);
        int x = arr[i];
        int y = arr[j];
        if (pos[x + 1] > i && pos[x + 1] < j) rounds++;
        if (pos[x - 1] > i && pos[x - 1] < j) rounds--;
        if (pos[y + 1] > i && pos[y + 1] < j) rounds--;
        if (pos[y - 1] > i && pos[y - 1] < j) rounds++;
        if (x == (y + 1)) rounds--;
        if (x == (y - 1)) rounds++; 
        cout << rounds << nl;
        swap(arr[i], arr[j]);
        pos[x] = j;
        pos[y] = i;
    }
}
int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int t = 1;
    while (t--)
        solve();
}