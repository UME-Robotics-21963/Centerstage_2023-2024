# Welcome!

## this is team 21963 

### we are a robotics team in FTC

our first year was in 2022-2023
year one: The multiversal coders
year two: Project strange

our code is in this repository and we hope to continue to update it

## arrays 101
our team uses arrays in code but in simpler terms we mainly use them in ways to make our lives easier(ish)
now, What is an array?
An array is basically a set of terms contained in a single variable these terms collectively are called indexes

but first a Question whats an index?
an index is just like a dictionary of terms all defined by what they are defined as in the code for the array

how do you define Arrays?
well like this
{
public boolean[][] toggleInputs = new boolean[2],[2];(you don't need the comma but this is what happens [2][2] in read me)
^       ^          ^its name                 ^ its maximum amount of terms in the index
|       |what it is being defined as 
|open to all code
}
so when we call 
robot.arrayName[0]
we are calling array index 0
etc...
2D arrays are where things get semi-complicated
robot.arrayName[1],[1];
this is just a fancy way to say we want index number 1,1
it easy to think of 2D arrays as a graph
{(1,0),(1,1)
{(0,0),(0,1) 
x = which set(1,0 1,1 or 0,0 0,1 are valid set pairs) it's in, and y = what value it holds
so say:
0,0 being false/ not active
0,1 being true/ is active
you can make arrays into (almost)anything
motors, servos(both types), telemetry, booleans, doubles, floats, you name it. 
for motors servos and other check out our hardware tests on github! https://github.com/FTC-21963/2023/blob/56fe2746bc30e995b7ce28ccf55260df4cfc3f26/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/Hardwaretests
