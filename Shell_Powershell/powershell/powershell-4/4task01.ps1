# Salo Maarit P0033
# 4task01
# Create a function named Get-FiluInfo that shows Name and Length properties of all files in user's home folder. Run the function to prove that it works.
#kutsuttu syötteillä
#PS C:\Users\OMISTAJA\Documents\ttc2060\powershell\powershell-4> . .\4task01.ps1
#PS C:\Users\OMISTAJA\Documents\ttc2060\powershell\powershell-4> Get-FiluInfo 
function Get-FiluInfo {
   
  return Get-ChildItem $HOME | Format-Table Name, Length
      
}

