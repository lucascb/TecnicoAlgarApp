package lit26.tecnicoalgarapp;

/**
 * Created by lucas on 05/03/17.
 */

public class Demand {
    private int id;
    private int cliente_id;
    private int tecnico_id;
    private String descricao;
    private double latitude;
    private double longitude;

    public Demand(int cliente_id, int tecnico_id, String descricao,
                  double latitude, double longitude) {
        this.cliente_id = cliente_id;
        this.tecnico_id = tecnico_id;
        this.descricao = descricao;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getTecnico_id() {
        return tecnico_id;
    }

    public void setTecnico_id(int tecnico_id) {
        this.tecnico_id = tecnico_id;
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

    @Override
    public String toString() {
        return "Demand{" +
                "cliente_id=" + cliente_id +
                ", tecnico_id=" + tecnico_id +
                ", descricao='" + descricao + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
