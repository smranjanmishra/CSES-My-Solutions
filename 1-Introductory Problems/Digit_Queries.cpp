#include <bits/stdc++.h>
using namespace std;

#define nl '\n'
#define sp ' '
#define len(x) int((x).size())
#define ll long long

void solve() {
    int q;
    cin >> q;

    while (q--) {
        ll k;
        cin >> k;

        ll dig_len = 1; 
        ll count = 9;   
        ll start = 1; 
        while (k > start + dig_len * count - 1) {
            start += dig_len * count; 
            dig_len++;              
            count *= 10;              
        }
        ll first_num = pow(10, dig_len - 1);  
        ll num_offset = (k - start) / dig_len; 
        ll target_num = first_num + num_offset;  
        string num_str = to_string(target_num);
        ll digit_offset = k - start;
        ll pos_in_num = digit_offset % dig_len;
        cout << num_str[pos_in_num] << "\n";
    }
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int t = 1;
    while (t--)
        solve();
}