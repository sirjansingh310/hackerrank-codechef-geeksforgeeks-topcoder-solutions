//// https://www.hackerearth.com/practice/data-structures/trees/binary-search-tree/practice-problems/algorithm/monk-and-cursed-tree/
#include<stdio.h>
#include<stdlib.h>
struct node{
  struct node* left;
  struct node* right;
  int val;
};
void insert(struct node* root, int n){
    if(root == NULL) 
       return;
    if(n<=root->val)
    {
        if(root->left)
           insert(root->left,n);
        else{
            struct node* new = (struct node*)malloc(sizeof(struct node));
            new->val = n;
            root->left = new;
        }
    }
    else{
            if(root->right)
              insert(root->right,n);
        else{
            struct node* new = (struct node*)malloc(sizeof(struct node));
            new->val = n;
            root->right = new;
        }
    }
}
void inorder(struct node* root){
    if(root == NULL)
       return ;
    else
    {
        inorder(root->left);
        printf("%d ",root->val);
        inorder(root->right);
    }
}
int distance(struct node* root,int n){
    if(root->val == n)
        return 0;
    if(n<root->val)
        return 1+distance(root->left,n);
    if(n>root->val)
        return 1+distance(root->right,n);
}
struct node* lca(struct node* root,int n1,int n2){
    if(root == NULL)
       return NULL;
    if(n1<root->val && n2<root->val)
       return lca(root->left,n1,n2);
    if(n1>root->val && n2>root->val)
    return  lca(root->right,n1,n2);
    return root;
}
int max(int a,int b){
    if(a>b)
    return a;
    else
    return b;
}
int maxPath(struct node* root, int n){
    if(root->val == n)
       return n;
    if(n<root->val)
          return max(maxPath(root->left,n),root->val);
    if(n>root->val)
          return max(maxPath(root->right,n),root->val);
}
int main(){
    int n,i;
    scanf("%d",&n);
    int a[n];
    for(i=0;i<n;i++){
        scanf("%d",&a[i]);
    }
    struct node* root = (struct node*)malloc(sizeof(struct node));
    root->val = a[0];
    for(i=1;i<n;i++){
        insert(root,a[i]);
    }
   // inorder(root);
    int n1,n2;
    scanf("%d %d",&n1,&n2);
    struct node* LCA = lca(root,n1,n2);
    printf("%d",max(maxPath(LCA,n1),maxPath(LCA,n2)));
return 0;

}
