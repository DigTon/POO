import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UsuarioGUI extends JFrame {

    // Cores
    private Color primaryColor = new Color(0, 123, 255); // Azul
    private Color secondaryColor = new Color(248, 249, 250); // Cinza
    private Color darkTextColor = new Color(34, 34, 34); // Cinza
    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtEmail;
    private JFormattedTextField txtDataNascimento;
    private JComboBox<String> cbSexo;
    private JComboBox<String> cbTipoDiabetes;
    private JFormattedTextField txtDataDiagnostico;
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
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 800);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(secondaryColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); // Espaçamento entre componentes

        // Título
        JLabel lblTitulo = new JLabel("Criar Usuário");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(primaryColor);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        painel.add(lblTitulo, gbc);

        adicionarCampo(painel, "Nome:", txtNome = new JTextField(), 1);


        adicionarCampo(painel, "CPF:", txtCpf = new JTextField(), 2);

        adicionarCampo(painel, "Email:", txtEmail = new JTextField(), 3);

        // Data de Nascimento
        adicionarCampo(painel, "Data de Nascimento (DD/MM/AAAA):",
                txtDataNascimento = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy")), 4);

        // Sexo
        adicionarCampo(painel, "Sexo:", cbSexo = new JComboBox<>(new String[]{"M", "F"}), 5);

        // Tipo de Diabetes
        adicionarCampo(painel, "Tipo de Diabetes:",
                cbTipoDiabetes = new JComboBox<>(new String[]{"Tipo 1", "Tipo 2", "Gestacional", "Outros"}), 6);

        // Data de Diagnóstico
        adicionarCampo(painel, "Data de Diagnóstico (DD/MM/AAAA):",
                txtDataDiagnostico = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy")), 7);

        // Nível de Açúcar no Sangue
        adicionarCampo(painel, "Nível de Açúcar no Sangue (mg/dL):",
                txtNivelAcucarSangue = new JTextField(), 8);

        // Peso
        adicionarCampo(painel, "Peso (kg):", txtPeso = new JTextField(), 9);

        // Altura
        adicionarCampo(painel, "Altura (m):", txtAltura = new JTextField(), 10);

        // Pressão Arterial
        adicionarCampo(painel, "Pressão Arterial (mmHg):", txtPressaoArterial = new JTextField(), 11);

        // Histórico Médico
        adicionarCampoTextArea(painel, "Histórico Médico:", txtHistoricoMedico = new JTextArea(3, 20), 12);

        // Medicamentos
        adicionarCampoTextArea(painel, "Medicamentos:", txtMedicamentos = new JTextArea(3, 20), 13);

        // Alergias
        adicionarCampoTextArea(painel, "Alergias:", txtAlergias = new JTextArea(3, 20), 14);

        // Senha
        adicionarCampo(painel, "Senha:", txtSenha = new JPasswordField(), 15);

        // Botão Criar
        btnCriar = new JButton("Criar Usuário");
        btnCriar.setBackground(primaryColor);
        btnCriar.setForeground(Color.WHITE);
        btnCriar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarUsuario();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 16;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painel.add(btnCriar, gbc);

        // Botão Limpar
        btnLimpar = new JButton("Limpar Dados");
        btnLimpar.setBackground(Color.LIGHT_GRAY);
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 16;
        painel.add(btnLimpar, gbc);

        add(painel);

        setVisible(true);
    }

    // Método auxiliar para adicionar campos de texto
    private void adicionarCampo(JPanel painel, String labelText, JComponent componente, int gridy) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridy = gridy;
        gbc.anchor = GridBagConstraints.WEST;
        painel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painel.add(componente, gbc);
    }

    // Método auxiliar para adicionar campos de texto de área
    private void adicionarCampoTextArea(JPanel painel, String labelText, JTextArea componente, int gridy) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridy = gridy;
        gbc.anchor = GridBagConstraints.WEST;
        painel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painel.add(new JScrollPane(componente), gbc);
    }




    private void criarUsuario() {
        UsuarioDAO dao = new UsuarioDAO();
        try {
            Usuario usuario = new Usuario();
            usuario.setNome(txtNome.getText());
            usuario.setCpf(txtCpf.getText());
            usuario.setEmail(txtEmail.getText());

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            usuario.setDataNascimento(dateFormat.format(dateFormat.parse(txtDataNascimento.getText())));            usuario.setSexo(cbSexo.getSelectedItem().toString());

            usuario.setTipoDiabetes(cbTipoDiabetes.getSelectedItem().toString());
            usuario.setDataDiagnostico(dateFormat.format(dateFormat.parse(txtDataDiagnostico.getText())));
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
            JOptionPane.showMessageDialog(this, "Erro: Insira dados numéricos válidos para Nível de Açúcar no Sangue, Peso e Altura.");
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Erro: Insira datas válidas no formato DD/MM/AAAA.");
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtCpf.setText("");
        txtEmail.setText("");
        txtDataNascimento.setText("");
        cbSexo.setSelectedIndex(0); // Reset ComboBox
        cbTipoDiabetes.setSelectedIndex(0); // Reset ComboBox
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