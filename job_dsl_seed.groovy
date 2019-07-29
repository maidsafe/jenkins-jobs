pipelineJob('ami_build-safe_cli_slave') {
    parameters {
        stringParam('BRANCH', 'master')
        stringParam(
            'REPO_URL',
            'https://github.com/maidsafe/safe-build-infrastructure.git')
        stringParam('SAFE_IMAGE_TAG', '')
    }

    description('Creates a Docker slave AMI for safe-cli')

    definition {
        cpsScm {
            scm {
                git {
                    remote { url('https://github.com/maidsafe/jenkins-jobs.git') }
                    branches('master')
                    scriptPath('ami_build-safe_cli_slave/Jenkinsfile')
                    extensions { }
                }
            }
        }
    }
}

pipelineJob('ami_build-safe_client_libs_slave') {
    parameters {
        stringParam('BRANCH', 'master')
        stringParam(
            'REPO_URL',
            'https://github.com/maidsafe/safe-build-infrastructure.git')
        stringParam('SAFE_IMAGE_TAG', '')
    }

    description('Creates a Docker slave AMI for safe_client_libs')

    definition {
        cpsScm {
            scm {
                git {
                    remote { url('https://github.com/maidsafe/jenkins-jobs.git') }
                    branches('master')
                    scriptPath('ami_build-safe_client_libs_slave/Jenkinsfile')
                    extensions { }
                }
            }
        }
    }
}

pipelineJob('ami_build-safe_nd_slave') {
    parameters {
        stringParam('BRANCH', 'master')
        stringParam(
            'REPO_URL',
            'https://github.com/maidsafe/safe-build-infrastructure.git')
        stringParam('SAFE_IMAGE_TAG', '')
    }

    description('Creates a Docker slave AMI for safe-nd')

    definition {
        cpsScm {
            scm {
                git {
                    remote { url('https://github.com/maidsafe/jenkins-jobs.git') }
                    branches('master')
                    scriptPath('ami_build-safe_nd_slave/Jenkinsfile')
                    extensions { }
                }
            }
        }
    }
}

pipelineJob('ami_build-safe_vault_slave') {
    parameters {
        stringParam('BRANCH', 'master')
        stringParam(
            'REPO_URL',
            'https://github.com/maidsafe/safe-build-infrastructure.git')
        stringParam('SAFE_IMAGE_TAG', 'build')
    }

    description('Creates a Docker slave AMI for safe_vault')

    definition {
        cpsScm {
            scm {
                git {
                    remote { url('https://github.com/maidsafe/jenkins-jobs.git') }
                    branches('master')
                    scriptPath('ami_build-safe_vault_slave/Jenkinsfile')
                    extensions { }
                }
            }
        }
    }
}

pipelineJob('docker_build-safe_cli_build_container') {
    parameters {
        stringParam('BRANCH', 'master')
        stringParam(
            'REPO_URL',
            'https://github.com/maidsafe/safe-cli.git')
    }

    description('Builds and pushes the container for safe-cli')

    definition {
        cpsScm {
            scm {
                git {
                    remote { url('https://github.com/maidsafe/jenkins-jobs.git') }
                    branches('master')
                    scriptPath('docker_build-safe_cli_build_container/Jenkinsfile')
                    extensions { }
                }
            }
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

pipelineJob('docker_build-safe_nd_build_container') {
    parameters {
        stringParam('BRANCH', 'master')
        stringParam(
            'REPO_URL',
            'https://github.com/maidsafe/safe-nd.git')
    }

    description('Builds and pushes the container for safe-nd')

    definition {
        cpsScm {
            scm {
                git {
                    remote { url('https://github.com/maidsafe/jenkins-jobs.git') }
                    branches('master')
                    scriptPath('docker_build-safe_nd_build_container/Jenkinsfile')
                    extensions { }
                }
            }
        }
    }
}

pipelineJob('docker_build-safe_vault_build_container') {
    parameters {
        stringParam('BRANCH', 'master')
        stringParam(
            'REPO_URL',
            'https://github.com/maidsafe/safe_vault.git')
    }

    description('Builds and pushes the container for safe_vault')

    definition {
        cpsScm {
            scm {
                git {
                    remote { url('https://github.com/maidsafe/jenkins-jobs.git') }
                    branches('master')
                    scriptPath('docker_build-safe_vault_build_container/Jenkinsfile')
                    extensions { }
                }
            }
        }
    }
}

multibranchPipelineJob('pipeline-safe_cli') {
    branchSources {
        github {
            checkoutCredentialsId('github_maidsafe_token_credentials')
            scanCredentialsId('github_maidsafe_token_credentials')
            repoOwner('maidsafe')
            repository('safe-cli')
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

multibranchPipelineJob('pipeline-safe_nd') {
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

multibranchPipelineJob('pipeline-safe_vault') {
    branchSources {
        github {
            checkoutCredentialsId('github_maidsafe_token_credentials')
            scanCredentialsId('github_maidsafe_token_credentials')
            repoOwner('maidsafe')
            repository('safe_vault')
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

pipelineJob('rust_cache_build-safe_client_libs-windows') {
    parameters {
        stringParam('BRANCH', 'experimental')
        stringParam(
            'REPO_URL',
            'https://github.com/maidsafe/safe_client_libs.git')
        stringParam('S3_BUCKET', 'safe-jenkins-build-artifacts')
    }

    description('Builds Safe Client Libs on Windows then uploads the target directory for use as a cache.')

    definition {
        cpsScm {
            scm {
                git {
                    remote { url('https://github.com/maidsafe/jenkins-jobs.git') }
                    branches('master')
                    scriptPath('rust_cache_build-safe_client_libs-windows/Jenkinsfile')
                    extensions { }
                }
            }
        }
    }
}
