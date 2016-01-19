#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <unordered_map>
using namespace std;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/


struct Node{
    public:
        int id;
        std::vector<Node> connections;
        Node(int x){id = x;}
        void SignalRemove();
        void Remove(int i);
        bool operator==(const Node& n) {return (id == n.id);}
        
};

void Node::SignalRemove(){
    connections.at(0).Remove(id);
}

void Node::Remove(int i){
    cerr<<id<<" "<<connections.size()<<endl;
    for(std::vector<Node>::iterator it = connections.begin(); 
            it != connections.end();) {
                if(it->id==i){
                    //cerr<<"Removed connection :"<<id<<" "<<i<<endl;
                    connections.erase(it);
                    return;
                }else{it++;}
    }
}



int main()
{
    typedef std::unordered_map<int, Node> ht;
    typedef ht::iterator htIter;
    ht nodes;
    ht leaves;
    int n; // the number of adjacency relations
    cin >> n; cin.ignore();
    for (int i = 0; i < n; i++) {
        int xi; // the ID of a person which is adjacent to yi
        int yi; // the ID of a person which is adjacent to xi
        cin >> xi >> yi; cin.ignore();
        if(nodes.count(xi)==0){
            nodes.emplace(xi, Node(xi));
            //cerr<<"inserting "<<xi<<endl;
        }
        
        if(nodes.count(yi)==0){
            nodes.emplace(yi, Node(yi));
            //cerr<<"inserting "<<yi<<endl;
        }
        //cerr<<xi<<" "<<yi<<endl;
        nodes.find(xi)->second.connections.push_back(nodes.find(yi)->second);
        nodes.find(yi)->second.connections.push_back(nodes.find(xi)->second);
    }
    //cerr<<"aaa";
    /*
     * DEBUGGING
    for(htIter it = nodes.begin() ;it != nodes.end(); it++){
        cerr<<"#####"<<endl;
        cerr<<it->first<<" "<<it->second.connections.size()<<endl;
        for(int i =0 ; i < it->second.connections.size(); i++){
            cerr<<it->second.connections.at(i).id<<" ";
        }
        cerr<<endl;
        cerr<<"#####"<<endl;
    }
    cerr<<"------------"<<endl;
    
    return 0;*/
    int turns = 0;
    //cerr<<"aaa";
    while(nodes.size()>1){
        
        for(htIter it = nodes.begin() ;it != nodes.end();){
            //cerr<<it->second.id<<" "<<it->second.connections.size()<<" %%%"<<endl;
            if(it->second.connections.size()==1){
                //cerr<<"Leaf :"<<it->second.id<<endl;
                leaves.emplace(it->second.id, it->second);
                it=nodes.erase(it);
            }else{it++;}
        }
        /*
         * DEBUGGING
        cerr<<"============="<<endl;
        cerr<<nodes.size()<<endl;
        cerr<<leaves.size()<<endl;
        cerr<<"============="<<endl;
        return 0;
        * */
        if(nodes.size()>0){
            for(htIter it = leaves.begin() ;it != leaves.end();it++){
                nodes.find(it->second.connections.at(0).id)->
                second.Remove(it->second.id);
                //nodes.erase(id);
            }
        }
        /*
         * DEBUGGING
        cerr<<"------------"<<endl;
        cerr<<nodes.size()<<endl;
        cerr<<leaves.size()<<endl;
        cerr<<"------------"<<endl;
        return 0;
        */
        leaves.clear();
        turns++;
        cerr<<nodes.size()<<endl;
    }

    cout << turns << endl; // The minimal amount of steps required to completely propagate the advertisement
}


