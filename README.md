# longint
Creation of the LongInt data type. The objective of this project was to create a datatype that represents a number of any digit length. When using normal primitive data types, there is a limit as to how many different numbers that can be represented. For example, a standard int is composed of 32 bits, whose maximum value is usually composed of 10 digits. The LongInt allows the creation of numbers of any size, from one digit to as many as can physically fit in a computer's memory. LongInts must also be able to be compared (less than, greater than, equal to) and can be manipulated using various arithmetic functions (addition, subtraction, multiplication, power). Additionally, there are several utility functions that are used to make these algorithms easier to write, as shown and described in the LongIntUtils.java file. 

There were a number of restrictions to add to the challenge of creating this data type, such as: -must be implemented using a singly linked list and array
-algorithms must utilize the abstract operations and cannot access the LongIntList directly
-strings can only be used to define the LongInt's value and nowhere else. Additionally, using primitives other than int is not allowed.
-the LongInt must use the minimum number of nodes to represent the value
-the sign cannot be changed outside of the LongInt.java file
-the power method must have no more than 2lg(p) calls

The test cases are:

A = 2222
B = 99999999
C = -246813575732
D = 180270361023456789
E = 1423550000000010056810000054593452907711568359
F = -350003274594847454317890 
G = 29302390234702973402973420937420973420937420937234872349872934872893472893749287423847
H = -98534342983742987342987339234098230498203894209928374662342342342356723423423
I = 8436413168438618351351684694835434894364351846843435168484351684684315384684313846813153843135138413513843813513813138438435153454154515151513141592654543515316848613242587561516511233246174561276521672162416274123076527612

The above LongInts also must be raised to the 5th, 10th, and 20th powers.

Additional test cases:
J = B + C, K = C + D, L = I + I, M = A + I, N = B + K, O = A – D, P = C – D, Q = D – C, R = L – L, S = P – O,
T = N – Q, U = A * D, V = B * C, W = D * D, X = O * I, Y = J * P, Z = M * N

Runtime statistics are located in the xyz_op_times.txt files, where xyz is SLL or array. The final recorded times for all test cases were:
SLL: 1.164 seconds
Array: 1.482 seconds
