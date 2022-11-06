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
// function SimpleFunction.test2
// C_LABEL SimpleFunction.test
(SimpleFunction.test)
// 2constant0
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
// 2constant0
@0
D=A
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
// 2local1
@1
D=A
@LCL
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
// not
@SP
A=M-1
M=!M
// 2argument0
@ARG
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
// return
// set FRAME = LCL
@LCL
D=M
@FRAME
M=D
// set RET = FRAME - 5
@5
A=D-A
D=M
@RET
M=D
// set ARG = pop()
@SP
AM=M-1
D=M
@ARG
A=M
M=D
// restore SP of the caller
@ARG
D=M+1
@SP
M=D
// restore THAT of the caller
@FRAME
A=M-1
D=M
@THAT
M=D
// restore THIS of the caller
@FRAME
D=M
@2
A=D-A
D=M
@THIS
M=D
// restore ARG of the caller
@FRAME
D=M
@3
A=D-A
D=M
@ARG
M=D
// restore LCL of the caller
@FRAME
D=M
@4
A=D-A
D=M
@LCL
M=D
// goto RET
@RET
A=M
0;JMP
