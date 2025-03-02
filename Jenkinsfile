pipeline {
    agent any

    environment {
        AWS_ACCOUNT_ID = "583187964056"
        AWS_REGION = "us-east-2"
    }

    parameters {
        string(name: 'BRANCH_NAME', defaultValue: 'sortable_data_tables.feature', description: 'Feature branch to test')
        string(name: 'FEATURE_FILE', defaultValue: 'src/test/resources/features/sortable_data_tables.feature', description: 'Feature file to run')
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

        stage('Run Cucumber Tests') {
            steps {
                dir('cucumber-tests') {
                    sh """
                    mvn clean test -Dcucumber.filter.tags='${params.CUCUMBER_TAGS}' -Dcucumber.options='${params.FEATURE_FILE}'
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
