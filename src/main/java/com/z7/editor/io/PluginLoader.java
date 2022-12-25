package com.z7.editor.io;

import com.whatever.editor.api.Plugin;
import com.z7.editor.App;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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
        if (!filePath.endsWith(".jar")){
            throw new IllegalArgumentException("File is not a jar");
        }
        File file = new File(filePath);
        URL url;
        try {
            url = file.toURI().toURL();
        } catch (MalformedURLException e){
            throw new IllegalArgumentException(e);
        }

        ClassLoader pluginClassLoader = Plugin.class.getClassLoader();
        try(URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url}, pluginClassLoader)){
            Manifest manifest = new Manifest(urlClassLoader.findResource("META-INF/MANIFEST.MF").openStream());
            String mainClassName = manifest.getMainAttributes().getValue("Main-Class").replaceAll(".java", "").replaceAll("/", ".");
            Class<?> loadedClass = urlClassLoader.loadClass(mainClassName);
            return (Plugin) loadedClass.getDeclaredConstructor().newInstance();
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
