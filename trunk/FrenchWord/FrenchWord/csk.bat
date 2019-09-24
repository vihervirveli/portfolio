@echo off
SET ERRORLEVEL=
"%WINDIR%\Microsoft.NET\Framework\v4.0.30319\csc" %* /nologo /reference:Jypeli4.dll;"%XNAGSv4%References\Windows\x86\Microsoft.Xna.Framework.Game.dll";"%XNAGSv4%References\Windows\x86\Microsoft.Xna.Framework.dll"  /platform:x86   /define:WINDOWS
if ERRORLEVEL 1 goto loppu
%~n1
:loppu