node{
   stage('SCM Checkout'){
       git url:'https://github.com/Hang-avi/gildedrose_backend', branch:'main'
   }
   stage('Run tests'){
       sh "sudo mount -o remount,exec /var"
       def mvnHome = tool name: 'maven-3', type: 'maven'
       def mvnCMD = "${mvnHome}/bin/mvn" 
       sh "${mvnHome} clean package"
   }
   stage('Build Docker Image'){
     sh 'docker build -t hangavi/hw5:backend .'
   }
   stage('Push Docker Image'){
       withCredentials([string(credentialsId: 'contrasena_docker', variable: 'contrasena')]) {
            sh "docker login -u hangavi -p ${contrasena}"
       }
       sh 'docker push hangavi/hw5:backend'
   }
}