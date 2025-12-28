pipeline {
  agent any

  environment {
    IMAGE_NAME = 'tanmoyb89/springboot-redis'
    IMAGE_TAG  = 'latest'
  }

  stages {

    stage('Build JAR') {
      agent {
        docker {
          image 'maven:3.9.9-eclipse-temurin-17'
          args '-v /root/.m2:/root/.m2'
        }
      }
      steps {
        sh 'mvn clean package -DskipTests'
      }
    }

    stage('Build Docker Image') {
      steps {
        sh """
          docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .
        """
      }
    }

    stage('Login to Docker Hub') {
      steps {
        withCredentials([
          usernamePassword(
            credentialsId: 'dockerhub-creds',
            usernameVariable: 'DOCKER_USER',
            passwordVariable: 'DOCKER_PASS'
          )
        ]) {
          sh '''
            echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
          '''
        }
      }
    }

    stage('Push Image to Docker Hub') {
      steps {
        sh """
          docker push ${IMAGE_NAME}:${IMAGE_TAG}
        """
      }
    }
  }
  post {
    always {
      sh 'docker logout || true'
    }
    failure {
      echo 'Pipeline failed'
    }
    success {
      echo 'Pipeline completed successfully'
    }
  }
}
