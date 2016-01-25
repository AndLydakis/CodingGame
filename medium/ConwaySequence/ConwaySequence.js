/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

var R = parseInt(readline());
var L = parseInt(readline());

// Write an action using print()
// To debug: printErr('Debug messages...');
var s=[];
s.push(R);
Conway (s, L);

function Conway(r, l){
    if(l == 1){
        var str ="";
        for(var i = 0; i <r.length-1; i ++){
            str+=(r[i]+" ");
        }
        str+=r[r.length-1];
        print(str);
        return;
    }
    var seq =[];
    var val = r[0];
    var counter = 1;
    for(var i = 1; i<r.length;i++){
        if(val === r[i]){
            counter++;
        }else{
            seq.push(counter);
            seq.push(val);
            val = r[i];
            counter = 1;
        }
    }
    seq.push(counter);
    seq.push(val);
    Conway(seq, l-1);
}
