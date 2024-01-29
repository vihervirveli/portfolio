# Salo Maarit P0033
# 5task02
# Create a script that opens all the given urls to a browser. The script has one parameter filename. The parameter points to a text file where urls are. Check first that the given file exist, then read all lines and open the urls in a browser. If the given file does not exist, the script will show a error message.
# kokeiltu syötteillä
# PS C:\Users\OMISTAJA\Documents\ttc2060\powershell\powershell-5> . .\5task02.ps1 
#eli käytetty defaulttia polkua. Toimii.
# Kokeiltu myös syötteellä
#PS C:\Users\OMISTAJA\Documents\ttc2060\powershell\powershell-5> . .\5task02.ps1 .\websites.txt
# ja toimii myös.
param (
    [string]$filename = "C:\Users\OMISTAJA\Documents\ttc2060\powershell\powershell-5\websites.txt"
)

$fileexists = Test-Path ($filename)

if ($fileexists -eq $true) {
    $usersar = Get-Content $filename
    $usersarlength = ((Get-Content $filename) | Measure-Object).Count
    
    for ($i = 0; $i -lt $usersarlength; $i++) {
        
        Start-Process -FilePath Firefox -ArgumentList $usersar[$i]

    }
}
else {
    Write-Host "Error, can't retrieve urls"
}