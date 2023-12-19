## Building and working with the codebase

Details for building from source and working with the codebase are provided in the [building and working with the code base](docs/building.md) guide.

### Finding something to work on

If you would like to contribute to ssl-handshake-debugger, but are not sure exactly what to work on, you can find a number of open
issues that are awaiting contributions in  [issues](https://github.com/gabrielpadilh4/ssl-handshake-debugger/issues)https://github.com/gabrielpadilh4/ssl-handshake-debugger/issues).

### Create an issue

Take your time to write a proper issue including a good summary and description. 

Remember this may be the first thing a reviewer of your PR will look at to get an idea of what you are proposing 
and it will also be used by the community in the future to find about what new features and enhancements are included in 
new releases.

### Implementing

Details for building from source and working with the codebase are provided in the 
[building and working with the code base](docs/building.md) guide.

Do not format or refactor code that is not directly related to your contribution. If you do this it will significantly
increase our effort in reviewing your PR. If you have a strong need to refactor code then submit a separate PR for the
refactoring.

### Submitting your PR

When preparing your PR make sure you have a single commit and your branch is rebased on the main branch from the 
project repository.

This means use the `git rebase` command and not `git pull` when integrating changes from main to your branch. See
[Git Documentation](https://git-scm.com/book/en/v2/Git-Branching-Rebasing) for more details.

We require that you squash to a single commit. You can do this with the `git rebase -i HEAD~X` command where X
is the number of commits you want to squash. See the [Git Documentation](https://git-scm.com/book/en/v2/Git-Tools-Rewriting-History)
for more details.

The above helps us review your PR and also makes it easier for us to maintain the repository. It is also required by
our automatic merging process. 

Please, also provide a good description [commit message, with a link to the issue](#commit-messages-and-issue-linking).
We also require that the commit message includes a link to the issue ([linking a pull request to an issue](https://docs.github.com/en/issues/tracking-your-work-with-issues/linking-a-pull-request-to-an-issue)).

### Commit messages and issue linking

The format for a commit message should look like:

```
A brief descriptive summary

Optionally, more details around how it was implemented

Closes #1234
``` 

The very last part of the commit message should be a link to the GitHub issue, when done correctly GitHub will automatically link the issue with the PR. There are 3 alternatives provided by GitHub here:

* Closes: Issues in the same repository
* Fixes: Issues in a different repository (this shouldn't be used, as issues should be created in the correct repository instead)
* Resolves: When multiple issues are resolved (this should be avoided)

Although, GitHub allows alternatives (close, closed, fix, fixed), please only use the above formats.

Creating multi line commit messages with `git` can be done with:

```
git commit -m "Summary" -m "Optional description" -m "Closes #1234"
```

Alternatively, `shift + enter` can be used to add line breaks:

```
$ git commit -m "Summary
> 
> Optional description
> 
> Closes #1234"
```

For more information linking PRs to issues refer to the [GitHub Documentation](https://docs.github.com/en/issues/tracking-your-work-with-issues/linking-a-pull-request-to-an-issue).
