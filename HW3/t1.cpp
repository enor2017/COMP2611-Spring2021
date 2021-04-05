#include <iostream>
using namespace std;

int RGB2Gray(int rgb[]){
    return (rgb[0] * 19595 + rgb[1] * 38469 + rgb[2] * 7472) >> 16;
}

int main(){
    int rgb[3];
    cin >> rgb[0] >> rgb[1] >> rgb[2];
    cout << RGB2Gray(rgb) << endl;
    return 0;
}