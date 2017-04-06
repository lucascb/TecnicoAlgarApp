package lit26.tecnicoalgarapp;

/**
 * Created by lucas on 05/03/17.
 */

public class Demand {
    private long id;
    private Pessoa cliente;
    private Pessoa tecnico;
    private String descricao;
    private double latitude;
    private double longitude;
    private boolean done = false;

    public Demand(Pessoa cliente, Pessoa tecnico, String descricao, double latitude,
                  double longitude) {
        this.cliente = cliente;
        this.tecnico = tecnico;
        this.descricao = descricao;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    public Pessoa getTecnico() {
        return tecnico;
    }

    public void setTecnico(Pessoa tecnico) {
        this.tecnico = tecnico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void finish() {
        this.done = true;
    }

    public boolean isFinished() {
        return this.done;
    }

    @Override
    public String toString() {
        return "Demand{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", tecnico=" + tecnico +
                ", descricao='" + descricao + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", done=" + done +
                '}';
    }
}
