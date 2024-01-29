#P0033 Maarit Salo
#task04 array
$names = "Steve Rogers", "Joker", "Ezekiel","Damien Wayne", "Stephanie Brown", "Dick Grayson","Batman"
Write-Host "There are", $names.Length, "names:"
foreach ($name in $names) {
    $name
  }
Write-Host "The first name is",$names[0], "and the last name is",$names[$names.Length-1]