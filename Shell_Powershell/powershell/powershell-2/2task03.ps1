# Salo Maarit P0033
# 2task03 sort command line arguments based on length
$paramarr = $args[0], $args[1], $args[2]
Write-Host "Parameters are:", $paramarr[0], $paramarr[1], $paramarr[2]
$paramarr = $paramarr | Sort-Object -Property Length
Write-Host "Your ordered words are:", $paramarr[0], $paramarr[1], $paramarr[2]
