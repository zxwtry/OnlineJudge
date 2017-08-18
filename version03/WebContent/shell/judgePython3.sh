#!/bin/bash
tempPath=$2
cd $tempPath
in="$1.input"
exec < $in
python3 py3.py $3
