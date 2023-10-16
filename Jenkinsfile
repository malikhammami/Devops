pipeline {
    agent any
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
     
     
       
    }   
    
}
