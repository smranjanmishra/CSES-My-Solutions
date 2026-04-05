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

    map<ll, ll> psum_count;
    psum_count[0] = 1;
    ll cur_sum = 0;
    ll total = 0;
    for (int i = 0; i < n; i++) {
        cur_sum += arr[i];
        total += psum_count[cur_sum - x];
        psum_count[cur_sum]++;
    }

    cout << total << nl;
}

int main() {
    int t = 1;
    while (t--)
        solve();
}