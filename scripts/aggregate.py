#!/usr/bin/python
import argparse
import json

parser = argparse.ArgumentParser()
parser.add_argument('file1', help='file name')
parser.add_argument('file2', help='file name')

args = parser.parse_args()

def merge(a, b):
    "merges b into a"
    a.update(b)
    return a

def merge_files(file1,file2):
 with open(file1) as f1:
    with open(file2) as f2:
        jsondata1=json.load(f1)
        jsondata2=json.load(f2)
        with open(file1, 'w') as f:
          json.dump(merge(jsondata1,jsondata2),f,sort_keys=True)
	
merge_files(args.file1,args.file2)
