#Yinuo Zhao

from math import isqrt

n = int(input("Please enter the n:\n"))
found = False

for x in range(isqrt(n) + 1):
    if found: break

    y = isqrt(n - x**2)
    if x**2 + y**2 == n:
        print("One solution found.")
        print("%d^2 + %d^2 = %d" % (x, y, n))
        found = True

if not found:
    print("There is no solution.")
