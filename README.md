# Jenkins Jobs

This repository houses the definitions for jobs on our Jenkins instance. They are defined using the [Job DSL](https://jenkinsci.github.io/job-dsl-plugin/). Each application we're building will have its Jenkinsfile defined in its own repository, but we'll also use this repository to define Jenkinsfiles for arbitrary utility jobs that don't belong to any particular application repo.

## Jobs Summary

| Name                                          | Description                                                                                                                                                                                                                                    |
| ----                                          | -----------                                                                                                                                                                                                                                    |
| ami_build-rust_slave_windows                  | Creates a new Windows AMI with the latest stable version of Rust and other tools like msys2.                                                                                                                                                                    |
| ami_build-safe_client_libs_slave              | Creates a new AMI with the Safe Client Libs build container pulled into it.                                                                                                                                                                    |
| ami_build-safe_cli_slave                      | Creates a new AMI with the Safe CLI build container pulled into it.                                                                                                                                                                    |
| ami_build-safe_nd_slave                       | Creates a new AMI with the Safe Network Data Types build container pulled into it.                                                                                                                                                                    |
| docker_build-safe_client_libs_build_container | Constructs a new build container for SCL. Currently this is using the experimental branch by default. It can be run manually, but soon I'll be hoping to change the SCL build process to trigger this job at the end of a merged branch build. |
| docker_build-safe_cli_libs_build_container    | Constructs a new build container for safe-cli. |
| docker_build-safe_nd_libs_build_container     | Constructs a new build container for safe-nd.  |
| pipeline-safe_cli                             | Release pipeline for [safe-cli](https://github.com/maidsafe/safe-nd). This pipeline will build and test on Linux, Windows and macOS and deploy artifacts to GitHub releases. |
| pipeline-safe_client_libs                     | Release pipeline for [safe_client_libs](https://github.com/maidsafe/safe_client_libs). Has a build, test and deploy stage, with artifacts for all platforms being deployed to an S3 bucket. |
| pipeline-safe_nd                              | Release pipeline for [safe-nd](https://github.com/maidsafe/safe-nd). Currently this release process only has a simple build and test stage. |
| pipeline-safe_vault                           | Release pipeline for [safe_vault](https://github.com/maidsafe/safe_vault). Builds on Linux, Windows and macOS and deploys to GitHub releases. |
| pipeline-sandbox                              | This is not a multibranch pipeline. It's intended for quick experimentation when that is necessary. It uses a simple Rust library that we can experiment with. |
| rust_cache_build-safe_client_libs-windows     | Builds an SCL branch on a Windows node then uploads the resulting target directory as a tar. It's intended to be used as a cache for Windows builds. |

## Contributing

### Creating New Jobs

When adding a new job, add the job definition to the job_dsl_seed.groovy file, then add a Jenkinsfile under a folder which should be named the same as the job name (note: this does not apply to product release pipelines; their Jenkinsfile will be declared in the product repository). The type of these jobs should generally be a pipeline, rather than a multibranch pipeline or a freestyle job. The reason for using the pipeline type rather than freestyle is because with a pipeline you can define what the job does in a separate Jenkinsfile, whereas with a freestyle, you need to define the steps for the job in the job definition itself. Looking at the [documentation for freestyle jobs](https://jenkinsci.github.io/job-dsl-plugin/#path/freeStyleJob) should hopefully make that clear. A pipeline type may seem excessive for simple jobs, but the separation of job definition and implementation is preferred.

Please also add an entry to the jobs summary table to briefly describe the job.

### Job Naming Guidelines

* No spaces
* All lowercase
* Try and put the project the job relates to somewhere in the name
* Use `job_type-job_description-job_description2` where hyphens separate categories and underscores separate words within categories
* The `job_type` prefix should be one of the following (or if none of these fit, add something new and add it to this list):
  - Use `ami_build` for jobs that build an AMI (e.g. a slave)
  - Use `deploy` for jobs that do some kind of deployment (e.g. updating the Jenkins environment)
  - Use `docker_build` for building and pushing containers
  - Use `pipeline` for product release pipelines
  - Use `rust_cache_build` for generating 'caches' for Windows or macOS
  - Use `util` for miscellaneous stuff that won't quite fit anywhere else

## License

This SAFE Network repository is dual-licensed under the Modified BSD ([LICENSE-BSD](LICENSE-BSD) https://opensource.org/licenses/BSD-3-Clause) or the MIT license ([LICENSE-MIT](LICENSE-MIT) http://opensource.org/licenses/MIT) at your option.

## Contribution

Copyrights in the SAFE Network are retained by their contributors. No copyright assignment is required to contribute to this project.
