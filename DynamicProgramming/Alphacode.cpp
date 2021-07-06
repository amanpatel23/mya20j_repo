/*
Created By: Aman Patel
Date: 06-07-2021
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

const int N = 5005;

bool check(string s, int len, int i, int digit) {

    if (digit == 1) {
        int num = s[i] - '0';
        if (num >= 1 && num <= 9)
            return true;
        return false;
    }

    if ((i + 1) < len) {
        int num = ((s[i] - '0') * 10) + (s[i + 1] - '0');
        if (num >= 10 && num <= 26)
            return true;

        return false;
    }

    return false;
}

int totalDecodings(string s, int len, v_i &dp, int i) {

    if (i >= len)
        return 1;

    if (dp[i] != -1)
        return dp[i];

    bool digit1 = check(s, len, i, 1);
    bool digit2 = check(s, len, i, 2);

    if (digit1 && digit2) {
        return (dp[i] = totalDecodings(s, len, dp, i + 1) + totalDecodings(s, len, dp, i + 2));
    } else if (digit1) {
        return (dp[i] = totalDecodings(s, len, dp, i + 1));
    } else if (digit2) {
        return (dp[i] = totalDecodings(s, len, dp, i + 2));
    }

    return 0;
}

void solve() {

    while (true) {

        string s;
        cin >> s;
        if (s == "0")
            break;

        int len = s.size();
        v_i dp(N, -1);
        int result = totalDecodings(s, len, dp, 0);
        cout << result << nline;
    }
}

int32_t main() {

    fast;

//#ifndef ONLINE_JUDGE
//    freopen("input.txt", "r", stdin);
//    freopen("output.txt", "w", stdout);
//#endif

    int t;
    t = 1;
    //cin >> t;

    while (t--) {
        solve();
    }

    return 0;
}
