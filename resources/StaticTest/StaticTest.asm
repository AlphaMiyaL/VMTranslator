// init
@256
D=A
@SP
M=D
// call Sys.init 0
// save calling function
@return-address0
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
// reposition ARG
@SP
D=M
@0
D=D-A
@5
D=D-A
@ARG
M=D
// reposition LCL
@SP
D=M
@LCL
M=D
// transfer control
// C_GOTO Sys.init
@Sys.init
0;JMP
// declare return address label
// C_LABEL return-address0
(return-address0)
// 2constant111
@111
D=A
@SP
A=M
M=D
@SP
M=M+1
// 2constant333
@333
D=A
@SP
A=M
M=D
@SP
M=M+1
// 2constant888
@888
D=A
@SP
A=M
M=D
@SP
M=M+1
// 3static8
@SP
AM=M-1
D=M
@StaticTest.8
M=D
// 3static3
@SP
AM=M-1
D=M
@StaticTest.3
M=D
// 3static1
@SP
AM=M-1
D=M
@StaticTest.1
M=D
// 2static3
@StaticTest.3
D=M
@SP
A=M
M=D
@SP
M=M+1
// 2static1
@StaticTest.1
D=M
@SP
A=M
M=D
@SP
M=M+1
// sub
@SP
AM=M-1
D=M
A=A-1
M=M-D
// 2static8
@StaticTest.8
D=M
@SP
A=M
M=D
@SP
M=M+1
// add
@SP
AM=M-1
D=M
A=A-1
M=D+M
