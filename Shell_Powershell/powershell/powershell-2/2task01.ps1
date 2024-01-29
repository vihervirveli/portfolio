# Salo Maarit P0033
# 2task01
#Create a script that counts how many files are in user's home folder. The script shows the home folder name and the number of the files.
Set-Location $HOME
Write-Host (Get-ChildItem | Measure-Object).Count, "files found at", $HOME