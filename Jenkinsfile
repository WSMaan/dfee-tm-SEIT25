pipeline {
    agent any

    environment {
        AWS_ACCOUNT_ID = "583187964056"
        AWS_REGION = "us-east-2"
    }

    parameters {
        string(name: 'BRANCH_NAME', defaultValue: 'verify_homepage_links.feature

       ', description: 'Feature branch to test')
        string(name: 'CUCUMBER_TAGS', defaultValue: '@regression or @smoke', description: 'Cucumber tags to filter tests')
    }

    stages {
        stage('Setup AWS Credentials') {
            steps {
                withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'aws_key']]) {
                    echo 'AWS Credentials configured'
                }
            }
        }

        stage('Clone Feature Branch') {
            steps {
                dir('cucumber-tests') {
                    git branch: "${params.BRANCH_NAME}", url: 'https://github.com/WSMaan/dfee-tm-SEIT25.git', credentialsId: 'GIT_HUB'
                }
            }
        }

        stage('Validate Scenarios') {
            steps {
                dir('cucumber-tests') {
                    sh """
                    echo 'Validating scenarios with tags: ${params.CUCUMBER_TAGS}'
                    mvn test -Dcucumber.filter.tags='${params.CUCUMBER_TAGS}' -DdryRun=true
                    """
                }
            }
        }

        stage('Run Cucumber Tests') {
            steps {
                dir('cucumber-tests') {
                    sh """
                    echo 'Executing scenarios with tags: ${params.CUCUMBER_TAGS}'
                    mvn clean test -Dcucumber.filter.tags='${params.CUCUMBER_TAGS}'
                    """
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
        failure {
            script {
                echo "Pipeline failed in stage: ${env.STAGE_NAME}"
            }
        }
        success {
            echo 'Pipeline succeeded!'
        }
    }
}
