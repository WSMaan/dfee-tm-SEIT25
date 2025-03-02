pipeline {
    agent any

    environment {
        AWS_ACCOUNT_ID = "583187964056"
        AWS_REGION = "us-east-2"
        ECR_REPOSITORY_NAME = "exam"
        ECR_REGISTRY = "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com"
        S3_BUCKET = "seit-25"
    }

    parameters {
        string(name: 'BRANCH_NAME', defaultValue: 'sortable_data_tables.feature', description: 'Feature branch to test')
        string(name: 'FEATURE_FILE', defaultValue: 'src/test/resources/features/sortable_data_tables.feature', description: 'Feature file to run')
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

        stage('Build Docker Image') {
            steps {
                dir('cucumber-tests') {
                    script {
                        def imageTag = "${ECR_REGISTRY}/${ECR_REPOSITORY_NAME}:test-${params.BRANCH_NAME}"
                        sh """
                        docker build -t ${imageTag} .
                        """
                    }
                }
            }
        }

        stage('Push Docker Image to ECR') {
            steps {
                script {
                    def imageTag = "${ECR_REGISTRY}/${ECR_REPOSITORY_NAME}:test-${params.BRANCH_NAME}"
                    withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'aws_key']]) {
                        sh """
                        aws ecr get-login-password --region ${AWS_REGION} | docker login --username AWS --password-stdin ${ECR_REGISTRY}
                        docker push ${imageTag}
                        """
                    }
                }
            }
        }

        stage('Run Cucumber Tests in Docker') {
            steps {
                script {
                    def imageTag = "${ECR_REGISTRY}/${ECR_REPOSITORY_NAME}:test-${params.BRANCH_NAME}"
                    sh """
                    docker run --rm ${imageTag} mvn clean test -Dcucumber.options='${params.FEATURE_FILE}'
                    """
                }
            }
        }

        stage('Generate Reports') {
            steps {
                dir('cucumber-tests') {
                    sh '''
                    mkdir -p target/cucumber-reports
                    mv target/cucumber-report/cucumber.html target/cucumber-reports/
                    '''
                }
            }
        }

        stage('Archive Results in Jenkins') {
            steps {
                archiveArtifacts artifacts: 'cucumber-tests/target/cucumber-reports/*', fingerprint: true
            }
        }

        stage('Upload Reports to S3') {
            steps {
                script {
                    withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'aws_key']]) {
                        sh """
                        aws s3 cp cucumber-tests/target/cucumber-reports/ s3://${S3_BUCKET}/cucumber-reports/ --recursive
                        """
                    }
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
