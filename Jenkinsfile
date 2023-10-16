def getGitBranchName() {
                return scm.branches[0].name
            }
def branchName
def targetBranch 

pipeline {
  agent any
	environment {
     DOCKERHUB_USERNAME = "hassenzayani"
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
          credentialsId: 'c4f27f67-87e5-4774-ae9a-a5e8aa7cb488'
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
  }
}
