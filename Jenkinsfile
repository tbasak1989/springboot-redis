pipeline {
  agent {
    docker {
      image 'maven:3.9.9-eclipse-temurin-17'
      args '-v /root/.m2:/root/.m2'
    }
  }

  stages {
    stage('Build') {
      steps {
        sh 'mvn clean package -DskipTests'
      }
    }
  }
}
