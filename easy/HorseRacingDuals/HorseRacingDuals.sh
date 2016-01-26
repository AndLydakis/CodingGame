# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

read N

for (( i=0; i<N; i++ )); do
    read p
	DIF[$i]=$p
done

#sortedColNums=($(for p in ${DIF[@]}; do echo $p; done | sort -n))
mapfile -t sortedColNums < <(printf '%s\n' "${DIF[@]}" | sort -n)
MINDIF=9999999999
for (( c=0; c<$N-1; c++ ))
do
	#echo ${sortedColNums[$c]}
	X=${sortedColNums[$c]}
	Y=${sortedColNums[$c+1]}
	TEMP=$((Y-X))
	#echo $TEMP
	if [ $TEMP -lt $MINDIF ]
	then
		MINDIF=$TEMP
	fi
done
echo $MINDIF
# Write an action using echo
# To debug: echo "Debug messages..." >&2

