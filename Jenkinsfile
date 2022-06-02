node{
   stage('SCM Checkout'){
       git url:'https://github.com/Hang-avi/gildedrose_backend', branch:'main'
   }
   stage('Run tests'){
       sh "/usr/share/maven/bin/mvn clean package"
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