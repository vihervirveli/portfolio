# Salo Maarit P0033
# 5task01
# Create a script that creates new accounts for a placebo system (placebo means here that the system not really exists). The script takes one parameter filename. The parameter points to a csv file including user names. Check first that the given file exist, then read all lines. User's account is created by taking first four characters of lastname and two characters of firstname. The account contains only lowercase letters. Give a message presented in the picture below. If the given file does not exist, the script will show an error message.
#kokeiltu syötteillä
#PS C:\Users\OMISTAJA\Documents\ttc2060> . .\powershell\powershell-5\5task01.ps1 C:\Users\OMISTAJA\Downloads\users.csv
#ja
#PS C:\Users\OMISTAJA\Documents\ttc2060> . .\powershell\powershell-5\5task01.ps1
#kivasti toimi
param (
    #tiedosto jossa enemmän nimiä
    #[string]$filename = "C:\Users\OMISTAJA\Documents\ttc2060\powershell\powershell-5\usersplus.csv" 
    [string]$filename = "C:\Users\OMISTAJA\Documents\ttc2060\powershell\powershell-5\users.csv"
    
)

$fileexists = Test-Path ($filename)

if ($fileexists -eq $true) {
    $usersar = Get-Content $filename
    $usersarlength = ((Get-Content $filename) | Measure-Object).Count
    $countteri = 0
    for ($i = 1; $i -lt $usersarlength; $i++) {
        
        $currentuser = $usersar[$i].Split(",")
        $firstname = $currentuser[0]
        $lastname = $currentuser[1]
        $account = $lastname.Substring(0, 4).ToLower() + $firstname.Substring(0, 2).ToLower()
        Write-Host "Hello my colleague $firstname $lastname, this is your new account:"
        Write-Host $account -ForegroundColor Green
        $countteri++

    }
    Write-Host "$countteri new accounts created successfully" -ForegroundColor Yellow
}
else {
    Write-Host "Error, can't retrieve users"
}