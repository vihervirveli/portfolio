# Salo Maarit P0033
# 4task02
#Create a function named Get-FiluInfo2 that shows Name, Length, LastWriteTime properties of files with given extension in user's home folder. The extension is like .txt or .csv, and it has been given as a parameter. Run the function to prove that it works.
#Itselläni ei kotihakemistossa ollut mitään .txt tai oikeastaan melkein mitään muuta kuin kansioita
#ja .ssh ja .vscode tyylisesti kamaa, ja ohjelma kyllä löysi ne. 
#PS C:\Users\OMISTAJA\Documents\ttc2060\powershell\powershell-4> Get-FiluInfoOnSpecificFiles .ssh
#PS C:\Users\OMISTAJA\Documents\ttc2060\powershell\powershell-4> Get-FiluInfoOnSpecificFiles .vscode
#toimivat siis
#Kokeilin ajaa tämän myös kotihakemiston alla Documents-kansiossa,
#ja syötteet
#PS C:\Users\OMISTAJA\Documents\ttc2060\powershell\powershell-4> Get-FiluInfoOnSpecificFiles .png 
#ja 
#PS C:\Users\OMISTAJA\Documents\ttc2060\powershell\powershell-4> Get-FiluInfoOnSpecificFiles .txt
#toimi.

function Get-FiluInfoOnSpecificFiles {
    Param([string]$extension)
    Get-ChildItem $HOME | Where-Object { $_.Name -like "*$extension" } | Format-Table Name, Length, LastWriteTime
        
}

