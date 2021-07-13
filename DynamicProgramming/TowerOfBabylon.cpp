/*
Created By: Aman Patel
Date: 13-07-2021
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

struct dimension {
    int a;
    int b;
    int c;
};

bool compare(struct dimension box1, struct dimension box2) {
    return (box1.b * box1.c > box2.b * box2.c);
}

void solve() {

    int n;
    while (true) {
        cin >> n;
        if (n == 0)
            break;

        int len = 3 * n;
        dimension boxes[len];
        for (int i = 0; i < len; i += 3) {
            int a, b, c;
            cin >> a >> b >> c;
            boxes[i] = dimension{a, max(b, c), min(b, c)};
            boxes[i + 1] = dimension{b, max(a, c), min(a, c)};
            boxes[i + 2] = dimension{c, max(a, b), min(a, b)};
        }

        sort(boxes, boxes + len, compare);

        int dp[len];
        rep(i, 0, len) {
            dp[i] = boxes[i].a;
        }

        int maxHt = 0;
        rep(i, 0, len) {
            rep(j, 0, i) {
                if ((boxes[j].b > boxes[i].b) && (boxes[j].c > boxes[i].c)) {
                    dp[i] = max(dp[i], dp[j] + boxes[i].a);
                }
                maxHt = max(maxHt, dp[i]);
            }
        }

        cout << maxHt << nline;
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