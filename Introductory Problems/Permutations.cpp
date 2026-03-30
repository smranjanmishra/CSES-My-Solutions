#include <bits/stdc++.h>

using namespace std;
#define nl '\n'  
#define sp ' '  

void solve() {
    int n;
    cin >> n;
    if (n == 1) {
        cout << 1 << nl;
    } 
    else if (n <= 3) {
        cout << "NO SOLUTION" << nl;
    } 
    else {
        for (int i = 2; i <= n; i += 2) {
            cout << i << sp;
        }
        for (int i = 1; i <= n; i += 2) {
            cout << i << sp;
        }
    }
}
int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int t = 1;
    while (t--)
        solve();
}