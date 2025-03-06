package com.piotrkulesza.models;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.List;

@RegisterForReflection
public class RepositoryModel {
    private String repositoryName;
    private String ownerLogin;
    private List<BranchModel> branches;

    //public RepositoryModel(String repositoryName, String ownerLogin, List<BranchInfo> branches) {
    public RepositoryModel(String repositoryName, String ownerLogin, List<BranchModel> branches){
        this.repositoryName = repositoryName;
        this.ownerLogin = ownerLogin;
        this.branches = branches;
    }

    public RepositoryModel(){}

    public String getRepositoryName() {
        return repositoryName;
    }
    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }
    public String getOwnerLogin() {
        return ownerLogin;
    }
    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }
    public List<BranchModel> getBranches() {
        return branches;
    }
    public void setBranches(List<BranchModel> branches) {
        this.branches = branches;
    }


}
