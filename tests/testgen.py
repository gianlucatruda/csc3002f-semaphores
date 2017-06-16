# Simply run $python3 testgen.py

import random

M = 100 #number of people
N = 50 #number of branches
X = 5 #range of branch visits
Y = 100 #range of durations

print(M, N, sep="\n")
for i in range(M):
	print(i, end="")
	length = random.randint(1, X-1)
	for j in range(length):
		print(" (",random.randint(0,N-1), ", ", random.randint(1,Y-1), ")", sep="", end="")
		if j<length-1:
			print(",", end="")
	print("")
