pipeline {
    agent any
    environment {
        MAVEN_HOME = tool name: 'maven-3', type: 'maven'
        EMAIL_TO = 'senlayuliya@gmail.com'
    }
triggers { cron('0 10 * * *') }
    stages {
        stage('Test') {
            steps { sh 'mvn clean test -Dtest=CucumberTestRunner' }
            post { always { archiveArtifacts artifacts: 'target/*' } }
        }
        stage('Report') {
            steps {
                allure results: [[path: 'target/allure-results']]
                publishHTML([reportDir: 'target/cucumber-reports', reportFiles: 'cucumber.html', reportName: 'Cucumber Report'])
            }
        }
        stage('Email') {
            steps {
                emailext(
                    subject: "Tests - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                    body: "Build: ${env.BUILD_URL}",
                    to: "${EMAIL_TO}"
                )
            }
        }
    }
    post {
        always { archiveArtifacts artifacts: 'target/*', allowEmptyArchive: true }
        success { echo 'OK' }
        failure { echo 'FAIL' }
    }
}