import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection conexao;

    public UsuarioDAO() {
        try {
            this.conexao = ConexaoBanco.obterConexao();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados.");
            e.printStackTrace();
        }
    }

    public void salvar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (nome, cpf, email, data_nascimento, sexo, tipo_diabetes, data_diagnostico, nivel_acucar_sangue, peso, altura, pressao_arterial, historico_medico, medicamentos, alergias, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getDataNascimento());
            stmt.setString(5, usuario.getSexo());
            stmt.setString(6, usuario.getTipoDiabetes());
            stmt.setString(7, usuario.getDataDiagnostico());
            stmt.setInt(8, usuario.getNivelAcucarSangue());
            stmt.setFloat(9, usuario.getPeso());
            stmt.setFloat(10, usuario.getAltura());
            stmt.setString(11, usuario.getPressaoArterial());
            stmt.setString(12, usuario.getHistoricoMedico());
            stmt.setString(13, usuario.getMedicamentos());
            stmt.setString(14, usuario.getAlergias());
            stmt.setString(15, usuario.getSenha());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    usuario.setId(rs.getInt(1)); // Defina o ID no objeto Usuario
                }
            }
        }
    }


    public void atualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET nome = ?, cpf = ?, email = ?, data_nascimento = ?, sexo = ?, tipo_diabetes = ?, data_diagnostico = ? , nivel_acucar_sangue = ?, peso = ?, altura = ?, pressao_arterial = ?, historico_medico = ?, medicamentos = ?, alergias = ?, senha = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getDataNascimento());
            stmt.setString(5, usuario.getSexo());
            stmt.setString(6, usuario.getTipoDiabetes());
            stmt.setString(7, usuario.getDataDiagnostico());
            stmt.setInt(8, usuario.getNivelAcucarSangue());
            stmt.setFloat(9, usuario.getPeso());
            stmt.setFloat(10, usuario.getAltura());
            stmt.setString(11, usuario.getPressaoArterial());
            stmt.setString(12, usuario.getHistoricoMedico());
            stmt.setString(13, usuario.getMedicamentos());
            stmt.setString(14, usuario.getAlergias());
            stmt.setString(15, usuario.getSenha());
            stmt.setInt(16, usuario.getId());
            stmt.executeUpdate();
        }
    }

    public void excluir(String campo, String valor) throws SQLException {
        String sql = "DELETE FROM usuario WHERE " + campo + " = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, valor);
            stmt.executeUpdate();
        }
    }

    public Usuario buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return converteResultSetParaUsuario(rs);
                }
            }
        }
        return null;
    }

    public List<Usuario> listarTodos() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usuarios.add(converteResultSetParaUsuario(rs));
            }
        }
        return usuarios;
    }

    private Usuario converteResultSetParaUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario(); // Crie o objeto Usuario com o construtor sem parâmetros

        usuario.setId(rs.getInt("id"));
        usuario.setNome(rs.getString("nome"));
        usuario.setCpf(rs.getString("cpf"));
        usuario.setEmail(rs.getString("email"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setDataNascimento(rs.getString("data_nascimento"));
        usuario.setSexo(rs.getString("sexo"));
        usuario.setTipoDiabetes(String.valueOf(rs.getInt("tipo_diabetes")));
        usuario.setDataDiagnostico(rs.getString("data_diagnostico"));
        usuario.setNivelAcucarSangue(rs.getInt("nivel_acucar_sangue"));
        usuario.setPeso(rs.getFloat("peso"));
        usuario.setAltura(rs.getFloat("altura"));
        usuario.setPressaoArterial(rs.getString("pressao_arterial"));
        usuario.setHistoricoMedico(rs.getString("historico_medico"));
        usuario.setMedicamentos(rs.getString("medicamentos"));
        usuario.setAlergias(rs.getString("alergias"));

        return usuario;
    }


    public void fecharConexao() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão com o banco de dados.");
            e.printStackTrace();
        }
    }
}