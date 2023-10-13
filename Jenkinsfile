def getGitBranchName() {
                return scm.branches[0].name
            }
def branchName
def targetBranch

pipeline {
  agent any
	environment {
     DOCKERHUB_USERNAME = "azizkhattech"
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
          credentialsId: 'bbb64100-0f3c-4eae-b0da-0c678c8ecafb'
      }
	  echo "Current branch name: ${branchName}"
	  echo "Current branch name: ${targetBranch}"
      }
    }
	  stage('MVN COMPILE') {
      when {
        expression {
          (params.CHANGE_ID != null) && ((targetBranch == 'Aziz'))
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
          (params.CHANGE_ID != null) && ((targetBranch == 'Aziz'))
        }
      }
      steps {
        sh 'mvn clean install'
        echo 'Build stage done'
      }
    }

  }

}

