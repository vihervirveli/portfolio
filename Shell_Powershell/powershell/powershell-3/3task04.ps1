# Salo Maarit P0033
# 3task04
#tehtävässä oletan että filename todellakin on vain tiedoston nimi, ja skripti itse hoitaa tiedoston päätteen, joka on .txt. Eli älä syötä filenameja jotka ovat filename.txt, koska sitten tulee filename.txt1.txt jne. Tehtävän annossa ei puhuttu tiedostotyypeistä
#testattu syötteellä
#PS C:\Users\OMISTAJA\Documents\ttc2060\powershell> .\powershell-3\3task04.ps1 powershell-test uusi 2 
#2 files created. Here are the names of the files:
#uusi0.txt
#uusi1.txt
#ja syötteellä
#PS C:\Users\OMISTAJA\Documents\ttc2060\powershell> .\powershell-3\3task04.ps1 powershell-test2 uusi 3
# The folder powershell-test2 did not exist before but has been created.
# 3 files created. Here are the names of the files:
# uusi0.txt
# uusi1.txt
# uusi2.txt

param (
    [string]$foldername,
    [string]$filename,
    [int]$number
)

$folderexists = Test-Path ($foldername)

if ($folderexists -ne $true) {
    
    New-Item -Path $foldername -ItemType Directory | Out-Null
    Write-Host "The folder $foldername did not exist before but has been created."
}

$filenames = @()
for ($i = 0; $i -lt $number; $i++) {
    $filenamefull = ($filename + $i + ".txt")
    $pathtofile = Join-Path -Path $foldername -ChildPath $filenamefull 
    New-Item -Path $pathtofile -ItemType File  | Out-Null
    $filenames += $filenamefull
}
Write-Host "$number files created. Here are the names of the files:"
foreach ($file in $filenames){
    Write-Host $file
}



