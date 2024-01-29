# Salo Maarit P0033
# 4task03
# Create a script that ask workstation names from an user. The names are asked until the user gives empty input. After that, the script writes all names to a file in user's home folder. The file name is given with the parameter: filename. Finally show a proper message that the file was created succesfully.
#in the script, I assume that the user will give the extension with the filename. If the user does not give an extension, the file will have no extension.
param (
    [string]$filename
)

$workstationsarray = @()
do {
    $wstation = (Read-Host "Give a workstation name, press Enter to quit")
    if ($wstation.Length -gt 1) {
        $workstationsarray += $wstation        
    } 
}
while ($wstation.Length -gt 1)

$filu = Join-Path -Path $HOME -ChildPath $filename
Add-Content -Path $filu -Value $workstationsarray
Write-Host "$filu created successfully"