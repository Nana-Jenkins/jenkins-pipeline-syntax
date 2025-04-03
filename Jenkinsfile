def gv
//def gv = load 'script.groovy' //alternative approach to load groovy script before the pipeline starts execution

pipeline {
    agent any
    parameters {
        choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description: 'select version to build')
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
            steps {

            script{
                gv.buildApp()

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
            steps {
                script {
                    gv.deployApp()
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