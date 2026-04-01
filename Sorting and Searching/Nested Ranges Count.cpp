#include <bits/stdc++.h>
#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/tree_policy.hpp>

#define len(x) int((x).size())
using namespace std;
using namespace __gnu_pbds;

template <class T>
using ordered_set = tree<T, null_type, less<T>, rb_tree_tag, tree_order_statistics_node_update>;
template <class T>
using ordered_multiset = tree<T, null_type, less_equal<T>, rb_tree_tag, tree_order_statistics_node_update>;

struct Interval {
    int start;
    int end;
    int original_index;

    Interval(int s, int e, int idx) : start(s), end(e), original_index(idx) {}
};

bool compareIntervals(const Interval& a, const Interval& b) {
    if (a.start == b.start) {
        return a.end > b.end;
    }
    return a.start < b.start;
}

void solve() {
    int n;
    cin >> n;

    vector<Interval> intervals;
    for (int i = 0; i < n; i++) {
        int start, end;
        cin >> start >> end;
        intervals.emplace_back(start, end, i);
    }

    sort(intervals.begin(), intervals.end(), compareIntervals);

    vector<int> is_contained_by_another(n, 0);
    vector<int> contains_another(n, 0);

    ordered_multiset<int> ets;
    for (int i = 0; i < n; i++) {
        const auto& current = intervals[i];

        int lb = ets.order_of_key(current.end);
        is_contained_by_another[current.original_index] = len(ets) - lb;
        ets.insert(current.end);
    }

    ets.clear();
    for (int i = n - 1; i >= 0; i--) {
        const auto& current = intervals[i];

        int ub = ets.order_of_key(current.end + 1);
        contains_another[current.original_index] = ub;
        ets.insert(current.end);
    }

    for (int i = 0; i < n; i++) {
        cout << contains_another[i];
        if (i < n - 1) cout << " ";
    }
    cout << '\n';

    for (int i = 0; i < n; i++) {
        cout << is_contained_by_another[i];
        if (i < n - 1) cout << " ";
    }
    cout << '\n';
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    solve();

    return 0;
}