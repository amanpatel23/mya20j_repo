#include <bits/stdc++.h>
#define ll long long
#define nline "\n"

using namespace std;

ll modmul(ll a, ll b, ll mod) {

    a %= mod;

    ll result = 0;
    while (b > 0) {
        if (b & 1 == 1) {
            result = (result + a) % mod;
            b--;
        }

        a = (2LL * a) % mod;
        b >>= 1;
    }

    return result;
}

ll power(ll a, ll n, ll mod) {

    ll result = 1;
    while (n > 0) {
        if (n & 1 == 1) {
            result = modmul(result, a, mod);
            n--;
        }
        a = modmul(a, a, mod);
        n >>= 1;
    }

    return result;
}

bool fermatPrime(ll n, int k) {

    if (n == 2 || n == 3)
        return true;

    for (int i = 1; i <= k; i++) {
        ll a = 2 + rand() % (n - 3);
        //cout << a << nline;
        if (__gcd(a, n) != 1)
            return false;
        if (power(a, n - 1, n) != 1)
            return false;
    }

    return true;
}

void solve() {
    ll n;
    cin >> n;

    string result = fermatPrime(n, 10) ? "YES" : "NO";
    cout << result << nline;
}

int main()
{
    int t;
    cin >> t;

    while (t--) {
        solve();
    }

    return 0;
}
