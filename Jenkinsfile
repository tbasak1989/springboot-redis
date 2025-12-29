pipeline {
  agent any

  environment {
    IMAGE_NAME = 'tanmoyb89/microservices_101'
    IMAGE_TAG = '1.0.${BUILD_NUMBER}'
  }

  stages {

    stage('Clean Workspace') {
        steps {
            deleteDir() // wipes entire workspace to avoid caching old files
        }
    }

    stage('Checkout Code') {
        steps {
            checkout scm
        }
    }

    stage('Build JAR') {
      agent {
        docker {
          image 'maven:3.9.9-eclipse-temurin-17'
          args "-v $WORKSPACE:$WORKSPACE -v /root/.m2:/root/.m2 -w $WORKSPACE"
        }
      }
      steps {
        sh 'rm -rf target/* && mvn clean package -DskipTests -B -U'
      }
    }

    stage('Build Docker Image') {
      steps {
        sh """
          docker build --no-cache -t ${IMAGE_NAME}:${IMAGE_TAG} .
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
