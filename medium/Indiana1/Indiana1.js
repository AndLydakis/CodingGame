/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

var inputs = readline().split(' ');
var W = parseInt(inputs[0]); // number of columns.
var H = parseInt(inputs[1]); // number of rows.
var rooms = new Array(H);
for(var i = 0; i < W; i++){
    rooms[i] = new Array(W);
}
var tokens;
for (var i = 0; i < H; i++) {
    tokens = readline().split(" "); // represents a line in the grid and contains W integers. Each integer represents one room of a given type.
    for(var j =0 ; j < W; j++){
        rooms[j][i]=parseInt(tokens[j]);
    }

    
}
var EX = parseInt(readline()); // the coordinate along the X axis of the exit (not useful for this first mission, but must be read).

// game loop
while (true) {
    var inputs = readline().split(' ');
    var XI = parseInt(inputs[0]);
    var YI = parseInt(inputs[1]);
    var POS = inputs[2];
    GoThroughRoom(parseInt(XI), parseInt(YI), POS, rooms[XI][YI]);
    // Write an action using print()
    // To debug: printErr('Debug messages...');
}

function GoThroughRoom(XI, YI, POS, TYPE){
    //printErr(XI+" "+YI+" "+POS+" "+TYPE);
    switch(TYPE){
            case 0:
                break;
            case 1:
                YI++;
                break;
            case 2:
                switch(POS){
                    case"LEFT":
                        XI++;
                        break;
                    case"RIGHT":
                        XI--;
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                printErr(XI+" "+YI+" "+POS+" "+TYPE);
                YI++;
                break;
            case 4:
                switch(POS){
                    case"TOP":
                        XI--;
                        break;
                    case"RIGHT":
                        YI++;
                        break;
                    default:
                        break;
                }
                break;
            case 5:
                switch(POS){
                    case"TOP":
                        XI++;
                        break;
                    case"LEFT":
                        YI++;
                        break;
                    default:
                        break;
                }
                break;
            case 6:
                switch(POS){
                    case"LEFT":
                        XI++;
                        break;
                    case"RIGHT":
                        XI--;
                        break;
                    default:
                        break;
                }
                break;
            case 7:
                YI++;
                break;
            case 8:
                YI++;
                break;
            case 9:
                YI++;
                break;
            case 10:
                switch(POS){
                    case("TOP"):
                        XI--;
                        break;
                }
                break;
            case 11:
                switch(POS){
                    case("TOP"):
                        XI++;
                        break;
                }
                break;
            case 12:
                YI++;
                break;
            case 13:
                YI++;
                break;
            default:
                printErr(TYPE);
                break;
        }
        print(XI+" "+YI);
}
