#include <bits/stdc++.h>
using namespace std;

#define nl '\n'
#define ll long long
#define vll vector<ll>

void solve() {
    int n;
    cin >> n;
    ll x;
    cin >> x;

    vll arr(n);
    for (int i = 0; i < n; i++)
        cin >> arr[i];

    int i = 0, j = 0;
    ll cur = 0;
    ll total = 0;
    while (j < n) {
        cur += arr[j];

        while (cur > x) {
            cur -= arr[i];
            i++;
        }
        if (cur == x) total++;
        j++;
    }
    cout << total << nl;
}

int main() {
    int t = 1;
    while (t--)
        solve();
}