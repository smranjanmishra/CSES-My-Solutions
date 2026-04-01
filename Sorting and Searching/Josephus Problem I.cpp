#include <bits/stdc++.h>
using namespace std;

#define sp ' '
#define nl '\n'
#define vi vector<int>
#define len(x) int((x).size())

void josephus_1(vi& nums) {
    int n = len(nums);

    if (n == 1) {
        cout << nums[0] << nl;
        return;
    }

    for (int i = 1; i < n; i += 2)
        cout << nums[i] << sp;

    vi narr;
    if (n % 2 == 0) {
        for (int i = 0; i < n; i += 2)
            narr.push_back(nums[i]);
    } else {
        narr.push_back(nums[n - 1]);
        for (int i = 0; i < n - 1; i += 2)
            narr.push_back(nums[i]);
    }

    josephus_1(narr);
}

void solve() {
    int n;
    cin >> n;

    vi nums(n);
    for (int i = 1; i <= n; i++)
        nums[i - 1] = i;

    josephus_1(nums);
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    int t = 1;
    // cin >> t;
    while (t--)
        solve();
}