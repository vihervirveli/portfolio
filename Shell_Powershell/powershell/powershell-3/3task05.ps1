# Salo Maarit P0033
# 3task05
#Kokeiltu syötteellä 
#PS C:\Users\OMISTAJA\Documents\ttc2060\powershell> .\powershell-3\3task05.ps1 powershell-test uusitiedosto
#All 10 files will be renamed with name uusitiedosto. Yes/No: Yes
#tehtävänannossa ei pyydetty outputtia luomisesta, joten sitä ei ole. 
param (
    [string]$foldername,
    [string]$newname
)

$folderexists = Test-Path ($foldername)

if ($folderexists -ne $true) {
    
    Write-Host "The folder $foldername does not exist. Terminating the program."
}

else {
    $files = Get-ChildItem -Path $foldername
    $filecount = $files.Length
    $renameYes = Read-Host "All $filecount files will be renamed with name $newname. Yes/No"
    if ($renameYes -eq "Yes") {

        for ($i = 0; $i -lt $filecount; $i++) { 
            $filename = [System.IO.Path]::GetFileNameWithoutExtension($files[$i])
            $filename = ($newname + $i)
            $extension = (Get-Item $files[$i] ).Extension
            Rename-Item -Path $files[$i] -NewName ($filename + $extension)
        }
    }
}