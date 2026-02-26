package ghostavus.solutions.model;

public class Fornecedor {

    private int id;
    private String razaoSocial;
    private String cnpj;
    private String telefone;
    private String email;

    // CONSTRUTOR VAZIO CORRETO
    public Fornecedor() {
    }

    public Fornecedor(String razaoSocial, String cnpj, String telefone, String email) {
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    private String representante;

    public String getRepresentante() {
    return representante;
}

    public void setRepresentante(String representante) {
    this.representante = representante;
}
}