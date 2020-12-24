package com.huipeng1982.support.maven.example;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.maven.plugin.MojoExecutionException;

import javax.inject.Named;
import javax.inject.Singleton;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Named
@Singleton
public class RuntimeExecVersionProvider implements VersionProvider {

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(
        10,
        15,
        10,
        TimeUnit.SECONDS,
        new LinkedBlockingQueue<Runnable>(1024),
        new ThreadFactoryBuilder().setNameFormat("maven-pool-%d").build(),
        new ThreadPoolExecutor.AbortPolicy()
    );

    @Override
    public String getVersion(String command) throws MojoExecutionException {

        try {
            StringBuilder builder = new StringBuilder();

            Process process = Runtime.getRuntime().exec(command);

            executor.submit(() ->
                new BufferedReader(new InputStreamReader(process.getInputStream())).lines().forEach(builder::append)
            );

            int exitCode = process.waitFor();

            if (exitCode != 0) {
                throw new MojoExecutionException("Execution of command '" + command + "' failed with exit code: " + exitCode);
            }

            // return the output
            return builder.toString();

        } catch (IOException | InterruptedException e) {
            throw new MojoExecutionException("Execution of command '" + command + "' failed", e);
        }
    }
}
