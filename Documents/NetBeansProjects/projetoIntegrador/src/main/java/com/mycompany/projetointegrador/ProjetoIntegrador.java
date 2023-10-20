package com.mycompany.projetointegrador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProjetoIntegrador {
    public static void main(String[] args) {
        DbConnection dataConnection = new DbConnection();
        Connection conexao = dataConnection.getConnection();
        String texto;

        if (conexao != null) {
            try {
                Statement statement = conexao.createStatement();
                
                System.out.println("Banco conectado");
                System.out.println("----------------");

                ResultSet rsClient = statement.executeQuery("SELECT * FROM public.tipoobjeto");

                while (rsClient.next()) {
                    System.out.println("Código: " + rsClient.getString("nome"));
                }
            } catch (SQLException e) {
                System.out.println("Erro ao executar a consulta: " + e.getMessage());
            }
        } else {
            System.out.println("Não foi possivel conectar ao banco");
        }
    }
    
}
