#include <bits/stdc++.h>

using namespace std;

void TH(int discs, int start, int end, int aux) {
    if (discs == 1) {
        cout << start << " " << end << endl;
        return;
    }
    TH(discs - 1, start, aux, end);
    cout << start << " " << end << endl;
    TH(discs - 1, aux, end, start);
}

int main() {
    int n;
    cin >> n;
    cout << (1 << n) - 1 << endl;
    TH(n, 1, 3, 2);
}