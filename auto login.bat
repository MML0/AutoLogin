@echo off
setlocal

set url=http://10.10.10.10:8090/login.xml
set referer=http://10.10.10.10:8090/httpclient.html
set mode=191
set username=01asdxxx
set password=ad12xxx
set producttype=0

for /f "tokens=*" %%A in ('powershell -command "[Math]::floor((Get-Date).ToUniversalTime().Subtract((Get-Date).ToUniversalTime().Date).TotalSeconds)"') do set "timestamp=%%A"

curl -X POST ^
    -H "User-Agent: %user_agent%" ^
    -H "Referer: %referer%" ^
    -d "mode=%mode%&username=%username%&password=%password%&a=%timestamp%&producttype=%producttype%" ^
    %url%

timeout /t 4 /nobreak >nul

endlocal
