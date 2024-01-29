# Salo Maarit P0033
# 3task02
#kokeiltu syötteellä 
#PS C:\Users\OMISTAJA\Documents\ttc2060\powershell> .\powershell-3\3task02.ps1 powershell-1 task01.ps1 #löytyy
#PS C:\Users\OMISTAJA\Documents\ttc2060\powershell> .\powershell-3\3task02.ps1 powershell-1 task010.ps1 #ei löydy
param (
    [string]$foldername,
    [string]$filename
)

$folderexists = Test-Path $foldername
$fileexists = Test-Path ($foldername+"\"+$filename)
if ($folderexists -and $fileexists){
    #I don't know what proper message here means, I assume it means 'write something that suits the situation'
    Write-Host "Yes, $filename in the $foldername does exist."
}
else {
    Write-Host "Sorry, $filename in the $foldername does not exist."
}