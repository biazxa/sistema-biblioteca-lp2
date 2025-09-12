public class DVD extends ItemDoAcervo{
    private int duracaoMinutos;

    public DVD(String titulo, int ano, int duracaoMinutos) {
        super(titulo, ano);
        this.duracaoMinutos = duracaoMinutos;
    }
    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }
    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }
    @Override
    public int getPrazo() {
        return 3;
    }

    @Override
    public double getValorMultaPorDiaAtraso(){
        return 2.00;
    }

    @Override
    public String toString() {
        return "DVD{" + "'" +getTitulo()+
                "' ," + " de (" + getAno() + ")"
                + " - " + duracaoMinutos + "min" +
                " - Status: " + getStatus() + '}';
    }
}


