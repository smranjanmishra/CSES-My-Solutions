#include <bits/stdc++.h>

using namespace std;
#define nl '\n'
#define vi vector<int>
#define ll long long
#define len(x) int((x).size())

void solve() {
    int n;
    cin >> n;

    vi arr(n);
    for (int i = 0; i < n; i++)
        cin >> arr[i];

    map<int, int> mp;
    ll ans = 0;
    int i = 0, j = 0;
    while (j < n) {
        mp[arr[j]]++;

        while (mp[arr[j]] > 1) {
            mp[arr[i]]--;
            if (mp[arr[i]] == 0)
                mp.erase(arr[i]);
            i++;
        }
        ans += len(mp);
        j++;
    }

    cout << ans << nl;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    int t = 1;
    while (t--)
        solve();
}