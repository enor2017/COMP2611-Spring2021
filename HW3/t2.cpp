#include <iostream>
using namespace std;

bool isPalindrome(char str[], int n){
    for(int i = 0 ; i < n / 2; ++i){
        // compare the two ends of the string
        if(str[i] == str[n - 1 - i]) continue;
        else return false;
    }
    return true;
}

int main(){
    char str[51];
    cout << "Type the string" << endl;
    cin >> str;
    int length = 0;
    while(str[length] != 0) length++;

    cout << boolalpha << isPalindrome(str, length) << endl;
    return 0;
}