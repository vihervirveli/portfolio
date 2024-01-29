# Salo Maarit P0033
# 3task01 
#kokeiltu syötteillä:
#PS C:\Users\OMISTAJA\Documents\ttc2060\powershell> .\powershell-3\3task01.ps1 powershell-10 #ei löydy
#PS C:\Users\OMISTAJA\Documents\ttc2060\powershell> .\powershell-3\3task01.ps1 powershell-test #löytyy 7
param (
    [string]$foldername
)

$exists = Test-Path $foldername

if ($exists){
#Write-Host "On olemassa"
$files = Get-ChildItem -Path $foldername

Write-Host ($files | Measure-Object).count, "files in the folder", $foldername,"`r`n`r`nName`r`n----"
foreach($file in $files){
    Write-Host $file
} 
}

else {
    Write-Host "Sorry,", $foldername, "does not exist"
}

