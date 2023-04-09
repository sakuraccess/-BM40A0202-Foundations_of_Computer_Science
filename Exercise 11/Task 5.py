#Yinuo Zhao

import itertools

input_sets = input("Please enter the set, separated by spaces: \n")

sets = [int(x) for x in input_sets.split(" ")]


n = int(input("Please enter the total sum: \n"))

for i in range(1, len(sets)+1):
    for subset in itertools.combinations(sets, i):
        if sum(subset) == n:
            print(subset)
