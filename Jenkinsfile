pipeline {
  agent any

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
        sh 'docker build -t springboot-redis:latest .'
      }
    }

    stage('Run via Docker Compose') {
      steps {
        sh 'docker-compose up -d'
      }
    }


  }
}
