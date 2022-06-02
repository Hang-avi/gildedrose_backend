node{
   stage('SCM Checkout'){
       git url:'https://github.com/Hang-avi/gildedrose_backend', branch:'main'
   }
   //stage('Run tests'){
   //    sh "/usr/share/maven/bin/mvn clean test"
   //}

   stage("Run the database image"){
                sh "docker stop my-postgres || true && docker rm my-postgres || true"
                sh "docker run --name my-postgres -e POSTGRES_PASSWORD=secret -p 5432:5432 -d postgres"
                sh "sleep 10"
        }
   stage('Test and build Docker Image'){
     sh 'docker build -t hangavi/hw5:backend .'
   }

   stage('Api testing'){
        sh 'npm install --save-dev mocha chai'
        sh "sleep 6"
        sh "npm install --save-dev http-status-codes"
        sh "sleep 6"
        sh "npm install --save superagent superagent-promise"
        sh "sleep 6"
        sh "npm install eslint-plugin-import"
        sh "sleep 6"
        sh "eslint-config-airbnb-base"
        sh "sleep 6"
        sh "npm run lint -- --fix"
        sh "sleep 3"
        sh "npm test"
   }
   stage('Push Docker Image'){
       withCredentials([string(credentialsId: 'contrasena_docker', variable: 'contrasena')]) {
            sh "docker login -u hangavi -p ${contrasena}"
       }
       sh 'docker push hangavi/hw5:backend'
   }
}