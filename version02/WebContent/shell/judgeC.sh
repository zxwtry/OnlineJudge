#!/bin/bash
tempPath=$2
cd $tempPath
in="$1.input"
exec < $in
gcc c.c -o c
./c
