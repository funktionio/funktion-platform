#!/usr/bin/groovy
def stage(){
  return stageProject{
    project = 'funktionio/funktion-platform'
    useGitTagForNextVersion = true
  }
}

def approveRelease(project){
  def releaseVersion = project[1]
  approve{
    room = null
    version = releaseVersion
    console = null
    environment = 'funktion'
  }
}

def release(project){
  releaseProject{
    stagedProject = project
    useGitTagForNextVersion = true
    helmPush = false
    groupId = 'io.fabric8.funktion.distro'
    githubOrganisation = 'funktionio'
    artifactIdToWatchInCentral = 'distro'
    artifactExtensionToWatchInCentral = 'pom'
    promoteToDockerRegistry = 'docker.io'
    dockerOrganisation = 'funktion'
    imagesToPromoteToDockerHub = []
    extraImagesToTag = null
  }
}


return this;
