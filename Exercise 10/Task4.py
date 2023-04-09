
#Yinuo Zhao
#FoCS_Exercise10 Task_4

transitionTable = [
    #Qs Qn  Tape1           Tape2           Tape3
    #0  1   2               3               4
    #        0   1   2        0   1   2       0   1   2
    [0, 0, ['1','1','r'],   ['0','0','r'],  ['b','b','r']],
    [0, 0, ['0','0','r'],   ['1','1','r'],  ['b','b','r']],
    [0, 0, ['1','1','r'],   ['1','1','r'],  ['b','b','r']],
    [0, 0, ['0','0','r'],   ['0','0','r'],  ['b','b','r']],
    [0, 1, ['b','b','l'],   ['b','b','l'],  ['b','b','l']],
    
    [1, 1, ['1','1','l'],   ['0','0','l'],  ['b','1','l']],
    [1, 1, ['0','0','l'],   ['1','1','l'],  ['b','1','l']],
    [1, 1, ['0','0','l'],   ['0','0','l'],  ['b','0','l']],
    [1, 2, ['1','1','l'],   ['1','1','l'],  ['b','0','l']],
    [1, 3, ['b','b','r'],   ['b','b','r'],  ['b','b','r']],
    
    [2, 2, ['1','1','l'],   ['1','1','l'],  ['b','1','r']],
    [2, 2, ['1','1','l'],   ['0','0','l'],  ['b','0','r']],
    [2, 2, ['0','0','l'],   ['1','1','l'],  ['b','0','r']],
    [2, 1, ['0','0','l'],   ['0','0','l'],  ['b','1','r']],
    [2, 3, ['b','b','r'],   ['b','b','r'],  ['b','1','l']],
]

def Trans(stateNow, head):
    for line in transitionTable:
        if stateNow == line[0]:
            if (tape1[head] == line[2][0] and
                tape2[head] == line[3][0] and
                tape3[head] == line[4][0]
                ):
                    stateNow = line[1]
                    tape1[head] = line[2][1]
                    tape2[head] = line[3][1]
                    tape3[head] = line[4][1]
                    
                    if (line[2][2] == 'r'): 
                        head += 1
                        print("Head move RIGHT")
                    else: 
                        head -= 1
                        print("Head move LEFT")
                    
                    print("Next state is: " + str(stateNow) + "\n")
                    
                    return stateNow, head
            
def printStates(stateNow, head):
    print("State now is " + str(stateNow))
    print("Head:" + str(head + 1) + " " + head * " " + ".")
    print("Tape1: " + "".join(tape1))
    print("Tape2: " + "".join(tape2))
    print("Tape3: " + "".join(tape3))

tape1 = list('b' + input("Initial tape 1: ") + 'b')
tape2 = list('b' + input("Initial tape 2: ") + 'b')

print()
print("Tape 1 is initialed as " + ''.join(tape1))
print("Tape 2 is initialed as " + ''.join(tape2))
print("Tape 3 is initially empty (so, full of b's)\n")

stateNow = 0
length = len(tape1)

head = length - 1
tape3 = list('b' * length)    

while True:
    printStates(stateNow, head)
    
    if (stateNow == 3): 
        print("\nHalted.")
        break
    
    stateNow, head = Trans(stateNow, head)
