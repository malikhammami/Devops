def getGitBranchName() {
                return scm.branches[0].name
            }
def branchName
def targetBranch 

pipeline {
  agent any
	environment {
     DOCKERHUB_USERNAME = "hassenzayani"
     PROD_TAG = "${DOCKERHUB_USERNAME}/Devops-test:v1.0.0-prod"
    }
	parameters {
	string(name: 'BRANCH_NAME', defaultValue: "${scm.branches[0].name}", description: 'Git branch name')
        string(name: 'CHANGE_ID', defaultValue: '', description: 'Git change ID for merge requests')
	string(name: 'CHANGE_TARGET', defaultValue: '', description: 'Git change ID for the target merge requests')
    }

  stages {
    stage('Github') {
      steps {
	script {
	branchName = params.BRANCH_NAME
        targetBranch = branchName

          git branch: branchName,
          url: 'https://github.com/ZayaniHassen/Devops.git',
          credentialsId: '4920dffc-bd22-4fe7-89af-ac983796a5ab'
      }
	  echo "Current branch name: ${branchName}"
	  echo "Current branch name: ${targetBranch}"
      }
    }

	  stage('MVN COMPILE') {
      when {
        expression {
          (params.CHANGE_ID != null) && ((targetBranch == 'Categorie_Produit'))
        }
      }
      steps {
        sh 'mvn clean compile'
        echo 'Clean stage done'
      }
    }

stage('MVN BUILD') {
      when {
        expression {
          (params.CHANGE_ID != null) && ((targetBranch == 'Categorie_Produit'))
        }
      }
      steps {
        sh 'mvn clean install'
        echo 'Build stage done'
      }
    }

	stage ('JUNIT TEST') {
	when {
         expression {
           (params.CHANGE_ID != null) && ((targetBranch == 'Categorie_Produit'))
            }
	   }
      steps {
        sh 'mvn test'
        echo 'test stage done'
      }
    }

	stage ('STATIC TEST WITH SONAR') {
       when {
         expression {
           (params.CHANGE_ID != null) && ((targetBranch == 'Categorie_Produit'))
         }
       }
       steps {
         withSonarQubeEnv('sonarqube') {
                sh 'mvn sonar:sonar'
         }
       }
    }

	  
	stage('Build Docker') {
    when {
        expression {
          (params.CHANGE_ID != null) && ((targetBranch == 'Categorie_Produit'))
        }
    }
    steps {
        script {
            if (targetBranch == 'Categorie_Produit') {
                sh "docker build -t ${PROD_TAG} ."
            } 
        }
    }
}



	  stage('Docker Login'){
	     when {
        expression {
          (params.CHANGE_ID != null) && ((targetBranch == 'Categorie_Produit'))
        }
    }
            steps{
                withCredentials([usernamePassword(credentialsId: 'e3bbae12-0224-4ade-800e-25851dbf474e', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
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
                sh 'docker push $DOCKERHUB_USERNAME/Devops-test --all-tags '
            }
        }





	  stage('Remove Containers') {
		when {
        expression {
          (params.CHANGE_ID != null) && ((targetBranch == 'Categorie_Produit'))
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
    }
}



	  

	  
  }
}
