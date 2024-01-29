# Salo Maarit P0033
# 4task05
# Create a script that takes one parameter: foldername. First check that the given folder exist, then write all names of files in the folder to a text file named files.txt. Create the file to user's home folder. If the folder does not exist, the script will show a message: "Sorry, {foldername} does not exist."
#Kokeiltu syötteillä
#PS C:\Users\OMISTAJA\Documents\ttc2060\powershell> . .\powershell-4\4task05.ps1 powershell-4
#ja toimi, tuli ulos
#5 filenames were written to file C:\Users\OMISTAJA\files.txt
param (
    [string]$foldername
)

$folderexists = Test-Path ($foldername)

if ($folderexists -eq $true) {
    
    $filut = Get-ChildItem -Path $foldername |  Format-Table Name | Out-String
    $filucount = (Get-ChildItem -Path $foldername | Measure-Object).Count
    $path = Join-Path -Path $HOME -ChildPath "files.txt"
    New-Item -Path $path -ItemType File | Out-Null
    Add-Content -Path $path -Value $filut
    Write-Host "$filucount filenames were written to file $path"
}
else {
    Write-Host "Sorry, $foldername does not exist."
}
