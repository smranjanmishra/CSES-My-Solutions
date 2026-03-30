#include <bits/stdc++.h>

using namespace std;

#define nl '\n'
#define sp ' '
#define ll long long

void solve() {
    string s;
    cin >> s;
    unordered_map<char, int> freq;
    for (char ch : s) {
        freq[ch]++;
    }
    char center = '\0';
    int oddCount = 0;
    for (const auto& [ch, count] : freq) {
        if (count % 2 == 1) {
            oddCount++;
            center = ch;
            if (oddCount > 1) {
                cout << "NO SOLUTION\n";
                return;
            }
        }
    }
    string result;
    result.reserve(s.length());  
    for (const auto& [ch, count] : freq) {
        result.append(count / 2, ch);
    }
    if (center != '\0') {
        result += center;
    }
    int firstHalfSize = s.length()/2;
    for (int i = firstHalfSize - 1; i >= 0; i--) {
        result.push_back(result[i]);
    }
    cout << result << '\n';
}
int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int t = 1;
    while (t--)
        solve();
}