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
                    Write-Host "=== Before login ==="

                    $env:DOCKER_PASS | docker login -u $env:DOCKER_USER --password-stdin

                    Write-Host "Exit Code: $LASTEXITCODE"

                    if ($LASTEXITCODE -ne 0) {
                        throw "Docker login failed with exit code $LASTEXITCODE"
                    }

                    Write-Host "=== Login successful ==="

                    docker logout
                    '''
                }
            }
        }
    }
}