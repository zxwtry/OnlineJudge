#!/bin/bash
#tempPath="/home/zxwtry/ojpath/shell/$2"
tempPath=$2
cd $tempPath
#in="/home/zxwtry/ojpath/data/poj/$1.input"
#echo $(pwd) > ttt.txt
#echo $(java -version) > ppp.txt
in="$1.input"
exec < $in
javac Main.java
#echo $3 > yyy.txt
java $3  Main $4
#rm Main*
