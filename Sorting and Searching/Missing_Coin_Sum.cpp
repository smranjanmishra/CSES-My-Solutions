#include <bits/stdc++.h>

using namespace std;
#define vi vector<int>
#define ll long long
#define nl '\n'

void solve() {
    int n;
    cin >> n;
    vector<ll> arr(n);
    for (int i = 0; i < n; i++) {
        cin >> arr[i];
    }
    sort(arr.begin(), arr.end());
    ll k = 0;
    for (int i = 0; i < n; i++) {
        if (arr[i] > k + 1) {
            cout << (k + 1) << nl;
            return;
        }
        k += arr[i];
    }
    cout << k + 1 << nl;
}
int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int t = 1;
    while (t--)
        solve();
}