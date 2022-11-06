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
// 2constant17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1
// 2constant17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1
// eq
@SP
AM=M-1
D=M
A=A-1
D=D-M
@79
D;JEQ
@SP
A=M-1
M=0
@82
0;JMP
@SP
A=M-1
M=-1
// 2constant17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1
// 2constant16
@16
D=A
@SP
A=M
M=D
@SP
M=M+1
// eq
@SP
AM=M-1
D=M
A=A-1
D=D-M
@108
D;JEQ
@SP
A=M-1
M=0
@111
0;JMP
@SP
A=M-1
M=-1
// 2constant16
@16
D=A
@SP
A=M
M=D
@SP
M=M+1
// 2constant17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1
// eq
@SP
AM=M-1
D=M
A=A-1
D=D-M
@137
D;JEQ
@SP
A=M-1
M=0
@140
0;JMP
@SP
A=M-1
M=-1
// 2constant892
@892
D=A
@SP
A=M
M=D
@SP
M=M+1
// 2constant891
@891
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
@166
D;JGT
@SP
A=M-1
M=0
@169
0;JMP
@SP
A=M-1
M=-1
// 2constant891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
// 2constant892
@892
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
@195
D;JGT
@SP
A=M-1
M=0
@198
0;JMP
@SP
A=M-1
M=-1
// 2constant891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
// 2constant891
@891
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
@224
D;JGT
@SP
A=M-1
M=0
@227
0;JMP
@SP
A=M-1
M=-1
// 2constant32767
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1
// 2constant32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
// gt
@SP
AM=M-1
D=M
A=A-1
D=D-M
@253
D;JLT
@SP
A=M-1
M=0
@256
0;JMP
@SP
A=M-1
M=-1
// 2constant32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
// 2constant32767
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1
// gt
@SP
AM=M-1
D=M
A=A-1
D=D-M
@282
D;JLT
@SP
A=M-1
M=0
@285
0;JMP
@SP
A=M-1
M=-1
// 2constant32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
// 2constant32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
// gt
@SP
AM=M-1
D=M
A=A-1
D=D-M
@311
D;JLT
@SP
A=M-1
M=0
@314
0;JMP
@SP
A=M-1
M=-1
// 2constant57
@57
D=A
@SP
A=M
M=D
@SP
M=M+1
// 2constant31
@31
D=A
@SP
A=M
M=D
@SP
M=M+1
// 2constant53
@53
D=A
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
// 2constant112
@112
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
// neg
D=0
@SP
A=M-1
M=D-M
// and
@SP
AM=M-1
D=M
A=A-1
M=D&M
// 2constant82
@82
D=A
@SP
A=M
M=D
@SP
M=M+1
// or
@SP
AM=M-1
D=M
A=A-1
M=D|M
// not
@SP
A=M-1
M=!M
