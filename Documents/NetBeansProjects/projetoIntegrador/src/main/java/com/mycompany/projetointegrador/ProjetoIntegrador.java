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
                
            while(opcaoMenuPrimario != 6) {
                interfaceMenuLayout.showMenuPrimary();
                opcaoMenuPrimario = Entrada.leiaInt(); 
                if(opcaoMenuPrimario == 1) {
                    interfaceMenuLayout.showMenuSecondary("PESSOAS");
                    opcaoMenuSecundario = Entrada.leiaInt("Qual ação você deseja realizar: "); 
                    if(opcaoMenuSecundario == 1) {
                        String nomePessoa = Entrada.leiaString("Qual o nome da Pessoa?");
                        String cargo = Entrada.leiaString("Qual o cargo da Pessoa?");
                        String cpf = Entrada.leiaString("Qual o cpf  da Pessoa?");
                        String telefone = Entrada.leiaString("Qual o telefone da Pessoa?");

                        if(interfaceMenuLayout.verificaPessoa(nomePessoa, cargo, cpf, telefone)){
                            dataConnection.registerPessoa(nomePessoa, cargo, cpf, telefone);
                        }
                    } else {
                        System.out.println("Opção ainda não foi implementada");
                        System.out.println("----------------");
                    }
                }
                if(opcaoMenuPrimario == 2) {
                    interfaceMenuLayout.showMenuSecondary("TIPOS DE OBJETOS");
                    opcaoMenuSecundario = Entrada.leiaInt("Qual ação você deseja realizar: ");
                    if(opcaoMenuSecundario == 1) {
                        String nomeObjeto = Entrada.leiaString("Qual o nome do tipo de objeto?");

                        if(interfaceMenuLayout.verificaObjeto(nomeObjeto)) {
                            dataConnection.registerTipoObjeto(nomeObjeto);
                        }
                    } else {
                        System.out.println("Opção ainda não foi implementada");
                        System.out.println("----------------");
                    }
                }
                if(opcaoMenuPrimario == 3) {
                    interfaceMenuLayout.showMenuSecondary("OBJETOS");
                    opcaoMenuSecundario = Entrada.leiaInt("Qual ação você deseja realizar: ");
                    if(opcaoMenuSecundario == 1) {
                        int idTipoObjeto = Entrada.leiaInt("Qual o ID do tipo do objeto?");
                        int idPessoa = Entrada.leiaInt("Qual o ID da pessoa dona do objeto?");
                        String status = "disponivel";
                        
                        dataConnection.registerObjeto(idTipoObjeto, idPessoa, status);

                    } else {
                        System.out.println("Opção ainda não foi implementada");
                        System.out.println("----------------");
                    }
                }
                if(opcaoMenuPrimario == 4) {
                    interfaceMenuLayout.showMenuSecondary("MANUTENÇÃO");
                    opcaoMenuSecundario = Entrada.leiaInt("Qual ação você deseja realizar: ");
                    if(opcaoMenuSecundario == 1) {
                        int idObjeto = Entrada.leiaInt("Qual o ID do objeto que sofrerá manutenção? ");
                        String status = "manutencao";
                        String descricao = Entrada.leiaString("Dê uma descrição à manutenção: ");
                        String dataInicialConserto = Entrada.leiaString("Qual a data de inicio do conserto? EX: '2023-10-25' ");
                        
                        dataConnection.registerManutencao(idObjeto, status, descricao, dataInicialConserto);

                    } else {
                        System.out.println("Opção ainda não foi implementada");
                        System.out.println("----------------");
                    }
                }
                if(opcaoMenuPrimario == 5) {
                    interfaceMenuLayout.showMenuSecondary("EMPRÉSTIMOS");
                    opcaoMenuSecundario = Entrada.leiaInt("Qual ação você deseja realizar: ");
                    if(opcaoMenuSecundario == 1) {
                        int idObjeto = Entrada.leiaInt("Qual o ID do objeto que irá ser emprestado? ");
                        int pessoaId = Entrada.leiaInt("Qual o ID da pessoa que está pegando o objeto emprestado? ");
                        String dataInicialEmprestimo = Entrada.leiaString("Qual a data de inicio do empréstimo? EX: '2023-10-25' ");
                        
                        dataConnection.registerEmprestimo(idObjeto, pessoaId, dataInicialEmprestimo);

                    } else {
                        System.out.println("Opção ainda não foi implementada");
                        System.out.println("----------------");
                    }
                }
            }
        } else {
            System.out.println("Não foi possivel conectar ao banco");
        }
    }
    
}
