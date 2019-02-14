package ru.alazarev.socket.fileManager;
//        Перед реализацией в коде. Составить каркас приложения на интерфейсах. С описанием.
//        1. Разработать клиент серверное приложение на сокетах.
//        2. Серверная часть должна реализовывать следующее апи
//        - получить список корневого каталога. Корневой каталог задается при запуске сервера.
//        - перейти в подкаталог.
//        - спуститься в родительский каталог
//        - скачать файл
//        - загрузить файл.
//        3. Клиент должен это апи уметь вызывать.
//
//        4. настройки портов и адреса считывать с app.properties


import com.sun.xml.internal.ws.commons.xmlutil.Converter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class FileManagerServer {
    private final String root;
    private final Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String currentCatalog;

    public FileManagerServer(String root, int port) throws IOException {
        this.root = root;
        this.currentCatalog = root;
        socket = new ServerSocket(port).accept();
    }

    public List<String> chk(String inputString) throws IOException {
        String[] splitInput = inputString.split(" ");
        List<String> result = new ArrayList<>();
        switch (splitInput[0]) {
            case ("child"): {
                result = getChildren();
                break;
            }
            case ("down"): {
                result = followChild(splitInput[1]);
                break;
            }
            case ("root"): {
                result = getRoot();
                break;
            }
            case ("download"): {
                result = getFile(splitInput[1]);
                break;
            }
            case ("upload"): {
                loadFile(new File(splitInput[1]));
                break;
            }
            default: {
                result = new ArrayList<>();
                result.add("Unknown command");
                break;
            }
        }
        return result;
    }

    public void start() {
        try {
            System.out.println("Server started.");
            out = new PrintWriter(this.socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            String ask;
            do {
                System.out.println("Wait command ...");
                ask = in.readLine();
                for (String s : chk(ask)) {
                    this.out.println(s);
                }
                this.out.println("End translation._!");
            } while (!("exit".equals(ask)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Получить список корневого каталога.
     *
     * @return Список корневого каталога.
     */
    public List<String> getChildren() {
        List<String> result = new ArrayList<>();
        Arrays.asList(new File(this.currentCatalog).listFiles())
                .stream().forEach(f -> result.add(f.getPath()));
        return result;
    }

    /**
     * Перейти в подкаталог.
     */
    public List<String> followChild(String catalog) {
        List<String> result = new ArrayList<>();
        String path = this.currentCatalog + "\\" + catalog;
        if (new File(path).exists()) {
            this.currentCatalog = new File(path).getPath();
            result.add(currentCatalog);
        }
        return result;
    }

    /**
     * Спуститься в родительский каталог
     */
    public List<String> getRoot() {
        List<String> result = new ArrayList<>();
        if (new File(this.root).exists()) {
            this.currentCatalog = this.root;
            result.add(currentCatalog);
        }
        return result;
    }

    /**
     * Скачать файл.
     *
     * @return
     */
    public List<String> getFile(String fileName) throws IOException {
        List<String> result = new ArrayList<>();
        File currentFile = new File(this.currentCatalog + "\\" + fileName);
        byte[] file = new byte[(int) currentFile.length()];
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF(String.valueOf(currentFile.length()));
        FileInputStream fis = new FileInputStream(currentFile);
        int count;
        while ((count = fis.read(file)) != -1) {
            dataOutputStream.write(file, 0, count);
        }
        result.add("Download file " + fileName + " complete.");
        return result;
    }

    /**
     * Загрузить файл.
     *
     * @param file
     */
    public boolean loadFile(File file) throws IOException {
        boolean result = false;
        String currentPath = this.currentCatalog + "\\" + file.getName();
        if (!new File(currentPath).exists()) {
            byte data[] = new byte[(int) file.length()];
            FileInputStream in = new FileInputStream(file);
            in.read(data);
            FileOutputStream out = new FileOutputStream(currentPath);
            out.write(data);
            out.close();
            new File(currentPath);
            result = true;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        FileManagerServer server = new FileManagerServer("C:\\chat", 5000);
        server.start();
    }
}
