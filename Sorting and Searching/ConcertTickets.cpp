#include <bits/stdc++.h>

using namespace std;
#define nl '\n'

void solve() {
    int n, m;
    cin >> n >> m;
    multiset<int> prices;
    for (int i = 0; i < n; i++) {
        int p;
        cin >> p;
        prices.insert(p);
    }
    for (int i = 0; i < m; i++) { 
        int th;
        cin >> th;
        auto it = prices.upper_bound(th);
        if (it == prices.begin()) {
            cout << "-1" << nl;
        } 
        else {
            it--;
            cout << *it << nl;
            prices.erase(it);
        }
    }
}  
int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int t = 1;
    while (t--)
        solve();
}