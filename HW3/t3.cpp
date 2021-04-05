#include <iostream>
#include <cstring>
using namespace std;

void Replace(char str[], int index, char substr2[], int substrLength){
    for(int i = 0 ; i < substrLength; ++i){
        str[index + i] = substr2[i];
    }
    return;
}

void FindAndReplaceSubstring(char str[], char substr1[], char substr2[]){
    bool processed = false;
    int length, substrlength;

    length = strlen(str);
    substrlength = strlen(substr1);

    for(int i = 0 ; i < length; ++i){
        if(str[i] == substr1[0]){  // step 1
            for(int j = 0 ; j < substrlength; ++j){  // step 2
                if(str[i + j] != substr1[j]){  // step 3: no match
                    break;
                }
                if(j == substrlength - 1){  // step 3: match and replace
                    Replace(str, i, substr2, substrlength);
                    i = i + substrlength - 1;
                    processed = true;
                }
            }
        }
    }
    if(processed)
        cout << "The processed string: " << str << endl;
    else
        cout << "substring not found!" << endl;

    return;
}

int main(){
    char str[50];
    char substr1[50];
    char substr2[50];

    cout << "Input the string (with length at most 50): ";
    cin >> str;

    cout << "Input the substring you want to replace: ";
    cin >> substr1;
    cout << "Input the substring you want to replace with (in the same length): ";
    cin >> substr2;
    FindAndReplaceSubstring(str, substr1, substr2);

    return 0;
}