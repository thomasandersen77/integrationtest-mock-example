package com.github.andtho;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestDataFileVisitor extends SimpleFileVisitor<File> {

    public static void main(String[] args) {
        ImmutableList.Builder<File> builder = ImmutableList.builder();
        Path path = Paths.get("/home/hota/projects/husbanken/startlan-esoknad/mockdata");

        Files.fileTreeTraverser().breadthFirstTraversal(path.toFile()).forEach(file -> {
            String name = file.getAbsolutePath();
            if(name.endsWith(".json") && !"kommune".contains(name)) {
                builder.add(file);
            }
        });

        builder.build().stream().forEachOrdered(file -> {
            try {
                if(file.getAbsolutePath().contains("kommune")) {
                    return;
                }
                String contents = Files.toString(file, Charsets.UTF_8);
                System.out.println(contents);
            } catch (IOException e) {
                log.error(e.toString(), e);
            }
        });
    }
}
