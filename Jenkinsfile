def gv
//def gv = load 'script.groovy' //alternative approach to load groovy script before the pipeline starts execution

pipeline {
    agent any
    parameters {
        choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description: 'Select version to build')
        booleanParam(name: 'Execute Tests Option', defaultValue: true, description: 'Test needed or not')

    }
    // environment{
    //     NEW_VERSION = '1.3.4'
    //     SERVER_CREDENTIALS = credentials('server-credentials')

    // }

    tools {
        maven 'maven-3.9'
    }
    stages {
        stage('init') {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage('build') {
            input{
                message "Select final build version and preferred cloud provider"
                ok "Done, Build Version Selected"
                parameters{
                    choice(name: 'Final Build Version', choices: ['2.7.8', '9.5.8', '7.0.0', '3.4.5'], description: 'Select build vers.')
                    choice(name: 'Cloud Provider', choices: ['AWS', 'GCP', 'Azure', 'DigitalOcean'], description: 'Select cloud provd.')

                }
            }
            steps {

            script{
                gv.buildApp()
                echo "Final build version selection is ${Final Build Version}"
                echo "Preferred cloud provider is ${Cloud Provider}"

            }
        }
            }

        
        stage('test') {
            when{
                expression{
                params.'Execute Tests Option' == true
                    }
                }
            steps {

                script {
                    gv.testApp()

                }
            }
        }
        stage('deploy') {
            input{ // this code block enables user input (single input selection) during the actual pipeline process.
                message "Select the environment to deploy to"
                ok 'Done, Environment Selected'
                parameters {
                choice(name: 'ENV', choices: ['dev', 'test', 'staging', 'prod'], description: 'Select environment for deployment')
                }

            }
            steps {
                script {
                    gv.deployApp()
                    echo "Application will be deployed to the ${ENV} environment"
                }
            }
        }

        }
}

        // post{
        //     always{
        //         //always block is to be executed regardless of whether the build pass or failed


        //     }

        //     success{

        //         //
        //     }

        //     failure{

        //         //
        //     }

        // }