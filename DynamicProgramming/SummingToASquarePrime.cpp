/*
Created By: Aman Patel
Date: 26-07-2021
*/

#include <iostream>
#include <vector>
#include <map>
#include <list>
#include <set>
#include <algorithm>
#include <queue>
#include <stack>
#include <cstring>
#include <cmath>
#include <bitset>
#include <string>
#include <unordered_set>
#include <unordered_map>
#include <cstdlib>
#include <iomanip>

#define int long long
#define mod 1000000007
#define i_max INT_MAX
#define i_min INT_MIN
#define s_i set<int>
#define v_i vector<int>
#define v_s vector<string>
#define v_c vector<char>
#define stk_i stack<int>
#define q_i queue<int>
#define qp_ii queue<pair<int, int>>
#define pqp_ii priority_queue<pair<int, int>>
#define vp_ii vector<pair<int, int>>
#define um_ii unordered_map<int, int>
#define m_ii map<int, int>
#define p_ii pair<int, int>
#define all(a) (a).begin(), (a).end()
#define mem1(a) memset(a, -1, sizeof(a))
#define mem0(a) memset(a, 0, sizeof(a))
#define lbnd lower_bond
#define ubnd upper_bond
#define ff first
#define ss second
#define mp make_pair
#define pb push_back
#define nline "\n"
#define yes (cout << "YES" << nline)
#define no (cout << "NO" << nline)
#define rep(i, a, b) for(int i = a; i < b; i++)
#define fast ios_base::sync_with_stdio(false), cin.tie(nullptr), cout.tie(nullptr)

using namespace std;

const int N = 8000;
const int N2 = 500;
bool primes[N + 5];
int squarePrimes[N2 + 5];

void init() {

    rep (i, 0, (N + 1)) primes[i] = true;
    for (int i = 2; i * i <= N; i++) {
        if (primes[i]) {
            for (int j = i * i; j <= N; j += i) primes[j] = false;
        }
    }

    // filling squarePrimes arr...
    squarePrimes[1] = 2;
    int idx = 2;
    for (int i = 3; i <= N; i++) {
        if (primes[i] && (i % 4 == 1)) {
            squarePrimes[idx] = i;
            idx++;
        }

        if (idx > N2)
            break;
    }
}

int n, k, num;

void solve() {

    cin >> n >> k;
    num = squarePrimes[n];

    int dpTable[k + 1][num + 1];
    rep (i, 0, (k + 1)) {
        rep (j, 0, (num + 1)) {

            if (i == 0 || j == 0)
                dpTable[i][j] = 0;
            else if (j < i)
                dpTable[i][j] = dpTable[i - 1][j];
            else {
                dpTable[i][j] = dpTable[i][j - i] + dpTable[i - 1][j];
                if (i == j)
                    dpTable[i][j] += 1;
            }
        }
    }

    cout << dpTable[k][num] << nline;
}

int32_t main() {

    fast;

#ifndef ONLINE_JUDGE
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
#endif

    init();
    int t;
    t = 1;
    cin >> t;

    while (t--) {
        solve();
    }

    return 0;
}