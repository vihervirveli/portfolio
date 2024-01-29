# Salo Maarit P0033
# 5task04
# Create a script that writes information of all the local users in a computer to a CSV-file. 
#The script has one parameter filename. The information includes local users's 
#1) name 2) full name 3) SID and 4) lastlogon.
#kokeiltu syötteillä
# PS C:\Users\OMISTAJA\Documents\ttc2060\powershell\powershell-5> . .\5task04.ps1
# ja
# PS C:\Users\OMISTAJA\Documents\ttc2060\powershell\powershell-5> . .\5task04.ps1 userslocaalit.csv
# ja hyvin toimi
param(
    $filename = "localusers.csv"
)


$path = Join-Path -Path $PSScriptRoot -ChildPath $filename
Get-LocalUser -Name * | Select-Object Name, FullName, SID, LastLogon | Export-Csv -Path $path -Delimiter ";"