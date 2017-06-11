#!/usr/bin/groovy
@Library('github.com/fabric8io/fabric8-pipeline-library@master')
def utils = new io.fabric8.Utils()
deployTemplate{
  mavenNode {
    ws{
      checkout scm
      sh "git remote set-url origin git@github.com:funktionio/funktion-platform.git"

          if (utils.isCI()){

            echo 'CI is not handled by pipelines yet'

          } else if (utils.isCD()){
            sh "git remote set-url origin git@github.com:fabric8io/fabric8-online.git"

            def pipeline = load 'release.groovy'
            def stagedProject

            stage ('Stage'){
              stagedProject = pipeline.stage()
              releaseVersion = stagedProject[1]
            }

          stage ('Promote'){
              pipeline.release(stagedProject)
          }
        }
    }
  }
}