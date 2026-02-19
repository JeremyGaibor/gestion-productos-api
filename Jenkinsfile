pipeline {
    agent any

    stages {
        stage('Pull Docker Image') {
            steps {
                sh 'docker pull jeremy28g/repositorio_wachiturros:latest'
            }
        }

        stage('Run Container') {
            steps {
                sh '''
                docker stop api-productos || true
                docker rm api-productos || true
                docker run -d -p 8081:8080 --name api-productos jeremy28g/repositorio_wachiturros:latest
                '''
            }
        }
    }
}