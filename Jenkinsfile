node{
   stage('Free disk space from previous deployments'){
       sh "docker rm -vf $(docker ps -aq) || true"
       sh "docker rmi -f $(docker images -aq) || true"
   }
   stage('SCM Checkout'){
       git url:'https://github.com/Hang-avi/gildedrose_backend', branch:'main'
   }


   stage("Run the database image"){
                sh "docker stop my-postgres || true && docker rm my-postgres || true"
                sh "docker run --name my-postgres -e POSTGRES_PASSWORD=secret -p 5432:5432 -d postgres"
                sh "sleep 10"
        }
   stage('Test and build Docker Image'){
     sh 'docker build -t hangavi/hw5:backend .'


   }

   stage("Run the backend image"){

       sh "docker stop backend || true && docker rm backend || true"
       sh "docker run --name backend -p 8081:8080 -d hangavi/hw5:backend"
       sh "sleep 30"

   }

   stage('Api testing'){


        sh 'npm install --save-dev mocha chai'
        sh "sleep 3"
        sh "npm install --save-dev http-status-codes"
        sh "sleep 3"
        sh "npm install --save superagent superagent-promise"
        sh "sleep 3"
        sh "npm install eslint-plugin-import"
        sh "sleep 3"
        sh "npm install eslint-config-airbnb-base"
        sh "sleep 3"
        sh "npm run lint -- --fix"
        sh "sleep 3"
        sh "npm test"
        sh "sleep 3"
        publishHTML (target : [allowMissing: false,
         alwaysLinkToLastBuild: true,
         keepAll: true,
         reportDir: 'report',
         reportFiles: 'ApiTesting.html',
         reportName: 'Api Testing',
         reportTitles: 'Api Testing'])
   }
   stage('Push Docker Image'){
       withCredentials([string(credentialsId: 'contrasena_docker', variable: 'contrasena')]) {
            sh "docker login -u hangavi -p ${contrasena}"
       }
       sh 'docker push hangavi/hw5:backend'
   }
}