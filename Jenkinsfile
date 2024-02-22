pipeline{

    agent any

    stages {

        stage('Build Jar'){
            steps{
                bat "mvn clean package -DskipTests"
            }
        }

        stage('Build Image'){
            steps{
                bat "docker build -t=maaxii/selenium:latest ."
            }
        }

        stage('Push Image'){
            environment{
                DOCKER_HUB = credentials('docker-hub-creds')
            }
            steps{
                bat 'docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%'
                bat "docker push maaxii/selenium:latest"
                bat "docker tag maaxii/selenium:latest maaxii/selenium:${env.BUILD_NUMBER}"
                bat "docker push maaxii/selenium:${env.BUILD_NUMBER}"
            }
        }  
    }

    post {
        always {
            bat "docker logout"
        }
    }
}