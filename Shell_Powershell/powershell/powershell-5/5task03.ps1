# Salo Maarit P0033
# 5task03
# Create a script that creates new localusers by a given csv-file. The script has one parameter filename. Check first that the given file exist, then read all lines and create a new local user account for every user in file. The account name is created same way as in the task 01. You can use in this script the parameter NoPassword Please remember: you have to run this script with Administrator privileges that you can use the New-LocalUser cmdlet. If the given file does not exist, the script gives a proper error message.
#Please note: and you can delete creted local users after you have tested that your script is working. Please remember take a screenshot from your test run.
#Screenshot toiminnasta
# screenshotit\createlocalusercommand.png
# kokeiltu käskyllä 
# PS C:\Users\OMISTAJA\Documents\ttc2060\powershell\powershell-5> . .\5task03.ps1 .\users.csv
# ja toimi
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
        $fullname = "$firstname $lastname"
        $account = $lastname.Substring(0, 4).ToLower() + $firstname.Substring(0, 2).ToLower()
        $createaccount = Read-Host "a new local account $account will be created for $firstname $lastname, [Yes]/No"
        $createaccount = $createaccount.ToLower()
        if ($createaccount -eq "yes" -or $createaccount -eq "y") {
            New-LocalUser -Name $account -FullName $fullname -NoPassword | Out-Null
            $countteri++
            
        }
    }
    
    Get-LocalUser -Name * | Format-Table Name, Enabled, Description

    Write-Host "$countteri new accounts created successfully" -ForegroundColor Green
}
else {
    Write-Host "Error, can't retrieve users"
}