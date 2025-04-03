def buildApp() {
    echo 'building the application'

}


def testApp() {
    echo 'testing the application'

}

def deployApp(){
    echo 'deployinggggg the application'
    echo "deploying version ${params.VERSION}"

}

return this