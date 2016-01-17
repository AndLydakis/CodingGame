import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.
# -=1-
# Hint: You can use the debug stream to print initialTX and initialTY, if Thor seems not follow your orders.

 # light_x: the X position of the light of power
 # light_y: the Y position of the light of power
 # initial_tx: Thor's starting X position
 # initial_ty: Thor's starting Y position
light_x, light_y, initial_tx, initial_ty = [int(i) for i in raw_input().split()]
thorX = initial_tx
thorY = initial_ty
# game loop
while 1:
    remaining_turns = int(raw_input())
    
    
    if(thorX>light_x):
        if(thorY>light_y):
            print"NW" 
            thorX-=1
            thorY-=1
        elif(thorY==light_y):
            print"W"
            thorX-=1
        else:
            print"SW"
            thorX-=1
            thorY+=1
    elif(thorX<light_x):
        if(thorY>light_y):
            print "NE" 
            thorX+=1
            thorY-=1
        elif(thorY==light_y):
            print"E"
            thorX+=1
        else:
            print"SE"
            thorX+=1
            thorY+=1
    else:
        if(thorY>light_y):
            print "N" 
            thorY-=1
        else:
            print "S" 
            thorY+=1
