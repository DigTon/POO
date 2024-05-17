import java.util.Objects;

public class Usuario {
    // Atributos
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String dataNascimento;
    private String sexo;
    private int tipoDiabetes;
    private String dataDiagnostico;
    private int nivelAcucarSangue;
    private float peso;
    private float altura;
    private String pressaoArterial;
    private String historicoMedico;
    private String medicamentos;
    private String alergias;
    private String senha;

    // Construtor
    public Usuario() {
        this.id = 0;
        this.nome = "";
        this.cpf = "";
        this.email = "";
        this.senha = "";
        this.dataNascimento = ""; // Inicialize dataNascimento
        this.sexo = "";
        this.tipoDiabetes = 0;
        this.dataDiagnostico = "";
        this.nivelAcucarSangue = 0;
        this.peso = 0.0f;
        this.altura = 0.0f;
        this.pressaoArterial = "";
        this.historicoMedico = "";
        this.medicamentos = "";
        this.alergias = "";
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        if (dataNascimento != null && !dataNascimento.isEmpty()) {
            String[] parts = dataNascimento.split("/");

            if (parts.length >= 3) {
                this.dataNascimento = parts[2] + "-" + parts[1] + "-" + parts[0];
            } else {
                // Faça algo para tratar o erro, como exibir uma mensagem de erro
                System.err.println("Formato de data inválido. A data deve estar no formato DD/MM/AAAA.");
            }
        }
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getTipoDiabetes() {
        return tipoDiabetes;
    }

    public void setTipoDiabetes(String tipoDiabetes) {
        this.tipoDiabetes = Integer.parseInt(tipoDiabetes);
    }

    public String getDataDiagnostico() {
        return dataDiagnostico;
    }

    public void setDataDiagnostico(String dataDiagnostico) {
        if (dataDiagnostico != null && !dataDiagnostico.isEmpty()) {
            String[] parts = dataDiagnostico.split("/");

            if (parts.length >= 3) {
                this.dataDiagnostico= parts[2] + "-" + parts[1] + "-" + parts[0];
            } else {
                System.err.println("Formato de data inválido. A data deve estar no formato DD/MM/AAAA.");
            }
        }
    }

    public int getNivelAcucarSangue() {
        return nivelAcucarSangue;
    }

    public void setNivelAcucarSangue(int nivelAcucarSangue) {
        this.nivelAcucarSangue = nivelAcucarSangue;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getPressaoArterial() {
        return pressaoArterial;
    }

    public void setPressaoArterial(String pressaoArterial) {
        this.pressaoArterial = pressaoArterial;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getHistoricoMedico() {
        return historicoMedico;
    }

    public void setHistoricoMedico(String historicoMedico) {
        this.historicoMedico = historicoMedico;
    }

    // Outros métodos
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(senha);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", sexo='" + sexo + '\'' +
                ", tipoDiabetes=" + tipoDiabetes +
                ", dataDiagnostico='" + dataDiagnostico + '\'' +
                ", nivelAcucarSangue=" + nivelAcucarSangue +
                ", peso=" + peso +
                ", altura=" + altura +
                ", pressaoArterial='" + pressaoArterial + '\'' +
                ", historicoMedico='" + historicoMedico + '\'' +
                ", medicamentos='" + medicamentos + '\'' +
                ", alergias='" + alergias + '\'' +
                '}';
    }
}