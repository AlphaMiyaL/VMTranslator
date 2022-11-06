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
// 2constant10
@10
D=A
@SP
A=M
M=D
@SP
M=M+1
// 3local0
@SP
AM=M-1
D=M
@LCL
A=M
M=D
// 2constant21
@21
D=A
@SP
A=M
M=D
@SP
M=M+1
// 2constant22
@22
D=A
@SP
A=M
M=D
@SP
M=M+1
// 3argument2
@2
D=A
@ARG
D=D+M
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
// 3argument1
@1
D=A
@ARG
D=D+M
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
// 2constant36
@36
D=A
@SP
A=M
M=D
@SP
M=M+1
// 3this6
@6
D=A
@THIS
D=D+M
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
// 2constant42
@42
D=A
@SP
A=M
M=D
@SP
M=M+1
// 2constant45
@45
D=A
@SP
A=M
M=D
@SP
M=M+1
// 3that5
@5
D=A
@THAT
D=D+M
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
// 3that2
@2
D=A
@THAT
D=D+M
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
// 2constant510
@510
D=A
@SP
A=M
M=D
@SP
M=M+1
// 3temp6
@6
D=A
@R5
D=D+A
@R13
M=D
@SP
AM=M-1
D=M
@R13
M=D
// 2local0
@LCL
A=M
D=M
@SP
A=M
M=D
@SP
M=M+1
// 2that5
@5
D=A
@THAT
A=D+M
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
// 2argument1
@1
D=A
@ARG
A=D+M
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
// 2this6
@6
D=A
@THIS
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
// 2this6
@6
D=A
@THIS
A=D+M
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
// sub
@SP
AM=M-1
D=M
A=A-1
M=M-D
// 2temp6
@6
D=A
@R5
A=D+A
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
