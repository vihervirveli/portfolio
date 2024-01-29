# Salo Maarit P0033
# 2task04 hello name
param (
    [string]$name = "unknown",
    [int]$hellos = 1
)

for ($i = 0; $i -lt $hellos ; $i++){
    Write-Host "Hello", $name
}
