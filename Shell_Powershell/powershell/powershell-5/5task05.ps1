# # Salo Maarit P0033
# 5task05
# In this task you have to create a new function called Add-MyEvent. The function takes two parameter: Type and 
# Message. The type can be: Error, Warning, or Information. Message is a clear message. Add the following 
# functionality to the task 04 using your new function.
#- if the script can create a new CSV-file succesfully, then it will add a Information message to the eventlog. - #if the script cannot create a file succesfully, then it adds a Error message to the eventlog.
#Before using your new function create a new source named "MyPowerShell" to the Evenlog for writing your events.
param(
    $filename = "localusers.csv"
)


function Add-MyEvent {
    Param([string]$type, [string]$message)
    
    Write-EventLog -LogName Application -Source MyPowerShell -EntryType $type -EventID 1  -Message $message
        
}


try {
    $path = Join-Path -Path $PSScriptRoot -ChildPath $filename
    Get-LocalUser -Name * | Select-Object Name, FullName, SID, LastLogon | Export-Csv -Path $path -Delimiter ";"
    $success = "CSV-file $filename was created successfully"
    Add-MyEvent -type Information -message $success
}
catch {
    $errormsg = "Something went wrong, error"
    Add-MyEvent -type Error -message $errormsg
}



