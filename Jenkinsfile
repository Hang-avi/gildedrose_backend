node{
   stage('SCM Checkout'){
       git url:'https://github.com/Hang-avi/gildedrose_backend', branch:'main'
   }
   //stage('Run tests'){
   //    sh "/usr/share/maven/bin/mvn clean test"
   //}

   stage("Run the database image"){
            steps{
                sh "docker stop my-postgres || true && docker rm my-postgres || true"
                sh "docker run --name my-postgres -e POSTGRES_PASSWORD=secret -p 5432:5432 -d postgres"
                sh "sleep 10"
            }
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