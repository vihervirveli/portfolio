# Salo Maarit P0033
# 3task03
#kokeiltu syötteellä
#PS C:\Users\OMISTAJA\Documents\ttc2060\powershell> .\powershell-3\3task03.ps1 powershell-test uusi3.txt
#ei löytynyt, kysyttiin luodaanko uusi
#PS C:\Users\OMISTAJA\Documents\ttc2060\powershell> .\powershell-3\3task03.ps1 powershell-test uusi3.txt
#löytyy nyt
param (
    [string]$foldername,
    [string]$filename
)
$pathtofile =  Join-Path -Path $foldername -ChildPath $filename 
$fileexists = Test-Path ($pathtofile)

if ($fileexists) {
    #I'm still assuming 'proper message' just means 'relevant to my case'
    Write-Host "Yes, $filename in the $foldername does exist."
}
else {
    $luodaanko = Read-Host "Create the file $filename ? Yes/No"
    if ($luodaanko -eq "Yes") {
        New-Item -Path $pathtofile -ItemType File | Out-Null
        Write-Host "$pathtofile created"
    }
}
