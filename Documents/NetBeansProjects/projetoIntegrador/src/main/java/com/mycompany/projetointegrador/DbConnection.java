package com.mycompany.projetointegrador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
    private static final String url = "jdbc:postgresql://localhost:5432/projeto-integrador-outubro-2023";
    private static final String usuario = "postgres";
    private static final String senha = "postgres";
    private Connection conexao;

    public void DatabaseConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão realizada com sucesso!");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro caso o driver JDBC não tenha sido instalado");
        } catch (SQLException e) {
            System.out.println("Erro caso haja problemas para se conectar ao banco de dados: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        System.out.println("Conectando ao banco");
        this.DatabaseConnection();
        return conexao;
    }
    
    public void registerPessoa(String nomePessoa,  String cargo, String cpf, String telefone) {
        try {
            Statement statement = conexao.createStatement();
            String query = String.format("INSERT INTO public.pessoa (nome, cargo, cpf, telefone) VALUES ('%s', '%s', '%s', '%s');", nomePessoa, cargo, cpf, telefone);
            
            int rowsAffected = statement.executeUpdate(query);

            if (rowsAffected > 0) {
                String response = String.format("O colaborador %s, com o cargo %s foi adicionado ao sistema", nomePessoa, cargo);
                System.out.println(response);
            } else {
                System.out.println("Nenhum registro foi adicionado ao banco de dados.");
            }
        } catch (SQLException e){
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
        }
    }
}
