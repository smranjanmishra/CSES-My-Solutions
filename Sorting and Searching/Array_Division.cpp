#include <bits/stdc++.h>

using namespace std;

#define nl '\n'
#define ll long long
#define vll vector<ll>

bool check(vector<ll>& v, ll target, int k) {
    int cnt = 1;
    ll sum = 0;
    int n = v.size();
    for (int i = 0; i < n; i++) {
        if (v[i] > target)
            return false;

        if (v[i] + sum > target) {
            sum = v[i];
            cnt++;
        } else {
            sum += v[i];
        }
    }

    return cnt <= k;
}

void solve() {
    int n, k;
    cin >> n >> k;

    vector<ll> v(n);
    for (int i = 0; i < n; i++)
        cin >> v[i];

    ll lo = 1, hi = accumulate(v.begin(), v.end(), 0LL);
    ll ans = hi;
    while (lo <= hi) {
        ll mid = lo + (hi - lo) / 2;
        if (check(v, mid, k)) {
            ans = mid;
            hi = mid - 1;
        } 
        else {
            lo = mid + 1;
        }
    }
    cout << ans << endl;
} 

int main() {
    int t = 1;
    while (t--)
        solve();
}