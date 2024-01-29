#P0033 Maarit Salo
#task03 laskuja
$int1 = Read-Host "Give the first integer"
$int1 = [int]$int1
$int2 = Read-Host "Give the second integer"
$int2 = [int]$int2
Write-Host "Sum is", ($int1+$int2), "`r`nSubtraction is ",($int1-$int2),"`r`nMultiplication is", ($int1*$int2),"`r`nDivision is",($int1/$int2)