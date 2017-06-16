# Simply run $python3 testgen.py

import random

M = 20 #number of people
N = 30 #number of branches
X = 10 #range of branch visits
Y = 20 #range of durations

print(M, N, sep="\n")
for i in range(M):
	print(i, end="")
	length = random.randint(1, X)
	for j in range(length):
		print(" (",random.randint(1,X), ", ", random.randint(1,Y), ")", sep="", end="")
		if j<length-1:
			print(",", end="")
	print("")
