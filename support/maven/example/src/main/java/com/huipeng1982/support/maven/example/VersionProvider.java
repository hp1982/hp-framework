package com.huipeng1982.support.maven.example;

import org.apache.maven.plugin.MojoExecutionException;

public interface VersionProvider {
    /**
     * getVersion
     *
     * @param command
     * @return
     * @throws MojoExecutionException
     */
    String getVersion(String command) throws MojoExecutionException;
}
