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
    
    public void registerTipoObjeto(String nomeObjeto) {
        try {
            Statement statement = conexao.createStatement();
            String query = String.format("INSERT INTO public.tipoobjeto (nome) VALUES ('%s');", nomeObjeto);
            
            int rowsAffected = statement.executeUpdate(query);

            if (rowsAffected > 0) {
                String response = String.format("O tipo objeto %s foi adicionado ao sistema", nomeObjeto);
                System.out.println(response);
            } else {
                System.out.println("Nenhum registro foi adicionado ao banco de dados.");
            }
        } catch (SQLException e){
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
        }
    }
     
    public void registerObjeto(int idTipoObjeto, int idPessoa, String status) {
        try {
            Statement statement = conexao.createStatement();
            String query = String.format("INSERT INTO public.objeto (tipoobjetoid, pessoaid, status) VALUES ('%s', '%s', '%s');", idTipoObjeto, idPessoa, status);
            
            int rowsAffected = statement.executeUpdate(query);

            if (rowsAffected > 0) {
                String response = String.format("O objeto foi adicionado ao sistema");
                System.out.println(response);
            } else {
                System.out.println("Nenhum registro foi adicionado ao banco de dados.");
            }
        } catch (SQLException e){
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
        }
    }
    
    public void registerManutencao(int idObjeto, String status, String descricao, String dataInicialConserto) {
        try {
            Statement statement = conexao.createStatement();
            String query = String.format("INSERT INTO public.manutencao (objetoid, status, descricao, datainicialconserto) VALUES ('%s', '%s', '%s','%s');", idObjeto, status, descricao, dataInicialConserto);
            
            int rowsAffected = statement.executeUpdate(query);

            if (rowsAffected > 0) {
                String response = String.format("O objeto foi adicionado a lista de manutenção");
                System.out.println(response);
            } else {
                System.out.println("Nenhum registro foi adicionado ao banco de dados.");
            }
        } catch (SQLException e){
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
        }
    }
    
    public void registerEmprestimo(int idObjeto, int pessoaId, String dataInicialEmprestimo) {
        try {
            Statement statement = conexao.createStatement();
            String query = String.format("INSERT INTO public.emprestimo (objetoid, pessoaportadoraid, dataemprestimo) VALUES ('%s', '%s', '%s');", idObjeto, pessoaId, dataInicialEmprestimo);
            
            int rowsAffected = statement.executeUpdate(query);

            if (rowsAffected > 0) {
                String response = String.format("Empréstimo adicionado");
                System.out.println(response);
            } else {
                System.out.println("Nenhum registro foi adicionado ao banco de dados.");
            }
        } catch (SQLException e){
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
        }
    }
}
