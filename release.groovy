#!/usr/bin/groovy
def imagesBuiltByPipeline() {
  return []
}

def externalImages(){
  return ['jenkins-jnlp-client','jenkins-docker']
}

def repo(){
 return 'funktionio/funktion-platform'
}

def stage(){
  return stageProject{
    project = repo()
    useGitTagForNextVersion = true
    extraImagesToStage = externalImages()
  }
}

def updateDownstreamDependencies(stagedProject) {
  /*
  pushPomPropertyChangePR {
    propertyName = 'fabric8-team-components.version'
    projects = [
            'fabric8io/fabric8-maven-dependencies'
    ]
    version = stagedProject[1]
  }
  */
}

def release(project){
  releaseProject{
    stagedProject = project
    useGitTagForNextVersion = true
    helmPush = false
    groupId = 'io.fabric8.funktion.platform'
    githubOrganisation = 'funktionio'
    artifactIdToWatchInCentral = 'parent'
    artifactExtensionToWatchInCentral = 'pom'
    promoteToDockerRegistry = 'docker.io'
    dockerOrganisation = 'funktion'
    imagesToPromoteToDockerHub = imagesBuiltByPipeline()
    extraImagesToTag = externalImages()
  }
}

return this;
