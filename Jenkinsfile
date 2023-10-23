def getGitBranchName() {
                return scm.branches[0].name
            }
def branchName
def targetBranch 

pipeline {
  agent any
	environment {
     DOCKERHUB_USERNAME = "hassenzayani"
     PROD_TAG = "${DOCKERHUB_USERNAME}/achat:v1.0-prod"
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

stage('MVN COMPILE') {
      when {
        expression {
          (params.CHANGE_ID != null) && ((targetBranch == 'Categorie_Produit'))
        }
      }
      steps {
       
	sh 'mvn compile'
        echo 'Compile stage done'
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

	   stage ('NEXUS DEPLOY') {
	when {
         expression {
          (params.CHANGE_ID != null) && ((targetBranch == 'Categorie_Produit'))
	}
	   }
       steps {
       sh 'mvn deploy -DskipTests'
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
                sh 'docker push $DOCKERHUB_USERNAME/achat-1.0 --all-tags '
            }
        }


	  	  stage('DOCKER COMPOSE') {
    when {
        expression {
            (params.CHANGE_ID != null) && (targetBranch == 'Categorie_Produit')
        }
    }
    steps {
        sh "docker-compose -f docker-compose.yml up"
    }
	}


// 	  stage('Remove Containers') {
// 		when {
//         expression {
//           (params.CHANGE_ID != null) && ((targetBranch == 'Categorie_Produit'))
//         }
//     }
//     steps {
//         sh '''
//         container_ids=$(docker ps -q --filter "publish=8089/tcp")
//         if [ -n "$container_ids" ]; then
//             echo "Stopping and removing containers..."
//             docker stop $container_ids
//             docker rm $container_ids
//         else
//             echo "No containers found using port 8089."
//         fi
//         '''
//     }
// }



	  

	  
  }
}
