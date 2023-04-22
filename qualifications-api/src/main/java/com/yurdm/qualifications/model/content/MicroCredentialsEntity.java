package com.yurdm.qualifications.model.content;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yurdm.qualifications.model.knowledge.Competency;

import java.util.HashSet;
import java.util.List;

abstract public class MicroCredentialsEntity {
    protected abstract List<Competency> getCompetencies();
    @JsonProperty("micro_credentials")
    public List<Competency> getMicroCredentials() {
        var originalList = getCompetencies();
        var unsorted = new HashSet<Competency>();

        for (var competency : originalList) {
            var temp = competency;
            unsorted.add(temp);

            while (temp.getParentCompetency() != null) {
                unsorted.add(temp.getParentCompetency());
                temp = temp.getParentCompetency();
            }
        }

        var result = unsorted.stream().filter(item -> item.getParentCompetency() == null).toList();

        for (var competency : result) {
            if (!competency.getMicroCompetencies().isEmpty()) {
                for (var micro : competency.getMicroCompetencies()) {
                    micro.getMicroCompetencies().removeIf(item -> !unsorted.contains(item));
                }
            }

            competency.getMicroCompetencies().removeIf(microCredential -> !unsorted.contains(microCredential));
        }

        return result;
    }
}
