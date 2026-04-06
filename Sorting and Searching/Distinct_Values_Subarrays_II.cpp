#include <bits/stdc++.h>
using namespace std;

#define nl '\n'
#define ll long long
#define vi vector<int>
#define len(x) int(x.size())

void solve() {
    int n, k;
    cin >> n >> k;

    vi arr(n);
    for (int i = 0; i < n; i++)
        cin >> arr[i];

    map<int, int> mp;
    ll ans = 0;
    int i = 0, j = 0;
    while (j < n) {
        mp[arr[j]]++;

        while (len(mp) > k) {
            mp[arr[i]]--;
            if (mp[arr[i]] == 0)
                mp.erase(arr[i]);
            i++;
        }

        ans += (j - i + 1);
        j++;
    }

    cout << ans << nl;
}

int main() {
    int t = 1;
    // cin >> t;

    while (t--)
        solve();
}