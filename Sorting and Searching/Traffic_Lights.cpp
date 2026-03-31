#include <bits/stdc++.h>

using namespace std;
#define nl '\n'

void solve() {
    int x, n;
    cin >> x >> n;

    multiset<int> plens;
    set<int> pos;

    pos.insert(0);
    pos.insert(x);
    plens.insert(x);
    for (int i = 1; i <= n; i++) {
        int p;
        cin >> p;

        auto right = pos.upper_bound(p);
        auto left = right;
        left--;
        int l = *left, r = *right;

        plens.erase(plens.find(r - l));
        plens.insert(p - l);
        plens.insert(r - p);

        cout << *plens.rbegin() << " ";

        pos.insert(p);
    }

    cout << nl;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    int t = 1;
    while (t--)
        solve();
}