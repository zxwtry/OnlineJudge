#!/bin/bash
tempPath=$2
cd $tempPath
in="$1.input"
exec < $in
python py2.py
