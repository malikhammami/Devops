
def getGitBranchName() {
                return scm.branches[0].name
            }
def branchName
def targetBranch 

pipeline {
    agent any

    environment {
        DOCKERHUB_USERNAME = "ghiloufiw"
        PROD_TAG = "${DOCKERHUB_USERNAME}/ghiloufi:v1.0-prod"
    }

    parameters {
        string(name: 'BRANCH_NAME', defaultValue: "${scm.branches[0].name}", description: 'Git branch name')
        string(name: 'CHANGE_ID', defaultValue: '', description: 'Git change ID for merge requests')
        string(name: 'CHANGE_TARGET', defaultValue: '', description: 'Git change ID for the target merge requests')
    }

    stages {
        stage('Git Checkout') {
            steps {
                script {
                    git branch: BRANCH_NAME,
                        url: 'https://github.com/ZayaniHassen/Devops.git',
                        credentialsId: 'fbbc561b-d65d-4aee-9ae3-0aabdd4a6163'
                    echo "Checked out branch: ${BRANCH_NAME}"
                }
            }
        }

        stage('Build and Test') {
            when {
                expression {
                    (params.CHANGE_ID != null) && (BRANCH_NAME == 'Reglement')
                }
            }
            steps {
                sh 'mvn clean install'
                sh 'mvn compile'
                // Add more build and test commands as needed
            }
        }

        stage('Static Analysis with SonarQube') {
            when {
                expression {
                    (params.CHANGE_ID != null) && (BRANCH_NAME == 'Reglement')
                }
            }
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage('Nexus Deployment') {
            when {
                expression {
                    (params.CHANGE_ID != null) && (BRANCH_NAME == 'Reglement')
                }
            }
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }

	stage('Build Docker') {
    when {
        expression {
          (params.CHANGE_ID != null) && ((targetBranch == 'Reglement'))
        }
    }
    steps {
        script {
            if (targetBranch == 'Reglement') {
                sh "docker build -t ${PROD_TAG} ."
            } 
        }
    }
}



	  stage('Docker Login'){
	     when {
        expression {
          (params.CHANGE_ID != null) && ((targetBranch == 'Reglement'))
        }
    }
            steps{
                withCredentials([usernamePassword(credentialsId: '928642c1-11a7-49cf-8d04-e89186dc78a1', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                sh "docker login -u ${DOCKERHUB_USERNAME} -p ${DOCKERHUB_PASSWORD}"
    }
  }

        }



	stage('Docker Push'){
		when {
        expression {
          (params.CHANGE_ID != null) && ((targetBranch == 'Categorie_Produit'))
        }
    }
            steps{
                sh 'docker push $DOCKERHUB_USERNAME/achat --all-tags '
            }
        }

        stage('Remove Containers and Images') {
            when {
                expression {
                    (params.CHANGE_ID != null) && (BRANCH_NAME == 'Reglement')
                }
            }
            steps {
                sh '''
                container_ids=$(docker ps -q --filter "publish=8085/tcp")
                if [ -n "$container_ids" ]; then
                    echo "Stopping and removing containers..."
                    docker stop $container_ids
                    docker rm $container_ids
                else
                    echo "No containers found using port 8085."
                fi
                '''
               // sh "docker-compose down --rmi all"
            }
        }

        stage('Docker Compose Up') {
            when {
                expression {
                    (params.CHANGE_ID != null) && (BRANCH_NAME == 'Reglement')
                }
            }
            steps {
	sh "docker-compose down -v"
        sh "docker-compose -f docker-compose.yml up -d"
            }
        }
    }
}
