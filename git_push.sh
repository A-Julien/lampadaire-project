#!/usr/bin/env bash

setup_git() {
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "Travis CI"
}
merge_branch() {
    git config --add remote.origin.fetch +refs/heads/*:refs/remotes/origin/* || exit
    git fetch --all || exit
    git stash || exit
    git checkout recette || exit
    git merge --no-ff "$TRAVIS_COMMIT" || exit
    git push https://${GH_TOKEN}@github.com/A-Julien/lampadaire-project.git
}

setup_git
merge_branch
