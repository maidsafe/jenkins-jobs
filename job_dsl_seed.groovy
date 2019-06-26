multibranchPipelineJob('pipeline-safe_client_libs') {
    branchSources {
        github {
            checkoutCredentialsId('github_maidsafe_token_credentials')
            scanCredentialsId('github_maidsafe_token_credentials')
            repoOwner('maidsafe')
            repository('safe_client_libs')
        }
    }
    orphanedItemStrategy {
        discardOldItems {
            numToKeep(20)
        }
    }
    factory {
        workflowBranchProjectFactory {
            scriptPath('scripts/Jenkinsfile')
        }
    }
}

multibranchPipelineJob('pipeline-safe-nd') {
    branchSources {
        github {
            checkoutCredentialsId('github_maidsafe_token_credentials')
            scanCredentialsId('github_maidsafe_token_credentials')
            repoOwner('maidsafe')
            repository('safe-nd')
        }
    }
    orphanedItemStrategy {
        discardOldItems {
            numToKeep(20)
        }
    }
    factory {
        workflowBranchProjectFactory {
            scriptPath('Jenkinsfile')
        }
    }
}

pipelineJob('docker_build-safe_client_libs_build_container') {
    parameters {
        stringParam('BRANCH', 'experimental')
        stringParam(
            'REPO_URL',
            'https://github.com/maidsafe/safe_client_libs.git')
    }

    description('Builds and pushes the container for Safe Client Libs')

    definition {
        cpsScm {
            scm {
                git {
                    remote { url('https://github.com/maidsafe/jenkins-jobs.git') }
                    branches('master')
                    scriptPath('docker_build-safe_client_libs_build_container/Jenkinsfile')
                    extensions { }
                }
            }
        }
    }
}
