#include <bits/stdc++.h>

using namespace std;
#define nl '\n'

void solve() {
    int num;
    cin >> num;
    int count_5s = 0;
    while (num > 0) {
        count_5s += num / 5;
        num /= 5;
    }
    cout << count_5s << nl;
}
int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int t = 1;
    while (t--) solve();
}