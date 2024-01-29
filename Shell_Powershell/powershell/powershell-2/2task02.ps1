# Salo Maarit P0033
# 2task02
$path = Read-Host "Please, give the folder to search"
Set-Location $path
Write-Host (Get-ChildItem | Measure-Object).Count, "files found at", $path