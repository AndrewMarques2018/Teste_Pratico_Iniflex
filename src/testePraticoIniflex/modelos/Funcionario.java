package testePraticoIniflex.modelos;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Locale;

public class Funcionario extends Pessoa{

    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, String dataNascimento, BigDecimal salario, Funcao funcao) {
        setNome(nome);
        setDataNascimento(dataNascimento);
        setSalario(salario);
        setFuncao(funcao.getDescricao());
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getSalarioFormatado() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');

        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);
        return decimalFormat.format(salario);
    }

    public void setSalario(double salario) {
        this.salario = new BigDecimal(Double.toString(salario));
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                " nome='" + getNome() + '\'' +
                ", dataNascimento=" + getDataNascimentoFormatada() +
                ", salario=" + getSalarioFormatado() +
                ", funcao='" + getFuncao() + '\'' +
                '}';
    }
}
