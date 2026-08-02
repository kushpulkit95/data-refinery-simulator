pipeline {
    agent any

    environment {
        IMAGE_NAME = "pkistrying/data-refinery-simulator"
    }

    stages {

        stage('Build JAR') {
            steps {
                bat 'mvnw.cmd clean package'
            }
        }

        stage('Archive JAR') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build -t %IMAGE_NAME%:%BUILD_NUMBER% .'
                bat 'docker tag %IMAGE_NAME%:%BUILD_NUMBER% %IMAGE_NAME%:latest'
            }
        }

        stage('Debug Credential') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-creds',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS'
                    )
                ]) {
                    powershell '''
                    Write-Host "Username length: $($env:DOCKER_USER.Length)"
                    Write-Host "Username chars: $([string]::Join(",", $env:DOCKER_USER.ToCharArray() | ForEach-Object { [int]$_ }))"
                    Write-Host "Password length: $($env:DOCKER_PASS.Length)"
                    Write-Host "Password first char code: $([int]$env:DOCKER_PASS[0])"
                    Write-Host "Password last char code: $([int]$env:DOCKER_PASS[-1])"
                    '''
                }
            }
        }

        stage('Push Docker Image') {
            environment {
                DOCKER_CONFIG = "${WORKSPACE}\\.docker"
            }
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-creds',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS'
                    )
                ]) {
                    powershell '''
                    Write-Host "=== DOCKER_CONFIG is: $env:DOCKER_CONFIG ==="
                    New-Item -ItemType Directory -Force -Path $env:DOCKER_CONFIG | Out-Null

                    Write-Host "=== Attempting login ==="
                    $pass = $env:DOCKER_PASS
                    $pass | docker login -u $env:DOCKER_USER --password-stdin
                    Write-Host "=== Login exit code: $LASTEXITCODE ==="

                    Write-Host "=== Contents of config.json ==="
                    Get-Content "$env:DOCKER_CONFIG\\config.json" -ErrorAction SilentlyContinue

                    Write-Host "=== Attempting push ==="
                    docker push "${env:IMAGE_NAME}:${env:BUILD_NUMBER}"
                    Write-Host "=== Push exit code: $LASTEXITCODE ==="
                    '''
                }
            }
        }
    }
}