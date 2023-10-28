pipeline {
    agent any
    parameters {
        string(name: 'BRANCH_NAME', defaultValue: "${scm.branches[0].name}", description: 'Git branch name')
        string(name: 'CHANGE_ID', defaultValue: '', description: 'Git change ID for merge requests')
        string(name: 'CHANGE_TARGET', defaultValue: '', description: 'Git change ID for the target merge requests')
    }
    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-credentials')
    }
    stages {
        stage('Github') {
            steps {
                script {
                    branchName = params.BRANCH_NAME
                    targetBranch = branchName

                    git branch: branchName,
                    url: 'https://github.com/ZayaniHassen/Devops.git',
                    credentialsId: '90b732ea-bdca-47c9-a9c3-0f09934b3646'
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
        stage('Mockito') {
            steps {
                sh 'mvn test'
            }
        }
        stage('SonarQube') {
            steps {
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar"
            }
        }

        stage('Nexus') {
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }
        stage('Build') {
            steps {
                sh 'docker build -t amaniboussaa/springachat .'
                echo 'Build Image Completed'
            }
        }
        stage('Login to Docker Hub') {
            steps {
                script {
                    // Login to Docker Hub using credentials
                    sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                }
                echo 'Login Completed'
            }
        }
        stage('Push Image to Docker Hub') {
            steps {
                sh 'docker push amaniboussaa/springachat'
                echo 'Push Image Completed'
            }
        }
        stage('Docker Compose') {
            steps {
                sh 'docker-compose up -d'
                echo 'Docker Compose Completed'
            }
        }
    }

    post {
        always {
            sh 'docker logout'
        }
    }
}
