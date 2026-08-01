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

        stage('Login Test') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-creds',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS'
                    )
                ]) {
                    powershell '''
                    Write-Host "Username: '$($env:DOCKER_USER)'"
                    Write-Host "Username Length: $($env:DOCKER_USER.Length)"

                    $bytes = [System.Text.Encoding]::UTF8.GetBytes($env:DOCKER_USER)
                    Write-Host "Username Bytes:"
                    $bytes

                    $env:DOCKER_PASS | docker login -u $env:DOCKER_USER --password-stdin

                    Write-Host "Exit Code: $LASTEXITCODE"
                    '''
                }
            }
        }
    }
}