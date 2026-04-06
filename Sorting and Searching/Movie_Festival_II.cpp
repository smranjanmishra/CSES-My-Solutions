#include <bits/stdc++.h>

using namespace std;

#define nl '\n'
#define ll long long
#define vii vector<pair<int, int>>
#define len(x) int(x.size())

void solve() {
    int n, k;
    cin >> n >> k;

    vii intervals;
    for (int i = 0; i < n; i++) {
        int start, end;
        cin >> start >> end;
        intervals.push_back({end, start});
    }
    sort(intervals.begin(), intervals.end());

    int total = 1;
    multiset<int> ets;
    ets.insert(intervals[0].first);

    for (int i = 1; i < n; i++) {
        auto [cur_et, cur_st] = intervals[i];

        auto it = ets.upper_bound(cur_st);
        if (it == ets.begin()) {
            if (ets.size() < k) {
                ets.insert(cur_et);
                total++;
            }
        } 
        else {
            it--;
            ets.erase(it);
            ets.insert(cur_et);
            total++;
        }
    }
    cout << total << nl;
}

int main() {
    int t = 1;
    while (t--)
        solve();
}