#!/bin/bash
tempPath=$2
cd $tempPath
in="$1.input"
exec < $in
g++ cpp.cpp -o cpp
./cpp
