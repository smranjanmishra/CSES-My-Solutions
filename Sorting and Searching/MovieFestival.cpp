#include <bits/stdc++.h>

using namespace std;
#define vii vector<pair<int, int>>
#define nl '\n'

void solve() {
    int n;
    cin >> n;
    vii times;
    for (int i = 0; i < n; i++) {
        int st, et;
        cin >> st >> et;
        times.push_back({et, st});
    }
    sort(times.begin(), times.end());
    int prev_et = times[0].first;
    int cnt = 1;
    for (int i = 1; i < n; i++) {
        if (times[i].second >= prev_et) {
            cnt++;
            prev_et = times[i].first;
        }
    }
    cout << cnt << nl;
} 

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int t = 1;
    while (t--)
        solve();
}