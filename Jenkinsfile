def getGitBranchName() {
                return scm.branches[0].name
            }
def branchName
def targetBranch 

pipeline {
  agent any
	environment {
             DOCKERHUB_USERNAME = "malikhammami99"
             DOCKER_REGISTRY = 'malikhammami99'
             DOCKER_IMAGE = 'achat:1-0'
             DOCKER_PASSWORD = 'password123'
             PROD_TAG = "${DOCKERHUB_USERNAME}/test:v1.0.0-prod"
		
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
          credentialsId: '3211957c-60c3-42bb-8798-827ef9bec12d'
      }
	  echo "Current branch name: ${branchName}"
	  echo "Current branch name: ${targetBranch}"
      }
    }
	  stage('MVN COMPILE') {
      when {
        expression {
          (params.CHANGE_ID != null) && ((targetBranch == 'Fournisseur'))
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
          (params.CHANGE_ID != null) && ((targetBranch == 'Fournisseur'))
        }
      }
      steps {
        sh 'mvn clean install'
        echo 'Build stage done'
      }
    }  
	 stage('SonarQube Analysis') {
            steps {
                // Lancer l'analyse SonarQube avec Maven
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar"
            }
        }
        stage('Nexus') {
            steps {
                
                sh "mvn deploy -DskipTests"
            }
        } 
	  
	stage('Docker build image') {
            steps {
                
                sh "docker build -t achat:1-0 ."
	        sh "docker tag achat:1-0  malikhammami99/achat:1-0"
		    
            }
	} 
	  
	  stage('Docker push image') {
            steps {
                sh "echo $DOCKER_PASSWORD | docker login -u login $DOCKER_REGISTRY -p-"
                sh "docker push $DOCKER_REGISTRY/$DOCKER_IMAGE"
            }
        }

	  
  }
}
