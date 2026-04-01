#include <algorithm>
#include <climits>
#include <iostream>
#include <vector>

using namespace std;

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

    int max_end_so_far = INT_MIN;
    for (int i = 0; i < n; i++) {
        const auto& current = intervals[i];
        if (max_end_so_far != INT_MIN && max_end_so_far >= current.end) {
            is_contained_by_another[current.original_index] = 1;
        }
        max_end_so_far = max(max_end_so_far, current.end);
    }

    int min_end_seen = INT_MAX;
    for (int i = n - 1; i >= 0; i--) {
        const auto& current = intervals[i];
        if (min_end_seen != INT_MAX && min_end_seen <= current.end) {
            contains_another[current.original_index] = 1;
        }
        min_end_seen = min(min_end_seen, current.end);
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