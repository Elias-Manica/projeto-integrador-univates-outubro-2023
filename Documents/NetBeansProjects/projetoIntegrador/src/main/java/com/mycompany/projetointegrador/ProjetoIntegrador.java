package com.mycompany.projetointegrador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjetoIntegrador {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String usuario = "postgres";
        String senha = "postgres";
        
        System.out.println("Hello World!");
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            
            System.out.println("Conexão realizada com sucesso!");
            
            ResultSet rsClient = conexao.createStatement().executeQuery("SELECT * FROM public.bicicleta");
        
            while(rsClient.next()) {
                System.out.println("Código!" + rsClient.getString("codigo"));
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Erro caso o driver JDBC não foi instalado");
        } catch (SQLException e) {
            System.out.println("Erro caso haja problemas para se conectar ao banco de dados" + e.getMessage());
        }
    }
}
