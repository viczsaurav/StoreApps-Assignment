call setenv.bat
echo %PATH%
set drive=%~d0
set baseDir= %~dp0
cd %drive%
%drive%
cd %baseDir%
javac -version
javac  -d bin -cp .;bin;src src\com\iss\storeApplication\common\*.java
javac  -d bin -cp .;bin;src src\com\iss\storeApplication\domain\*.java
javac  -d bin -cp .;bin;src src\com\iss\storeApplication\enums\*.java
javac  -d bin -cp .;bin;src src\com\iss\storeApplication\dao\*.java
javac  -d bin -cp .;bin;src src\com\iss\storeApplication\component\*.java
javac  -d bin -cp .;bin;src src\com\iss\storeApplication\controller\*.java
javac  -d bin -cp .;bin;src src\com\iss\storeApplication\view\*.java
javac  -d bin -cp .;bin;src src\com\iss\storeApplication\business\*.java
javac  -d bin -cp .;bin;src src\com\iss\storeApplication\*.java

pause