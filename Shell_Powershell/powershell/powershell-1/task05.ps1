#P0033 Maarit Salo
#task05 applications
#get-process hakee käynnissä olevat prosessit ja listaa ne allekkain, putkitettuna
#measure-objectille listauksesta tulee objekti josta saadaan count irti
Write-Host "You got", (Get-Process | Measure-Object).count ,"applications in Powershell"