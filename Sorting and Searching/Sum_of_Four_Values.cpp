#include <bits/stdc++.h>

using namespace std;

#define nl '\n'
#define sp ' '
#define ll long long
#define vi vector<int>
#define len(x) int((x).size())
#define all(n) n.begin(), n.end()

void solve() {
    int n, x;
    cin >> n >> x;

    vi arr(n);
    for (int i = 0; i < n; i++)
        cin >> arr[i];

    set<tuple<int, int, int>> pair_sums;
    for (int i = 0; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
            pair_sums.insert({arr[i] + arr[j], i, j});
        }
    }

    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            pair_sums.erase({arr[i] + arr[j], i, j});
        }

        for (int j = i - 1; j >= 0; j--) {
            int diff = x - arr[i] - arr[j];

            auto it = pair_sums.lower_bound({diff, -1, -1});
            if (it == pair_sums.end()) continue;

            auto [sum, k, l] = *it;
            if (sum != diff) continue;

            cout << (i + 1) << sp << (j + 1) << sp << (k + 1) << sp << (l + 1) << nl;
            return;
        }
    }

    cout << "IMPOSSIBLE" << nl;
}

int main() {
    int t = 1;
    while (t--)
        solve();
}