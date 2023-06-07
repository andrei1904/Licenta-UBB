bits 32
global start
extern exit, scanf, printf
import exit msvcrt.dll
import scanf msvcrt.dll
import printf msvcrt.dll
segment data use32 class=data
format db '%d', 0
a dd 0
b dd 0
temp0 dd 1

segment code use32 class=code
start:
push dword a
push dword format
call [scanf]
add esp, 4*2
push dword b
push dword format
call [scanf]
add esp, 4*2
mov eax, [a]
add eax, [b]
mov [temp0], eax
mov eax, [temp0]
mov [a], eax
push dword [a]
push dword format
call [printf]
add esp, 4*2
push dword 0
call [exit]
