CALL setenv.bat
echo %PATH%
set drive=%~d0
set baseDir= %~dp0
cd %drive%
%drive%
cd %baseDir%
javac -version
javac  -d classes -cp .;classes;src src\com\iss\storeApplication\common\*.java
javac  -d classes -cp .;classes;src src\com\iss\storeApplication\domain\*.java
javac  -d classes -cp .;classes;src src\com\iss\storeApplication\enums\*.java
javac  -d classes -cp .;classes;src src\com\iss\storeApplication\dao\*.java
javac  -d classes -cp .;classes;src src\com\iss\storeApplication\component\*.java
javac  -d classes -cp .;classes;src src\com\iss\storeApplication\controller\*.java
javac  -d classes -cp .;classes;src src\com\iss\storeApplication\view\*.java
javac  -d classes -cp .;classes;src src\com\iss\storeApplication\business\*.java
javac  -d classes -cp .;classes;src src\com\iss\storeApplication\*.java

pause