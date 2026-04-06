#include <bits/stdc++.h>
using namespace std;

#define ll long long

ll solve_using_multiset(vector<ll>& nums, int n, int a, int b) {
    multiset<ll> wnd;
    vector<ll> psums(n + 1, 0);
    ll ans = -1e18;

    for (int i = 1; i <= n; i++) {
        psums[i] = psums[i - 1] + nums[i];
        if (i < a) continue;
        wnd.insert(psums[i - a]);
        if (i > b) {
            wnd.erase(wnd.find(psums[i - b - 1]));
        }
        ll mss_i = psums[i] - *begin(wnd);
        ans = max(mss_i, ans);
    }

    return ans;
} 

ll solve_using_deque(vector<ll>& nums, int n, int a, int b) {
    deque<ll> wnd;
    vector<ll> psum(n + 1, 0);
    ll ans = -1e18;

    for (int i = 1; i <= n; i++) {
        psum[i] = psum[i - 1] + nums[i];
        if (i < a) continue;

        while (!wnd.empty() && wnd.back() > psum[i - a]) wnd.pop_back();
        wnd.push_back(psum[i - a]);
        if (i > b)
            if (wnd.front() == psum[i - b - 1]) wnd.pop_front();

        ll mss_i = psum[i] - wnd.front();
        ans = max(mss_i, ans);
    }

    return ans;
}
int main() {
    int n, a, b;
    cin >> n >> a >> b;

    vector<ll> nums(n + 1);
    for (int i = 1; i <= n; i++)
        cin >> nums[i];
    ll ans = solve_using_deque(nums, n, a, b);
    cout << ans << "\n";
}