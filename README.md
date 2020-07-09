# RxHub4k
A reactive GitHub client built for JVM apps that wraps the GitHub v4 graphql API and the [apollo-android](https://github.com/apollographql/apollo-android) graphql client.

![Build](https://github.com/rxhub4k/rxhub4k/workflows/CI/badge.svg)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)

## Features
**rxhub4k** is a reactive client for GitHub and, therefore, returns a stream for all requests.

### Reactive Library Support
* [Reactor](https://projectreactor.io/)
* [Kotlin Flows](https://kotlinlang.org/docs/reference/coroutines/flow.html#flows)
* [JavaRx](https://github.com/ReactiveX/RxJava)

### Roadmap
In its infancy, the goal is to provide basic **Pull Request READ** support.  Upon covering the Pull Request READ use case, the next step is to move into Issue READ or support mutation.  Depending on library adoption, the roadmap will adjust to community need as this library lives to serve its users.

## Getting Started
### Prerequisites

### Importing the Library
#### Gradle
From Maven Central:

`Coming soon!`

### Auth
RxHub4k authenticates with the GitHub API with a GitHub-issued oauth token.  To configure the client for authentication, simply pass the token to the client build function as shown in the **Configuring the Client** section.

### Configuring the Client
```kotlin
val rxHubClient = RxHubClient.build("GitHub OAuth Token")
```
###Schema
RxHub4k compiles schema types from the latest version of the GitHub schema at build time.  The following strongly-typed queries are available:

### Making a Request
#### RxJava
```kotlin
val query = RepositoryPullRequestQuery(
    "rxhub4k",
    "rxhub4k",
    Input.optional(4) ,
    Input.optional(listOf(PullRequestState.MERGED))
)

rxHubClient.rx3query(query)
    .map { data -> data?.repository?.pullRequests?.nodes?.map { it } }
    .subscribe { it?.forEach(::println) }
```
#### Reactor
```kotlin
val query = RepositoryPullRequestQuery(
    "rxhub4k",
    "rxhub4k",
    Input.optional(4) ,
    Input.optional(listOf(PullRequestState.MERGED))
)

rxHubClient.reactorQuery(query)
    .map { data -> data?.repository?.pullRequests?.nodes?.map { it } }
    .subscribe { it?.forEach(::println) }
```

## Contributing
The project is not quite ready for contribution just yet.  Check back soon!

When we open up for contributions, the following will be helpful in getting started:
* We adhere to a strict [Code of Conduct](https://github.com/rxhub4k/rxhub4k/blob/master/CODE_OF_CONDUCT.md) intended to create a safe space for ALL to contribute.  You are welcome here.
* For contribution guidelines, please see [CONTRIBUTING](https://github.com/rxhub4k/rxhub4k/blob/master/CONTRIBUTING.md) for details regarding communication, pull requests, style, and test coverage.

### Building
To build from the source:
1. Clone the repository.
1. Generate the schema:
    ```
    ./gradlew downloadApolloSchema \
      -Pcom.apollographql.apollo.endpoint=https://api.github.com/graphql \
      -Pcom.apollographql.apollo.schema=src/main/graphql/com/nevinsjr/rxhubk/schema.json \
      "-Pcom.apollographql.apollo.headers=Authorization=Bearer <Your GitHub Access token here>"
    ```
1. Build:
    ```
    ./gradlew build
    ```

## Versioning
We subscribe to [Semantic Versioning](https://semver.org/) for versioning. For the versions available, please see the [releases](https://github.com/rxhub4k/rxhub4k/releases) on this repository.

## Authors
* John Nevins - _Initial Work_ - [rxhub4k](https://github.com/rxhub4k)

Please also see our list of amazing [contributors](https://github.com/rxhub4k/rxhub4k/people).

## License
[Apache-2.0](https://github.com/rxhub4k/rxhub4k/blob/master/LICENSE)

## Acknowledgements
* The [apollo-android](https://github.com/apollographql/apollo-android) project made this a very simple undertaking.
