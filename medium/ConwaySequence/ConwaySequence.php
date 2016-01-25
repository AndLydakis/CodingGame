<?php
/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

fscanf(STDIN, "%d",
    $R
);
fscanf(STDIN, "%d",
    $L
);

// Write an action using echo(). DON'T FORGET THE TRAILING \n
// To debug (equivalent to var_dump): error_log(var_export($var, true));

Conway($R, $L);

function Conway($v, $l){
	$tokens = explode(" ", $v);
	if($l===1){
		for ($i = 0; $i < count($tokens)-1; ++$i) {
			echo $tokens[$i];
			echo " ";
		}
		echo $tokens[count($tokens)-1];
		return;
	}
	$seq="";
	$counter = 1;
	$val=$tokens[0];
	for ($i = 1; $i < count($tokens); ++$i) {
		if($val==$tokens[$i]){
			$counter++;
			}
		else{
			$seq.=" ";
			$seq.=$counter;
			$seq.=" ";
			$seq.=$val;
			$counter = 1;
			$val = $tokens[$i];
		}
	}
	$seq.=" ";
	$seq.=$counter;
	$seq.=" ";
	$seq.=$val;
	$seq=trim($seq);
	Conway($seq, $l-1);
	
	
}
?>
