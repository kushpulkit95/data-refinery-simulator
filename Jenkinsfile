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
    }
}