/**
Autores: 
  Patric
  Mariana
*/

package core;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.Date;

public class Equipamento {
  
   // Propriedades  para o nome, id e tipo (permitem binding e listeners)
    private IntegerProperty id;
    private StringProperty nome;
    private StringProperty categoria;
    private Date dataCadastro;
    private StringProperty detalhes;

    public Equipamento(int id, String nome, String categoria, String detalhes) {
      
      // Inicializam as propriedades com o valor recebido
        this.id = new SimpleIntegerProperty(id);
        this.nome = new SimpleStringProperty(nome);
        this.categoria = new SimpleStringProperty(categoria);
        this.dataCadastro = new Date();
        this.detalhes = new SimpleStringProperty(detalhes);
    }

    // Getters e Setters
    public int getId() { return id.get(); }
    public void setId(int id) { this.id.set(id); }
    public IntegerProperty idProperty() { return id; }

    public String getNome() { return nome.get(); }
    public void setNome(String nome) { this.nome.set(nome); }
    public StringProperty nomeProperty() { return nome; }

    public String getCategoria() { return categoria.get(); }
    public void setCategoria(String categoria) { this.categoria.set(categoria); }
    public StringProperty categoriaProperty() { return categoria; }

    public String getDetalhes(){ return detalhes.get(); }
    public void setDetalhes(String detalhes) { this.detalhes.set(detalhes); }
    public StringProperty detalhesProperty() { return detalhes; }

    public Date getDataCadastro() { return dataCadastro; }
}   
