#include <bits/stdc++.h>

using namespace std;
#define vi vector<int>
#define ll long long
#define nl '\n'

void kadanes_algo() {
    int n;
    cin >> n;
    vi arr(n);
    for (int i = 0; i < n; i++)
        cin >> arr[i];
    ll mss = arr[0];
    ll mss_i = arr[0];
    for (int i = 1; i < n; i++) {
        mss_i = max(1LL * arr[i], mss_i + arr[i]);
        mss = max(mss_i, mss);
    }
    cout << mss << nl;
}  
void solve() {
    int n;
    cin >> n;
    vi arr(n);
    for (int i = 0; i < n; i++)
        cin >> arr[i];
    ll mss = arr[0];
    ll psum_i = arr[0];
    ll psum_mini = min(0, arr[0]);
    for (int i = 1; i < n; i++) {
        psum_i += arr[i];
        ll mss_i = psum_i - psum_mini;
        mss = max(mss, mss_i);
        psum_mini = min(psum_mini, psum_i);
    }
    cout << mss << nl;
} 
int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int t = 1;
    while (t--)
        kadanes_algo();
}