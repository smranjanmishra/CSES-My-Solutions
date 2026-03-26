#include <bits/stdc++.h>

using namespace std;

#define len(x) int((x).size())
#define nl '\n'
#define sp ' '
#define ll long long

void solve_using_stl(string& s) {
    sort(s.begin(), s.end());
    vector<string> v;

    do {
        v.push_back(s);
    } while (next_permutation(s.begin(), s.end()));

    cout << v.size() << nl;
    for (auto perm : v)
        cout << perm << nl;
}

void generate_permutations(string& s, vector<int>& flag, string& aux, set<string>& results) {
    if (len(aux) == len(s)) {
        results.insert(aux);
        return;
    }

    for (int i = 0; i < len(flag); i++) {
        if (flag[i]) continue;

        aux.push_back(s[i]);
        flag[i] = 1;

        generate_permutations(s, flag, aux, results);

        aux.pop_back();
        flag[i] = 0;
    }
}

void solve_using_flag_array(string& s) {
    int n = len(s);
    string aux = "";
    vector<int> flag(n, 0);
    set<string> unq_perms;

    generate_permutations(s, flag, aux, unq_perms);

    cout << len(unq_perms) << nl;
    for (auto perm : unq_perms)
        cout << perm << nl;
}

void print_permutation(map<char, int>& fm, string& aux, int n) {
    if (len(aux) == n) {
        cout << aux << nl;
        return;
    }

    for (int i = 0; i < 26; i++) {
        char ch = 'a' + i;
        if (fm.find(ch) != fm.end()) {
            aux.push_back(ch);
            if (fm[ch] == 1)
                fm.erase(ch);
            else
                fm[ch]--;

            print_permutation(fm, aux, n);

            fm[ch]++;
            aux.pop_back();
        }
    }
}

int fact(int n) {
    int res = 1;
    for (int i = 1; i <= n; i++) res *= i;
    return res;
}

void optimal_solve_using_fm(string& s) {
    int n = len(s);
    map<char, int> fm;
    for (auto ch : s)
        fm[ch]++;

    int divisor = 1;
    for (auto& [ch, f] : fm)
        divisor *= fact(f);
    cout << (fact(n) / divisor) << nl;

    string aux = "";
    print_permutation(fm, aux, n);
}

void solve() {
    string s;
    cin >> s;

    optimal_solve_using_fm(s);
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    int t = 1;
    while (t--)
        solve();
}