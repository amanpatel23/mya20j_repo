/*
Created By: Aman Patel
Date: 08-07-2021
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

int assignments[20][20];
int dp[20][1 << 20];
int n;

int diffAssignments(int i, int masking) {

    if(i >= n)
        return 1;

    if(dp[i][masking] != -1)
        return dp[i][masking];

    int ans = 0;
    rep(j, 0, n) {
        if(assignments[i][j] && ((1 << j) & masking)) {
            ans += diffAssignments(i + 1, ((1 << j) ^ masking));
        }
    }

    return (dp[i][masking] = ans);
}

void solve() {

    cin >> n;

    rep(i, 0, n) {
        rep(j, 0, n) {
            cin >> assignments[i][j];
        }
    }

    mem1(dp);
    int result = diffAssignments(0, (1 << n) - 1);
    cout << result << nline;
}

int32_t main() {

    fast;

//#ifndef ONLINE_JUDGE
//    freopen("input.txt", "r", stdin);
//    freopen("output.txt", "w", stdout);
//#endif

    int t;
    t = 1;
    cin >> t;

    while (t--) {
        solve();
    }

    return 0;
}