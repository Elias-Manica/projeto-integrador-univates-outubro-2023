package com.mycompany.projetointegrador;

public class InterfaceMenu {
    public void showMenuPrimary() {
        System.out.println("Sistema de empréstimos de objetos");
        System.out.println("----------------");
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Pessoas");
        System.out.println("2 - Tipos de objetos");
        System.out.println("3 - Objetos");
        System.out.println("4 - Manutenção");
        System.out.println("5 - Empréstimo");
        System.out.println("6 - Sair");
    }
    
    public void showMenuSecondary(String typeMenu) {
       System.out.println("----------------");
       System.out.println("Você está no sistema de " + typeMenu);
       System.out.println("Escolha uma ação para realizar:");
       System.out.println("1 - Incluir / Cadastrar");
       System.out.println("2 - Consultar");
       System.out.println("3 - Alterar");
       System.out.println("4 - Dar baixa");
       System.out.println("5 - Excluir");
       if(typeMenu == "OBJETOS") {
            System.out.println("6 - Realizar empréstimo");
            System.out.println("7 - Voltar");
       } else {
           System.out.println("6 - Voltar");
       }
    }
    
    public Boolean verificaPessoa(String nomePessoa,  String cargo, String cpf, String telefone) {
        if(nomePessoa.length() <= 0 || nomePessoa.length() > 200) {
            System.out.println("Campo inválido! O nome deve ter entre 1 ou 200 caracteres");
            return false;
        }
        if(cargo.length() <= 0 || cargo.length() > 200) {
            System.out.println("Campo inválido! O cargo deve ter entre 1 ou 200 caracteres");
            return false;
        }
        if(cpf.length() > 11) {
            System.out.println("Campo inválido! O cpf deve ter 11 caracteres");
            return false;
        }
        if(telefone.length() > 11) {
            System.out.println("Campo inválido! O telefone deve ter 11 caracteres");
            return false;
        }
        return true;
    }
    
     public Boolean verificaObjeto(String nomeObjeto) {
        if(nomeObjeto.length() <= 0 || nomeObjeto.length() > 200) {
            System.out.println("Campo inválido! O nome deve ter entre 1 ou 200 caracteres");
            System.out.println(" ");
            return false;
        }
        return true;
    }
     
     public Boolean verificaManutencao(String descricao) {
        if(descricao.length() <= 0 || descricao.length() > 500) {
            System.out.println("Campo inválido! A descricao deve ter entre 1 ou 500 caracteres");
            System.out.println(" ");
            return false;
        }
        return true;
    }
}
