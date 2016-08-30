package main.br.uefs.sgad.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;

public class TesteArquivo {
	
	public static void leitor(String caminho) throws IOException{
        BufferedReader leitor = new BufferedReader(new FileReader(caminho));
        String linha = "";
        do{
        	if(linha != null)
        		System.out.println(linha);
        	linha = leitor.readLine();
        }while(linha != null);
        leitor.close();
    }
 
    public static void escritor(String caminho) throws IOException {
        BufferedWriter escritor = new BufferedWriter(new FileWriter(caminho));
        System.out.print("Escreva algo: ");
        String input = Console.readString();
        escritor.append(input + "\n");
        escritor.close();
    }
    
    public static void main(String[] args) throws IOException{
    	escritor("/home/pfplvaz/Documentos/teste.txt");
    	leitor("/home/pfplvaz/Documentos/teste.txt");
    }

}
