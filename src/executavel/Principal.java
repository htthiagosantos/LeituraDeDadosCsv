package executavel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entidades.Funcionario;



public class Principal {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Der entrada do caminho do arquivo: ");
		String caminho = sc.nextLine();
		
		try(BufferedReader br = new BufferedReader(new FileReader(caminho))){
			
			List<Funcionario> lista = new ArrayList<>();
			
			String line = br.readLine();
			while(line != null) {
				String[] campos = line.split(",");
				lista.add(new Funcionario(campos[0], campos[1], Double.parseDouble(campos[2])));
				line = br.readLine();
			}
			
			System.out.print("Der entrada no salario: ");
			double salario = sc.nextDouble();
			
			List<String> emails = lista.stream()
					.filter(x -> x.getSalario() > salario)
					.map(x -> x.getEmail())
					.sorted()
					.collect(Collectors.toList());
			
			System.out.println("E-mail de pessoas cujo salário é maior que " + String.format("%.2f", salario) + ":");
			emails.forEach(System.out::println);
			
			double soma = lista.stream()
					.filter(x -> x.getNome().charAt(0) == 'M')
					.map(x -> x.getSalario())
					.reduce(0.0, (x,y) -> x + y);
			
			System.out.println("Soma do salário de pessoas cujo nome começa com 'M': " + String.format("%.2f", soma));
		
		}catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		
		
		sc.close();
	}
}