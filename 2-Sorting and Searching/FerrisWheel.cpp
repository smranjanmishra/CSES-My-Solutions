#include <bits/stdc++.h>

using namespace std;

#define nl '\n'
#define sp ' '
#define vi vector<int>

void solve() {
    int n, x;
    cin >> n >> x;
    vi weights(n);
    for (int i = 0; i < n; i++)
        cin >> weights[i];
    sort(weights.begin(), weights.end());
    int i = 0, j = n - 1;
    int cnt = 0;
    while (i <= j) {
        if (i != j) {
            if (weights[i] + weights[j] <= x) {
                i++;
                j--;
            } 
            else { 
                j--;
            }
        } 
        else { 
            i++;
            j--;
        }
        cnt++;
    }
    cout << cnt << nl;
}

int main() {
    int t = 1;
    while (t--)
        solve();
}