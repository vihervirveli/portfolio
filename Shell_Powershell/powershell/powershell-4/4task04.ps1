# Salo Maarit P0033
# 4task04
# Create a script that ask file names from an user. The names are asked until the user gives empty input. After that, the script creates the files with given names to user's home folder. The script writes the current date and time to the files in the following format 30.9.2021 12.30. Finally show a message that how many files were created succesfully.
# Oletin että Enter on tässä tyhjä merkkijono, eikä tarvita mitään kikkailua Enter-näppäimen kanssa
# oletin myös että käyttäjä antaa extensionin mukana. Jos ei antanut, tiedostolla ei ole päätettä.
$d = Get-Date -Format "dd.M.yyyy"
$t = Get-Date -Format t
$datetimestr = "$d $t"
Write-Host "Today is $d and time is $t"

$filenames = @()
do {
    $filename = (Read-Host "Give a filename, press Enter to quit")
    if ($filename.Length -gt 1) {
        $filenames += $filename        
    } 
}
while ($filename.Length -gt 1)

foreach ($filu in $filenames) {
    $path = Join-Path -Path $HOME -ChildPath $filu
    New-Item -Path $path -ItemType File | Out-Null
    Add-Content -Path $path -Value $datetimestr | Out-Null
}


$filecount = $filenames.Length
Write-Host "$filecount files created successfully"