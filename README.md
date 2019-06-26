# Jenkins Jobs

This repository houses the definitions for jobs on our Jenkins instance. They are defined using the [Job DSL](https://jenkinsci.github.io/job-dsl-plugin/). Each application we're building will have its Jenkinsfile defined in its own repository, but we'll also use this repository to define Jenkinsfiles for arbitrary utility jobs that don't belong to any particular application repo.

## Jobs Summary

| Name                                          | Description                                                                                                                                                                                                                                    |
| ----                                          | -----------                                                                                                                                                                                                                                    |
| docker_build-safe_client_libs_build_container | Constructs a new build container for SCL. Currently this is using the experimental branch by default. It can be run manually, but soon I'll be hoping to change the SCL build process to trigger this job at the end of a merged branch build. |

## Contributing

### Creating New Jobs

When adding a new job, add the job definition to the job_dsl_seed.groovy file, then add a Jenkinsfile under a folder which should be named the same as the job name. The type of these jobs should generally be a pipeline, rather than a multibranch pipeline or a freestyle job. The reason for using the pipeline type rather than freestyle is because with a pipeline you can define what the job does in a separate Jenkinsfile, whereas with a freestyle, you need to define the steps for the job in the job definition itself. Looking at the [documentation for freestyle jobs](https://jenkinsci.github.io/job-dsl-plugin/#path/freeStyleJob) should hopefully make that clear. A pipeline type may seem excessive for these types of jobs, but the separation of job definition and implemenation is preferred.

### Job Naming Guidelines

* No spaces
* All lowercase
* Try and put the project the job relates to somewhere in the name
* Use `job_type-job_description-job_description2` where hyphens separate categories and underscores separate words within categories
* The `job_type` prefix should be one of the following (or if none of these fit, add something new and add it to this list):
  - Use `deploy` for jobs that do some kind of deployment (e.g. updating the Jenkins environment)
  - Use `docker_build` for building and pushing containers
  - Use `pipeline` for product release pipelines
  - Use `util` for miscellaneous stuff that won't quite fit anywhere else

## License

This SAFE Network repository is dual-licensed under the Modified BSD ([LICENSE-BSD](LICENSE-BSD) https://opensource.org/licenses/BSD-3-Clause) or the MIT license ([LICENSE-MIT](LICENSE-MIT) http://opensource.org/licenses/MIT) at your option.

## Contribution

Copyrights in the SAFE Network are retained by their contributors. No copyright assignment is required to contribute to this project.
