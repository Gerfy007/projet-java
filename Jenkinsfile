pipeline {
    agent any

    triggers {
        cron('H/5 * * * *')
    }

    environment {
        IMAGE_NAME = 'hamedmejri/alpine'
        IMAGE_TAG  = 'latest'
    }

    stages {

        stage('Checkout Source Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Gerfy007/projet-java.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                echo "Building Docker image..."
                docker build -t $IMAGE_NAME:$IMAGE_TAG .
                '''
            }
        }

        stage('Login to Docker Hub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh '''
                    echo "Logging into Docker Hub..."
                    echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
                    '''
                }
            }
        }

        stage('Push Image to Docker Hub') {
            steps {
                sh '''
                echo "Pushing Docker image..."
                docker push $IMAGE_NAME:$IMAGE_TAG
                '''
            }
        }
    }

    post {
        success {
            echo '✅ Docker image successfully pushed to Docker Hub'
        }
        failure {
            echo '❌ Build failed'
        }
        always {
            sh 'docker logout || true'
        }
    }
}
