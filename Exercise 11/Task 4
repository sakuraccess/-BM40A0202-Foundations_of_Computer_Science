# Yinuo Zhao

from math import sqrt

n = int(input("Please enter the n:\n"))
found = False
MAX = int(sqrt(n)) + 1

for x in range(MAX):
    if found: break

    for y in range(x, MAX):
        left = x**2 + y**2
        
        if left > n:
            break
        
        if left == n:
            print("One solution found.")
            print("%d^2 + %d^2 = %d" % (x, y, n))
            found = True
            break
        
if not found:
    print("There is no solution.")
