package com.mycompany.projetointegrador;

import java.sql.Connection;

public class ProjetoIntegrador {
    public static void main(String[] args) {
        DbConnection dataConnection = new DbConnection();
        Connection conexao = dataConnection.getConnection();
        
        InterfaceMenu interfaceMenuLayout = new InterfaceMenu();
        int opcaoMenuPrimario = 0;
        int opcaoMenuSecundario = 0;

        if (conexao != null) {
            System.out.println("Banco conectado");
            System.out.println("----------------");
                
            while(opcaoMenuPrimario != 7) {
                interfaceMenuLayout.showMenuPrimary();
                opcaoMenuPrimario = Entrada.leiaInt(); 
                if(opcaoMenuPrimario == 1) {
                    interfaceMenuLayout.showMenuSecondary();
                    opcaoMenuSecundario = Entrada.leiaInt(); 
                    if(opcaoMenuSecundario == 1) {
                        String nomePessoa = Entrada.leiaString("Qual o nome da Pessoa?");
                        String cargo = Entrada.leiaString("Qual o cargo da Pessoa?");
                        String cpf = Entrada.leiaString("Qual o cpf  da Pessoa?");
                        String telefone = Entrada.leiaString("Qual o telefone da Pessoa?");

                        interfaceMenuLayout.verificaPessoa(nomePessoa, cargo, cpf, telefone);
                        
                        dataConnection.registerPessoa(nomePessoa, cargo, cpf, telefone);
                    }
                }
            }
        } else {
            System.out.println("Não foi possivel conectar ao banco");
        }
    }
    
}
