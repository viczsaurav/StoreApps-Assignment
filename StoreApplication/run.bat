call setenv.bat
echo %PATH%
set drive=%~d0
set baseDir= %~dp0
cd %drive%
%drive%
cd %baseDir%
javac -version
java -cp .;classes com.iss.storeApplication.StoreApps