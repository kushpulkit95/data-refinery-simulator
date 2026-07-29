pipeline {
    agent any

    stages {

        stage('Build') {
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
                bat 'docker build -t data-refinery-simulator:%BUILD_NUMBER% .'
                bat 'docker tag data-refinery-simulator:%BUILD_NUMBER% data-refinery-simulator:latest'
            }
        }

    }
}