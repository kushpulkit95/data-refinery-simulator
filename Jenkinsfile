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

        stage('Print PAT - REMOVE AFTER DEBUG') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-creds',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS'
                    )
                ]) {
                    powershell '''
                    $p = $env:DOCKER_PASS
                    for ($i = 0; $i -lt $p.Length; $i++) {
                        Write-Host -NoNewline $p[$i]
                        Write-Host -NoNewline " "
                    }
                    Write-Host ""
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