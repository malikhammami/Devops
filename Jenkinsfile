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
          credentialsId: '71b257c7-ecbb-4b16-8294-ddd1f4ac0a5e'
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

	  
	  stage('Remove Containers') {
		when {
        expression {
          (params.CHANGE_ID != null) && ((targetBranch == 'Categorie_Produit'))
        }
    }
    steps {
        sh '''
        container_ids=$(docker ps -q --filter "publish=8089/tcp")
        if [ -n "$container_ids" ]; then
            echo "Stopping and removing containers..."
            docker stop $container_ids
            docker rm $container_ids
        else
            echo "No containers found using port 8089."
        fi
        '''
    }
}



	  	  stage('DOCKER COMPOSE') {
    when {
        expression {
            (params.CHANGE_ID != null) && (targetBranch == 'Categorie_Produit')
        }
    }
    steps {
	sh "docker-compose down -v"
        sh "docker-compose -f docker-compose.yml up -d"
    }
	}

	  def notifySuccess() {
    def imageUrl = 'https://www.weodeo.com/wp-content/uploads/2023/02/DevOps-scaled.webp'  // Replace with the actual URL of your image
    def imageWidth = '800px';  // Set the desired width in pixels
    def imageHeight = 'auto';  // Set 'auto' to maintain the aspect ratio

    emailext body: """
        <html>
            <body>
                <p>YEEEEY, The Jenkins job was successful.</p>
                <p>You can view the build at: <a href="${BUILD_URL}">${BUILD_URL}</a></p>
                <p><img src="${imageUrl}" alt="Your Image" width="${imageWidth}" height="${imageHeight}"></p>
            </body>
        </html>
    """,
    subject: "Jenkins Job - Success",
    to: 'hassen.zayani@esprit.tn',
    mimeType: 'text/html'
}




def notifyFailure() {
            emailext body: "OUUUPS, The Jenkins job failed.\n You can view the build at: ${BUILD_URL}",
                subject: "Jenkins Job - Failure",
                to: 'hassen.zayani@esprit.tn'
        }





	//   stage('Deploy to Prod') {
 //            when {
 //                expression {
	// 		(params.CHANGE_ID != null)  && (targetBranch == 'Categorie_Produit')
	// 	}
 //            }
 //           steps {
	// 	kubernetesDeploy (configs: 'k8s.yml',kubeconfigId: 'k8sconfigpwd')
	//    }
	// }

	  

	  
  }
}
