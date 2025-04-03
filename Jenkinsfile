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
                    // An alternative method to enable user input (single or multi input selection???) during the actual pipeline process. Here is through env varaibles
                    env.MYLANGUAGE = input message: "Select preferred scripting language", ok "Done", parameters: [choice(name: 'LANGUAGE', choices: ['python', 'groovy', 'bash', 'Go-lang'], description: 'Select language')]

                    echo "Preferred Language is ${MYLANGUAGE}"
                }
            }
        }
        stage('build') {
            input{ // this code block enables user input (multi input selection) during the actual pipeline process.
                message "Select final build version and preferred cloud provider"
                ok "Done, Build Version Selected"
                parameters{
                    choice(name: 'Final Build Version', choices: ['2.7.8', '9.5.8', '7.0.0', '3.4.5'], description: 'Select build vers.')
                    choice(name: 'Cloud_Provider', choices: ['AWS', 'GCP', 'Azure', 'DigitalOcean'], description: 'Select cloud provd.')

                }
            }
            steps {

                script{
                    gv.buildApp()
                    echo "Final build version selection is ${'Final Build Version'}" //important to remember to enclose with quotes any variable/strings that have spaces inside. Just like was done during the variable definition above. If not, it throws a syntax error during pipeline execution. However, even after enclosing in quotes, the pipeline runs and executes and you can select the options you need but when you want to call the variable in an echo/print message like this, it doesn't pick the variable value but only prints the variable name. It seems it is better to format like below, without a space 
                    echo "Preferred cloud provider is ${Cloud_Provider}" //Better formating

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