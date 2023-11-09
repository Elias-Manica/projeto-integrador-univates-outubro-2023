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
            String query = String.format("INSERT INTO public.pessoa (nome, cargo, cpf, telefone) VALUES ('%s', '%s', '%s', '%s');", nomePessoa.toUpperCase(), cargo.toUpperCase(), cpf, telefone);
            
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
    
    public void findPessoa() {
        try {
            Statement statement = conexao.createStatement();
            String query = String.format("SELECT id, nome, cargo, cpf, telefone FROM public.pessoa;");
            
            ResultSet rsClient = statement.executeQuery(query);

            int rowcount = 0;
                        
            while (rsClient.next()) {
                System.out.println("===================================");
                System.out.println("Id: " + rsClient.getString("id"));
                System.out.println("Nome: " + rsClient.getString("nome"));
                System.out.println("Cargo: " + rsClient.getString("cargo"));
                System.out.println("CPF: " + rsClient.getString("cpf"));
                System.out.println("Telefone: " + rsClient.getString("telefone"));
                System.out.println("===================================");
                rowcount++;
            }
            
            if(rowcount == 0) {
                System.out.println("Sem pessoas cadastradas");
                System.out.println("===================================");
            }
        } catch (SQLException e){
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
        }
    }
    
    public void editPessoa(int idPessoa, String nomePessoa,  String cargo, String cpf, String telefone) {
        try {
            Statement statement = conexao.createStatement();
            String query = String.format("UPDATE public.pessoa SET nome='%s', cargo='%s', cpf='%s', telefone='%s' WHERE id=%s;", nomePessoa.toUpperCase(), cargo.toUpperCase(), cpf, telefone, idPessoa);
            
            int rowsAffected = statement.executeUpdate(query);

            if (rowsAffected > 0) {
                String response = String.format("O colaborador %s foi modificado", nomePessoa, cargo);
                System.out.println(response);
            } else {
                System.out.println("Nenhum registro foi modificado no banco de dados.");
            }
        } catch (SQLException e){
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
        }
    }
    
    public void deletePessoa(int idPessoa) {
        try {
            Statement statement = conexao.createStatement();
            String query = String.format("DELETE FROM public.pessoa WHERE id=%s;", idPessoa);
            
            int rowsAffected = statement.executeUpdate(query);

            if (rowsAffected > 0) {
                String response = String.format("O colaborador com id %s e suas relações foram foi excluidas", idPessoa);
                System.out.println(response);
            } else {
                System.out.println("Nenhum registro foi modificado no banco de dados.");
            }
        } catch (SQLException e){
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
        }
    }
    
    public void registerTipoObjeto(String nomeObjeto) {
        try {
            Statement statement = conexao.createStatement();
            String query = String.format("INSERT INTO public.tipoobjeto (nome) VALUES ('%s');", nomeObjeto.toUpperCase());
            
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
    
     public void findTipoObjeto() {
        try {
            Statement statement = conexao.createStatement();
            String query = String.format("SELECT id, nome FROM public.tipoobjeto;");
            
            ResultSet rsClient = statement.executeQuery(query);

            int rowcount = 0;
            while (rsClient.next()) {
                System.out.println("===================================");
                System.out.println("Id: " + rsClient.getString("id"));
                System.out.println("Nome: " + rsClient.getString("nome"));
                System.out.println("===================================");
                rowcount++;
            }
            
            if(rowcount == 0) {
                System.out.println("Sem tipos de objetos cadastrados");
                System.out.println("===================================");
            }
        } catch (SQLException e){
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
        }
    }
     
    public void editTipoObjeto(int idTipoObjeto, String nomeObjeto) {
        try {
            Statement statement = conexao.createStatement();
            String query = String.format("UPDATE public.tipoobjeto SET nome='%s' WHERE id=%s;", nomeObjeto.toUpperCase(), idTipoObjeto);
  
            int rowsAffected = statement.executeUpdate(query);

            if (rowsAffected > 0) {
                String response = String.format("O tipo objeto com id %s foi modificado no sistema", idTipoObjeto);
                System.out.println(response);
            } else {
                System.out.println("Nenhum registro foi modificado no banco de dados.");
            }
        } catch (SQLException e){
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
        }
    }
    
    public void deleteTipoObjeto(int idTipoObjeto) {
        try {
            Statement statement = conexao.createStatement();
            String query = String.format("DELETE FROM public.tipoobjeto WHERE id=%s;", idTipoObjeto);
            
            int rowsAffected = statement.executeUpdate(query);

            if (rowsAffected > 0) {
                String response = String.format("O TIPO OBJETO com id %s e suas relações foram foi excluidas", idTipoObjeto);
                System.out.println(response);
            } else {
                System.out.println("Nenhum registro foi modificado no banco de dados.");
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
    
    public void findObjeto() {
        try {
            Statement statement = conexao.createStatement();
            String query = String.format("SELECT objeto.id, tipoobjeto.nome AS nomeferramenta, pessoa.nome AS nomePessoa, objeto.status FROM objeto JOIN tipoobjeto ON objeto.tipoobjetoid = tipoobjeto.id JOIN pessoa ON objeto.pessoaid = pessoa.id WHERE objeto.status = 'DISPONIVEL';");
            
            ResultSet rsClient = statement.executeQuery(query);

            int rowcount = 0;
            
            while (rsClient.next()) {
                System.out.println("===================================");
                System.out.println("Id: " + rsClient.getString("id"));
                System.out.println("Ferramenta: " + rsClient.getString("nomeferramenta"));
                System.out.println("Dono: " + rsClient.getString("nomePessoa"));
                System.out.println("Status: " + rsClient.getString("status"));
                System.out.println("===================================");
                rowcount++;
            }
            
            if(rowcount == 0) {
                System.out.println("Sem objetos disponiveis");
                System.out.println("===================================");
            }
           
        } catch (SQLException e){
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
        }
    }
    
    public void editObjeto(int idObjeto, int idTipoObjeto, int idPessoa) {
        try {
            Statement statement = conexao.createStatement();
            String query = String.format("UPDATE public.objeto SET tipoobjetoid=%s, pessoaid=%s WHERE id=%s;", idTipoObjeto, idPessoa, idObjeto);
  
            int rowsAffected = statement.executeUpdate(query);

            if (rowsAffected > 0) {
                String response = String.format("O objeto com id %s foi modificado no sistema", idObjeto);
                System.out.println(response);
            } else {
                System.out.println("Nenhum registro foi modificado no banco de dados.");
            }
        } catch (SQLException e){
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
        }
    }
    
    public void deleteObjeto(int idObjeto) {
        try {
            Statement statement = conexao.createStatement();
            String query = String.format("DELETE FROM public.objeto WHERE id=%s;", idObjeto);
            
            int rowsAffected = statement.executeUpdate(query);

            if (rowsAffected > 0) {
                String response = String.format("O OBJETO com id %s e suas relações foram foi excluidas", idObjeto);
                System.out.println(response);
            } else {
                System.out.println("Nenhum registro foi modificado no banco de dados.");
            }
        } catch (SQLException e){
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
        }
    }
    
    public void baixaObjeto(int idObjeto) {
        try {
            Statement statement = conexao.createStatement();
            String updateObjeto = String.format("UPDATE public.objeto SET status='BAIXADO' WHERE id = %s;", idObjeto);
            String deleteManutencao = String.format("DELETE FROM public.manutencao WHERE objetoid=%s;", idObjeto);
            String deleteEmprestimo = String.format("DELETE FROM public.emprestimo WHERE objetoid=%s;", idObjeto);
            
            statement.executeUpdate(deleteManutencao);
            statement.executeUpdate(deleteEmprestimo);
            int rowsAffected = statement.executeUpdate(updateObjeto);

            if (rowsAffected > 0) {
                String response = String.format("O OBJETO com id %s e suas relações foram foi baixadas", idObjeto);
                System.out.println(response);
            } else {
                System.out.println("Nenhum registro foi modificado no banco de dados.");
            }
        } catch (SQLException e){
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
        }
    }
    
    public void findAllObjeto() {
        try {
            Statement statement = conexao.createStatement();
            String query = String.format("SELECT objeto.id, tipoobjeto.nome AS nomeObjeto, objeto.status, pessoa.nome AS dono, pessoa2.nome AS pessoaQuePegouObjeto FROM emprestimo JOIN objeto ON emprestimo.objetoId = objeto.id JOIN tipoObjeto ON objeto.tipoObjetoId = tipoObjeto.id JOIN pessoa ON objeto.pessoaId = pessoa.id JOIN pessoa AS pessoa2 ON emprestimo.pessoaPortadoraId = pessoa2.id WHERE emprestimo.datadevolucao IS NULL;");
            
            ResultSet rsClient = statement.executeQuery(query);

            int rowcount = 0;
            
            while (rsClient.next()) {
                System.out.println("===================================");
                System.out.println("Id: " + rsClient.getString("id"));
                System.out.println("Ferramenta: " + rsClient.getString("nomeObjeto"));
                System.out.println("Dono: " + rsClient.getString("dono"));
                System.out.println("Pessoa que pegou objeto: " + rsClient.getString("pessoaQuePegouObjeto"));
                System.out.println("Status: " + rsClient.getString("status"));
                System.out.println("===================================");
                rowcount++;
            }
           
            String queryObject = String.format("SELECT objeto.id, tipoobjeto.nome AS nomeferramenta, pessoa.nome AS nomePessoa, objeto.status FROM objeto JOIN tipoobjeto ON objeto.tipoobjetoid = tipoobjeto.id JOIN pessoa ON objeto.pessoaid = pessoa.id WHERE objeto.status != 'EMPRESTADO' ORDER BY tipoobjeto.nome ASC;");
           
            ResultSet rsClientObject = statement.executeQuery(queryObject);
            
            while (rsClientObject.next()) {
                System.out.println("===================================");
                System.out.println("Id: " + rsClientObject.getString("id"));
                System.out.println("Ferramenta: " + rsClientObject.getString("nomeferramenta"));
                System.out.println("Dono: " + rsClientObject.getString("nomePessoa"));
                System.out.println("Status: " + rsClientObject.getString("status"));
                System.out.println("===================================");
                rowcount++;
            }
            
            if(rowcount == 0) {
                System.out.println("Sem objetos para serem mostrados");
                System.out.println("===================================");
            }
        } catch (SQLException e){
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
        }
    }
    
    public void findObjetoBasedOnType(int idTipo) {
        try {
            Statement statement = conexao.createStatement();
            String query = String.format("SELECT objeto.id, tipoobjeto.nome AS nomeferramenta, pessoa.nome AS nomePessoa, objeto.status FROM objeto JOIN tipoobjeto ON objeto.tipoobjetoid = tipoobjeto.id JOIN pessoa ON objeto.pessoaid = pessoa.id WHERE tipoobjeto.id = %s;", idTipo);
            
            ResultSet rsClient = statement.executeQuery(query);

            int rowcount = 0;
            
            while (rsClient.next()) {
                System.out.println("===================================");
                System.out.println("Id: " + rsClient.getString("id"));
                System.out.println("Ferramenta: " + rsClient.getString("nomeferramenta"));
                System.out.println("Dono: " + rsClient.getString("nomePessoa"));
                System.out.println("Status: " + rsClient.getString("status"));
                System.out.println("===================================");
                rowcount++;
            }
            
            if(rowcount == 0) {
                System.out.println("Sem objetos disponiveis");
                System.out.println("===================================");
            }
           
        } catch (SQLException e){
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
        }
    }
    
    public void registerManutencao(int idObjeto, String status, String descricao, String dataInicialConserto) {
        try {
            Statement statement = conexao.createStatement();
            
            String temObjeto = String.format("SELECT id, status FROM public.objeto WHERE id=%s AND status='DISPONIVEL';", idObjeto);
            ResultSet temObjetoDisponivel = statement.executeQuery(temObjeto);
            
            if(!temObjetoDisponivel.next()) {
                System.out.println("Objeto indisponível");
                return;
            }
            
            String query = String.format("INSERT INTO public.manutencao (objetoid, status, descricao, datainicialconserto) VALUES ('%s', '%s', '%s','%s');", idObjeto, status, descricao, dataInicialConserto);
            String updateObjeto = String.format("UPDATE public.objeto SET status='MANUTENCAO' WHERE id = %s;", idObjeto); 
            
            int rowsAffected = statement.executeUpdate(query);
            statement.executeUpdate(updateObjeto);
            
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
    
    public void findManutencao() {
        try {
            Statement statement = conexao.createStatement();
            String query = String.format("SELECT manutencao.id, pessoa.id AS donoId, pessoa.nome AS dono, tipoobjeto.nome AS tipoObjeto, objeto.id AS idObjeto, objeto.status, manutencao.descricao, manutencao.datainicialconserto, manutencao.datafinalconserto FROM manutencao JOIN objeto ON manutencao.objetoid = objeto.id JOIN pessoa ON objeto.pessoaid = pessoa.id JOIN tipoobjeto ON objeto.tipoobjetoid = tipoobjeto.id WHERE manutencao.datafinalconserto IS NULL;");
            
            ResultSet rsClient = statement.executeQuery(query);

            int rowcount = 0; 
            while (rsClient.next()) {
                System.out.println("===================================");
                System.out.println("Id: " + rsClient.getString("id"));
                System.out.println("Id da ferramenta: " + rsClient.getString("idObjeto"));
                System.out.println("Ferramenta: " + rsClient.getString("tipoObjeto"));
                System.out.println("Id do dono: " + rsClient.getString("donoId"));
                System.out.println("Dono: " + rsClient.getString("dono"));
                System.out.println("Descrição: " + rsClient.getString("descricao"));
                System.out.println("Data inicial conserto: " + rsClient.getString("datainicialconserto"));
                if(rsClient.getString("datafinalconserto") != null) {
                    System.out.println("Data conclusão conserto: " + rsClient.getString("datafinalconserto"));
                }
                System.out.println("===================================");
                rowcount++;
            }
           
            if(rowcount == 0) {
                System.out.println("Sem manutenções cadastradas");
                System.out.println("===================================");
            }
        } catch (SQLException e){
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
        }
    }
    
    public void editManutencao(int idManutencao, String descricao, String dataInicialConserto, String dataFinalConserto) {
        try {
            Statement statement = conexao.createStatement();
            
            String temManutencao = String.format("SELECT objetoid FROM public.manutencao WHERE id=%s;", idManutencao);
            ResultSet queryManutencao = statement.executeQuery(temManutencao);
            
            if(!queryManutencao.next()) {
                System.out.println("Essa manutenção não existe");
                return;
            }
            
            String query;
            if(dataFinalConserto.length() == 0) {
                query = String.format("UPDATE public.manutencao SET descricao='%s', datainicialconserto='%s' WHERE id=%s;", descricao, dataInicialConserto, idManutencao);
            } else {
                String updateObjeto = String.format("UPDATE public.objeto SET status='DISPONIVEL' WHERE id = %s;", queryManutencao.getString("objetoid")); 
                statement.executeUpdate(updateObjeto);
                query = String.format("UPDATE public.manutencao SET descricao='%s', datainicialconserto='%s', datafinalconserto='%s' WHERE id=%s;", descricao, dataInicialConserto, dataFinalConserto, idManutencao);
            }
            
            
            int rowsAffected = statement.executeUpdate(query);

            if (rowsAffected > 0) {
                String response = String.format("A manutenção com id %s foi modificado no sistema", idManutencao);
                System.out.println(response);
            } else {
                System.out.println("Nenhum registro foi modificado no banco de dados.");
            }
        } catch (SQLException e){
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
        }
    }
    
    public void deleteManutencao(int idManutencao) {
        try {
            Statement statement = conexao.createStatement();
            
            String temManutencao = String.format("SELECT objetoid FROM public.manutencao WHERE id=%s;", idManutencao);
            ResultSet queryManutencao = statement.executeQuery(temManutencao);
            
            if(!queryManutencao.next()) {
                System.out.println("Essa manutenção não existe");
                return;
            }
            
        
            String query = String.format("DELETE FROM public.manutencao WHERE id=%s;", idManutencao);
            String updateObjeto = String.format("UPDATE public.objeto SET status='DISPONIVEL' WHERE id = %s;", queryManutencao.getString("objetoid")); 
           
            statement.executeUpdate(updateObjeto);
                
            int rowsAffected = statement.executeUpdate(query);

            if (rowsAffected > 0) {
                String response = String.format("A manutencao com id %s e suas relações foram foi excluidas", idManutencao);
                System.out.println(response);
            } else {
                System.out.println("Nenhum registro foi modificado no banco de dados.");
            }   
            
            
        } catch (SQLException e){
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
        }
    }
    
    public void registerEmprestimo(int idObjeto, int pessoaId, String dataInicialEmprestimo, String status) {
        try {
            Statement statement = conexao.createStatement();
            
            String temObjeto = String.format("SELECT id, status FROM public.objeto WHERE id=%s AND status='DISPONIVEL';", idObjeto);
            ResultSet temObjetoDisponivel = statement.executeQuery(temObjeto);
            
            if(!temObjetoDisponivel.next()) {
                System.out.println("Objeto indisponível");
                return;
            }
            
            String query = String.format("INSERT INTO public.emprestimo (objetoid, pessoaportadoraid, dataemprestimo, status) VALUES ('%s', '%s', '%s', '%s');", idObjeto, pessoaId, dataInicialEmprestimo, status);
            String updateObjeto = String.format("UPDATE public.objeto SET status='EMPRESTADO' WHERE id = %s;", idObjeto); 
            
            int rowsAffected = statement.executeUpdate(query);
            statement.executeUpdate(updateObjeto);
            
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
    
    public void findEmprestimo() {
        try {
            Statement statement = conexao.createStatement();
            String query = String.format("SELECT emprestimo.id, tipoobjeto.nome AS nomeObjeto, objeto.id AS objetoId, objeto.status, pessoa.nome AS dono, pessoa.id AS donoID, pessoa2.id AS pessoaQuePegouObjetoID, pessoa2.nome AS pessoaQuePegouObjeto, emprestimo.datadevolucao, emprestimo.dataemprestimo FROM emprestimo JOIN objeto ON emprestimo.objetoId = objeto.id JOIN tipoObjeto ON objeto.tipoObjetoId = tipoObjeto.id JOIN pessoa ON objeto.pessoaId = pessoa.id JOIN pessoa AS pessoa2 ON emprestimo.pessoaPortadoraId = pessoa2.id WHERE emprestimo.datadevolucao IS NULL;");
            
            ResultSet rsClient = statement.executeQuery(query);

            int rowcount = 0;
            while (rsClient.next()) {
                System.out.println("===================================");
                System.out.println("Id: " + rsClient.getString("id"));
                System.out.println("ID da ferramenta: " + rsClient.getString("objetoId"));
                System.out.println("Ferramenta: " + rsClient.getString("nomeObjeto"));
                System.out.println("ID do dono: " + rsClient.getString("donoID"));
                System.out.println("Dono: " + rsClient.getString("dono"));
                System.out.println("ID da pessoa que está com a ferramenta: " + rsClient.getString("pessoaQuePegouObjetoID"));
                System.out.println("Pessoa que está com a ferramenta: " + rsClient.getString("pessoaQuePegouObjeto"));
                System.out.println("Data do empréstimo: " + rsClient.getString("dataemprestimo"));
                if(rsClient.getString("datadevolucao") != null) {
                    System.out.println("Data devolução empréstimo: " + rsClient.getString("datadevolucao"));
                }
                System.out.println("===================================");
                rowcount++;
            }
           
            if(rowcount == 0) {
                System.out.println("Sem empréstimos cadastrados");
                System.out.println("===================================");
            }
        } catch (SQLException e){
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
        }
    }
    
    public void findEmprestimoBasedOnDate(String date) {
        try {
            Statement statement = conexao.createStatement();
            String query = String.format("SELECT emprestimo.id, tipoobjeto.nome AS nomeObjeto, objeto.status, pessoa.nome AS dono, pessoa2.nome AS pessoaQuePegouObjeto, emprestimo.dataemprestimo FROM emprestimo JOIN objeto ON emprestimo.objetoId = objeto.id JOIN tipoObjeto ON objeto.tipoObjetoId = tipoObjeto.id JOIN pessoa ON objeto.pessoaId = pessoa.id JOIN pessoa AS pessoa2 ON emprestimo.pessoaPortadoraId = pessoa2.id WHERE emprestimo.datadevolucao IS NULL AND emprestimo.dataemprestimo = '%s';", date);
            
            ResultSet rsClient = statement.executeQuery(query);

            int rowcount = 0;
            System.out.println("Objetos emprestados: ");
            while (rsClient.next()) {
                System.out.println("===================================");
                System.out.println("Id: " + rsClient.getString("id"));
                System.out.println("Ferramenta: " + rsClient.getString("nomeObjeto"));
                System.out.println("Dono: " + rsClient.getString("dono"));
                System.out.println("Pessoa que está com a ferramenta: " + rsClient.getString("pessoaQuePegouObjeto"));
                System.out.println("Data do empréstimo: " + rsClient.getString("dataemprestimo"));
                System.out.println("===================================");
                rowcount++;
            }
            
            String queryEmprestados = String.format("SELECT emprestimo.id, tipoobjeto.nome AS nomeObjeto, objeto.status, pessoa.nome AS dono, pessoa2.nome AS pessoaQuePegouObjeto, emprestimo.dataemprestimo FROM emprestimo JOIN objeto ON emprestimo.objetoId = objeto.id JOIN tipoObjeto ON objeto.tipoObjetoId = tipoObjeto.id JOIN pessoa ON objeto.pessoaId = pessoa.id JOIN pessoa AS pessoa2 ON emprestimo.pessoaPortadoraId = pessoa2.id WHERE emprestimo.datadevolucao IS NOT NULL AND emprestimo.dataemprestimo = '%s';", date);
            
            ResultSet rsClientEmprestados = statement.executeQuery(queryEmprestados);

            System.out.println("Objetos devolvidos: ");
            while (rsClientEmprestados.next()) {
                System.out.println("===================================");
                System.out.println("Id: " + rsClientEmprestados.getString("id"));
                System.out.println("Ferramenta: " + rsClientEmprestados.getString("nomeObjeto"));
                System.out.println("Dono: " + rsClientEmprestados.getString("dono"));
                System.out.println("Pessoa que está com a ferramenta: " + rsClientEmprestados.getString("pessoaQuePegouObjeto"));
                System.out.println("Data do empréstimo: " + rsClientEmprestados.getString("dataemprestimo"));
                System.out.println("===================================");
                rowcount++;
            }
           
            if(rowcount == 0) {
                System.out.println("Sem empréstimos cadastrados");
                System.out.println("===================================");
            }
        } catch (SQLException e){
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
        }
    }
    
    public void editEmprestimo(int idEmprestimo, int pessoaId, String dataInicialConserto, String dataFinalEmprestimo) {
        try {
            Statement statement = conexao.createStatement();
            
            String temEmprestimo = String.format("SELECT objetoid FROM public.emprestimo WHERE id=%s;", idEmprestimo);
            ResultSet queryManutencao = statement.executeQuery(temEmprestimo);
            
            if(!queryManutencao.next()) {
                System.out.println("Esse emprestimo não existe");
                return;
            }
            
            String query;
            if(dataFinalEmprestimo.length() == 0) {
                query = String.format("UPDATE public.emprestimo SET pessoaportadoraid=%s, dataemprestimo='%s' WHERE id=%s;", pessoaId, dataInicialConserto, idEmprestimo);
            } else {
                String updateObjeto = String.format("UPDATE public.objeto SET status='DISPONIVEL' WHERE id = %s;", queryManutencao.getString("objetoid")); 
                statement.executeUpdate(updateObjeto);
                query = String.format("UPDATE public.emprestimo SET pessoaportadoraid=%s, dataemprestimo='%s', datadevolucao='%s' WHERE id=%s;", pessoaId, dataInicialConserto, dataFinalEmprestimo,idEmprestimo);
            }
              
            int rowsAffected = statement.executeUpdate(query);

            if (rowsAffected > 0) {
                String response = String.format("O empréstimo com id %s foi modificado no sistema", idEmprestimo);
                System.out.println(response);
            } else {
                System.out.println("Nenhum registro foi modificado no banco de dados.");
            }
        } catch (SQLException e){
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
        }
    }
    
    public void deleteEmprestimo(int idEmprestimo) {
        try {
            Statement statement = conexao.createStatement();
            
            String temManutencao = String.format("SELECT objetoid FROM public.emprestimo WHERE id=%s;", idEmprestimo);
            ResultSet queryManutencao = statement.executeQuery(temManutencao);
            
            if(!queryManutencao.next()) {
                System.out.println("Essa emprestimo não existe");
                return;
            }
            
        
            String query = String.format("DELETE FROM public.emprestimo WHERE id=%s;", idEmprestimo);
            String updateObjeto = String.format("UPDATE public.objeto SET status='DISPONIVEL' WHERE id = %s;", queryManutencao.getString("objetoid")); 
           
            statement.executeUpdate(updateObjeto);
                
            int rowsAffected = statement.executeUpdate(query);

            if (rowsAffected > 0) {
                String response = String.format("O emprestimo com id %s e suas relações foram foi excluidas", idEmprestimo);
                System.out.println(response);
            } else {
                System.out.println("Nenhum registro foi modificado no banco de dados.");
            }   
            
            
        } catch (SQLException e){
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
        }
    }
}
