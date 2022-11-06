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
// function Main.fibonacci0
// C_LABEL Main.fibonacci
(Main.fibonacci)
// 2argument0
@ARG
A=M
D=M
@SP
A=M
M=D
@SP
M=M+1
// 2constant2
@2
D=A
@SP
A=M
M=D
@SP
M=M+1
// lt
@SP
AM=M-1
D=M
A=A-1
D=D-M
@80
D;JGT
@SP
A=M-1
M=0
@83
0;JMP
@SP
A=M-1
M=-1
// C_IF IF_TRUE
@SP
AM=M-1
D=M
@IF_TRUE
D;JNE
// C_GOTO IF_FALSE
@IF_FALSE
0;JMP
// C_LABEL IF_TRUE
(IF_TRUE)
// 2argument0
@ARG
A=M
D=M
@SP
A=M
M=D
@SP
M=M+1
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
// C_LABEL IF_FALSE
(IF_FALSE)
// 2argument0
@ARG
A=M
D=M
@SP
A=M
M=D
@SP
M=M+1
// 2constant2
@2
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
// call Main.fibonacci 1
// save calling function
@return-address1
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
@1
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
// C_GOTO Main.fibonacci
@Main.fibonacci
0;JMP
// declare return address label
// C_LABEL return-address1
(return-address1)
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
// call Main.fibonacci 1
// save calling function
@return-address2
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
@1
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
// C_GOTO Main.fibonacci
@Main.fibonacci
0;JMP
// declare return address label
// C_LABEL return-address2
(return-address2)
// add
@SP
AM=M-1
D=M
A=A-1
M=D+M
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
// function Sys.init0
// C_LABEL Sys.init
(Sys.init)
// 2constant4
@4
D=A
@SP
A=M
M=D
@SP
M=M+1
// call Main.fibonacci 1
// save calling function
@return-address3
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
@1
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
// C_GOTO Main.fibonacci
@Main.fibonacci
0;JMP
// declare return address label
// C_LABEL return-address3
(return-address3)
// C_LABEL WHILE
(WHILE)
// C_GOTO WHILE
@WHILE
0;JMP
