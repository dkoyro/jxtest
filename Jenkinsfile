pipeline {
  agent {
    label "jenkins-maven"
  }
  environment {
    ORG = 'dkoyro'
    APP_NAME = 'jxtest'
    CHARTMUSEUM_CREDS = credentials('jenkins-x-chartmuseum')
  }
  stages {
    stage('CI Build and push snapshot') {
      when {
        branch 'PR-*'
      }
      environment {
        PREVIEW_VERSION = "0.0.0-SNAPSHOT-$BRANCH_NAME-$BUILD_NUMBER"
        PREVIEW_NAMESPACE = "$APP_NAME-$BRANCH_NAME".toLowerCase()
        HELM_RELEASE = "$PREVIEW_NAMESPACE".toLowerCase()
      }
      steps {
      container('maven') {
		sh "mvn versions:set -DnewVersion=$PREVIEW_VERSION"
		sh "mvn install"
		sh "export VERSION=$PREVIEW_VERSION && skaffold build -f skaffold.yaml"
		sh "jx step post build --image $DOCKER_REGISTRY/$ORG/$APP_NAME:$PREVIEW_VERSION"
		 dir('charts/preview') {
		  sh "make preview"
		  sh "jx preview --app $APP_NAME --dir ../.."
		 }
        }
      }
    }
    stage('Build Release') {
      when {
        branch 'master'
      }
      steps {
      container('maven') {
	    git 'https://github.com/dkoyro/jxtest.git'

         // so we can retrieve the version in later steps
         sh "echo \$(jx-release-version) > VERSION"
         sh "mvn versions:set -DnewVersion=\$(cat VERSION)"
         sh "jx step tag --version \$(cat VERSION)"
         sh "mvn clean deploy"
         sh "export VERSION=`cat VERSION` && skaffold build -f skaffold.yaml"
         sh "jx step post build --image $DOCKER_REGISTRY/$ORG/$APP_NAME:\$(cat VERSION)"
        }
      }
    }
    stage('Promote to Environments') {
      when {
        branch 'master'
      }
      steps {
        dir('charts/jxtest') {
          sh "jx step changelog --version v\$(cat ../../VERSION)"

          // release the helm chart
          sh "jx step helm release"

          // promote through all 'Auto' promotion Environments
          sh "jx promote -b --all-auto --timeout 1h --version \$(cat ../../VERSION)"
        }
      }
    }
  }
}
