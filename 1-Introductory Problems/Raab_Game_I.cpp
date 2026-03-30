#include <bits/stdc++.h>

using namespace std;

#define nl '\n'
#define sp ' '

void solve() {
    int n, a, b;
    cin >> n >> a >> b;

    if (a + b > n) {
        cout << "NO" << nl;
        return;
    }
    if ((a == 0 || b == 0) && (a + b != 0)) {
        cout << "NO" << nl;
        return;
    }

    cout << "YES" << nl;
    for (int i = 1; i <= n; i++) cout << i << sp;
    cout << nl;

    for (int i = a + 1; i <= a + b; i++) cout << i << sp;
    for (int i = 1; i <= a; i++) cout << i << sp;
    for (int i = a + b + 1; i <= n; i++) cout << i << sp;
    cout << nl;
}

int main() {
    int t = 1;
    cin >> t;

    while (t--)
        solve();
}