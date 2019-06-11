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
