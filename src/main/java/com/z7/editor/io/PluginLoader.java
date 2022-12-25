package com.z7.editor.io;

import com.z7.editor.App;
import com.z7.editor.api.Plugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.stream.Collectors;

public class PluginLoader {

    private static PluginLoader instance;

    private PluginLoader(){}

    public static synchronized PluginLoader getInstance(){
        if (Objects.isNull(instance)){
            instance = new PluginLoader();
        }
        return instance;
    }

    public List<Plugin> loadAll() {
        String path = App.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String decodedPath = URLDecoder.decode(path, StandardCharsets.UTF_8)
                .replaceAll(new File(path).getName(), "/plugins");

        File folder = new File(decodedPath);
        File[] files = folder.listFiles();
        if (files == null) {
            return new ArrayList<>();
        }
        return Arrays.stream(files).map(file -> load(file.toString())).collect(Collectors.toList());
    }

    public Plugin load(String filePath) {
        Plugin result = null;
        if (!filePath.endsWith(".jar")){
            throw new IllegalArgumentException("File is not a jar");
        }

        ClassLoader pluginClassLoader = Plugin.class.getClassLoader();
        try (JarFile file = new JarFile(filePath);
             URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{new URL("jar:file:" + filePath + "!/")}, pluginClassLoader)) {
            Enumeration<JarEntry> entries = file.entries();

            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (entry.isDirectory() || !entry.getName().endsWith(".class") || entry.getName().equals("module-info.class")){
                    continue;
                }
                String className = entry.getName().replaceAll(".class", "").replaceAll("/", ".");
                Class<?> clazz = urlClassLoader.loadClass(className);
                if (clazz.getDeclaredConstructor().newInstance() instanceof Plugin plugin){
                    result = plugin;
                }
            }
            if (Objects.nonNull(result)){
                return result;
            } else {
                throw new IllegalArgumentException("Plugin class is not present");
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("File is not found, Manifest is not found or Manifest does not contain Main-Class attribute");
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Main-Class attribute is invalid");
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Main class is not a Plugin");
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
