def getGitBranchName() {
  return scm.branches[0].name
}
def branchName
def targetBranch
pipeline {
  agent any
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
            credentialsId: 'git-credential-hassen'
        }
        echo "Current branch name: ${branchName}"
        echo "Current branch name: ${targetBranch}"
      }
    }
    stage('MVN CLEAN') {
      steps {
        sh "mvn clean"
      }
    }
    stage('MVN COMPILE') {
      steps {
        sh "mvn compile"
      }
    }
    stage('SonarQube') {
      steps {
        // Lancer l'analyse SonarQube avec Maven
        sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar"
      }
    }
    stage('Mockito') {
      steps {
        sh 'mvn test'
      }
    }
    stage('Nexus') {
      steps {
        sh 'mvn deploy -DskipTests'
      }
    }

  }
}