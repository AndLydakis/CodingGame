/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
var Elevator = function(pos, floor){
    this.pos = pos;
    this.floor = floor;
    }
var inputs = readline().split(' ');
var nbFloors = parseInt(inputs[0]); // number of floors
var width = parseInt(inputs[1]); // width of the area
var nbRounds = parseInt(inputs[2]); // maximum number of rounds
var exitFloor = parseInt(inputs[3]); // floor on which the exit is found
var exitPos = parseInt(inputs[4]); // position of the exit on its floor
var nbTotalClones = parseInt(inputs[5]); // number of generated clones
var nbAdditionalElevators = parseInt(inputs[6]); // ignore (always zero)
var nbElevators = parseInt(inputs[7]); // number of elevators
var elevators = [];
for (var i = 0; i < nbElevators; i++) {
    var inputs = readline().split(' ');
    var elevatorFloor = parseInt(inputs[0]); // floor on which this elevator is found
    var elevatorPos = parseInt(inputs[1]); // position of the elevator on its floor
    elevators[i] = new Elevator(elevatorPos, elevatorFloor);
}

// game loop
while (true) {
    var inputs = readline().split(' ');
    var cloneFloor = parseInt(inputs[0]); // floor of the leading clone
    var clonePos = parseInt(inputs[1]); // position of the leading clone on its floor
    var direction = inputs[2]; // direction of the leading clone: LEFT or RIGHT

    if(atEdge(clonePos, direction, width)){
        print("BLOCK");
    }else if(elevBlock(cloneFloor, clonePos, direction, elevators)){
        print("BLOCK");
    }else if(lastFloor(cloneFloor, clonePos, direction, exitFloor, exitPos)){
        print("BLOCK");
    }else{print("WAIT");}
}

function lastFloor(cloneFloor, clonePos, dir, lastFloor, lastPos){
    if(cloneFloor == lastFloor){
        if((clonePos < lastPos)&&(dir=="LEFT")){return true;}
        if((clonePos > lastPos)&&(dir=="RIGHT")){return true;}
    }
}

function atEdge(pos, dir, w){
    if((pos == w-1)&&(dir==="RIGHT")){return true;}
    if((pos === 0)&&(dir==="LEFT")){return true;}
    return false;
}
    
function elevBlock(cloneFloor, clonePos, dir, elev){
    for each(var e in elev){
        if(cloneFloor === e.floor){
            if((clonePos < e.pos)&&(dir==="LEFT")){return true;}
            if((clonePos > e.pos)&&(dir==="RIGHT")){return true;}
        }
    }
    return false;
}
