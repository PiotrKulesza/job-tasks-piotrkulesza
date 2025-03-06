package com.piotrkulesza.ghhandler;

import com.piotrkulesza.models.BranchModel;
import com.piotrkulesza.models.ErrorModel;
import com.piotrkulesza.models.RepositoryModel;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.resteasy.reactive.RestResponse;
import org.kohsuke.github.*;
import org.jboss.logging.Logger;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.stream.Collectors;

@ApplicationScoped
public class GHService {

    private static final Logger LOG = Logger.getLogger(String.valueOf(GHService.class));

    private final ExecutorService executor = Executors.newFixedThreadPool(4); // Worker thread pool


    public Uni<RestResponse<Object>> getRepositories(String username) {
        return Uni.createFrom().item(() -> {
            try {
                GitHub github = GitHub.connectAnonymously();
                GHUser user;
                try {
                    user = github.getUser(username);
                }  catch (GHFileNotFoundException e) {
                    ErrorModel error = new ErrorModel(404, "User " + username + " not found.");
                    LOG.error("Returning error response: " + error.getMessage());
                    return RestResponse.<Object>status(RestResponse.Status.NOT_FOUND, error);
                }

                List<RepositoryModel> repos = user.getRepositories().values().stream()
                        .map(repo -> new RepositoryModel(
                                repo.getName(),
                                repo.getOwnerName(),
                                getBranches(repo)
                        ))
                        .collect(Collectors.toList());
                return RestResponse.<Object>ok(repos);

            } catch (IOException e) {
                ErrorModel error =  new ErrorModel(404, "Failed to fetch repositories.");
                LOG.error("Returning error response: " + error.getMessage());
                return RestResponse.<Object>status(RestResponse.Status.INTERNAL_SERVER_ERROR, error);
            }


        }).runSubscriptionOn(executor);

    }

    private List<BranchModel> getBranches(GHRepository repo) {

        try {

            return repo.getBranches().entrySet().stream()
                    .map(entry -> new BranchModel(entry.getKey(), entry.getValue().getSHA1()))
                    .collect(Collectors.toList());

        } catch  (IOException e) {
            throw new RuntimeException("Failed to fetch branches for repo: " + repo.getName(), e);
        }



    }
}




