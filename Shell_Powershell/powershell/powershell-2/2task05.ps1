# Salo Maarit P0033
# 2task05 ping
param (
    [string]$address = "192.168.1.1",
    [int]$times = 1
)

Write-Host "I will ping address", $address, $times, "time, Okay?"
$yesOrNo = Read-Host "[Y/n]"

if ($yesOrNo -eq "Y" -or $yesOrNo -eq "y") {
    for ($i = 0; $i -lt $times; $i++) {
        Write-Host "try", ($i + 1), "to ping: ", $address
    }
}
