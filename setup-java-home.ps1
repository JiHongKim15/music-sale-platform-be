# Java 21 설치 및 JAVA_HOME 설정 스크립트

Write-Host "Java 21 설치 확인 중..." -ForegroundColor Yellow

# Java 설치 경로 확인
$javaPaths = @(
    "C:\Program Files\Java\jdk-21*",
    "C:\Program Files\Java\jdk21*",
    "C:\Program Files\Eclipse Adoptium\jdk-21*",
    "C:\Program Files\Eclipse Adoptium\jdk-21*",
    "$env:USERPROFILE\.jdks\jdk-21*",
    "$env:LOCALAPPDATA\Programs\Eclipse Adoptium\jdk-21*"
)

$javaHome = $null
foreach ($path in $javaPaths) {
    $found = Get-ChildItem $path -ErrorAction SilentlyContinue | Select-Object -First 1
    if ($found -and (Test-Path "$($found.FullName)\bin\java.exe")) {
        $javaHome = $found.FullName
        Write-Host "Java 설치 발견: $javaHome" -ForegroundColor Green
        break
    }
}

if (-not $javaHome) {
    Write-Host "`nJava 21이 설치되어 있지 않습니다." -ForegroundColor Red
    Write-Host "다음 중 하나의 방법으로 Java 21을 설치하세요:" -ForegroundColor Yellow
    Write-Host "1. Eclipse Adoptium: https://adoptium.net/temurin/releases/?version=21" -ForegroundColor Cyan
    Write-Host "2. Oracle JDK: https://www.oracle.com/java/technologies/downloads/#java21" -ForegroundColor Cyan
    Write-Host "3. Chocolatey: choco install openjdk21" -ForegroundColor Cyan
    Write-Host "4. Scoop: scoop install openjdk21" -ForegroundColor Cyan
    Write-Host "`n설치 후 이 스크립트를 다시 실행하세요." -ForegroundColor Yellow
    exit 1
}

# 현재 세션에 JAVA_HOME 설정
$env:JAVA_HOME = $javaHome
$env:PATH = "$javaHome\bin;$env:PATH"

Write-Host "`n현재 세션에 JAVA_HOME 설정 완료: $env:JAVA_HOME" -ForegroundColor Green

# 영구적으로 설정할지 물어보기
$response = Read-Host "`n사용자 환경 변수로 영구 설정하시겠습니까? (Y/N)"
if ($response -eq 'Y' -or $response -eq 'y') {
    [System.Environment]::SetEnvironmentVariable("JAVA_HOME", $javaHome, [System.EnvironmentVariableTarget]::User)
    
    # PATH에도 추가 (중복 방지)
    $currentPath = [System.Environment]::GetEnvironmentVariable("Path", [System.EnvironmentVariableTarget]::User)
    $binPath = "$javaHome\bin"
    if ($currentPath -notlike "*$binPath*") {
        $newPath = "$currentPath;$binPath"
        [System.Environment]::SetEnvironmentVariable("Path", $newPath, [System.EnvironmentVariableTarget]::User)
        Write-Host "PATH에도 추가했습니다." -ForegroundColor Green
    }
    
    Write-Host "`n영구 설정 완료! 새 터미널을 열어야 적용됩니다." -ForegroundColor Green
}

# Java 버전 확인
Write-Host "`nJava 버전 확인:" -ForegroundColor Yellow
& "$javaHome\bin\java.exe" -version

Write-Host "`n설정 완료! 이제 ./gradlew spotlessApply를 실행할 수 있습니다." -ForegroundColor Green

