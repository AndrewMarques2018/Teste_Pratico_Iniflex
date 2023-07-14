package testePraticoIniflex;


import testePraticoIniflex.modelos.Funcao;
import testePraticoIniflex.modelos.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static List<Funcionario> funcionarios = new ArrayList<>();
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");


    public static void main(String[] args) {

        // 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
        adicionaFuncionarios();

        // 3.2 – Remover o funcionário “João” da lista.
        removerFuncionario("João");

        // 3.3 – Imprimir todos os funcionários com todas suas informações
        mostrarFuncionarios();

        // 3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
        aumentarSalario(10);

        // 3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
        Map<String, List<Funcionario>> funcionariosPorFuncao = agruparFuncionariosPorFuncoes();

        // 3.6 – Imprimir os funcionários, agrupados por função.
        mostrarFuncionariosAgrupados(funcionariosPorFuncao);

        // 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        mostrarFuncionariosPorMesAniversario(11, 12);

        // 3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        mostrarFuncionarioMaisVelho();

        // 3.10 – Imprimir a lista de funcionários por ordem alfabética.
        mostrarFuncionariosOrdemAlfabetica();

        // 3.11 – Imprimir o total dos salários dos funcionários.
        imprimirTotalSalarios();

        // 3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
        mostraQuntidadeSalariosPorFuncionario();
    }

    private static void adicionaFuncionarios () {
        funcionarios.add(new Funcionario("Maria", "18/10/2000", new BigDecimal("2009.44"), Funcao.OPERADOR));
        funcionarios.add(new Funcionario("João", "12/05/1990", new BigDecimal("2284.38"), Funcao.OPERADOR));
        funcionarios.add(new Funcionario("Caio", "02/05/1961", new BigDecimal("9836.14"), Funcao.COORDENADOR));
        funcionarios.add(new Funcionario("Miguel", "14/10/1988", new BigDecimal("19119.88"), Funcao.DIRETOR));
        funcionarios.add(new Funcionario("Alice", "05/01/1995", new BigDecimal("2234.68"), Funcao.RECEPCIONISTA));
        funcionarios.add(new Funcionario("Heitor", "19/11/1999", new BigDecimal("1582.72"), Funcao.OPERADOR));
        funcionarios.add(new Funcionario("Arthur", "31/03/1993", new BigDecimal("4071.84"), Funcao.CONTADOR));
        funcionarios.add(new Funcionario("Laura", "08/07/1994", new BigDecimal("3017.45"), Funcao.GERENTE));
        funcionarios.add(new Funcionario("Heloísa", "24/05/2003", new BigDecimal("1606.85"), Funcao.ELETRICISTA));
        funcionarios.add(new Funcionario("Helena", "02/09/1996", new BigDecimal("2799.93"), Funcao.GERENTE));
    }

    private static void mostrarFuncionarios(){
        System.out.println("Lista de funcionários:");
        funcionarios.forEach(System.out::println);
        System.out.println();
    }
    private static void removerFuncionario(String nome){
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals(nome));
    }

    private static void aumentarSalario(double porcentagem){
        BigDecimal aumentoPercentual = new BigDecimal(String.valueOf(1 + porcentagem / 100));

        funcionarios.forEach(funcionario -> {
            BigDecimal novoSalario = funcionario.getSalario().multiply(aumentoPercentual);
            funcionario.setSalario(novoSalario);
        });
    }

    private static Map<String, List<Funcionario>> agruparFuncionariosPorFuncoes() {
        return funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));
    }

    private static void mostrarFuncionariosAgrupados(Map<String, List<Funcionario>> funcionariosPorFuncao) {
        System.out.println("Lista de funcionários agrupados por função:");
        for(String key: funcionariosPorFuncao.keySet()){
            System.out.println(key);
            funcionariosPorFuncao.get(key).forEach(System.out::println);
            System.out.println();
        }
    }

    private static void mostrarFuncionariosPorMesAniversario(Integer... mesesAniversario) {
        System.out.println("Funcionários com aniversário no(s) mês(es): " + Arrays.toString(mesesAniversario));
        List<Funcionario> funcionariosPorMes = funcionarios.stream()
                .filter(funcionario -> Arrays.asList(mesesAniversario).contains(funcionario.getDataNascimento().getMonthValue()))
                .toList();

        for (Funcionario funcionario : funcionariosPorMes) {
            System.out.println(funcionario.getNome() + ": " + funcionario.getDataNascimentoFormatada());
        }
        System.out.println();
    }

    private static void mostrarFuncionarioMaisVelho(){
        System.out.println("Funcionário mais velho:");
        Comparator<Funcionario> comparadorDataNascimento = Comparator.comparing(Funcionario::getDataNascimento);
        Funcionario funcionarioMaisVelho = funcionarios.stream()
                .min(comparadorDataNascimento)
                .orElse(null);

        if (funcionarioMaisVelho != null) {
            System.out.println("Nome: " + funcionarioMaisVelho.getNome());
            System.out.println("Idade: " + calcularIdade(funcionarioMaisVelho.getDataNascimento()));
        } else {
            System.out.println("Não há funcionários na lista.");
        }
        System.out.println();
    }

    private static int calcularIdade(LocalDate dataNascimento) {
        LocalDate hoje = LocalDate.now();
        int idade = hoje.getYear() - dataNascimento.getYear();
        if (hoje.getMonthValue() < dataNascimento.getMonthValue() ||
                (hoje.getMonthValue() == dataNascimento.getMonthValue() && hoje.getDayOfMonth() < dataNascimento.getDayOfMonth())) {
            idade--;
        }
        return idade;
    }

    private static void mostrarFuncionariosOrdemAlfabetica() {
        System.out.println("Funcionários em ordem alfabética:");
        Comparator<Funcionario> comparadorNome = Comparator.comparing(Funcionario::getNome);
        funcionarios.sort(comparadorNome);
        funcionarios.forEach(System.out::println);
        System.out.println();
    }

    private static void imprimirTotalSalarios() {
        System.out.println("O total dos salários dos funcionários:");

        BigDecimal salarioTotal = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Locale locale = new Locale("pt", "BR");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        String salarioTotalFormatado = formatter.format(salarioTotal);

        System.out.println(salarioTotalFormatado);
        System.out.println();
    }

    private static void mostraQuntidadeSalariosPorFuncionario() {
        System.out.println("Quantos salários mínimos ganha cada funcionário:");

        for (Funcionario funcionario : funcionarios) {
            BigDecimal salario = funcionario.getSalario();
            BigDecimal salariosMinimos = salario.divide(SALARIO_MINIMO, 2, RoundingMode.HALF_UP);
            System.out.println(funcionario.getNome() + ": " + salariosMinimos + " salários mínimos");
        }
    }
}
