import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Connection;


public class UsuarioGUI extends JFrame {


    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtEmail;
    private JTextField txtDataNascimento;
    private JComboBox<String> cbSexo;
    private JTextField txtTipoDiabetes;
    private JTextField txtDataDiagnostico;
    private JTextField txtNivelAcucarSangue;
    private JTextField txtPeso;
    private JTextField txtAltura;
    private JTextField txtPressaoArterial;
    private JTextArea txtHistoricoMedico;
    private JTextArea txtMedicamentos;
    private JTextArea txtAlergias;
    private JPasswordField txtSenha;
    private JButton btnCriar;
    private JButton btnLimpar;

    private Connection conexao;

    public UsuarioGUI() {
        super("Criar Usuário");


        try {
            this.conexao = ConexaoBanco.obterConexao();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados.");
            e.printStackTrace();
        }



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(248, 249, 250));

        Color azulPrincipal = new Color(0, 123, 255);
        Color cinzaClaro = new Color(248, 249, 250);
        Color cinzaEscuro = new Color(34, 34, 34);
        Color branco = Color.WHITE;

        JPanel painel = new JPanel(new GridLayout(16, 2, 10, 10));

        painel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painel.add(txtNome);

        painel.add(new JLabel("CPF:"));
        txtCpf = new JTextField();
        painel.add(txtCpf);

        painel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        painel.add(txtEmail);

        painel.add(new JLabel("Data de Nascimento (DD/MM/AAAA):"));
        txtDataNascimento = new JTextField();
        painel.add(txtDataNascimento);

        painel.add(new JLabel("Sexo:"));
        cbSexo = new JComboBox<>(new String[]{"M", "F"});
        painel.add(cbSexo);

        painel.add(new JLabel("Tipo de Diabetes:"));
        txtTipoDiabetes = new JTextField();
        painel.add(txtTipoDiabetes);

        painel.add(new JLabel("Data de Diagnóstico (DD/MM/AAAA):"));
        txtDataDiagnostico = new JTextField();
        painel.add(txtDataDiagnostico);

        painel.add(new JLabel("Nível de Açúcar no Sangue:"));
        txtNivelAcucarSangue = new JTextField();
        painel.add(txtNivelAcucarSangue);

        painel.add(new JLabel("Peso (kg):"));
        txtPeso = new JTextField();
        painel.add(txtPeso);

        painel.add(new JLabel("Altura (m):"));
        txtAltura = new JTextField();
        painel.add(txtAltura);

        painel.add(new JLabel("Pressão Arterial:"));
        txtPressaoArterial = new JTextField();
        painel.add(txtPressaoArterial);

        painel.add(new JLabel("Histórico Médico:"));
        txtHistoricoMedico = new JTextArea(3, 20);
        painel.add(new JScrollPane(txtHistoricoMedico));

        painel.add(new JLabel("Medicamentos:"));
        txtMedicamentos = new JTextArea(3, 20);
        painel.add(new JScrollPane(txtMedicamentos));

        painel.add(new JLabel("Alergias:"));
        txtAlergias = new JTextArea(3, 20);
        painel.add(new JScrollPane(txtAlergias));

        painel.add(new JLabel("Senha:"));
        txtSenha = new JPasswordField();
        painel.add(txtSenha);

        btnCriar = new JButton("Criar Usuário");
        btnCriar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarUsuario();
            }
        });
        painel.add(btnCriar);

        btnLimpar = new JButton("Limpar Dados");
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });
        painel.add(btnLimpar);

        add(painel);

        setVisible(true);
    }

    private void criarUsuario() {
        UsuarioDAO dao = new UsuarioDAO();
        try {
            Usuario usuario = new Usuario();
            usuario.setNome(txtNome.getText());
            usuario.setCpf(txtCpf.getText());
            usuario.setEmail(txtEmail.getText());
            usuario.setDataNascimento(txtDataNascimento.getText());
            usuario.setSexo(cbSexo.getSelectedItem().toString());
            usuario.setTipoDiabetes(String.valueOf(Integer.parseInt(txtTipoDiabetes.getText())));
            usuario.setDataDiagnostico(txtDataDiagnostico.getText());
            usuario.setNivelAcucarSangue(Integer.parseInt(txtNivelAcucarSangue.getText()));
            usuario.setPeso(Float.parseFloat(txtPeso.getText()));
            usuario.setAltura(Float.parseFloat(txtAltura.getText()));
            usuario.setPressaoArterial(txtPressaoArterial.getText());
            usuario.setHistoricoMedico(txtHistoricoMedico.getText());
            usuario.setMedicamentos(txtMedicamentos.getText());
            usuario.setAlergias(txtAlergias.getText());
            usuario.setSenha(new String(txtSenha.getPassword()));

            dao.salvar(usuario);
            dao.fecharConexao();

            JOptionPane.showMessageDialog(this, "Usuário criado com sucesso!");
            limparCampos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao criar usuário: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro: Insira dados numéricos válidos para Tipo de Diabetes, Nível de Açúcar no Sangue, Peso e Altura.");
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtCpf.setText("");
        txtEmail.setText("");
        txtDataNascimento.setText("");
        cbSexo.setSelectedItem("");
        txtTipoDiabetes.setText("");
        txtDataDiagnostico.setText("");
        txtNivelAcucarSangue.setText("");
        txtPeso.setText("");
        txtAltura.setText("");
        txtPressaoArterial.setText("");
        txtHistoricoMedico.setText("");
        txtMedicamentos.setText("");
        txtAlergias.setText("");
        txtSenha.setText("");
    }

    public static void main(String[] args) {
        new UsuarioGUI();
    }
}