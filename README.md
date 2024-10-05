# AutoLogin

## Overview
**AutoLogin** is an application designed to automate the login process , making it easier for users to access their accounts without manually entering credentials each time.

## Download Links

### Android Version
You can download the Android APK to automate the login process:

- [Download AutoLogin APK](https://github.com/MML0/AutoLogin/releases/download/apk/AutoLogin.apk)

### Windows Version
For Windows users, a batch file is provided to automate the login:

1. **Edit User Credentials**: Open the batch file in Notepad and update the username and password with your own.
2. **Save the File**: Ensure the file is saved with a `.bat` extension.

- [Download AutoLogin Batch File](https://github.com/MML0/AutoLogin/blob/main/auto%20login.bat)

### Sample Batch File
Below is an example of the batch script used for automation:

```batch
@echo off
setlocal

::change this 
set username=01asdxxx
set password=ad12xxx

set url=http://10.10.10.10:8090/login.xml
set referer=http://10.10.10.10:8090/httpclient.html
set mode=191
set producttype=0

for /f "tokens=*" %%A in ('powershell -command "[Math]::floor((Get-Date).ToUniversalTime().Subtract((Get-Date).ToUniversalTime().Date).TotalSeconds)"') do set "timestamp=%%A"

curl -X POST ^
    -H "User-Agent: %user_agent%" ^
    -H "Referer: %referer%" ^
    -d "mode=%mode%&username=%username%&password=%password%&a=%timestamp%&producttype=%producttype%" ^
    %url%

timeout /t 4 /nobreak >nul

endlocal
```
