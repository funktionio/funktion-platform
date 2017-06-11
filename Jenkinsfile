#!/usr/bin/groovy
@Library('github.com/fabric8io/fabric8-pipeline-library@master')
def dummy
deployTemplate{
  mavenNode {
    ws{
      checkout scm
      sh "git remote set-url origin git@github.com:funktionio/funktion-platform.git"

      def pipeline = load 'release.groovy'

      stage 'Promote'
      pipeline.release(stagedProject)
    }
  }
}