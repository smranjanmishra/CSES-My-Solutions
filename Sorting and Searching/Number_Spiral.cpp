#include <iostream>
using namespace std;
#define ll long long

int main() {
    ll t;
    cin >> t;
    while (t--) {
        ll x, y;
        cin >> x >> y;
        ll ans;
        if (x <= y) {
            if (y % 2 == 0) {
                ans = (y - 1) * (y - 1) + x;
            } 
            else {
                ans = y * y - x + 1;
            }
        } 
        else {
            if (x % 2 == 0) {
                ans = x * x - y + 1;
            } 
            else {
                ans = (x - 1) * (x - 1) + y;
            }
        }
        cout << ans << "\n";
    }
    return 0;
}