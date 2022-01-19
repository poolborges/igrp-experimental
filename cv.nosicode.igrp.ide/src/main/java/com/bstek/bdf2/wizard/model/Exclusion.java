package com.bstek.bdf2.wizard.model;

public class Exclusion implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String groupId;
    private String artifactId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }
}
