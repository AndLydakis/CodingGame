/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

var n = parseInt(readline());
var inputs = readline().split(' ');
var max = -1;
var maxDif = 0;
for (var i = 0; i < n; i++) {
    var v = parseInt(inputs[i]);
    if(v>max){max = v;}
    else{
        if(-Math.abs((max - v))<maxDif){
            maxDif = -Math.abs((max - v));
        }
    }
}

// Write an action using print()
// To debug: printErr('Debug messages...');

print(maxDif);
