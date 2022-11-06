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
// 2constant0
@0
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
// C_LABEL LOOP_START
(LOOP_START)
// 2argument0
@ARG
A=M
D=M
@SP
A=M
M=D
@SP
M=M+1
// 2local0
@LCL
A=M
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
// 3local0
@SP
AM=M-1
D=M
@LCL
A=M
M=D
// 2argument0
@ARG
A=M
D=M
@SP
A=M
M=D
@SP
M=M+1
// 2constant1
@1
D=A
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
// 3argument0
@SP
AM=M-1
D=M
@ARG
A=M
M=D
// 2argument0
@ARG
A=M
D=M
@SP
A=M
M=D
@SP
M=M+1
// C_IF LOOP_START
@SP
AM=M-1
D=M
@LOOP_START
D;JNE
// 2local0
@LCL
A=M
D=M
@SP
A=M
M=D
@SP
M=M+1
