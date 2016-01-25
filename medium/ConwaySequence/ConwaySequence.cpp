#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
void Conway(std::vector<std::string> v, int l); 

int main()
{
    int R;
    cin >> R; cin.ignore();
    int L;
    cin >> L; cin.ignore();
	
	std::vector<string> v;
	v.push_back(to_string(R));
	
	Conway(v, L);
	
}

void Conway(const std::vector<std::string> v, int l){
	if(l==1){
		for(int i = 0 ; i < v.size()-1; i++){
			cout<<v.at(i)<<" ";
		}
		cout<<v.at(v.size()-1)<<endl;
		return;
	}
	
	std::vector<std::string> seq ;
	string val = v.at(0);
	int counter = 1;
	for(int i = 1; i<v.size(); i++){
		if(val.compare(v.at(i))==0){
			counter++;
		}else{
			seq.push_back(to_string(counter));
			seq.push_back(val);
			counter = 1;
			val = v.at(i);
		}
	}
	seq.push_back(to_string(counter));
	seq.push_back(val);
	Conway(seq, l-1);
	
}