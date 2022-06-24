package br.com.api.crud.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static List<String> incluir(String path) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        List<String> linhas = new ArrayList<>();
        String linha = "";
        while (true) {
            if (linha != null) {
                linhas.add(linha);
            } else
                break;
            linha = buffRead.readLine();
        }
        buffRead.close();
        return linhas;
    }

    public static void escritor(String path, List<String> linhas) throws IOException {
        FileWriter writer = new FileWriter(path);
        PrintWriter saida = new PrintWriter(writer);
        for (String texto : linhas) {
            saida.write(texto+"\n");
        }
        writer.close();
    }

    public static void main(String[] args) throws IOException {

        String path = "C:/Users/Cleber/Desktop/arquivos/teste.txt";
        String pathBkp = "C:/Users/Cleber/Desktop/arquivos/testeBKP.txt";

        if (verificaAlteracao(path, pathBkp)){
            System.out.println("Nao teve alteracao!!");
        }else {
            adicionarLinhas(pathBkp, "Chule");
            System.out.println("Lista local atualizada com sucesso!!");
        }
    }

    public static boolean verificaAlteracao(String path, String PathUltimoBkp) throws IOException {
        List<String> listaAtual = incluir(path);
        List<String> listaBkp = incluir(PathUltimoBkp);

        return listaAtual.equals(listaBkp) ? true : false;

    }

    public static void adicionarLinhas(String path, String linha) throws IOException {
            List<String> lista = incluir(path);
            lista.add(linha);
            List<String> lista2 = new ArrayList<>();

            lista.forEach(l -> {
                if (!l.isEmpty()) {
                    lista2.add(l);
                }
            });

            escritor(path, lista2);
    }

    public static void removeLinhas(String path, String linha) throws IOException {
            List<String> lista = incluir(path);
            lista.remove(linha);
            List<String> lista2 = new ArrayList<>();
            lista.forEach(l -> {
                if (!l.isEmpty()) {
                    lista2.add(l);
                }
            });
            escritor(path, lista2);
    }
}