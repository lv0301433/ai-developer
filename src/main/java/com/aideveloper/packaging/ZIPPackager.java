package com.aideveloper.packaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.nio.file.*;
import java.util.zip.*;

public class ZIPPackager {
    private static final Logger logger = LoggerFactory.getLogger(ZIPPackager.class);

    public String packageProject(String projectPath, String outputName) throws IOException {
        logger.info("Packaging project: {}", projectPath);
        String zipPath = outputName + ".zip";
        
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath))) {
            Path projectDir = Paths.get(projectPath);
            Files.walk(projectDir)
                    .filter(Files::isRegularFile)
                    .forEach(file -> addToZip(zos, projectDir, file));
        }
        
        logger.info("Project packaged successfully: {}", zipPath);
        return zipPath;
    }

    private void addToZip(ZipOutputStream zos, Path basePath, Path file) {
        try {
            String zipEntryName = basePath.relativize(file).toString();
            ZipEntry zipEntry = new ZipEntry(zipEntryName);
            zos.putNextEntry(zipEntry);
            Files.copy(file, zos);
            zos.closeEntry();
        } catch (IOException e) {
            logger.error("Error adding file to ZIP: {}", file, e);
        }
    }

    public void unpackProject(String zipPath, String outputDir) throws IOException {
        logger.info("Unpacking ZIP: {}", zipPath);
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipPath))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                String filePath = outputDir + File.separator + entry.getName();
                if (!entry.isDirectory()) {
                    new File(filePath).getParentFile().mkdirs();
                    FileOutputStream fos = new FileOutputStream(filePath);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                }
                zis.closeEntry();
            }
        }
        logger.info("ZIP unpacked successfully to: {}", outputDir);
    }
}