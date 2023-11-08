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
                    } 
                    if(opcaoMenuSecundario == 2) {
                        dataConnection.findPessoa();
                    }
                    if(opcaoMenuSecundario == 3) {
                        int idPessoa = Entrada.leiaInt("Qual o ID da pessoa que você deseja editar?");
                        String nomePessoa = Entrada.leiaString("Qual o novo nome da Pessoa?");
                        String cargo = Entrada.leiaString("Qual o nov cargo da Pessoa?");
                        String cpf = Entrada.leiaString("Qual o cpf novo da Pessoa?");
                        String telefone = Entrada.leiaString("Qual o novo telefone da Pessoa?");
                        if(interfaceMenuLayout.verificaPessoa(nomePessoa, cargo, cpf, telefone)){
                            dataConnection.editPessoa(idPessoa, nomePessoa, cargo, cpf, telefone);
                        }
                    }
                    if(opcaoMenuSecundario == 4) {
                        System.out.println("VOCÊ ESTÁ EXCLUINDO UMA PESSOA, TODOS OS OBJETOS/MANUTENÇÕES/EMPRÉSTIMOS VINCULADOS À ELA SERÃO EXCLUIDOS PERMANENTEMENTE");
                        String resposta = Entrada.leiaString("Deseja continuar com essa ação? sim/nao ");
                        if(resposta.equalsIgnoreCase("sim")) {
                              int idPessoa = Entrada.leiaInt("Qual o ID da pessoa que você deseja EXCLUIR? ");
                              dataConnection.deletePessoa(idPessoa);
                        }
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
                    } 
                    if(opcaoMenuSecundario == 2) {
                        dataConnection.findTipoObjeto();
                    }
                    if(opcaoMenuSecundario == 3) {
                        int idTipoObjeto = Entrada.leiaInt("Qual o ID do tipo do objeto que você deseja editar?");
                        String nomeObjeto = Entrada.leiaString("Qual o novo nome do tipo de objeto?");

                        if(interfaceMenuLayout.verificaObjeto(nomeObjeto)) {
                            dataConnection.editTipoObjeto(idTipoObjeto, nomeObjeto);
                        }
                    }
                    if(opcaoMenuSecundario == 4) {
                        System.out.println("VOCÊ ESTÁ EXCLUINDO UM TIPO DE OBJETO, TODOS OS OBJETOS/MANUTENÇÕES/EMPRÉSTIMOS VINCULADOS À ELE SERÃO EXCLUIDOS PERMANENTEMENTE");
                        String resposta = Entrada.leiaString("Deseja continuar com essa ação? sim/nao ");
                        if(resposta.equalsIgnoreCase("sim")) {
                              int idTipoObjeto = Entrada.leiaInt("Qual o ID do tipo do objeto que você deseja EXCLUIR? ");
                              dataConnection.deleteTipoObjeto(idTipoObjeto);
                        }
                    }
                }
                if(opcaoMenuPrimario == 3) {
                    interfaceMenuLayout.showMenuSecondary("OBJETOS");
                    opcaoMenuSecundario = Entrada.leiaInt("Qual ação você deseja realizar: ");
                    if(opcaoMenuSecundario == 1) {
                        int idTipoObjeto = Entrada.leiaInt("Qual o ID do tipo do objeto?");
                        int idPessoa = Entrada.leiaInt("Qual o ID da pessoa dona do objeto?");
                        String status = "DISPONIVEL";
                        
                        dataConnection.registerObjeto(idTipoObjeto, idPessoa, status);

                    }
                    if(opcaoMenuSecundario == 2) {
                        dataConnection.findObjeto();
                    }
                    if(opcaoMenuSecundario == 3) {
                        int idObjeto = Entrada.leiaInt("Qual o ID do objeto que você deseja editar?");
                        
                        int idTipoObjeto = Entrada.leiaInt("Qual o novo ID do tipo do objeto?");
                        int idPessoa = Entrada.leiaInt("Qual o ID da nova pessoa dona do objeto?");

                        dataConnection.editObjeto(idObjeto, idTipoObjeto, idPessoa);
                    }
                    if(opcaoMenuSecundario == 4) {
                        System.out.println("VOCÊ ESTÁ EXCLUINDO UM OBJETO, TODOS AS MANUTENÇÕES/EMPRÉSTIMOS VINCULADOS À ELE SERÃO EXCLUIDOS PERMANENTEMENTE");
                        String resposta = Entrada.leiaString("Deseja continuar com essa ação? sim/nao ");
                        if(resposta.equalsIgnoreCase("sim")) {
                              int idObjeto = Entrada.leiaInt("Qual o ID do objeto que você deseja EXCLUIR? ");
                              dataConnection.deleteObjeto(idObjeto);
                        }
                    }
                    if(opcaoMenuSecundario == 5) {
                        System.out.println("VOCÊ ESTÁ DANDO BAIXA EM UM OBJETO, TODOS AS MANUTENÇÕES/EMPRÉSTIMOS VINCULADOS À ELE SERÃO EXCLUIDOS PERMANENTEMENTE");
                        System.out.println("Essa ação não terá retorno");
                        String resposta = Entrada.leiaString("Deseja continuar com essa ação? sim/nao ");
                        if(resposta.equalsIgnoreCase("sim")) {
                              int idObjeto = Entrada.leiaInt("Qual o ID do objeto que você deseja baixar? ");
                              dataConnection.baixaObjeto(idObjeto);
                        }
                    }
                }
                if(opcaoMenuPrimario == 4) {
                    interfaceMenuLayout.showMenuSecondary("MANUTENÇÃO");
                    opcaoMenuSecundario = Entrada.leiaInt("Qual ação você deseja realizar: ");
                    if(opcaoMenuSecundario == 1) {
                        int idObjeto = Entrada.leiaInt("Qual o ID do objeto que sofrerá manutenção? ");
                        String status = "ATIVO";
                        String descricao = Entrada.leiaString("Dê uma descrição à manutenção: ");
                        String dataInicialConserto = Entrada.leiaString("Qual a data de inicio do conserto? EX: '2023-10-25' ");
                        
                        dataConnection.registerManutencao(idObjeto, status, descricao, dataInicialConserto);
                    }
                    if(opcaoMenuSecundario == 2) {
                        dataConnection.findManutencao();
                    }
                    if(opcaoMenuSecundario == 3) {
                        int idManutencao = Entrada.leiaInt("Qual o ID da manutenção que você deseja editar?");
                        
                        String descricao = Entrada.leiaString("Qual a nova descrição da manutenção: ");
                        String dataInicialConserto = Entrada.leiaString("Qual a nova data de inicio do conserto? EX: '2023-10-25' ");
                        String taConcluido = Entrada.leiaString("A manutenção acabou? sim/nao ");
                        String dataFinalConserto = "";
                        if(taConcluido.equalsIgnoreCase("sim")) {
                            dataFinalConserto = Entrada.leiaString("Qual a nova data de conclusão do conserto? EX: '2023-10-25' ");
                        }

                        dataConnection.editManutencao(idManutencao, descricao, dataInicialConserto, dataFinalConserto);
                    }
                    if(opcaoMenuSecundario == 4) {
                        System.out.println("VOCÊ ESTÁ EXCLUINDO UMA MANUTENÇÃO PERMANENTEMENTE");
                        String resposta = Entrada.leiaString("Deseja continuar com essa ação? sim/nao ");
                        if(resposta.equalsIgnoreCase("sim")) {
                              int idManutencao = Entrada.leiaInt("Qual o ID do manutenção que você deseja EXCLUIR? ");
                              dataConnection.deleteManutencao(idManutencao);
                        }
                    }
                }
                if(opcaoMenuPrimario == 5) {
                    interfaceMenuLayout.showMenuSecondary("EMPRÉSTIMOS");
                    opcaoMenuSecundario = Entrada.leiaInt("Qual ação você deseja realizar: ");
                    if(opcaoMenuSecundario == 1) {
                        int idObjeto = Entrada.leiaInt("Qual o ID do objeto que irá ser emprestado? ");
                        int pessoaId = Entrada.leiaInt("Qual o ID da pessoa que está pegando o objeto emprestado? ");
                        String dataInicialEmprestimo = Entrada.leiaString("Qual a data de inicio do empréstimo? EX: '2023-10-25' ");
                        String status = "ATIVO";
                        
                        dataConnection.registerEmprestimo(idObjeto, pessoaId, dataInicialEmprestimo, status);

                    } 
                    if(opcaoMenuSecundario == 2) {
                        dataConnection.findEmprestimo();
                    }
                    if(opcaoMenuSecundario == 3) {
                        int idEmprestimo = Entrada.leiaInt("Qual o ID do empréstimo que você deseja editar?");
                        
                        int pessoaId = Entrada.leiaInt("Qual o ID da nova pessoa que está pegando o objeto emprestado? ");
                        String dataInicialEmprestimo = Entrada.leiaString("Qual a nova data de inicio do empréstimo? EX: '2023-10-25' ");
                        String taConcluido = Entrada.leiaString("A manutenção acabou? sim/nao ");
                        String dataFinalEmprestimo = "";
                        if(taConcluido.equalsIgnoreCase("sim")) {
                            dataFinalEmprestimo = Entrada.leiaString("Qual a nova data de conclusão do conserto? EX: '2023-10-25' ");
                        }
                        
                        dataConnection.editEmprestimo(idEmprestimo, pessoaId, dataInicialEmprestimo, dataFinalEmprestimo);
                    }
                    if(opcaoMenuSecundario == 4) {
                        System.out.println("VOCÊ ESTÁ EXCLUINDO UM EMPRÉSTIMO PERMANENTEMENTE");
                        String resposta = Entrada.leiaString("Deseja continuar com essa ação? sim/nao ");
                        if(resposta.equalsIgnoreCase("sim")) {
                              int idEmprestimo = Entrada.leiaInt("Qual o ID do emprestimo que você deseja EXCLUIR? ");
                              dataConnection.deleteEmprestimo(idEmprestimo);
                        }
                    }
                }
            }
        } else {
            System.out.println("Não foi possivel conectar ao banco");
        }
    }
    
}
