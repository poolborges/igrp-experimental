/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *  http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package cv.nosicode.igrpframework.maven;

import java.util.Optional;
import java.util.stream.Stream;

import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.repository.ArtifactRepository;
import org.eclipse.aether.resolution.ArtifactRequest;
import org.eclipse.aether.resolution.ArtifactResolutionException;
import org.eclipse.aether.resolution.ArtifactResult;

/**
 *
 * @author pauloborges
 */
public class MyResolveArtifact {

    private RepositorySystem repositorySystem;
    private ArtifactRepository localRepository;
    private java.util.List<ArtifactRepository> remoteRepositories;

    private void Main() {

        //localRepository
    }

    public static void main(String[] args) throws Exception {

        MyResolveArtifact artifactRepository = new MyResolveArtifact();

        //Artifact artifact = new DefaultArtifact( "org.apache.maven.resolver:maven-resolver-util:1.3.3" );
        Optional<Artifact> artifact = artifactRepository.resolveArtifact(new DefaultArtifact("junit", "junit-dep", "", "jar", "4.10"));

        artifact.ifPresent((t) -> {
            System.out.println(artifact + " resolved to  " + t.getFile());
        });
    }

    public Stream<Artifact> resolveArtifactTransitively(Artifact reqArtifact) throws ArtifactResolutionException {

        Stream<Artifact> artifacts = Stream.empty();

        return artifacts;
    }

    public static Artifact createArtifact(String groupId, String artifactId, String version, String type) {
        return new DefaultArtifact(groupId, artifactId, type, version);
    }

    public Optional<Artifact> resolveArtifact(Artifact reqArtifact) throws ArtifactResolutionException {

        RepositorySystem system = Booter.newRepositorySystem();
        RepositorySystemSession session = Booter.newRepositorySystemSession(system);

        ArtifactRequest artifactRequest = new ArtifactRequest();
        artifactRequest.setArtifact(reqArtifact);
        artifactRequest.setRepositories(Booter.newRepositories(system, session));

        ArtifactResult artifactResult = system.resolveArtifact(session, artifactRequest);
        Optional<Artifact> artifact = Optional.of(artifactResult.getArtifact());

        return artifact;
    }
}
