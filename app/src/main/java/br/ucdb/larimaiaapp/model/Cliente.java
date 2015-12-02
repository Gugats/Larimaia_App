package br.ucdb.larimaiaapp.model;

import java.io.Serializable;

/**
 * Created by Mar_Ju on 23/09/2015.
 */
public class Cliente implements Serializable {
        private int idCliente;
        private String nome;
        private String email;
        private String telefone;

        public int getId() { return idCliente; }

        public void setId(int idCliente) {this.idCliente = idCliente; }

        public String getEmail() {  return email; }
        public void setEmail(String email) {this.email = email; }

        public String getTelefone() { return telefone; }
        public void setTelefone(String telefone) { this.telefone = telefone; }

        public String getNome() {return nome; }
        public void setNome(String nome) {this.nome = nome; }

        @Override
        public String toString() {
                return  nome ;
        }
}
