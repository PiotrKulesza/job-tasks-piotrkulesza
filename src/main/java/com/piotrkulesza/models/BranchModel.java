package com.piotrkulesza.models;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class BranchModel {

    private String branchName;
    private String lastCommitSha;

    public BranchModel() {}

    public BranchModel(String branchName, String lastCommitSha) {
        this.branchName = branchName;
        this.lastCommitSha = lastCommitSha;
    }

    public String getBranchName() {
        return branchName;
    }
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    public String getLastCommitSha() {
        return lastCommitSha;
    }
    public void setLastCommitSha(String lastCommitSha) {
        this.lastCommitSha = lastCommitSha;
    }


}
