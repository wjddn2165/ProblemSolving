#include <iostream>
#include <vector>
#include <stack>
#include <queue>
#include <algorithm>
#include <vector>
#include <utility>
#include <deque>
#include <string.h>

using namespace std;

typedef pair<int,int> pii;
typedef long long ll;

const ll INFLL = 1LL << 60;
const int INF = 21*1e8;
const int MAX = 1e3;

vector<vector<int>> point;
vector<vector<int>> quest;
vector<vector<int>> diff;
vector<vector<bool>> can;
vector<pair<pii,int>> vec;
int n;



int main()
{
   ios_base :: sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);
   cin>>n;

   vec = vector<pair<pii,int>>(n);
   
   for(int i = 0;i<n;i++)
   {
       cin>>vec[i].first.first>>vec[i].first.second>>vec[i].second;
   }

   point = vector<vector<int>>(MAX+1,vector<int>(MAX+1,0));
   diff = vector<vector<int>>(MAX+1,vector<int>(MAX+1,0));
   quest = vector<vector<int>>(MAX+1,vector<int>(MAX+1,0));
   can = vector<vector<bool>>(MAX+1,vector<bool>(MAX+1,false));

   can[1][1] = true;
   
   for(int i = 1;i<=MAX;i++)
   {
       for(int j = 1;j<=MAX;j++)
       {
           for(int x = 0;x<n;x++)
           {
               if(i >= vec[x].first.first || j >= vec[x].first.second)
               {
                   quest[i][j]++;
                   point[i][j] += vec[x].second;
               }
           }
       }
   }

   diff[1][1] = point[1][1];
   queue<pii> q;
   q.push({1,1});

   while(!q.empty())
   {
       pii cur = q.front();
       q.pop();

       int s = cur.first;
       int i = cur.second;

       for(int x = 0;x<=diff[s][i];x++)
       {
           diff[min(MAX,s+diff[s][i]-x)][min(MAX,i+x)] =  max(diff[min(MAX,s+diff[s][i]-x)][min(MAX,i+x)],
            point[min(MAX,s+diff[s][i]-x)][min(MAX,i+x)] - point[s][i]);

           if(!can[min(MAX,s+diff[s][i]-x)][min(MAX,i+x)])
           {
               can[min(MAX,s+diff[s][i]-x)][min(MAX,i+x)] = true;
               q.push({min(MAX,s+diff[s][i]-x),min(MAX,i+x)});
           }
       }
   }

   int ans = 0;

    for(int i = 1;i<=MAX;i++)
   {
       for(int j = 1;j<=MAX;j++)
       {
           if(can[i][j]) 
           {
               ans = max(ans,quest[i][j]);
           }
       }
   }

   cout<<ans;
}