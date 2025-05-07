/**
Autores: 
  Patric
  Mariana
*/

package core;
import java.util.Date;

public class Equipamento {
  public static void main(String[] args){}
    private int id;
    private String nome;
    private String categoria;
    private Date dataCadastro;
    private String detalhes;
      public Equipamento(int id, String nome, String categoria, String detalhes){
         this.id = id;
         this.nome = nome;
         this.categoria = categoria;
         this.dataCadastro = new Date();
         this.detalhes = detalhes;
      }
        public String getNome(){
          return nome;
        }  
         public void setNome(String nome){
          this.nome = nome;
         }
         public String getCategoria(){
          return categoria;

         }
         public void setCategoria(String categoria){
          this.categoria = categoria;

         }
         public String getDetalhes(){
          return detalhes;
         }
         public void setDetalhes(String detalhes){
          this.detalhes = detalhes;
         }
         public Date getDataCadastro(){
          return dataCadastro;
         }
         public void setDataCadastro(Date dataCadastro){
          this.dataCadastro = dataCadastro;
  }
  @Override
  public String toString(){
    return "Equipamento [ID= " + id +", Nome = " + nome + ", Categoria = " + categoria +
     ", Data de Cadastro = " + dataCadastro + ", Detalhes = " + detalhes + "]";
  }
}      
